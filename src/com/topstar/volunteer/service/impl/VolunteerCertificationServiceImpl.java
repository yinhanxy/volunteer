package com.topstar.volunteer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.VolunteerCertificationDao;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.entity.OrgUser;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.service.OrgService;
import com.topstar.volunteer.service.OrgUserService;
import com.topstar.volunteer.service.VolunteerCertficationService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
@Service
public class VolunteerCertificationServiceImpl implements VolunteerCertficationService {
	
	
	@Autowired
	private VolunteerCertificationDao volunteerCertificationDao;
	
	@Autowired
	private OrgUserService orgUserService;
	
	@Autowired
	private OrgService orgService;
	
	public PageInfo<VolunteerView> getVolunteerCertsByEntity(Volunteer volunteer,String orderBy, int pageIndex, int pageSize){

		PageInfo<VolunteerView> result=null;
		//得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		//判断用户是不是超级管理员
		boolean isAdmin= user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有培训记录.
			result = volunteerCertificationDao.findVolunteerCertByEntity(volunteer, orderBy, pageIndex, pageSize);
		} else {
			//如果不是超级管理员。获取用户所在的机构，（机构分为业务机构和管理机构）
			OrgUser orgUser = orgUserService.getOrgUserByUserId(user.getId());
			//如果普通用户没有机构，看不到数据。
			if (orgUser!=null) {
				//获取组织机构Id
				Long key = orgUser.getOrgId();
				//得到org对象
				Org org= orgService.selectByKey(key);
				//获取机构类型
				Integer orgType= org.getType();
				//获取机构级别
				Integer grade= org.getGrade();
				if (orgType==1) {
					//如果是管理机构，可以看到所管辖的所有机构的发布的数据。比如是文化厅的，可以看到所有数据。如果是武汉市文化局的，可以看到本市的所有数据。如果是XXX县的文化局，可以看到本县的数据。
					if (grade==1) {
						//如果是省级管理机构，可以看到所有培训记录.
						result = volunteerCertificationDao.findVolunteerCertByEntity(volunteer, orderBy, pageIndex, pageSize);
					} 
					else {
						//显示本机构及下属机构
						volunteer.setParentOrgId(key);
						result = volunteerCertificationDao.findVolunteerCertByEntity(volunteer, orderBy, pageIndex, pageSize);
					}
				} else {
					//如果是业务机构，只能看到自己机构的数据。比如：如果是湖北省图书馆的，只能看到图书馆的数据。
					//根据机构Id查询得到当前机构下的所有的培训记录
					volunteer.setOrgId(key);
					result = volunteerCertificationDao.findVolunteerCertByEntity(volunteer, orderBy, pageIndex, pageSize);
				}
			}
		}
		return result;
		
	}
	
	public PageInfo<VolunteerView> getVolunteerWithoutCertByEntity(Volunteer volunteer,String orderBy, int pageIndex, int pageSize){
		PageInfo<VolunteerView> result=null;
		//得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		//判断用户是不是超级管理员
		boolean isAdmin= user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有培训记录.
			result = volunteerCertificationDao.findVolunteerWithoutCertByEntity(volunteer, orderBy, pageIndex, pageSize);
		} else {
			//如果不是超级管理员。获取用户所在的机构，（机构分为业务机构和管理机构）
			OrgUser orgUser = orgUserService.getOrgUserByUserId(user.getId());
			//如果普通用户没有机构，看不到数据。
			if (orgUser!=null) {
				//获取组织机构Id
				Long key = orgUser.getOrgId();
				//得到org对象
				Org org= orgService.selectByKey(key);
				//获取机构类型
				Integer orgType= org.getType();
				//获取机构级别
				Integer grade= org.getGrade();
				if (orgType==1) {
					//如果是管理机构，可以看到所管辖的所有机构的发布的数据。比如是文化厅的，可以看到所有数据。如果是武汉市文化局的，可以看到本市的所有数据。如果是XXX县的文化局，可以看到本县的数据。
					if (grade==1) {
						//如果是省级管理机构，可以看到所有培训记录.
						result = volunteerCertificationDao.findVolunteerWithoutCertByEntity(volunteer, orderBy, pageIndex, pageSize);
					} 
					else {
						//显示本机构及下属机构
						volunteer.setParentOrgId(key);
						result = volunteerCertificationDao.findVolunteerWithoutCertByEntity(volunteer, orderBy, pageIndex, pageSize);
					}
				} else {
					//如果是业务机构，只能看到自己机构的数据。比如：如果是湖北省图书馆的，只能看到图书馆的数据。
					//根据机构Id查询得到当前机构下的所有的培训记录
					volunteer.setOrgId(key);
					result = volunteerCertificationDao.findVolunteerWithoutCertByEntity(volunteer, orderBy, pageIndex, pageSize);
				}
			}
		}
		return result;
		
	}
	
	/**
	 * 根据志愿者ID查询指定志愿者证书信息
	 * @param volunteerId 查询的志愿者ID
	 * @return
	 */
	public VolunteerView getVolunteerCertByVolunteerId(Long volunteerId){
		return volunteerCertificationDao.findVolunteerCertByVolunteerId(volunteerId);
	}
	
}
