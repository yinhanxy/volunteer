package com.topstar.volunteer.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.BaseDao;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.service.BaseService;
import com.topstar.volunteer.util.ConfigUtils;
import com.topstar.volunteer.util.FileUtils;

public class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	private BaseDao<T> dao;

	@Override
	public T selectByKey(Object key) {
		return dao.selectByKey(key);
	}
	
	/**
	 * 添加<br/>
	 * 返回sql更新的行数
	 */
	@Override
	public int insert(T entity) {
		return dao.insert(entity);
	}
	
	@Override
	public int insertNotNull(T record) {
		return dao.insertNotNull(record);
	}

	@Override
	public int deleteByKey(Object key) {
		return dao.deleteByKey(key);
	}
	
	@Override
	public int deleteByKeys(Object[] keys){
		int row = 0;
		if(keys != null && keys.length > 0){
			row = dao.deleteByKeys(keys);
		}
		return row;
	}
	
	@Override
	public int deleteByExample(Example example) {
		return dao.deleteByExample(example);
	}
	@Override
	public int updateByPrimaryKey(T entity){
		return dao.updateByPrimaryKey(entity);
	}

	@Override
	public int updateNotNull(T entity) {
		return dao.updateNotNull(entity);
	}
	
	public boolean existsWithPrimaryKey(Object key){
		return dao.existsWithPrimaryKey(key);
	}

	@Override
	public List<T> selectByExample(Example example) {
		return dao.selectByExample(example);
	}

	@Override
	public PageInfo<T> selectByExample(Example example, int pageIndex,int pageSize) {
		PageInfo<T> page = dao.selectByExample(example, pageIndex, pageSize);
		return page;
	}
	
	@Override
	public PageInfo<T> selectByExample(Example example,String orderBy,int pageIndex,int pageSize){
		PageInfo<T> page = dao.selectByExample(example, orderBy, pageIndex, pageSize);
		return page;
	}
	

}
