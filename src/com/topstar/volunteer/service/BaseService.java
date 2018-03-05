package com.topstar.volunteer.service;

import java.util.List;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageInfo;

public interface BaseService<T> {
	
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
    
    int insertNotNull(T record);
    
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
    
}
