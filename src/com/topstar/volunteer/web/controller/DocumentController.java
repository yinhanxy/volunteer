package com.topstar.volunteer.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.ActionEnter;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Appendix;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.entity.Document;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.ChannelView;
import com.topstar.volunteer.service.ChannelService;
import com.topstar.volunteer.service.DocumentService;
import com.topstar.volunteer.service.OrgService;
import com.topstar.volunteer.service.UserService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ConfigUtils;
import com.topstar.volunteer.util.DateUtil;
import com.topstar.volunteer.util.FileUtils;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.annotations.Token;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * 文档类控制器
 * @author Administrator
 *
 */
@Controller
public class DocumentController {

	private static Logger logger = LoggerFactory.getLogger(DocumentController.class);
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 文档列表页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/document/list.html")
	public String toList(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		return "/webSite/document/list";
	}
	
	/**
	 * 文档列表页,文档状态为待发布
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/document/Released.html")
	public String toReleased(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		model.addAttribute("selected", true);
		return "/webSite/document/list";
	}
	
	/**
	 * 文档回收站
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/document/recycleList.html")
	public String recycleList(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Long channelId = ActionContext.getActionContext().getParameterAsLong("channelId");
		Long siteId = ActionContext.getActionContext().getParameterAsLong("siteId");
		request.setAttribute("channelId", channelId);
		request.setAttribute("siteId", siteId);
		return "/webSite/document/recycleList";
	}
	
	/**
	 * 文档列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/document/list.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String userList(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		BaseUser user = ShiroSessionMgr.getLoginUser();
		Document doc = new Document();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String title = ActionContext.getActionContext().getParameterAsString("title");
		Integer status = ActionContext.getActionContext().getParameterAsInt("status");
		Long channelId = ActionContext.getActionContext().getParameterAsLong("channelid");
		Long siteId = ActionContext.getActionContext().getParameterAsLong("siteid");
		
		if(currPage < 1){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		if(status.intValue() == -1){
			status = null;
		}
		if(channelId.longValue() <= 0L){
			channelId = null;
		}
		if(siteId.longValue() <= 0L){
			data.setSuccess(false);
			data.setMessage("站点ID不能为空");
			return data.toJSONString();
		}
		
		doc.setSiteId(siteId);
		doc.setTitle(title);
		doc.setStatus(status);
		
		String orderBy = "DOC_ORDER DESC";
		
		try {
			//用户可见的当前栏目和字栏目的栏目ID集合 ,综合计算最后结果集
			List<Long>  channelIds = new ArrayList<Long>();
			//用户可见的所有栏目ID
			List<Long> roleChannelIds = userService.getAllChannelIdsNotINRecycle(siteId);
			
			if(channelId != null && channelId.longValue() > 0){
				//当前栏目及所有子栏目的ID集合
				List<Long>  allChannelIds = channelService.getAllChildrenChannelIds(channelId);
				if(!user.isAdmin()){
					if(roleChannelIds != null && !roleChannelIds.isEmpty()){
						
						if(allChannelIds != null && !allChannelIds.isEmpty()){
							//两个集合取交集
							roleChannelIds.retainAll(allChannelIds);
							channelIds = roleChannelIds;
						}
					}
				}else{
					channelIds = allChannelIds;
				}
			}else{
				channelIds = roleChannelIds;
			}
			
			if(!user.isAdmin() && channelId != null && (channelIds == null || channelIds.isEmpty())){
				data.setSuccess(false);
				data.setMessage("您没有权限查看此栏目的文档信息");
				return data.toJSONString();
			}
			if(!user.isAdmin() && (channelIds == null || channelIds.isEmpty())){
				data.setSuccess(true);
				return data.toString();
			}
			
			PageInfo<Document> pageInfo = documentService.list(channelIds,doc, orderBy, currPage, pageSize);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("分页查询文档信息出错");
			String message = "查询条件：[siteId="+doc.getSiteId()+",channelId="+doc.getChannelId()+",title="+doc.getTitle()+",status="+doc.getStatus()+"]";
			LoggerServer.error(ObjectType.DOCUMENT, OperateType.SHOWDOCUMENTLIST, "分页查询文档信息出错,"+e.getMessage(), -1L,message);
			logger.error("分页查询文档信息出错,pageIndex:{},pageSize:{},title:{},status:{},siteId:{},channelId:{}",currPage,pageSize,title,status,siteId,channelId,e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到添加文档页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@Token(save=true,customKey = "DocumentController_addDocument")
	@RequestMapping("/document/addDocument.html")
	public String toAddDocument(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Long channelId = ActionContext.getActionContext().getParameterAsLong("channelid");
		if(channelId.longValue() > 0L){
			Channel channel = channelService.selectByKey(channelId);
			request.setAttribute("channel",channel);
		}
		String relTime = DateUtil.getNow(DateUtil.YYYY_MM_DD_HHMMSS);
		request.setAttribute("relTime", relTime);
		request.setAttribute("channelId", channelId);
		return "/webSite/document/addDocument";
	}
	
	/**
	 * 添加文档
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@Token(remove=true,customKey = "DocumentController_addDocument")
	@RequestMapping(value="/document/addDocument.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addDocument(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String title = request.getParameter("title"); //标题
		String channelId = request.getParameter("channelId");//栏目ID
		String keyword = request.getParameter("keyword");	//关键字
		String source = request.getParameter("source");	//来源
		String summary = request.getParameter("summary");	//摘要
		String reltime = request.getParameter("reltime");	//时间
		String author = request.getParameter("author");	//作者
		String content = request.getParameter("content"); //正文纯文本内容
		String htmlcon = request.getParameter("htmlcon");	//正文html内容
		
		Long id =-1L;
		String idStr = request.getParameter("id");
		if(StringUtils.isNotBlank(idStr)){
			try {
				id = Long.parseLong(idStr);
			} catch (Exception e) {
				id = -1L;
			}
		}
		if(id.longValue() > 0){
			data.setSuccess(false);
			data.setMessage("文档已保存成功！");
			data.setToken(request, "DocumentController_addDocument");
			return data.toJSONString();
		}
		
		String statusDesc =request.getParameter("status"); //文档状态
		Integer status = Document.StatusType.new_doc.getValue();
		try {
			status = Integer.parseInt(statusDesc);
		} catch (Exception e) {
			status = Document.StatusType.new_doc.getValue();
		}
		
		//附件的XML结构信息
		String fileInfo = request.getParameter("fileInfo");
		
		//已删除的附件ID信息
		String deleteFileIds = request.getParameter("deleteFileIds");
		
		if(StringUtils.isBlank(channelId)){
			data.setSuccess(false);
			data.put("propertyName", "channelId");
			data.setMessage("请选择栏目！");
			data.setToken(request, "DocumentController_addDocument");
			return data.toJSONString();
		}
		Long channelid = -1L;
		try {
			channelid = Long.parseLong(channelId);
		} catch (Exception e) {
			data.setSuccess(false);
			data.put("propertyName", "channelId");
			data.setMessage("请选择栏目！");
			data.setToken(request, "DocumentController_addDocument");
			return data.toJSONString();
		}
		
		if(StringUtils.isBlank(reltime)){
			data.setSuccess(false);
			data.setMessage("请输入时间！");
			data.setToken(request, "DocumentController_addDocument");
			return data.toJSONString();
		}
		
		Timestamp relTime =  null;
		try {
			relTime = new Timestamp(DateUtil.parseDate(reltime,DateUtil.YYYY_MM_DD_HHMMSS).getTime());
		} catch (Exception e) {
			data.setSuccess(false);
			data.put("propertyName", "reltime");
			data.setMessage("时间格式不正确！");
			data.setToken(request, "DocumentController_addDocument");
			return data.toJSONString();
		}
		Document document = new Document();
		document.setTitle(title);
		document.setKeyword(keyword);
		document.setSource(source);
		document.setSummary(summary);
		document.setAuthor(author);
		document.setHitsCount(0L);
		
		List<ValidateMessage> errors = ValidatorUtil.validate(document, Groups.Add.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).setToken(request, "DocumentController_addDocument").toJSONString();
		}
		
		Channel channel = channelService.selectByKey(channelid);
		if(channel == null){
			data.setSuccess(false);
			data.put("propertyName", "channelId");
			data.setMessage("请选择栏目！");
			data.setToken(request, "DocumentController_addDocument");
			return data.toJSONString();
		}
		Long siteId = channel.getSiteId();
		
		document.setSiteId(siteId);
		document.setChannelId(channelid);
		document.setRelTime(relTime);
		
		document.setContent(content);
		document.setHtmlcon(htmlcon);
		document.setStatus(status);
		
		Timestamp crTime = new Timestamp(new Date().getTime());
		document.setCrTime(crTime);
		BaseUser user=ShiroSessionMgr.getLoginUser();
		document.setCrUser(user.getUserName());
		document.setLastUpdateTime(crTime);
		document.setLastUpdateUser(user.getUserName());
		Org org = orgService.getOrgByUserId(user.getId());
		if(org != null){
			document.setOrgId(org.getId());
		}else{
			document.setOrgId(0L);
		}
		//如果发布文档，判断机构是否为一级机构
		if(status.intValue() == 10){
			if(!user.isAdmin()){
				Integer grade =  org.getGrade();
				if(grade.intValue() != 1){
					data.setSuccess(false);
					data.setMessage("您没有权限发布文档！");
					data.setToken(request, "DocumentController_addDocument");
					return data.toJSONString();
				}
			}
			document.setSubTime(crTime);
			document.setSubUser(user.getUserName());
		}
		
		try {
			List<Appendix> appendixList = null;
			if(StringUtils.isNotBlank(fileInfo)){
				fileInfo = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + fileInfo;
				appendixList = documentService.parseFileInfo(fileInfo);
				if(appendixList != null){
					for(Appendix x : appendixList){
						x.setCrUser(user.getUserName());
					}
				}
			}
			List<Long> appendixIds = null;
			if(StringUtils.isNoneBlank(deleteFileIds)){
				String[] ids = deleteFileIds.split(",");
				if(ids != null && ids.length >0){
					for(int i = 0 ; i < ids.length;i++){
						try {
							Long appendixId = Long.parseLong(ids[i]);
							appendixIds.add(appendixId);
						} catch (Exception e) {
							continue;
						}
					}
				}
			}
			id = documentService.addDocument(document,appendixList);
			data.put("id", id);
			data.setSuccess(true);
			data.setMessage("新建文档成功！");
			LoggerServer.info(ObjectType.DOCUMENT, OperateType.CREATEDOCUMENT, "新建文档信息成功", document.getId(), document.getTitle(), true);
		} catch (Exception e) {
			data.setSuccess(false);
			logger.error("新建文档出错",e);
			data.setMessage("新建文档出错");
			data.setToken(request, "DocumentController_addDocument");
			LoggerServer.error(ObjectType.DOCUMENT, OperateType.CREATEDOCUMENT, "新建文档信息出错，"+e.getMessage(), document.getId(), document.getTitle());
		}
		return data.toJSONString();
	} 
	
	/**
	 * 编辑文档
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/document/editDocument.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String editDocument(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String idStr = request.getParameter("id");
		String title = request.getParameter("title"); //标题
		String channelId = request.getParameter("channelId");//栏目ID
		String keyword = request.getParameter("keyword");	//关键字
		String source = request.getParameter("source");	//来源
		String summary = request.getParameter("summary");	//摘要
		String reltime = request.getParameter("reltime");	//时间
		String author = request.getParameter("author");	//作者
		String content = request.getParameter("content"); //正文纯文本内容
		String htmlcon = request.getParameter("htmlcon");	//正文html内容
		
		String statusDesc =request.getParameter("status"); //文档状态
		Integer status = Document.StatusType.new_doc.getValue();
		try {
			status = Integer.parseInt(statusDesc);
		} catch (Exception e) {
			status = Document.StatusType.new_doc.getValue();
		}
		
		//附件的XML结构信息
		String fileInfo = request.getParameter("fileInfo");
		
		//已删除的附件ID信息
		String deleteFileIds = request.getParameter("deleteFileIds");
		Long id = -1L;
		if(StringUtils.isBlank(idStr)){
			data.setSuccess(false);
			data.setMessage("文档不存在，无法编辑！");
			return data.toJSONString();
		}
		
		try {
			id = Long.parseLong(idStr);
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("文档参数错误，无法编辑！");
			return data.toJSONString();
		}
		
		if(StringUtils.isBlank(channelId)){
			data.setSuccess(false);
			data.put("propertyName", "channelId");
			data.setMessage("请选择栏目！");
			return data.toJSONString();
		}
		Long channelid = -1L;
		try {
			channelid = Long.parseLong(channelId);
		} catch (Exception e) {
			data.setSuccess(false);
			data.put("propertyName", "channelId");
			data.setMessage("请选择栏目！");
			return data.toJSONString();
		}
		if(StringUtils.isBlank(reltime)){
			data.setSuccess(false);
			data.setMessage("请输入时间！");
			return data.toJSONString();
		}
		
		Timestamp relTime =  null;
		try {
			relTime = new Timestamp(DateUtil.parseDate(reltime).getTime());
		} catch (Exception e) {
			data.setSuccess(false);
			data.put("propertyName", "reltime");
			data.setMessage("时间格式不正确！");
			return data.toJSONString();
		}
		
		Document document = new Document();
		document.setId(id);
		document.setTitle(title);
		document.setKeyword(keyword);
		document.setSource(source);
		document.setSummary(summary);
		document.setAuthor(author);
		document.setStatus(status);
		document.setContent(content);
		document.setHtmlcon(htmlcon);
		List<ValidateMessage> errors = ValidatorUtil.validate(document, Groups.Add.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		
		Channel channel = channelService.selectByKey(channelid);
		if(channel == null){
			data.setSuccess(false);
			data.put("propertyName", "channelId");
			data.setMessage("请选择栏目！");
			return data.toJSONString();
		}
		
		Long siteId = channel.getSiteId();
		document.setSiteId(siteId);
		document.setChannelId(channelid);
		document.setRelTime(relTime);
		Timestamp lastUpdateTime = new Timestamp(new Date().getTime());
		document.setLastUpdateTime(lastUpdateTime);
		BaseUser user=ShiroSessionMgr.getLoginUser();
		document.setLastUpdateUser(user.getUserName());
		Org org = orgService.getOrgByUserId(user.getId());
		if(org != null){
			document.setOrgId(org.getId());
		}else{
			document.setOrgId(0L);
		}
		//如果发布文档，判断机构是否为一级机构
		if(status.intValue() == 10){
			if(!user.isAdmin()){
				Integer grade =  org.getGrade();
				if(grade.intValue() != 1){
					data.setSuccess(false);
					data.setMessage("您没有权限发布文档！");
					return data.toJSONString();
				}
			}
			document.setSubTime(lastUpdateTime);
			document.setSubUser(user.getUserName());
		}
		try {
			List<Appendix> appendixList = null;
			if(StringUtils.isNotBlank(fileInfo)){
				fileInfo = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + fileInfo;
				appendixList = documentService.parseFileInfo(fileInfo);
				if(appendixList != null){
					for(Appendix x : appendixList){
						x.setCrUser(user.getUserName());
					}
				}
			}
			List<Long> appendixIds = new ArrayList<Long>();
			if(StringUtils.isNoneBlank(deleteFileIds)){
				String[] ids = deleteFileIds.split(",");
				if(ids != null && ids.length >0){
					for(int i = 0 ; i < ids.length;i++){
						try {
							Long appendixId = Long.parseLong(ids[i]);
							appendixIds.add(appendixId);
						} catch (Exception e) {
							continue;
						}
					}
				}
			}
			documentService.editDocument(document,appendixList,appendixIds);
			data.put("id", id);
			data.setSuccess(true);
			data.setMessage("修改文档成功！");
			LoggerServer.info(ObjectType.DOCUMENT, OperateType.EDITDOCUMENT, "修改文档信息成功", document.getId(), document.getTitle(), true);
		} catch (Exception e) {
			data.setSuccess(false);
			logger.error("修改文档出错",e);
			data.setMessage("修改文档出错");
			LoggerServer.error(ObjectType.DOCUMENT, OperateType.EDITDOCUMENT, "修改文档信息出错，"+e.getMessage(), document.getId(), document.getTitle());
		}
		return data.toJSONString();
	} 
	
	/**
	 * 选择栏目
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/document/selectChannel.html")
	public String toSelectChannel(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		long channelId = ActionContext.getActionContext().getParameterAsLong("channelId");
		if(channelId > 0){
			request.setAttribute("channelId", channelId);
		}
		return "/webSite/document/selectChannel";
	}
	
	@RequestMapping(value="/document/allSites.do",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public @ResponseBody String loadSites(HttpServletRequest request,HttpServletResponse response){
		ResultData data=new ResultData();
		try{
			List<ChannelView> channels= channelService.getAllChannelBySiteIdInCache();
			if(channels!=null){
				data.setSuccess(true);
				data.put("channels",channels);
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CHANNEL,OperateType.LOADSITESANDTOPCHANNEL, "站点下顶级栏目的查询操作失败【"+e.getMessage()+"】",null,null);
			logger.error("获取站点下的栏目信息列表出现内部错误",e);
			data.setSuccess(false);
			data.setMessage("获取站点下栏目数据出现内部错误");
		}
		return data.toJSONString();
	}
	
	
	/**
	 * ueditor插件的url访问地址
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/document/ue.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String ue(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type" , "text/html");
		String rootPath = request.getServletContext().getRealPath("/");
		ActionEnter enter = new ActionEnter(request, rootPath);
		String configStr = enter.exec();
        return configStr;
	}
	
	/**
	 * 上传图片
	 * @param upfile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/document/uploadImages.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String uploadImages(MultipartFile upfile,HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String basePath =ConfigUtils.getConfigContentByName("WEBPIC");
        String visitUrl = "/webpic/";
        if(StringUtils.isBlank(basePath)){
        	data.setSuccess(false).setMessage("WEBPIC项配置不正确");
       	 	return data.toJSONString();
        }
        File basePathFile = new File(basePath);
        if(!basePathFile.exists() || basePathFile.isFile()){
        	data.setSuccess(false).setMessage("WEBPIC项配置不正确");
       	 	return data.toJSONString();
        }
        //文件原文件名
		String originalFileName = upfile.getOriginalFilename();
		String fileExt =FileUtils.getFileSuffix(originalFileName);
		if (!imageCanUpload(fileExt)) {
			data.setSuccess(false);
			data.put("state", "上传图片出错，不允许上传的图片类型:"+fileExt);
			return data.toJSONString();
		}
        //得到相对存储路径
        String filePath =FileUtils.getFilePath();
        String fileName =FileUtils.generateFileName(upfile.getOriginalFilename());
        StringBuilder sb = new StringBuilder();  
        //拼接保存路径  
        sb.append(basePath).append(File.separator).append(filePath).append(File.separator).append(fileName);
        visitUrl = visitUrl.concat(filePath+File.separator+fileName);
        visitUrl = visitUrl.replaceAll("\\\\", "/");
        InputStream in = null;
        try{
             File f = new File(sb.toString());
             File dir = f.getParentFile();
             if(!dir.exists()){
            	 synchronized (dir) {
					if(!dir.exists()){
						dir.mkdirs();
					}
				}
             }
             in = upfile.getInputStream();
             FileUtils.writeFileFromInputStream(in,f.getPath());
             if(in != null){
            	 in.close();
            	 in = null;
             }
             data.put("state", "SUCCESS");
             data.put("url", visitUrl);
             data.put("size", upfile.getSize());  
             data.put("original", fileName);
             data.put("type", upfile.getContentType());  
        } catch (Exception e){
        	logger.error("上传图片"+upfile.getOriginalFilename()+"出错",e);
        	data.put("state", "上传图片出错");
             if(in != null){
            	 try {
            		 in.close();
                	 in = null;
				} catch (Exception e2) {}
             }
        }
        return data.toJSONString(); 
	}
	
	/**
	 * 附件管理
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/document/appendixList.html")
	public String appendixList(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		long channelId = ActionContext.getActionContext().getParameterAsLong("channelId");
		model.addAttribute("channelId", channelId);
		return "/webSite/document/appendixList";
	}
	
	/**
	 * 提交/撤销文档
	 * status :2 提交文档
	 *         0 撤销文档
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/document/commitDocument.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String commitDocument(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ResultData data = new ResultData();
		String idStr = request.getParameter("id");
		String statusDesc =request.getParameter("status"); //文档状态
		Integer status = Document.StatusType.new_doc.getValue();
		Long id = 0L;
		try {
			id = Long.parseLong(idStr);
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("参数出错");
			return data.toJSONString();
		}
		try {
			status = Integer.parseInt(statusDesc);
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("参数出错");
			return data.toJSONString();
		}
		
		if(status.intValue() == 1){
			data.setSuccess(false);
			data.setMessage("参数出错");
			return data.toJSONString();
		}
		
		try {
			Document doc = documentService.findDocById(id);
			if(doc == null){
				data.setSuccess(false);
				data.setMessage("文档不存在！");
				return data.toJSONString();
			}
			Integer oldStatus = doc.getStatus();
			//文档不是新稿和已否状态，无法提交
			if(oldStatus == null || oldStatus.intValue() == Document.StatusType.pub.getValue() || 
					oldStatus.intValue() == Document.StatusType.delete.getValue()){
				data.setSuccess(false);
				data.setMessage("文档不是可提交状态，无法提交！");
				return data.toJSONString();
			}
			BaseUser user = ShiroSessionMgr.getLoginUser();
			//提交文档
			if(status.intValue() == Document.StatusType.commit.getValue()){
				if(oldStatus.intValue() != Document.StatusType.commit.getValue()){
					Document document = new Document();
					document.setId(doc.getId());
					document.setStatus(Document.StatusType.commit.getValue());
					document.setSubTime(new Timestamp(new Date().getTime()));
					document.setSubUser(user.getUserName());
					try {
						documentService.updateNotNull(document);
						LoggerServer.info(ObjectType.DOCUMENT, OperateType.COMMITDOCUMENT, "提交文档信息成功", document.getId(), document.getTitle(), true);
					} catch (Exception e) {
						LoggerServer.error(ObjectType.DOCUMENT, OperateType.COMMITDOCUMENT, "提交文档信息失败，"+e.getMessage(),document.getId(), document.getTitle());
						logger.error("提交文档[ID="+doc.getId()+"]出错",e);
					}
				}
			}
			//撤回文档
			if(status.intValue() == 0){
				if(oldStatus.intValue() != Document.StatusType.cancel.getValue()){
					Document document = new Document();
					document.setId(doc.getId());
					document.setStatus(Document.StatusType.cancel.getValue());
					document.setSubTime(new Timestamp(new Date().getTime()));
					document.setSubUser(user.getUserName());
					try {
						documentService.updateNotNull(document);
						LoggerServer.info(ObjectType.DOCUMENT, OperateType.COMMITDOCUMENT, "撤回已提交的文档信息成功", document.getId(), document.getTitle(),true);
					} catch (Exception e) {
						LoggerServer.error(ObjectType.DOCUMENT, OperateType.COMMITDOCUMENT, "撤回已提交的文档信息失败，"+e.getMessage(), document.getId(), document.getTitle());
						logger.error("撤销已提交的文档[ID="+doc.getId()+"]出错",e);
					}
				}
			}
			data.setSuccess(true);
			if(status.intValue() == 2){
				data.setMessage("提交文档成功");
			}else{
				data.setMessage("撤销文档成功");
			}
		} catch (Exception e) {
			logger.error("提交文档[ID="+idStr+"]出错",e);
			data.setSuccess(false);
			data.setMessage("提交文档失败");
		}
		return data.toJSONString();
	}
	

	/**
	 * 发布文档
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/document/pubDocument.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String pubDocument(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ResultData data = new ResultData();
		String[] idStr = request.getParameterValues("id");
		Long[] id = new Long[idStr.length];
		try {
			for(int i = 0 ;i < idStr.length;i++){
				id[i] = Long.parseLong(idStr[i]);
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("参数出错");
			return data.toJSONString();
		}
		BaseUser user = ShiroSessionMgr.getLoginUser();
		Org org = orgService.getOrgByUserId(user.getId());
		boolean isAdmin = user.isAdmin();
		if(!isAdmin){
			if(org == null){
				data.setSuccess(false);
				data.setMessage("用户没有所属部门，无法发布文档！");
				return data.toJSONString();
			}
			Integer grade = org.getGrade();
			if(grade.intValue() != 1){
				data.setSuccess(false);
				data.setMessage("用户暂无权限发布文档！");
				return data.toJSONString();
			}
		}
		
		try {
			List<String> messages = new ArrayList<String>();
			for(int i = 0; i< id.length;i++){
				Document doc = documentService.findDocById(id[i]);
				if(doc == null){
					messages.add("文档[ID="+id[i]+"]不存在");
					continue;
				}
				Integer oldStatus = doc.getStatus();
				if(oldStatus == null || oldStatus.intValue() == Document.StatusType.delete.getValue()){
					messages.add("文档[ID="+id[i]+"]不是可发布状态，无法发布！");
					continue;
				}
				//不是超级管理员时，如果文档不是本部门的文档，不是已提交状态，无法发布
				if(!isAdmin && org.getId().intValue() != doc.getOrgId().intValue()){
					//文档不是已提交状态
					if(oldStatus != Document.StatusType.commit.getValue()){
						messages.add("文档[ID="+id[i]+"]不是可发布状态，无法发布！");
						continue;
					}
				}
				//发布文档
				if(oldStatus.intValue() != Document.StatusType.pub.getValue()){
					Document document = new Document();
					document.setId(doc.getId());
					document.setStatus(Document.StatusType.pub.getValue());
					document.setPubTime(new Timestamp(new Date().getTime()));
					try {
						documentService.updateNotNull(document);
						LoggerServer.info(ObjectType.DOCUMENT, OperateType.PUBDOCUMENT, "发布文档信息成功", document.getId(), document.getTitle(), true);
					} catch (Exception e) {
						LoggerServer.error(ObjectType.DOCUMENT, OperateType.PUBDOCUMENT, "发布文档信息成功", document.getId(), document.getTitle());
						logger.error("发布文档[ID="+document.getId()+"]出错",e);
					}
				}
			}
			data.setSuccess(true);
			data.setMessage("发布文档成功");
			data.put("messages", messages);
			data.put("docNum", id.length);
		} catch (Exception e) {
			logger.error("发布文档IDS="+idStr.toString()+"出错",e);
			data.setSuccess(false);
			data.setMessage("发布文档失败");
			
		}
		return data.toJSONString();
	}
	
	/**
	 *  取消已发布的文档
	 * @param upfile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/document/cancelDocument.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String cancelDocument(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ResultData data = new ResultData();
		String[] idStr = request.getParameterValues("id");
		Long[] id = new Long[idStr.length];
		try {
			for(int i = 0 ;i < idStr.length;i++){
				id[i] = Long.parseLong(idStr[i]);
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("参数出错");
			return data.toJSONString();
		}
		BaseUser user = ShiroSessionMgr.getLoginUser();
		Org org = orgService.getOrgByUserId(user.getId());
		boolean isAdmin = user.isAdmin();
		if(!isAdmin){
			if(org == null){
				data.setSuccess(false);
				data.setMessage("用户没有所属部门，无法取消文档的发布状态！");
				return data.toJSONString();
			}
			Integer grade = org.getGrade();
			if(grade.intValue() != 1){
				data.setSuccess(false);
				data.setMessage("用户暂无取消发布文档的权限！");
				return data.toJSONString();
			}
		}
		
		try {
			List<String> messages = new ArrayList<String>();
			for(int i = 0; i< id.length;i++){
				Document doc = documentService.findDocById(id[i]);
				if(doc == null){
					messages.add("文档[ID="+id[i]+"]不存在");
					continue;
				}
				Integer oldStatus = doc.getStatus();
				if(oldStatus.intValue() != Document.StatusType.pub.getValue()){
					messages.add("文档[ID="+id[i]+"]不是发布状态，无法取消发布状态！");
					continue;
				}
				//取消发布文档的状态，改为已否状态
				if(oldStatus.intValue() == Document.StatusType.pub.getValue()){
					Document document = new Document();
					document.setId(doc.getId());
					document.setStatus(Document.StatusType.cancel.getValue());
					document.setPubTime(null);
					try {
						documentService.updateNotNull(document);
						LoggerServer.info(ObjectType.DOCUMENT, OperateType.CANCELDOCUMENT, "撤销发布文档信息成功", document.getId(), document.getTitle(), true);
					} catch (Exception e) {
						LoggerServer.error(ObjectType.DOCUMENT, OperateType.CANCELDOCUMENT, "撤销发布文档信息出错，"+e.getMessage(), document.getId(), document.getTitle());
						logger.error("撤回已发布的文档[ID="+doc.getId()+"]出错",e);
					}
				}
			}
			data.setSuccess(true);
			data.setMessage("撤回文档成功");
			data.put("messages", messages);
			data.put("docNum", id.length);
		} catch (Exception e) {
			logger.error("撤回文档IDS="+idStr.toString()+"出错",e);
			data.setSuccess(false);
			data.setMessage("撤回文档失败");
		}
		return data.toJSONString();
	}
	
	/**
	 *  将文档置为删除状态
	 * @param upfile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/document/deleteDocument.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String deleteDocument(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ResultData data = new ResultData();
		String[] idStr = request.getParameterValues("id");
		Long[] id = new Long[idStr.length];
		try {
			for(int i = 0 ;i < idStr.length;i++){
				id[i] = Long.parseLong(idStr[i]);
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("参数出错");
			return data.toJSONString();
		}
		BaseUser user = ShiroSessionMgr.getLoginUser();
		Org org = orgService.getOrgByUserId(user.getId());
		boolean isAdmin = user.isAdmin();
		if(!isAdmin){
			if(org == null){
				data.setSuccess(false);
				data.setMessage("用户没有所属部门，无法删除文档！");
				return data.toJSONString();
			}
		}
		
		try {
			List<String> messages = new ArrayList<String>();
			for(int i = 0; i< id.length;i++){
				Document doc = documentService.findDocById(id[i]);
				if(doc == null){
					messages.add("文档[ID="+id[i]+"]不存在");
					continue;
				}
				Integer oldStatus = doc.getStatus();
				if(oldStatus.intValue() !=  Document.StatusType.delete.getValue()){
					Document document = new Document();
					document.setId(doc.getId());
					document.setStatus(Document.StatusType.delete.getValue());
					document.setDelTime(new Timestamp(new Date().getTime()));
					document.setDelUser(user.getUserName());
					try {
						documentService.updateNotNull(document);
						LoggerServer.info(ObjectType.DOCUMENT, OperateType.DELDOCUMENT, "删除文档信息成功", document.getId(), document.getTitle(), true);
					} catch (Exception e) {
						LoggerServer.error(ObjectType.DOCUMENT, OperateType.DELDOCUMENT, "删除文档信息失败，"+e.getMessage(), document.getId(), document.getTitle());
						logger.error("删除文档[ID="+doc.getId()+"]出错",e);
					}
				}
			}
			data.setSuccess(true);
			data.setMessage("删除文档成功");
			data.put("messages", messages);
			data.put("docNum", id.length);
		} catch (Exception e) {
			logger.error("删除文档IDS="+idStr.toString()+"出错",e);
			data.setSuccess(false);
			data.setMessage("删除文档失败");
		}
		return data.toJSONString();
	}
	
	/**
	 *  彻底删除文档
	 * @param upfile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/document/removeDocument.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String removeDocument(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ResultData data = new ResultData();
		String[] idStr = request.getParameterValues("id");
		Long[] id = new Long[idStr.length];
		try {
			for(int i = 0 ;i < idStr.length;i++){
				id[i] = Long.parseLong(idStr[i]);
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("参数出错");
			return data.toJSONString();
		}
		BaseUser user = ShiroSessionMgr.getLoginUser();
		Org org = orgService.getOrgByUserId(user.getId());
		boolean isAdmin = user.isAdmin();
		if(!isAdmin){
			if(org == null){
				data.setSuccess(false);
				data.setMessage("用户没有所属部门，无法删除文档！");
				return data.toJSONString();
			}
		}
		
		try {
			List<String> messages = new ArrayList<String>();
			for(int i = 0; i< id.length;i++){
				Document doc = documentService.findDocById(id[i]);
				if(doc == null){
					messages.add("文档[ID="+id[i]+"]不存在");
					continue;
				}
				Integer oldStatus = doc.getStatus();
				if(oldStatus.intValue() !=  Document.StatusType.delete.getValue()){
					messages.add("文档[ID="+id[i]+"] 不存在回收站，不可删除");
					continue;
				}
				try {
					documentService.deleteByKey(doc.getId());
					LoggerServer.info(ObjectType.DOCUMENT, OperateType.DROPDOCUMENT, "彻底删除文档信息成功", doc.getId(), doc.getTitle(), true);
				} catch (Exception e) {
					LoggerServer.error(ObjectType.DOCUMENT, OperateType.DROPDOCUMENT, "彻底删除文档信息失败，"+e.getMessage(), doc.getId(), doc.getTitle());
					logger.error("彻底删除文档[ID="+doc.getId()+"]出错",e);
				}
			}
			data.setSuccess(true);
			data.setMessage("彻底删除文档成功");
			data.put("messages", messages);
			data.put("docNum", id.length);
		} catch (Exception e) {
			logger.error("彻底删除文档IDS="+idStr.toString()+"出错",e);
			data.setSuccess(false);
			data.setMessage("彻底删除文档失败");
		}
		return data.toJSONString();
	}
	
	/**
	 *  从回收站还原文档
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/document/restoreDocument.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String restoreDocument(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ResultData data = new ResultData();
		String[] idStr = request.getParameterValues("id");
		Long[] id = new Long[idStr.length];
		try {
			for(int i = 0 ;i < idStr.length;i++){
				id[i] = Long.parseLong(idStr[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			data.setSuccess(false);
			data.setMessage("参数出错");
			return data.toJSONString();
		}
		BaseUser user = ShiroSessionMgr.getLoginUser();
		Org org = orgService.getOrgByUserId(user.getId());
		boolean isAdmin = user.isAdmin();
		if(!isAdmin){
			if(org == null){
				data.setSuccess(false);
				data.setMessage("用户没有所属部门，无法删除文档！");
				return data.toJSONString();
			}
		}
		
		try {
			List<String> messages = new ArrayList<String>();
			for(int i = 0; i< id.length;i++){
				Document doc = documentService.findDocById(id[i]);
				if(doc == null){
					messages.add("文档[ID="+id[i]+"]不存在");
					continue;
				}
				Integer oldStatus = doc.getStatus();
				if(oldStatus.intValue() !=  Document.StatusType.delete.getValue()){
					messages.add("文档[ID="+id[i]+"] 不存在回收站，不可还原！");
					continue;
				}
				Document document = new Document();
				document.setId(doc.getId());
				document.setStatus(Document.StatusType.cancel.getValue());
				try {
					documentService.restoreDocument(document);
					LoggerServer.info(ObjectType.DOCUMENT, OperateType.RESTOREDOCUMENT, "恢复文档信息成功", doc.getId(), doc.getTitle(), true);
				} catch (Exception e) {
					LoggerServer.error(ObjectType.DOCUMENT, OperateType.RESTOREDOCUMENT, "恢复文档信息失败，"+e.getMessage(), doc.getId(), doc.getTitle());
				}
			}
			data.setSuccess(true);
			data.setMessage("恢复文档成功");
			data.put("messages", messages);
			data.put("docNum", id.length);
		} catch (Exception e) {
			logger.error("恢复文档IDS="+idStr.toString()+"出错",e);
			data.setSuccess(false);
			data.setMessage("恢复文档失败");
		}
		return data.toJSONString();
	}
	
	/**
	 * 查看文档的详细信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/document/viewDocument.html")
	public String viewDocument(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String docId = request.getParameter("docId");
		Long id = 0L;
		try {
			id = Long.parseLong(docId);
		} catch (Exception e) {
			id = 0L;
		}
		if (id.longValue() > 0) {
			try {
				Document doc = documentService.selectByKey(id);
				if(doc != null){
					
					BaseUser user = ShiroSessionMgr.getLoginUser();
					Org org = orgService.getOrgByUserId(user.getId());
					/**
					 * 是否显示提交按钮
					 */
					boolean isCommit = false;
					/**
					 * 是否显示发布按钮
					 */
					boolean isPub = false;
					boolean isAdmin = user.isAdmin();
					if(!isAdmin){
						if(org != null){
							Integer grade = org.getGrade();
							if(grade.intValue() == 1){
								isPub = true;
							}
							if(org.getId().longValue() == doc.getOrgId().longValue()){
								isCommit = true;
							}
						}
					}else{
						isCommit = true;
						isPub = true;
					}
					
					Long channelId = doc.getChannelId();
					Channel channel = channelService.selectByKey(channelId);
					request.setAttribute("doc", doc);
					request.setAttribute("channel", channel);
					request.setAttribute("isCommit", isCommit);
					request.setAttribute("isPub", isPub);
					Long orgId = doc.getOrgId();
					if(orgId != null && orgId.longValue() > 0){
						Org docOrg = orgService.selectByKey(orgId);
						if(docOrg != null){
							request.setAttribute("orgName", docOrg.getName());
						}
					}
					List<Appendix> appendixList = documentService.selectAppendix(doc.getId());
					if(appendixList != null  && ! appendixList.isEmpty()){
						String fileInfo = documentService.fileInfoToXml(appendixList);
						request.setAttribute("fileInfo", fileInfo);
					}
				}
			} catch (Exception e) {
				logger.error("查看文档[ID="+id+"]的详细信息出错",e);
				LoggerServer.error(ObjectType.DOCUMENT, OperateType.SHOWDOCUMENT, "查看文档信息失败，"+e.getMessage(), id, "[文档ID="+id+"]");
			}
			
		}
		return "/webSite/document/viewDocument";
	}
	
	/**
	 * 编辑文档
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/document/editDocument.html")
	public String editDocument(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String docId = request.getParameter("docId");
		Long id = 0L;
		try {
			id = Long.parseLong(docId);
		} catch (Exception e) {
			id = 0L;
		}
		if (id.longValue() > 0) {
			try {
				Document doc = documentService.selectByKey(id);
				if(doc != null){
					Long channelId = doc.getChannelId();
					Channel channel = channelService.selectByKey(channelId);
					
					List<Appendix> list = documentService.selectAppendix(id);
					
					String fileInfo = "";
					if(list != null  && ! list.isEmpty()){
						fileInfo = documentService.fileInfoToXml(list);
					}
					
					request.setAttribute("doc", doc);
					request.setAttribute("channel", channel);
					request.setAttribute("fileInfo", fileInfo);
				}
			} catch (Exception e) {
				LoggerServer.error(ObjectType.DOCUMENT, OperateType.EDITDOCUMENT, "跳转到编辑文档信息页面失败，"+e.getMessage(), id, "[文档ID="+id+"]");
				logger.error("跳转到编辑文档[ID="+id+"]信息页面失败",e);
			}
		}
		return "/webSite/document/editDocument";
	}
	
	
	/**
	 * 上传图片附件
	 * @param upfile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/document/appendixImage.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String appendixImage(@RequestParam(value="file")MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException{
		ResultData data = new ResultData();
		if (file.isEmpty()) {
			data.setSuccess(false);
			data.setMessage("文件内容不允许为空");
			return data.toJSONString();
		}
		byte[] bytes = file.getBytes();
		//文件原文件名
		String originalFileName = file.getOriginalFilename();
		String fileExt =FileUtils.getFileSuffix(originalFileName);
		if (!imageCanUpload(fileExt)) {
			data.setSuccess(false);
			data.setMessage("不允许上传的文件类型"+fileExt);
			return data.toJSONString();
		}
		long fileSize = bytes.length/1024;
		if (fileSize > Integer.valueOf(ConfigUtils.getConfigContentByName("UPLOADFIEL_MAXSIZE"))) {
			data.setSuccess(false);
			data.setMessage("文件大小不能超过"+ConfigUtils.getConfigContentByName("UPLOADFIEL_MAXSIZE")+"KB");
			return data.toJSONString();
		}
		//得到临时路径
		String uploadDir =ConfigUtils.getConfigContentByName("PIC_TEMPORARY_PATH");
		File dirPath = new File(uploadDir);
		if (!dirPath.exists()) dirPath.mkdirs();
		//得到文件结构
		String tempName =FileUtils.getFilePath();
	    uploadDir +=File.separator+tempName;
		dirPath = new File(uploadDir);
		try {
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			//得到生成的文件名称
			String fileName =FileUtils.generateFileName(originalFileName);
			String filePath =tempName +File.separator+fileName;
			logger.debug("上传文件的相对路径为:{}",filePath);
			File uploaderFile = new File(uploadDir+ File.separator+ fileName);
			FileCopyUtils.copy(bytes,uploaderFile);
			data.setSuccess(true);
			data.setMessage("上传成功");
			data.put("fileName", fileName);
			data.put("fileDesc", originalFileName);
			data.put("fileExt", fileExt);
			data.put("fileType", Appendix.Type.image.getValue());
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("文件上传失败");
			logger.error(originalFileName+"文件上传失败",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 上传文件附件
	 * @param upfile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/document/appendixFile.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String appendixFile(@RequestParam(value="file")MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException{
		ResultData data = new ResultData();
		if (file.isEmpty()) {
			data.setSuccess(false);
			data.setMessage("文件内容不允许为空");
			return data.toJSONString();
		}
		byte[] bytes = file.getBytes();
		//文件原文件名
		String originalFileName = file.getOriginalFilename();
		String fileExt =FileUtils.getFileSuffix(originalFileName);
		if (!fileCanUpload(fileExt)) {
			data.setSuccess(false);
			data.setMessage("不允许上传的文件类型:"+fileExt);
			return data.toJSONString();
		}
		long fileSize = bytes.length/1024;
		if (fileSize > Integer.valueOf(ConfigUtils.getConfigContentByName("UPLOADFIEL_MAXSIZE"))) {
			data.setSuccess(false);
			data.setMessage("文件大小不能超过"+ConfigUtils.getConfigContentByName("UPLOADFIEL_MAXSIZE")+"KB");
			return data.toJSONString();
		}
		//得到临时路径
		String uploadDir =ConfigUtils.getConfigContentByName("PIC_TEMPORARY_PATH");
		File dirPath = new File(uploadDir);
		if (!dirPath.exists()) dirPath.mkdirs();
		//得到文件结构
		String tempName =FileUtils.getFilePath();
	    uploadDir +=File.separator+tempName;
		dirPath = new File(uploadDir);
		try {
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			//得到生成的文件名称
			String fileName =FileUtils.generateFileName(originalFileName);
			String filePath =tempName +File.separator+fileName;
			logger.debug("上传文件的相对路径为:{}",filePath);
			File uploaderFile = new File(uploadDir+ File.separator+ fileName);
			FileCopyUtils.copy(bytes,uploaderFile);
			data.setSuccess(true);
			data.setMessage("上传成功");
			data.put("fileName", fileName);
			data.put("fileDesc", originalFileName);
			data.put("fileExt", fileExt);
			data.put("fileType", Appendix.Type.file.getValue());
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("文件上传失败");
			logger.error(originalFileName+"文件上传失败",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 上传视频链接
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/document/appendixLink.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String appendixLink(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String fileName= ActionContext.getActionContext().getParameterAsString("link");
		ResultData data = new ResultData();
		if (fileName.isEmpty()) {
			data.setSuccess(false);
			data.setMessage("链接内容不允许为空");
			return data.toJSONString();
		}
		//链接名
		String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
		Pattern pattern = Pattern.compile(regex);
		if (!pattern.matcher(fileName).matches()) {
			data.setSuccess(false);
			data.setMessage("请输入正确的链接！");
			return data.toJSONString();
		}
		Appendix alink=new Appendix();
		alink.setFileName(fileName);
		alink.setFileDesc(fileName);
		alink.setFileType(Appendix.Type.link.getValue());
		alink.setFileExt("url");
		try{
			data.setSuccess(true);
			data.setMessage("链接添加成功");
			data.put("fileName", fileName);
			data.put("fileDesc", fileName);
			data.put("fileExt", "url");
			data.put("fileType", Appendix.Type.link.getValue());
			data.put("alink", alink);
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("链接添加失败");
			logger.error(fileName+"链接添加失败",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 上传视频附件
	 * @param upfile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/document/appendixVideo.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String appendixVideo(@RequestParam(value="file")MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException{
		ResultData data = new ResultData();
		if (file.isEmpty()) {
			data.setSuccess(false);
			data.setMessage("文件内容不允许为空");
			return data.toJSONString();
		}
		byte[] bytes = file.getBytes();
		//文件原文件名
		String originalFileName = file.getOriginalFilename();
		String fileExt =FileUtils.getFileSuffix(originalFileName);
		if (!fileCanUpload(fileExt)) {
			data.setSuccess(false);
			data.setMessage("不允许上传的文件类型:"+fileExt);
			return data.toJSONString();
		}
		long fileSize = bytes.length/1024;
		if (fileSize > Integer.valueOf(ConfigUtils.getConfigContentByName("UPLOADVIDEO_MAXSIZE"))) {
			data.setSuccess(false);
			data.setMessage("文件大小不能超过"+Integer.valueOf(ConfigUtils.getConfigContentByName("UPLOADVIDEO_MAXSIZE"))/1024+"M");
			return data.toJSONString();
		}
		//得到临时路径
		String uploadDir =ConfigUtils.getConfigContentByName("PIC_TEMPORARY_PATH");
		File dirPath = new File(uploadDir);
		if (!dirPath.exists()) dirPath.mkdirs();
		//得到文件结构
		String tempName =FileUtils.getFilePath();
	    uploadDir +=File.separator+tempName;
		dirPath = new File(uploadDir);
		try {
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			//得到生成的文件名称
			String fileName =FileUtils.generateFileName(originalFileName);
			String filePath =tempName +File.separator+fileName;
			logger.debug("上传文件的相对路径为:{}",filePath);
			File uploaderFile = new File(uploadDir+ File.separator+ fileName);
			FileCopyUtils.copy(bytes,uploaderFile);
			data.setSuccess(true);
			data.setMessage("上传成功");
			data.put("fileName", fileName);
			data.put("fileDesc", originalFileName);
			data.put("fileExt", fileExt);
			data.put("fileType", Appendix.Type.video.getValue());
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("文件上传失败");
			logger.error(originalFileName+"文件上传失败",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 
	 * @Description: 判断文件类型是否是设置的类型
	 * @param fileExt 文件后缀名
	 * @return 
	 */
	private boolean fileCanUpload(String fileExt){
		//取得设置的文件类型
		String [] allowUploadFileExts=ConfigUtils.getConfigContentByName("ALLOWUPLOAD_FILEEXT").split(",");
		for(int i=0;i<allowUploadFileExts.length;i++){
			if(allowUploadFileExts[i].trim().equalsIgnoreCase(fileExt)){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * @Description: 判断文件类型是否是设置的类型
	 * @param fileExt 文件后缀名
	 * @return 
	 */
	private boolean imageCanUpload(String fileExt){
		//取得设置的文件类型
		String [] allowUploadFileExts=ConfigUtils.getConfigContentByName("ALLOWUPLOAD_IMAGEEXT").split(",");
		for(int i=0;i<allowUploadFileExts.length;i++){
			if(allowUploadFileExts[i].trim().equalsIgnoreCase(fileExt)){
				return true;
			}
		}
		return false;
	}
	
	
}
