package com.topstar.volunteer.service;


import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.StarEvaluate;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.entity.VolunteerBlack;
import com.topstar.volunteer.model.VolunteerView;

/**
 * 志愿者星级评定业务层
 * @author TRS
 *
 */
public interface VolunteerBlackService extends BaseService<VolunteerBlack>{
	
	/**
	 * 根据志愿者实体字段的值过滤查询志愿者黑名单的信息列表 
	 * @param volunteer
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<VolunteerView> getVolunteerBlackByEntity(Volunteer volunteer,String orderBy, int pageIndex, int pageSize);
	
	/**
	 * 根据志愿者ID查询指定志愿者黑名单信息
	 * @param volunteerId 查询的志愿者ID
	 * @return
	 */
	public VolunteerView getVolunteerBlackByVolunteerId(Long volunteerId);
	
	/**
	 * 查询没有黑名单记录的志愿者信息列表
	 * @param userName
	 * @param orderBy
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageInfo<VolunteerView> getVolunteersWithoutBlack(String userName,String orderBy, int pageIndex, int pageSize);
	
	/**
	 * 删除指定的志愿者黑名单信息
	 * @param volunteerBlackIds
	 * @param volunteerIds 黑名单中志愿者的id数组
	 * @return
	 */
	public boolean deleleVolunteerBlacks(Long[] volunteerBlackIds,Long[] volunteerIds);
}
