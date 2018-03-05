package com.topstar.volunteer.service;

import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.entity.VolunteerCheck;

public interface VolunteerCheckService extends BaseService<VolunteerCheck>{
	
	/**
	 * 查找志愿者对应的审核信息
	 * @param params
	 * @return
	 */
	public VolunteerCheck getVolunteerCheckByVolunteerId(Long volunteerId);
	
	/**
	 * 批量审核志愿者信息
	 * @param params
	 * @return
	 */
	public boolean batchCheckVolunteer(VolunteerCheck volunteerCheck,Long[] volunteerIds);
	
	/**
	 * 保存志愿者审核信息
	 * @param volunteerCheck
	 * @param volunteer
	 * @return
	 */
	public int saveVolunteerCheck(VolunteerCheck volunteerCheck,Volunteer volunteer);
	
	/**
	 * 更新志愿者审核信息
	 * @param volunteerCheck
	 * @param volunteer
	 * @return
	 */
	public int updateVolunteerCheck(VolunteerCheck volunteerCheck,Volunteer volunteer);
	
}
