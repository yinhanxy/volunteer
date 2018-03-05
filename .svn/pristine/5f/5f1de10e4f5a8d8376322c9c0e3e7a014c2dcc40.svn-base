package com.topstar.volunteer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.entity.Document;

public interface DocumentDao extends BaseDao<Document> {

	/**
	 * 分页显示文档信息<br/>
	 * 供超级管理员和省级管理机构查看文档
	 * @param doc
	 * @return
	 */
	public PageInfo<Document> selectPageByAdmin(List<Long> channelIds,Document doc,String orderBy, int pageIndex, int pageSize);
	
	
	
	/**
	 * 管理机构分页显示文档信息<br/>
	 * 可查看本机构及下属机构的所有文档
	 * @param orgId 管理机构ID
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Document> manageOrgList(List<Long> channelIds,Long orgId,Document doc,String orderBy,int pageIndex,int pageSize);
	
	/**
	 * 分页显示文档信息<br/>
	 * 省级业务机构可查看同系统的所有机构的数据
	 * @param orgId 管理机构ID
	 * @param systemCode 机构所属单位系统
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Document> listBySystemCode(List<Long> channelIds,Long orgId,Long systemCode,Document doc,String orderBy,int pageIndex,int pageSize);
	
	/**
	 * 普通业务机构的分页显示文档信息
	 * @param channelIds 可见栏目ID集合
	 * @param doc 
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Document> listByOrgId(List<Long> channelIds,Document doc,String orderBy, int pageIndex, int pageSize);
	
	/**
	 * 通过主键查询文档信息<br/>
	 * 不返回大字段信息
	 * @param id 文档主键
	 * @return
	 */
	public Document findDocById(Long id);
	
	/**
	 * 得到栏目的最大排序值
	 * @param channelId
	 * @return
	 */
	public Long getMaxDocOrder(@Param("channelId")Long channelId);
	
	/**
	 * 设置文档的排序号
	 * @param id 文档编号
	 * @param docOrder 文档排序号
	 */
	public void setDocOrder(Long id,Long docOrder);
	
	/**
	 * 修改文档的状态
	 * @param ids 文档编号集合
	 * @param status 文档状态
	 * @return
	 */
	public int updateDocumentStatus(Long[] ids,Integer status);
	
	/**
	 * 将文档从回收站恢复<br/>
	 * 将删除时间和删除用户字段值清空，将文档置为已否状态
	 * @return
	 */
	public int restoreDocument(Document doc);
	
	/**
	 * 根据栏目ID彻底删除文档
	 * @param channelId 栏目ID
	 * @return
	 */
	public int deleteByChannelId(Long channelId);
	
	/**
	 * 得到指定栏目下的'通知公告'下的前n条文档
	 * @param channel
	 * @param n
	 * @return
	 */
	public List<Document> getDocNotice(Channel channel,int n);
}
