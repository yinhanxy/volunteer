package com.topstar.volunteer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.VolunteerDao;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.mapper.VolunteerMapper;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.model.VolunteerView;

import tk.mybatis.orderbyhelper.OrderByHelper;

@Repository
public class VolunteerDaoImpl extends BaseDaoImpl<Volunteer> implements VolunteerDao{

	@Autowired
	private VolunteerMapper volunteerMapper;
	
	@Override
	public PageInfo<VolunteerView> findByEntity(Volunteer volunteer,String orderBy, int page, int rows) {
		PageHelper.startPage(page, rows);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<VolunteerView> volunteers = volunteerMapper.findByEntity(volunteer);
		PageInfo<VolunteerView> pageVolunteer = new PageInfo<VolunteerView>(volunteers);
		return pageVolunteer;
	}
	
	public PageInfo<VolunteerView> findVolunteerCheckByEntity(Volunteer volunteer,String orderBy, int page, int rows){
		PageHelper.startPage(page, rows);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<VolunteerView> volunteers = volunteerMapper.findVolunteerCheckByEntity(volunteer);
		PageInfo<VolunteerView> pageVolunteer = new PageInfo<VolunteerView>(volunteers);
		return pageVolunteer;
	}
	
	public int setVolunteerPassword(long[] ids,String newEncodePwd){
		Map<String,Object> params = new HashMap<String,Object>();
        params.put("ids", ids);
        params.put("newEncodePwd", newEncodePwd);
		return volunteerMapper.setVolunteerPassword(params);
	}

	@Override
	public Long selectVolId(String idCard) {
		return volunteerMapper.selectVolId(idCard);
	}

	@Override
	public int editRetreatTeamStatus(Long volId, Long retreatTeamStatus) {
		return volunteerMapper.editRetreatTeamStatus(volId, retreatTeamStatus);
	}

	@Override
	public List<Statistics> statisticsShow(Statistics statistics) {
		return volunteerMapper.statisticsShow(statistics);
	}

	@Override
	public PageInfo<Volunteer> getVolByOrgId(Long trainId,String orderBy, Volunteer volunteer, int page, int rows) {
		PageHelper.startPage(page,rows);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Volunteer> volunteers =volunteerMapper.getVolByOrgId(trainId, volunteer); 
		PageInfo<Volunteer> pageVolunteer = new PageInfo<Volunteer>(volunteers);
		return pageVolunteer;
	}

	@Override
	public PageInfo<Volunteer> getVols(Long orgId, String orderBy, int page, int rows) {
		PageHelper.startPage(page,rows);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Volunteer> volunteers =volunteerMapper.getVols(orgId);
		PageInfo<Volunteer> pageVolunteer = new PageInfo<Volunteer>(volunteers);
		return pageVolunteer;
	}

	@Override
	public Statistics getVolInfo(Statistics statistics) {
		return volunteerMapper.getVolInfo(statistics);
	}

	@Override
	public List<Statistics> getVolInfoList(Statistics statistics) {
		return volunteerMapper.getVolInfoList(statistics);
	}

	@Override
	public PageInfo<Statistics> getVolStatis(Statistics statistics, String orderBy, int page, int rows) {
		PageHelper.startPage(page, rows);
		if(StringUtils.isNotBlank(orderBy)){
			OrderByHelper.orderBy(orderBy);
		}
		List<Statistics> list=volunteerMapper.getVolStatis(statistics);
		PageInfo<Statistics> pageInfo= new PageInfo<Statistics>(list);
 		return pageInfo;
	}
}
