package com.topstar.volunteer.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topstar.volunteer.dao.VolunteerCheckDao;
import com.topstar.volunteer.dao.VolunteerDao;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.entity.VolunteerCheck;
import com.topstar.volunteer.service.VolunteerCheckService;
@Service
public class VolunteerCheckServiceImpl extends BaseServiceImpl<VolunteerCheck> implements VolunteerCheckService {
	
	
	@Autowired
	private VolunteerCheckDao volunteerCheckDao;
	
	@Autowired
	private VolunteerDao volunteerDao;
	

	@Override
	public VolunteerCheck getVolunteerCheckByVolunteerId(Long volunteerId) {
		return volunteerCheckDao.findVolunteerCheckByVolunteerId(volunteerId);
	}

	@Override
	public boolean batchCheckVolunteer(VolunteerCheck volc, Long[] volunteerIds) {
		if(volunteerIds!=null && volunteerIds.length>0){
			for (long volunteerId : volunteerIds) {
				
				VolunteerCheck check=new VolunteerCheck();
				check.setOperTime(volc.getOperTime());
				check.setStatus(volc.getStatus());
				check.setUserName(volc.getUserName());
				int checkVolunteer=0;
				check.setVolunteerId(volunteerId);
				VolunteerCheck existsVolunteerCheck=getVolunteerCheckByVolunteerId(volunteerId);
				if(existsVolunteerCheck!=null){
					check.setId(existsVolunteerCheck.getId());
					check.setCheckContent("");
					checkVolunteer=updateByPrimaryKey(check);
				}else{
					checkVolunteer=volunteerCheckDao.insertNotNull(check);
				}
				
				if(checkVolunteer<1){
					return false;
				}
				Volunteer volunteer=new Volunteer();
				volunteer.setId(volunteerId);
				try{
					int volunteerStatus=Integer.valueOf(check.getStatus());
					if(volunteerStatus==Volunteer.StatusType.pass.getValue()){
						volunteer.setStatus(Volunteer.StatusType.pass.getValue());
						volunteerDao.updateNotNull(volunteer);
					}else if(volunteerStatus==Volunteer.StatusType.deny.getValue()){
						volunteer.setStatus(Volunteer.StatusType.deny.getValue());
						volunteerDao.updateNotNull(volunteer);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int saveVolunteerCheck(VolunteerCheck volunteerCheck,Volunteer volunteer) {
		try{
			int volunteerCheckResult=insertNotNull(volunteerCheck);
			if(volunteerCheckResult>0){
			
				int volunteerStatus=Integer.valueOf(volunteerCheck.getStatus());
				if(volunteerStatus==Volunteer.StatusType.pass.getValue()){
					volunteer.setStatus(Volunteer.StatusType.pass.getValue());
					volunteerDao.updateNotNull(volunteer);
				}else if(volunteerStatus==Volunteer.StatusType.deny.getValue()){
					volunteer.setStatus(Volunteer.StatusType.deny.getValue());
					volunteerDao.updateNotNull(volunteer);
				}
			}
			return volunteerCheckResult;
		}catch (Exception e) {
			throw new RuntimeException("新增志愿者审核操作结果出现错误",e);
		}	
		
	}

	@Override
	public int updateVolunteerCheck(VolunteerCheck volunteerCheck,Volunteer volunteer) {
		try{
				int volunteerCheckResult=updateByPrimaryKey(volunteerCheck);
				if(volunteerCheckResult>0){
					int volunteerStatus=Integer.valueOf(volunteerCheck.getStatus());
					if(volunteerStatus==Volunteer.StatusType.pass.getValue()){
						volunteer.setStatus(Volunteer.StatusType.pass.getValue());
						volunteerDao.updateNotNull(volunteer);
					}else if(volunteerStatus==Volunteer.StatusType.deny.getValue()){
						volunteer.setStatus(Volunteer.StatusType.deny.getValue());
						volunteerDao.updateNotNull(volunteer);
					}
				}	
				return volunteerCheckResult;
			}catch (Exception e) {
				throw new RuntimeException("更新志愿者审核操作结果出现错误",e);
			}
		}
}
