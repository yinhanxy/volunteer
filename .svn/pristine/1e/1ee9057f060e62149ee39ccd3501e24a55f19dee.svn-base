package com.topstar.volunteer.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.TrainVol;
import com.topstar.volunteer.entity.Volunteer;

public class TrainVolServiceTest  extends BaseTest{

	@Autowired
	private TrainVolService trainVolService;
	
	@Test
	public void test(){
		long trainId = 1l;
		int page=1;
		int rows=10;
		PageInfo<TrainVol> res= trainVolService.findByEntity(trainId, page, rows);
		List<TrainVol> users=res.getList();
		for (TrainVol user : users) {
			System.out.println(user.toString());
		}
		
	}
	
	@Test
	public void testGetTrainVols(){
		Volunteer vol =new Volunteer();
//		long trainId = 1l;
		long trainId = 1l;
		int page=1;
		int rows=10;
		PageInfo<Volunteer> vols = trainVolService.getTrainVols(vol, trainId, page, rows);
		List<Volunteer> volunteers =vols.getList();
 		for (Volunteer volunteer : volunteers) {
 			System.out.println(volunteer.getUserName()+","+volunteer.getTrainIdList());
		}
	}
	
//	@Test
//	public void get(){
//		User userss = new User();
//		long teamId = 3l;
//		int page=1;
//		int rows=10;
//		PageInfo<User> res=teamUserService.getUserTeams(userss, teamId, page, rows);
//		List<User> users=res.getList();
//		for (User user : users) {
//			System.out.println(user.getTeamIdList()+",");
//		}
//	}
//	s
//	@Test
//	public void del(){
//		long teamId = 4l;
//		List<Long> userKeys=new ArrayList<Long>();
//		userKeys.add(3l) ;
//		userKeys.add(4l) ;
//		boolean res=teamUserService.delTeamUser(userKeys, teamId);
//		System.out.println(res);
//	}
}
