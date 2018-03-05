package com.topstar.volunteer.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Appendix;
import com.topstar.volunteer.entity.Document;
import com.topstar.volunteer.exception.TPSException;

public interface DocumentService extends BaseService<Document>{

	
	
	public PageInfo<Document> list(List<Long> channelIds,Document doc,String orderBy, int pageIndex, int pageSize);
	
	/**
	 * 通过主键查询文档信息<br/>
	 * 不返回大字段信息
	 * @param id 文档主键
	 * @return
	 */
	public Document findDocById(Long id);
	
	/**
	 * 添加文档
	 * @param doc
	 * @param appendixList
	 * @return
	 * @throws TPSException
	 */
	public Long addDocument(Document doc,List<Appendix> appendixList) throws TPSException;
	
	/**
	 * 修改文档
	 * @param doc
	 * @param appendixList
	 * @param deleteAppIds
	 * @return
	 * @throws TPSException
	 */
	public Long editDocument(Document doc,List<Appendix> appendixList,List<Long> deleteAppIds) throws TPSException;
	
	/**
	 * 将文档的排序值设置为最大
	 * @param id 文档编号
	 * @param channelId 栏目编号
	 */
	public void setMaxDocOrder(Long id,Long channelId);
	
	/**
	 * 查询文档的所有附件信息
	 * @param docId
	 * @return
	 */
	public List<Appendix> selectAppendix(Long docId);
	
	/**
	 * 将XML结构的appendix信息解析为附件对象集合
	 * @param fileInfo
	 * @return
	 * @throws TPSException
	 */
	public List<Appendix> parseFileInfo(String fileInfo) throws TPSException;
	
	/**
	 * 将附件信息拼接为XML结构数据
	 * @param appendixList
	 * @return
	 */
	public String fileInfoToXml(List<Appendix> appendixList);
	
	/**
	 * 修改文档的状态
	 * @param ids 文档主键集合
	 * @param status 状态值
	 * @return
	 */
	public int updateDocumentStatus(Long[] ids, Integer status);
	
	/**
	 * 将文档从回收站恢复<br/>
	 * 将删除时间和删除用户字段值清空，将文档置为已否状态
	 * @return
	 */
	public int restoreDocument(Document doc);
	
	/**
	 * 根据栏目ID删除文档信息
	 * @param channelId 栏目ID
	 * @return
	 */
	public int deleteDocumentByChannelId(Long channelId);
	
	/**
	 * 得到'通知公告'下的文档
	 * @return
	 */
	public List<Document> getDocNotice() throws TPSException;
}
