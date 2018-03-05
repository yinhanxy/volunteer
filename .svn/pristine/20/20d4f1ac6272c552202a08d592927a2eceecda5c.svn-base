package com.topstar.volunteer.mapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Area;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.RetreatTeamView;
import com.topstar.volunteer.model.Statistics;

public class VolunteerMapperTest extends BaseTest{
	
	@Autowired
	private VolunteerMapper volunteerMapper;
	
	@Autowired
	private RetreatTeamMapper retreatTeamMapper;
	
	@Autowired
	private AreaMapper areaMapper;
	
	@Test
	public void selectVolId(){
		String idcard ="123457851516151565";
		Long id = volunteerMapper.selectVolId(idcard);
		System.out.println("id:"+id);
	}
	
	@Test
	public void findRetreatTeamApplysByEntity(){
		Volunteer volunteer = new Volunteer();
		volunteer.setTeamName("武汉市美术馆大队");
		List<RetreatTeamView> results=retreatTeamMapper.findRetreatTeamApplysByEntity(volunteer);
		for (RetreatTeamView retreatTeamView : results) {
			System.out.println(retreatTeamView.toString());
		}
	
	}
	
	@Test
	public void getRetreatTeamByVolunteerId(){
		Long volunteerId= 2l;
		RetreatTeamView res =retreatTeamMapper.getRetreatTeamByVolunteerId(volunteerId);
		System.out.println(res.toString());
	}
	
	@Test
	public void editRetreatTeamStatus(){
		Long volId=71l;
		Long retreatTeamStatus=5l;
		int res= volunteerMapper.editRetreatTeamStatus(volId, retreatTeamStatus);
		System.out.println("res:"+res);
	}
	
	@Test
	public void statisticsShow(){
		List<Statistics> countyList= null;
		//城市名称
		String cityName="";
		//城市区域编码
		String cityCode="";
		//区,县级区域编码
		String countyCode="";
		Statistics statistics =new Statistics();
//		statistics.setAreaType(1);
		//得到需匹配的市级名称及区域编码
		List<Area> cityList=areaMapper.getAreas();
		//得到所有的区级志愿者信息集合
		countyList=volunteerMapper.statisticsShow(statistics);
		List<Statistics> res= new ArrayList<Statistics>();
		for (Area area : cityList) {
			//志愿者人数
			int volNum=0;
			cityName=area.getName();
			cityCode=area.getCode().substring(0,4);
			System.out.println("cityName:"+cityName+",cityCode:"+cityCode);
			for (Statistics stas : countyList) {
				countyCode=stas.getAreaCode().substring(0,4);
				if (cityCode.equals(countyCode)) {
					volNum+=stas.getVolNum();
				}
			}
			Statistics scs = new Statistics();
			scs.setAreaName(cityName);
			scs.setVolNum(volNum);
			System.out.println("areaName:"+scs.getAreaName()+",volNum:"+scs.getVolNum());
			res.add(scs);
		}
		
		System.out.println(res.toString());
	}
	
	@Test 
	public void getVolByOrgId(){
		Volunteer volunteer =new Volunteer();
		List<Volunteer> volList= volunteerMapper.getVolByOrgId(1l, volunteer);
		for (Volunteer volunteer2 : volList) {
			System.out.println(volunteer2.toString());
		}
	}
	
	@Test
	public void getVols(){
		List<Volunteer> volList= volunteerMapper.getVols(5l);
		for (Volunteer volunteer2 : volList) {
			System.out.println(volunteer2.toString());
		}
	}
	
	@Test
	public void getVolStatis(){
		Statistics s =new Statistics();
		 List<Statistics> list= volunteerMapper.getVolStatis(s);
		 for (Statistics statistics : list) {
			System.out.println(statistics.toString());
		}
	}
}
