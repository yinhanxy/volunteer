package com.topstar.volunteer.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tk.mybatis.orderbyhelper.OrderByHelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.DocumentDao;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.entity.Document;
import com.topstar.volunteer.mapper.DocumentMapper;

@Repository
public class DocumentDaoImpl extends BaseDaoImpl<Document> implements DocumentDao {

	@Autowired
	private DocumentMapper documentMapper;
	
	/**
	 * 分页显示文档信息 
	 * @param doc
	 * @return
	 */
	public PageInfo<Document> selectPageByAdmin(List<Long> channelIds,Document doc,String orderBy, int pageIndex, int pageSize){
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Document> list = documentMapper.selectPageByAdmin(doc, channelIds);
		PageInfo<Document> page = new PageInfo<Document>(list);
		return page;
	}
	
	/**
	 * 管理机构分页显示文档信息<br/>
	 * 可查看本机构及下属机构的所有文档
	 * @param orgId 管理机构ID
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageInfo<Document> manageOrgList(List<Long> channelIds,Long orgId, Document doc,String orderBy, int pageIndex,
			int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Document> list = documentMapper.manageOrgList(orgId,doc,channelIds);
		PageInfo<Document> page = new PageInfo<Document>(list);
		return page;
	}
	
	
	/**
	 * 分页显示文档信息<br/>
	 * 省级业务机构可查看同系统的所有机构的数据
	 * @param orgId 业务机构
	 * @param systemCode 机构所属单位系统
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Document> listBySystemCode(List<Long> channelIds,Long orgId,Long systemCode,Document doc,String orderBy,int pageIndex,int pageSize){
		if(orgId != null && systemCode != null){
			PageHelper.startPage(pageIndex, pageSize);
			if(StringUtils.isNotBlank(orderBy)){
				OrderByHelper.orderBy(orderBy);
			}
			List<Document> list = documentMapper.listBySystemCode(orgId, systemCode, doc,channelIds);
			PageInfo<Document> page = new PageInfo<Document>(list);
			return page;
		}
		return null;
	}
	
	/**
	 * 普通业务机构的分页显示文档信息
	 * @param channelIds 可见栏目ID集合
	 * @param doc 
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Document> listByOrgId(List<Long> channelIds,Document doc,String orderBy, int pageIndex, int pageSize){
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Document> list = documentMapper.listByOrgId(doc,channelIds);
		PageInfo<Document> page = new PageInfo<Document>(list);
		return page;
	}
	
	/**
	 * 通过主键查询文档信息<br/>
	 * 不返回大字段信息
	 * @param id 文档主键
	 * @return
	 */
	public Document findDocById(@Param("id")Long id){
		return documentMapper.findDocById(id);
	}
	/**
	 * 得到栏目的最大排序值
	 * @param channelId
	 * @return
	 */
	public Long getMaxDocOrder(@Param("channelId")Long channelId){
		return documentMapper.getMaxDocOrder(channelId);
	}
	
	/**
	 * 设置文档的排序号
	 * @param id 文档编号
	 * @param docOrder 文档排序号
	 */
	public void setDocOrder(Long id,Long docOrder){
		documentMapper.setDocOrder(id, docOrder);
	}

	/**
	 * 修改文档的状态
	 * @param id 文档编号集合
	 * @param status 文档状态
	 * @return
	 */
	public int updateDocumentStatus(Long[] ids,Integer status){
		int row = 0;
		if(ids != null && ids.length > 0){
			for(Long id : ids){
				row += documentMapper.updateDocumentStatus(id, status);
			}
		}
		return row;
	}
	
	/**
	 * 将文档从回收站恢复<br/>
	 * 将删除时间和删除用户字段值清空，将文档置为已否状态
	 * @return
	 */
	public int restoreDocument(Document doc){
		if(doc != null && doc.getId() != null && doc.getId().longValue() > 0){
			doc.setStatus(Document.StatusType.cancel.getValue());
			return documentMapper.restoreDocument(doc);
		}
		return -1;
	}
	
	/**
	 * 根据栏目ID彻底删除文档
	 * @param channelId 栏目ID
	 * @return
	 */
	public int deleteByChannelId(Long channelId){
		if(channelId != null && channelId.longValue() >0){
			return documentMapper.deleteByChannelId(channelId);
		}
		return -1;
	}

	/**
	 * 得到指定栏目下的'通知公告'下的前n条文档
	 * @param channel
	 * @param n
	 * @return
	 */
	public List<Document> getDocNotice(Channel channel, int n) {
		return documentMapper.getDocNotice(channel, n);
	}
}
