package com.topstar.volunteer.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.secrecy.MD5Util;

import tk.mybatis.mapper.entity.Example;

public class UserDaoTest extends BaseTest{

	private static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	
	@Autowired
	private UserDao userDao;

	@Test
	public void selectByUser(){
		User user = new User();
		user.setUserName("admin");
		user.setRealName("四");
		user.setNickName("管理");
		List<User> users = userDao.selectByUser(user, 1, 10);
		if(users != null){
			for(User u : users){
				System.out.println(u);
			}
		}
	}
	
	@Test
	public void selectByExampleByPage(){
		User user = new User();
		user.setUserName("admin");
		user.setRealName("四");
		Example example = new Example(User.class);
		example.setOrderByClause("user_Id asc");
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(user.getUserName())) {
            criteria.andLike("userName", "%" + user.getUserName() + "%");
        }
        if (StringUtils.isNotEmpty(user.getRealName())) {
            criteria.andLike("realName", "%" + user.getRealName() + "%");
        }
		PageInfo<User> page = userDao.selectByExample(example, 1, 10);
		List<User> users = page.getList();
		if(users != null){
			for(User u : users){
				System.out.println(u);
			}
		}
		logger.info("total:"+page.getTotal());
		logger.info("pageNum:"+page.getPageNum());
		logger.info("pageSize:"+page.getPageSize());
		logger.info("startRow:"+page.getStartRow());
		logger.info("endRow:"+page.getEndRow());
		logger.info("pages:"+page.getPages());
		logger.info("isFirstPage:"+page.isIsFirstPage());
		logger.info("isLastPage:"+page.isIsLastPage());
		logger.info("HasPreviousPage:"+page.isHasPreviousPage());
		logger.info("HasNextPage:"+page.isHasNextPage());
	}
	
	@Test
	public void selectByExampleByPageOrder(){
		User user = new User();
//		user.setUserName("admin");
//		user.setRealName("四");
		Example example = new Example(User.class);
		example.setOrderByClause("id asc");
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(user.getUserName())) {
            criteria.andLike("userName", "%" + user.getUserName() + "%");
        }
        if (StringUtils.isNotEmpty(user.getRealName())) {
            criteria.andLike("realName", "%" + user.getRealName() + "%");
        }
        String orderBy = "id asc";
		PageInfo<User> page = userDao.selectByExample(example,orderBy, 1, 10);
		List<User> users = page.getList();
		if(users != null){
			for(User u : users){
				System.out.println(u);
			}
		}
		logger.info("total:"+page.getTotal());
		logger.info("pageNum:"+page.getPageNum());
		logger.info("pageSize:"+page.getPageSize());
		logger.info("startRow:"+page.getStartRow());
		logger.info("endRow:"+page.getEndRow());
		logger.info("pages:"+page.getPages());
		logger.info("isFirstPage:"+page.isIsFirstPage());
		logger.info("isLastPage:"+page.isIsLastPage());
		logger.info("HasPreviousPage:"+page.isHasPreviousPage());
		logger.info("HasNextPage:"+page.isHasNextPage());
	}
	
	@Test
	public void  findByEntity(){
		User user = new User();
		user.setUserName("admin");
		int pageIndex = 2;
		int pageSize = 5;
		PageInfo<User> page = userDao.findByEntity(user,"", pageIndex, pageSize);
		List<User> users = page.getList();
		if(users != null){
			for(User u : users){
				System.out.println(u);
			}
		}
		logger.info("total:"+page.getTotal());
		logger.info("pageNum:"+page.getPageNum());
		logger.info("pageSize:"+page.getPageSize());
		logger.info("startRow:"+page.getStartRow());
		logger.info("endRow:"+page.getEndRow());
		logger.info("pages:"+page.getPages());
		logger.info("isFirstPage:"+page.isIsFirstPage());
		logger.info("isLastPage:"+page.isIsLastPage());
		logger.info("HasPreviousPage:"+page.isHasPreviousPage());
		logger.info("HasNextPage:"+page.isHasNextPage());
	}
	
	
	@Test
	public void add(){
		User user = new User();
//		user.setUserId(45);
		user.setMobile("13982718292");
		user.setRealName("测试");
		user.setRegTime(new Timestamp(new Date().getTime()));
		user.setRemark("备注测试");
		user.setStatus(1);
		user.setUserName("测试姓名");
		user.setNickName("昵称");
		user.setUserPwd("aadddd");
		user.setUseTime(new Timestamp(new Date().getTime()));
		user.setEmail("3333333@qq.com");
		int a = userDao.insert(user);
		System.out.println(a);
	}
	
	@Test
	public void addList(){
		List<User> list = new ArrayList<User>();
		for( int i = 0 ;i < 3;i++){
			User user = new User();
			user.setMobile("13982718292");
			user.setRealName("测试"+i);
			user.setRegTime(new Timestamp(new Date().getTime()));
			user.setRemark("备注测试"+i);
			user.setStatus(1);
			user.setUserName("测试姓名"+i);
			user.setNickName("昵称"+i);
			user.setUserPwd("aadddd"+i);
			user.setUseTime(new Timestamp(new Date().getTime()));
			user.setEmail("3333333@qq.com");
			list.add(user);
		}
//		int a = userService.insertList(list);
//		System.out.println(a);
	}
	
	@Test
	public void update(){
		User user = new User();
		user.setId(24L);
		user.setMobile("13982718292");
		user.setRealName("测试");
		user.setRegTime(new Timestamp(new Date().getTime()));
//		user.setRemark("备注测试");
		user.setStatus(1);
		user.setUserName("测试姓名");
		user.setNickName("昵称");
		user.setUserPwd("aadddd");
		user.setUseTime(new Timestamp(new Date().getTime()));
		user.setEmail("3333333@qq.com");
//		int a = userService.updateByPrimaryKey(user);
		int a = userDao.updateNotNull(user);
		System.out.println(a);
	}
	
	@Test
	public void DeleteAll(){
		Integer[] keys = new Integer[]{14,15,16,17};
		int a = userDao.deleteByKeys((Object[]) keys);
		System.out.println(a);
	}
	
	@Test
	public void sqlExecuteIntQuery(){
		String sql = "select count(*) from sys_user where user_name like ?";
		try {
			LinkedHashMap<String, Object> params = new LinkedHashMap<>();
			params.put("user_name", "%admin%");
			int i = userDao.sqlExecuteIntQuery(sql,params);
			System.out.println(i);
		} catch (TPSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void sqlExecuteQuery(){
		String sql = "select u.id,u.user_name,u.nick_name,u.real_name,r.id role_id,r.role_name "+
					" from sys_user u inner join role_user ru on(u.id= ru.user_id) "+
					" inner join sys_role r on (ru.role_id=r.id)"
					+ " where u.user_name like ?";
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();
		params.put("user_name", "%admin%");
		String idColumn = "id";
		try {
			 PageInfo<LinkedHashMap<String, Object>> page = userDao.sqlExecuteQuery(sql,idColumn,params,1,10);
			 if(page != null && page.getTotal() > 0){
				 ResultData data = new ResultData(true, page);
				 System.out.println(data.toJSONString());
				 System.out.println(page.getList().size());
				 logger.info("total:"+page.getTotal());
				 logger.info("pageNum:"+page.getPageNum());
				 logger.info("pageSize:"+page.getPageSize());
				 logger.info("startRow:"+page.getStartRow());
				 logger.info("endRow:"+page.getEndRow());
				 logger.info("pages:"+page.getPages());
				 logger.info("isFirstPage:"+page.isIsFirstPage());
				 logger.info("isLastPage:"+page.isIsLastPage());
				 logger.info("HasPreviousPage:"+page.isHasPreviousPage());
				 logger.info("HasNextPage:"+page.isHasNextPage());
			 }
		} catch (TPSException e) {
			e.printStackTrace();
		}
		
	}
	
//	@Test
//	public void sqlExecuteQuery(){
//		String sql = "select u.id,u.user_name,u.nick_name,u.real_name,r.id role_id,r.role_name "+
//					" from sys_user u inner join role_user ru on(u.id= ru.user_id) "+
//					" inner join sys_role r on (ru.role_id=r.id)"
//					+ " where u.user_name like ?";
//		LinkedHashMap<String, Object> params = new LinkedHashMap<>();
//		params.put("user_name", "%admin%");
//		try {
//			 PageInfo<LinkedHashMap<String, Object>> page = userDao.sqlExecuteQuery(sql,params,1,10);
//			 if(page != null && page.getTotal() > 0){
//				 ResultData data = new ResultData(true, page);
//				 System.out.println(data.toJSONString());
//				 System.out.println(page.getList().size());
//				 logger.info("total:"+page.getTotal());
//				 logger.info("pageNum:"+page.getPageNum());
//				 logger.info("pageSize:"+page.getPageSize());
//				 logger.info("startRow:"+page.getStartRow());
//				 logger.info("endRow:"+page.getEndRow());
//				 logger.info("pages:"+page.getPages());
//				 logger.info("isFirstPage:"+page.isIsFirstPage());
//				 logger.info("isLastPage:"+page.isIsLastPage());
//				 logger.info("HasPreviousPage:"+page.isHasPreviousPage());
//				 logger.info("HasNextPage:"+page.isHasNextPage());
//			 }
//		} catch (TPSException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	@Test
	public void selectByRoleId(){
		PageInfo<User> users=userDao.findUsersByRoleId("2", "d", null, 1, 10);
		List<User> userList=users.getList();
		for (User user : userList) {
			System.out.println(user);
		}
	}
	
	@Test
	public void resetPwd(){
		String encodePass=MD5Util.encode("123456");
		System.out.println(encodePass);
	}
	
	@Test
	public void findUsersByOrgId(){
		PageInfo<User> users=userDao.findUsersByOrgId(1l, null, null, 10, 1);
		List<User> lists=users.getList();
		for (User user : lists) {
			System.out.println(user.toString());
		}
	}
	
	@Test
	public void getUsersByOrgId(){
		User user =new User();
		PageInfo<User> users=userDao.getUsersByOrgId(1l, user, 1, 10);
		List<User> lists=users.getList();
		for (User use : lists) {
			System.out.println(use.toString());
		}
	}
}
