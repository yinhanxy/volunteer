package com.topstar.volunteer.dao;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Role;

public class RoleDaoTest extends BaseTest{

	private static Logger logger = LoggerFactory.getLogger(RoleDaoTest.class);
	
	@Autowired
	private RoleDao roleDao;

	@Test
	public void selectByRole(){
		Role role= new Role();
		role.setRoleName("");
		PageInfo<Role> roles= roleDao.findByEntity(role, "", 1, 10);
		if(roles != null){
			
		}
	}
	
	@Test
	public void updateByKey(){
		Role role= new Role();
		role.setId(30l);
		role.setStatus(0);
		//int rows=roleDao.updateNotNull(role);
		int rows=roleDao.updateByPrimaryKey(role);
		System.out.println("Ӱ���������"+rows);
	}
	
	/*
	@Test
	public void selectByExampleByPage(){
		User user = new User();
		user.setUserName("admin");
		user.setRealName("��");
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
//		user.setRealName("��");
		Example example = new Example(User.class);
		example.setOrderByClause("user_Id asc");
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(user.getUserName())) {
            criteria.andLike("userName", "%" + user.getUserName() + "%");
        }
        if (StringUtils.isNotEmpty(user.getRealName())) {
            criteria.andLike("realName", "%" + user.getRealName() + "%");
        }
        String orderBy = "user_id asc";
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
	}*/
	
	@Test
	public void  findByEntity(){
		Role role= new Role();
		role.setRoleName("����");
		int pageIndex = 1;
		int pageSize = 2;
		PageInfo<Role> page = roleDao.findByEntity(role,"", pageIndex, pageSize);
		List<Role> roles= page.getList();
		if(roles != null){
			for(Role u : roles){
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
	
	
	/*@Test
	public void add(){
		User user = new User();
//		user.setUserId(45);
		user.setMobile("13982718292");
		user.setRealName("����");
		user.setRegTime(new Timestamp(new Date().getTime()));
		user.setRemark("��ע����");
		user.setStatus(1);
		user.setUserName("��������");
		user.setNickName("�ǳ�");
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
			user.setRealName("����"+i);
			user.setRegTime(new Timestamp(new Date().getTime()));
			user.setRemark("��ע����"+i);
			user.setStatus(1);
			user.setUserName("��������"+i);
			user.setNickName("�ǳ�"+i);
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
		user.setRealName("����");
		user.setRegTime(new Timestamp(new Date().getTime()));
//		user.setRemark("��ע����");
		user.setStatus(1);
		user.setUserName("��������");
		user.setNickName("�ǳ�");
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
		try {
			 PageInfo<LinkedHashMap<String, Object>> page = userDao.sqlExecuteQuery(sql,params,1,10);
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
		
	}*/
	
	
	@Test
	public void insert(){
		Role role=new Role();
		role.setRoleName("1");
		role.setStatus(0);
//		role.setCrTime(new Date());
		role.setCrUser("123");
		role.setRoleType(0);
		role.setRoleDesc("");
		int row=roleDao.insert(role);
		System.out.println(row);
	}
}
