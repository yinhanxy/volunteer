package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.entity.Document;
import com.topstar.volunteer.util.BaseMapper;

public interface DocumentMapper extends BaseMapper<Document> {
	
	/**
	 * 分页显示文档信息<br/>
	 * 供超级管理员和省级管理机构查看文档
	 * @param doc
	 * @return
	 */
	public List<Document> selectPageByAdmin(@Param("doc")Document doc,@Param("channelIds")List<Long> channelIds);
	
	/**
	 * 显示所有文档<br/>
	 * 二级、三级管理机构查看本机构及下属机构的文档
	 * @param orgId 管理机构的ID
	 * @param doc 文档查询对象
	 * @return
	 */
	public List<Document> manageOrgList(@Param("orgId")Long orgId,@Param("doc")Document doc,@Param("channelIds")List<Long> channelIds);
	
	
	/**
	 * 显示文档信息<br/>
	 * 省级业务机构可查看同系统的所有机构的数据
	 * @param orgId 业务机构
	 * @param systemCode 机构所属单位系统
	 * @return
	 */
	public List<Document> listBySystemCode(@Param("orgId")Long orgId,@Param("systemCode")Long systemCode,@Param("doc")Document doc,@Param("channelIds")List<Long> channelIds);
	
	/**
	 * 普通业务机构的分页显示文档信息
	 * @param channelIds 可见栏目ID集合
	 * @param doc 
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Document> listByOrgId(@Param("doc")Document doc,@Param("channelIds")List<Long> channelIds);
	
	/**
	 * 通过主键查询文档信息<br/>
	 * 不返回大字段信息
	 * @param id 文档主键
	 * @return
	 */
	public Document findDocById(@Param("id")Long id);
	
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
	public void setDocOrder(@Param("id")Long id,@Param("docOrder")Long docOrder);
	
	/**
	 * 修改文档的状态
	 * @param id 文档编号
	 * @param status 文档状态
	 * @return
	 */
	public int updateDocumentStatus(@Param("id")Long id,@Param("status")Integer status);
	
	/**
	 * 将文档从回收站恢复<br/>
	 * 将删除时间和删除用户字段值清空，将文档置为已否状态
	 * @return
	 */
	public int restoreDocument(@Param("doc")Document doc);
	
	/**
	 * 根据栏目ID彻底删除文档
	 * @param channelId 栏目ID
	 * @return
	 */
	public int deleteByChannelId(@Param("channelId")Long channelId);
	
	/**
	 * 得到指定栏目下的'通知公告'下的前n条文档
	 * @param channel
	 * @param n
	 * @return
	 */
	public List<Document> getDocNotice(@Param("channel")Channel channel,@Param("n")int n);
}