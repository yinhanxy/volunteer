package com.topstar.volunteer.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.DocumentDao;
import com.topstar.volunteer.entity.Appendix;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.entity.Document;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.service.AppendixService;
import com.topstar.volunteer.service.ChannelService;
import com.topstar.volunteer.service.DocumentService;
import com.topstar.volunteer.service.OrgService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ConfigUtils;
import com.topstar.volunteer.util.FileUtils;

@Service
public class DocumentServiceImpl  extends BaseServiceImpl<Document>  implements DocumentService {

	@Autowired
	private DocumentDao documentDao;
	
	@Autowired
	private AppendixService appendixService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private ChannelService channelService;
	
	
	public PageInfo<Document> list(List<Long> channelIds,Document doc,String orderBy, int pageIndex, int pageSize){
		BaseUser user = ShiroSessionMgr.getLoginUser();
		PageInfo<Document> page = null;
		
		//如果是超级管理员，显示所有数据
		if(user.isAdmin()){
			page = documentDao.selectPageByAdmin(channelIds,doc, orderBy, pageIndex, pageSize);
		}else{
			//获取用户的机构
			Org org = orgService.getOrgByUserId(user.getId());
			if(org != null){
				Long orgId = org.getId();
				//获取机构的类型
				Integer type = org.getType();
				// 获取机构级别
				Integer grade = org.getGrade();
				//如果是管理机构
				if(type.intValue() == 1){
					//省级管理机构，显示所有数据
					if(grade.intValue() == 1){
						page = documentDao.selectPageByAdmin(channelIds,doc, orderBy, pageIndex, pageSize);
					}else{
						//其他级别管理机构看自己和子机构的数据
						page = documentDao.manageOrgList(channelIds,orgId, doc, orderBy, pageIndex, pageSize);
					}
				}else{
					//省级业务机构，可看同系统类所有的数据
					if(grade.intValue() == 1){
						Long systemCode = org.getSystemCode();
						page = documentDao.listBySystemCode(channelIds,orgId, systemCode, doc, orderBy, pageIndex, pageSize);
					}else{
						//普通业务机构，只能看自己的数据
						doc.setOrgId(orgId);
						page = documentDao.listByOrgId(channelIds,doc, orderBy, pageIndex, pageSize);
					}
				}
			}
		}
		
		if(page != null){
			List<Document> list = page.getList();
			if(list != null && list.size() > 0){
				List<Document> tempList = new ArrayList<Document>(list.size());
				for(Document tempDoc: list){
					Long orgId = tempDoc.getOrgId();
					if(orgId != null && orgId.longValue() > 0L){
						Org org = orgService.selectByKey(orgId);
						if(org != null){
							tempDoc.setOrgName(org.getName());
						}
					}
					Long channelId = tempDoc.getChannelId();
					Channel channel = channelService.selectByKey(channelId);
					if(channel != null){
						tempDoc.setChannelName(channel.getChnlDesc());
					}
					tempList.add(tempDoc);
				}
				page.setList(tempList);
			}
		}
		return page;
	}
	
	/**
	 * 添加文档
	 */
	public Long addDocument(Document doc,List<Appendix> appendixList) throws TPSException{
		if(doc != null){
			this.insertNotNull(doc);
			Long id = doc.getId();
			if(appendixList != null){
				appendixService.insertAppendixs(id, appendixList);
				// 将附件从临时文件夹移动到正式文件夹
				for(Appendix appendix : appendixList){
					String fileName = appendix.getFileName();
					//临时文件上传路径
					String tempUploadDir =ConfigUtils.getConfigContentByName("PIC_TEMPORARY_PATH");
					String tempFilePath = FileUtils.getUploadFilePath(fileName);
					//正式文件上传路径
					String uploadDir =ConfigUtils.getConfigContentByName("UPLOADPATH");
					
					try {
						File dir = new File(uploadDir);
						if(!dir.exists() && dir.isDirectory()){
							dir.mkdirs();
						}else if(!dir.isDirectory()){
							throw new RuntimeException("创建正式文件上传路径["+uploadDir+"]出错，不是合理的文件夹路径！");
						}
					} catch (Exception e) {
						throw new RuntimeException("创建正式文件上传路径["+uploadDir+"]出错",e);
					}
					
					String tempFile = tempUploadDir + File.separator + tempFilePath;
					
					String filePath = uploadDir + File.separator + tempFilePath;
					FileOutputStream out = null;
					try {
						File oldFile = new File(tempFile);
						if(oldFile.exists() && oldFile.isFile()){
							File file = new File(filePath);
							File tempDir = file.getParentFile();
							if(!tempDir.exists()){
								tempDir.mkdirs();
							}
							synchronized (file) {
								if(!file.exists()){
									file.createNewFile();
								}
								out = new FileOutputStream(file);
								Files.copy(new File(tempFile).toPath(), out);
								out.close();
							}
						}
					} catch (Exception e) {
						throw new RuntimeException("将文件["+tempFile+"]从临时目录复制到正式目录出错",e);
					}finally{
						if(out != null){
							try {
								out.close();
							} catch (IOException e) {}
							out = null;
						}
					}
				}
			}
			Long channelId = doc.getChannelId();
			this.setMaxDocOrder(id, channelId);
			return id;
		}
		return null;
	}
	
	public Document findDocById(Long id){
		if(id != null && id.longValue() > 0){
			return documentDao.findDocById(id);
		}
		return null;
	}
	
	
	/**
	 * 修改文档
	 * @param doc
	 * @param appendixList
	 * @param deleteAppIds
	 * @return
	 * @throws TPSException
	 */
	public Long editDocument(Document doc,List<Appendix> appendixList,List<Long> deleteAppIds) throws TPSException{
		if(doc != null){
			this.updateNotNull(doc);
			Long id = doc.getId();
			
			//临时文件上传路径
			String tempUploadDir =ConfigUtils.getConfigContentByName("PIC_TEMPORARY_PATH");
			//正式文件上传路径
			String uploadDir =ConfigUtils.getConfigContentByName("UPLOADPATH");
			if(appendixList != null){
				appendixService.insertAppendixs(id, appendixList);
				// 将附件从临时文件夹移动到正式文件夹
				
				for(Appendix appendix : appendixList){
					String fileName = appendix.getFileName();
					String tempFilePath = FileUtils.getUploadFilePath(fileName);
					try {
						File dir = new File(uploadDir);
						if(!dir.exists() && dir.isDirectory()){
							dir.mkdirs();
						}else if(dir.isFile()){
							throw new RuntimeException("创建正式文件上传路径["+uploadDir+"]出错，不是合理的文件夹路径！");
						}
					} catch (Exception e) {
						throw new RuntimeException("创建正式文件上传路径["+uploadDir+"]出错",e);
					}
					
					String tempFile = tempUploadDir + File.separator + tempFilePath;
					
					String filePath = uploadDir + File.separator + tempFilePath;
					FileOutputStream out = null;
					try {
						File oldFile = new File(tempFile);
						if(oldFile.exists() && oldFile.isFile()){
							File file = new File(filePath);
							File tempDir = file.getParentFile();
							if(!tempDir.exists()){
								tempDir.mkdirs();
							}
							synchronized (file) {
								if(!file.exists()){
									file.createNewFile();
								}
								out = new FileOutputStream(file);
								Files.copy(new File(tempFile).toPath(), out);
								out.close();
								out = null;
							}
						}
					} catch (Exception e) {
						throw new RuntimeException("将文件["+tempFile+"]从临时目录复制到正式目录出错",e);
					}finally{
						if(out != null){
							try {
								out.close();
							} catch (IOException e) {}
							out = null;
						}
					}
				}
			}
			if(deleteAppIds != null && !deleteAppIds.isEmpty()){
				Long[] ids = new Long[deleteAppIds.size()];
				String[] fileNames = new String[deleteAppIds.size()];
				for(int i = 0 ; i < deleteAppIds.size();i++){
					ids[i] = deleteAppIds.get(i);
					fileNames[i] = appendixService.getFileName(ids[i]);
				}
				
				if(fileNames.length > 0){
					for(int i = 0 ; i < fileNames.length;i++){
						String fileName = fileNames[i];
						String filePath = FileUtils.getUploadFilePath(fileName);
						
						FileUtils.deleteFile(filePath);
					}
				}
				//从数据库中删除对应的文件
				appendixService.deleteAppendixs(ids);
			}
			return id;
		}
		return null;
		
		
	}
	
	/**
	 * 将文档的排序值设置为最大
	 * @param id 文档编号
	 * @param channelId 栏目编号
	 */
	public synchronized void setMaxDocOrder(Long id,Long channelId){
		Long maxDocOrder = documentDao.getMaxDocOrder(channelId);
		if(maxDocOrder == null || maxDocOrder.longValue() == 0L){
			maxDocOrder = 1L;
		}
		documentDao.setDocOrder(id, maxDocOrder);
	}
	
	/**
	 * 查询文档的所有附件信息
	 * @param docId
	 * @return
	 */
	public List<Appendix> selectAppendix(Long docId){
		return appendixService.selectByDocId(docId);
	}
	
	/**
	 * 
	 * @param appendixList
	 * @return
	 *   <file>
	 *     <name><![CDATA[fileName]]></name>
	 *	   <desc><![CDATA[fileDesc]]></desc>
	 *	   <ext>fileExt</ext>
	 *	   <type>fileType</type>
	 *	   <id>id</id>
	 * </file>
	 * </files>
	 */
	public String fileInfoToXml(List<Appendix> appendixList){
		if(appendixList != null && !appendixList.isEmpty()){
			org.dom4j.Document doc = DocumentHelper.createDocument();
			Element root =  doc.addElement("files");
			for(Appendix a : appendixList){
				Element file = root.addElement("file");
				
				Element name = file.addElement("name");
				name.addCDATA(a.getFileName());
				
				Element desc = file.addElement("desc");
				desc.addCDATA(a.getFileDesc());
				
				Element type = file.addElement("type");
				type.setText(a.getFileType().toString());
				
				Element id = file.addElement("id");
				id.setText(a.getId().toString());
			}
			return root.asXML();
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param fileInfo  <files>
	 * <file>
	 *     <name><![CDATA[fileName]]></name>
	 *	   <desc><![CDATA[fileDesc]]></desc>
	 *	   <ext>fileExt</ext>
	 *	   <type>fileType</type>
	 *	   <id>id</id>
	 * </file>
	 * </files>
	 * @return
	 */
	@SuppressWarnings({"unchecked" })
	public List<Appendix> parseFileInfo(String fileInfo) throws TPSException{
		List<Appendix> appendixList = new ArrayList<Appendix>();
		if(StringUtils.isNotBlank(fileInfo)){
			try {
				org.dom4j.Document doc = DocumentHelper.parseText(fileInfo);
				
				Element root = doc.getRootElement();
				if(root != null){
					List<Element> files = root.elements("file");
					if(files != null && files.size() > 0){
						for(Element file : files){
							String id = file.elementText("id");
							//如果没有ID，说明是新上传的附件
							if(StringUtils.isBlank(id)){
								String name = file.elementText("name");
								String desc = file.elementText("desc");
								String ext = file.elementText("ext");
								String type = file.elementText("type");
								
								Integer fileType = 1;
								try {
									fileType = Integer.parseInt(type);
								} catch (Exception e) {
									fileType = -1;
								}
								
								if(fileType.intValue() > -1){
									Appendix appendix = new Appendix();
									appendix.setFileName(name);
									appendix.setFileDesc(desc);
									appendix.setFileExt(ext);
									appendix.setFileType(fileType);
									
									appendixList.add(appendix);
								}
							}
						}
					}
				}
			} catch (DocumentException e) {
				throw new TPSException(fileInfo+"附件XML结构解析出错",e);
			}
		}
		return appendixList;
	}
	
	/**
	 * 修改文档的状态
	 * @param ids 文档主键集合
	 * @param status 状态值
	 * @return
	 */
	public int updateDocumentStatus(Long[] ids, Integer status){
		return documentDao.updateDocumentStatus(ids, status);
	}
	
	/**
	 * 将文档从回收站恢复<br/>
	 * 将删除时间和删除用户字段值清空，将文档置为已否状态
	 * @return
	 */
	public int restoreDocument(Document doc){
		if(doc != null && doc.getId() != null && doc.getId().longValue() > 0){
			return documentDao.restoreDocument(doc);
		}
		return -1;
	}
	
	/**
	 * 根据栏目ID删除文档信息
	 * @param channelId 栏目ID
	 * @return
	 */
	public int deleteDocumentByChannelId(Long channelId){
		if(channelId != null && channelId.longValue() > 0){
			return documentDao.deleteByChannelId(channelId);
		}
		return -1;
	}

	@Override
	public List<Document> getDocNotice() throws TPSException{
		int n=5;
		Channel channel = new Channel();
		String id=ConfigUtils.getConfigContentByName("channel_id");
		Long key = null;
		try {
			key = Long.valueOf(id);
		} catch (Exception e) {
			throw new RuntimeException("查看培训记录信息的参数id:["+id+"]不合法",e);
		}
		channel.setId(key);
		return documentDao.getDocNotice(channel, n);
	}
}
