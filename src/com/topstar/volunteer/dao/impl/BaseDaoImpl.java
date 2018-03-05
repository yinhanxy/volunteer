package com.topstar.volunteer.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.orderbyhelper.OrderByHelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.BaseDao;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.jdbc.DBManagerProvider;
import com.topstar.volunteer.util.BaseMapper;

public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
    private BaseMapper<T> baseMapper;

    @Autowired
    private DBManagerProvider provider;
    

	public DBManagerProvider getProvider() {
		return provider;
	}

	public void setProvider(DBManagerProvider provider) {
		this.provider = provider;
	}

	@Override
	public T selectByKey(Object key) {
		return baseMapper.selectByPrimaryKey(key);
	}
	
	/**
	 * 添加<br/>
	 * 返回sql更新的行数
	 */
	@Override
	public int insert(T entity) {
		return baseMapper.insert(entity);
	}

	@Override
	public int insertNotNull(T record) {
		return baseMapper.insertSelective(record);
	}

	@Override
	public int deleteByKey(Object key) {
		return baseMapper.deleteByPrimaryKey(key);
	}
	
	@Override
	public int deleteByExample(Example example) {
		return baseMapper.deleteByExample(example);
	}
	
	@Override
	public int deleteByKeys(Object[] keys){
		int row = 0;
		if(keys != null && keys.length > 0){
			for(int i = 0 ; i < keys.length;i++){
				row += baseMapper.deleteByPrimaryKey(keys[i]);
			}
		}
		return row;
	}
	
	@Override
	public int updateByPrimaryKey(T entity){
		return baseMapper.updateByPrimaryKey(entity);
	}

	@Override
	public int updateNotNull(T entity) {
		return baseMapper.updateByPrimaryKeySelective(entity);
	}
	
	public boolean existsWithPrimaryKey(Object key){
		return baseMapper.existsWithPrimaryKey(key);
	}

	@Override
	public List<T> selectByExample(Example example) {
		return baseMapper.selectByExample(example);
	}

	@Override
	public PageInfo<T> selectByExample(Example example, int pageIndex,int pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		List<T> list = baseMapper.selectByExample(example);
		PageInfo<T> page = new PageInfo<T>(list);
		return page;
	}
	
	@Override
	public PageInfo<T> selectByExample(Example example,String orderBy,int pageIndex,int pageSize){
		PageHelper.startPage(pageIndex, pageSize);
		if(StringUtils.isNoneBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<T> list = baseMapper.selectByExample(example);
		PageInfo<T> page = new PageInfo<T>(list);
		return page;
	}
	
	public List<LinkedHashMap<String, Object>> sqlExecuteQuery(String sql) throws TPSException{
		return provider.sqlExecuteQuery(sql);
	}

	public PageInfo<LinkedHashMap<String, Object>> sqlExecuteQuery(String sql, String idColumn,int pageIndex, int pageSize) throws TPSException {
		return provider.sqlExecuteQuery(sql, idColumn,pageIndex, pageSize);
	}

	public List<LinkedHashMap<String, Object>> sqlExecuteQuery(String sql, LinkedHashMap<String, Object> params) throws TPSException {
		return provider.sqlExecuteQuery(sql, params);
	}

	public PageInfo<LinkedHashMap<String, Object>> sqlExecuteQuery(String sql,String idColumn, LinkedHashMap<String, Object> params, int pageIndex, int pageSize)
			throws TPSException {
		return provider.sqlExecuteQuery(sql,idColumn, params, pageIndex, pageSize);
	}

	public int sqlExecuteIntQuery(String sql) throws TPSException {
		return provider.sqlExecuteIntQuery(sql);
	}

	public int sqlExecuteIntQuery(String sql, LinkedHashMap<String, Object> params) throws TPSException{
		return provider.sqlExecuteIntQuery(sql, params);
	}

}
