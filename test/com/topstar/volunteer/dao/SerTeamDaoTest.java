package com.topstar.volunteer.dao;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.entity.TeamUser;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.mapper.TeamUserMapper;
import com.topstar.volunteer.model.Statistics;

public class SerTeamDaoTest extends BaseTest{

	@Autowired
	private SerTeamDao serTeamDao;
	
	@Autowired
	private TeamUserDao teamUSerDao;
	
	@Autowired
	private TeamUserMapper teamUserMapper;
	
	
	@Test
	public void test(){
		TeamUser teamUser=new TeamUser();
		teamUser.setTeamId(4l);
		long teamId = 4l;
		String orderBy="";
		int page=1;
		int rows=10;
		User userss=new User();
//		userss.setUserName("cf");
//		userss.setRealName("超");
		PageInfo<User> res=teamUSerDao.findByUserTeamID(userss, teamId, orderBy, page, rows);
		List<User> users=res.getList();
		for (User user : users) {
			System.out.println(user.getUserName());
		}
	}
	
	@Test
	public void del(){
		long userId=2l;
		long teamId=4l;
//		int res=teamUSerDao.delTeamUser(userId, teamId);
		int delRes=teamUserMapper.delTeamUser(userId, teamId);
		System.out.println(delRes);
	}
	
	@Test
	public void get(){
		long userId=3l;
//		int res=teamUSerDao.delTeamUser(userId, teamId);
		List<Long> res=teamUserMapper.findTeamIdsByUserId(userId);
		for (Long long1 : res) {
			System.out.println(long1);
		}
		
	}

	/**
	 * 获取服务队信息
	 */
	@Test
	public void  findByEntity(){
		SerTeam serTeam= new SerTeam();
		int pageIndex = 1;
		int pageSize = 2;
		PageInfo<SerTeam> page = serTeamDao.findByEntity(serTeam,"", pageIndex, pageSize);
		List<SerTeam> SerTeams= page.getList();
		if(SerTeams != null){
			for(SerTeam u : SerTeams){
				System.out.println(u.toString());
			}
		}
	}
	
	

	
	
	
	
	@Test
	public void DeleteAll(){
		Integer[] keys = new Integer[]{14,15,16,17};
		int a = serTeamDao.deleteByKeys((Object[]) keys);
		System.out.println(a);
	}
	
	@Test
	public void sqlExecuteIntQuery(){
		String sql = "select count(*) from serteam where cr_user like ?";
		try {
			LinkedHashMap<String, Object> params = new LinkedHashMap<>();
			params.put("cr_user", "%yin%");
			int i = serTeamDao.sqlExecuteIntQuery(sql,params);
			System.out.println(i);
		} catch (TPSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getSerByArea(){
		Statistics statistics = new Statistics();
		List<Statistics> lists= serTeamDao.getSerByArea(statistics);
		for (Statistics statistics2 : lists) {
			System.out.println(statistics2.toString());
		}
	}
	
	
//	@Test
//	public void insert(){
//		SerTeam SerTeam=new SerTeam();
//		SerTeam.setSerTeamName("1");
//		SerTeam.setStatus(0);
//		SerTeam.setCrTime(new Date());
//		SerTeam.setCrUser("123");
//		SerTeam.setSerTeamType(0);
//		SerTeam.setSerTeamDesc("");
//		int row=SerTeamDao.insert(SerTeam);
//		System.out.println(row);
//	}
}
