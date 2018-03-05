package com.topstar.volunteer.cache;

import com.topstar.volunteer.entity.Org;

public interface OrgCache {

	public boolean init();
	
	/**
	 * 在缓存中查找对应的组织机构信息
	 * @param id 组织机构ID
	 * @return
	 */
	public Org findById(Long id);
	
	/**
	 * 新建组织时将组织添加到缓存中
	 * @param org 组织对象
	 * @return
	 */
	public boolean add(Org org);
	
	/**
	 * 删除组织时将组织从缓存中移除
	 * @param orgId
	 * @return
	 */
	public boolean delete(Long orgId);
	
	/**
	 * 修改组织基本信息时，更新缓存中的组织
	 * @param org 修改后的组织信息
	 * @return
	 */
	public boolean update(Org org);
}
