package com.topstar.volunteer.dao;

import java.util.LinkedHashMap;
import java.util.List;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.exception.TPSException;

public interface BaseDao<T> {

	/**
	 * 根据主键进行查询,必须保证结果唯一
	 * @param key 单个字段做主键时,可以直接写主键的值;联合主键时,key可以是实体类,也可以是Map
	 * @return
	 */
    T selectByKey(Object key);

    /**
	 * 添加<br/>
	 * 返回sql更新的行数
	 */
    int insert(T entity);
    
    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     * @param entity
     * @return
     */
    int insertNotNull(T entity);
    
    /**
     * 根据主键修改实体类的字段值
     * @param entity
     * @return
     */
    int updateByPrimaryKey(T entity);

    /**
     * 根据主键修改实体类中不为null的字段值
     * @param entity
     * @return
     */
    int updateNotNull(T entity);

    /**
     * 通过主键进行删除,这里最多只会删除一条数据<br/>
     * @param key 单个字段做主键时,可以直接写主键的值;联合主键时,key可以是实体类,也可以是Map
     * @return sql影响的行数
     */
    int deleteByKey(Object key);
    
    /**
     * 根据主键集合删除对象，返回sql影响的行数
     * @param keys
     * @return
     */
    int deleteByKeys(Object[] keys);
    
    /**
     * Example条件删除数据
     * @param example
     * @return
     */
    int deleteByExample(Example example);

    /**
     * 根据主键判断数据是否存在
     * @param key 主键
     * @return
     */
    boolean existsWithPrimaryKey(Object key);
    
    
    List<T> selectByExample(Example example);
    
    
    PageInfo<T> selectByExample(Example example,int pageIndex,int pageSize);
    
    PageInfo<T> selectByExample(Example example,String orderBy,int pageIndex,int pageSize);
    
    
    public List<LinkedHashMap<String, Object>> sqlExecuteQuery(String sql) throws TPSException;

    /**
     * 分页查询数据
     * @param sql 查询SQL
     * @param idColumn SQL中的主键，供通过COUNT(IDCOLUMN)统计总条数使用
     * @param pageIndex 当前页
     * @param pageSize 每页显示的条数
     * @return
     * @throws TPSException
     */
	public PageInfo<LinkedHashMap<String, Object>> sqlExecuteQuery(String sql,String idColumn, int pageIndex, int pageSize) throws TPSException ;

	public List<LinkedHashMap<String, Object>> sqlExecuteQuery(String sql, LinkedHashMap<String, Object> params) throws TPSException;

	 /**
     * 分页查询数据
     * @param sql 查询SQL
     * @param idColumn SQL中的主键，供通过COUNT(IDCOLUMN)统计总条数使用
     * @param params 参数值键值对
     * @param pageIndex 当前页
     * @param pageSize 每页显示的条数
     * @return
     * @throws TPSException
     */
	public PageInfo<LinkedHashMap<String, Object>> sqlExecuteQuery(String sql,String idColumn,LinkedHashMap<String, Object> params, int pageIndex, int pageSize)
			throws TPSException;

	/**
	 * 执行SQL，返回INT类型的结果
	 * @param sql
	 * @return
	 * @throws TPSException
	 */
	public int sqlExecuteIntQuery(String sql) throws TPSException;

	/**
	 * 执行SQL，返回INT类型的结果
	 * @param sql
	 * @param params 参数键值对
	 * @return
	 * @throws TPSException
	 */
	public int sqlExecuteIntQuery(String sql, LinkedHashMap<String, Object> params) throws TPSException;
}
