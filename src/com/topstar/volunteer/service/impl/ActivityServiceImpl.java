package com.topstar.volunteer.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.ActivityDao;
import com.topstar.volunteer.dao.AreaDao;
import com.topstar.volunteer.entity.Activity;
import com.topstar.volunteer.entity.Area;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.entity.OrgUser;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.service.ActivityService;
import com.topstar.volunteer.service.OrgService;
import com.topstar.volunteer.service.OrgUserService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ConfigUtils;
import com.topstar.volunteer.util.FileUtils;

@Service
public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements ActivityService{

	private static Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);
	
	@Autowired
	private ActivityDao activityDao; 
	
	@Autowired
	private OrgUserService orgUserService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private AreaDao areaDao;
	
	@Override
	public PageInfo<Activity> findByEntity(Activity activity, String orderBy, int pageIndex,
			int pageSize) {
		PageInfo<Activity> result=null;
		//得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		//判断用户是不是超级管理员
		boolean isAdmin= user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有活动登记信息.
			result = activityDao.findByEntity(activity,orderBy, pageIndex, pageSize);
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
						result = activityDao.findByEntity(activity, orderBy, pageIndex, pageSize);
					} 
					else {
						//显示本机构及下属机构
						activity.setParentOrgId(key);
						result = activityDao.findByEntity(activity, orderBy, pageIndex, pageSize);
					}
				} else {
					//如果是业务机构，只能看到自己机构的数据。比如：如果是湖北省图书馆的，只能看到图书馆的数据。
					//根据机构Id查询得到当前机构下的所有的培训记录
					activity.setOrgId(key);
					result = activityDao.findByEntity(activity, orderBy, pageIndex, pageSize);
				}
			}
		}
		return result;
	}

	@Override
	public PageInfo<Activity> getDoingAndCompletedByEntity(Activity activity, String orderBy, int pageIndex,
			int pageSize) {
		PageInfo<Activity> result=null;
		//得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		//判断用户是不是超级管理员
		boolean isAdmin= user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有活动登记信息.
			result = activityDao.findDoingAndCompletedByEntity(activity,orderBy, pageIndex, pageSize);
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
						result = activityDao.findDoingAndCompletedByEntity(activity, orderBy, pageIndex, pageSize);
					} 
					else {
						//显示本机构及下属机构
						activity.setParentOrgId(key);
						result = activityDao.findDoingAndCompletedByEntity(activity, orderBy, pageIndex, pageSize);
					}
				} else {
					//如果是业务机构，只能看到自己机构的数据。比如：如果是湖北省图书馆的，只能看到图书馆的数据。
					//根据机构Id查询得到当前机构下的所有的培训记录
					activity.setOrgId(key);
					result = activityDao.findDoingAndCompletedByEntity(activity, orderBy, pageIndex, pageSize);
				}
			}
		}
		return result;
	}

	@Override
	public List<Statistics> stsActivityShow() {
		List<Statistics> res= null;
		//得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		//判断用户是不是超级管理员
		boolean isAdmin= user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员,可以看到市级的统计信息
			res=returnActivity();
		}
		else{
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
					//如果是管理机构
					if (grade==1) {
						//如果是省级管理机构
						res=returnActivity();
					}
					else{
						Statistics statistics = new Statistics();
						statistics.setCurOrgId(key);
						res=activityDao.getActivityArea(statistics);
					}
				}
				else{
					// 如果是业务机构
					res= new ArrayList<Statistics>();
					Statistics statistics = new Statistics();
					statistics.setIstreatAgency(true);
					statistics.setOrgId(key);
					res.add(statistics);
				}
			}
		}
		return res;
	}

	@Override
	public List<Statistics> returnActivity() {
		List<Statistics> res= new ArrayList<Statistics>();
		List<Statistics> countyList= null;
		//城市名称
		String cityName="";
		//城市区域编码
		String cityCode="";
		//区,县级区域编码
		String countyCode="";
		List<Area> cityList= areaDao.getAreas();
		Statistics statistics = new Statistics();
		//得到所有的区级活动信息集合
		countyList=activityDao.getActivityArea(statistics);
		for (Area area : cityList) {
			//活动数量
			int activityNum=0;
			cityName=area.getName();
			cityCode=area.getCode().substring(0,4);
			for (Statistics stas : countyList) {
				countyCode=stas.getAreaCode().substring(0,4);
				if (cityCode.equals(countyCode)) {
					activityNum+=stas.getActivityNum();
				}
			}
			Statistics scs = new Statistics();
			scs.setActivityNum(activityNum);
			scs.setAreaName(cityName);
			res.add(scs);
		}
		return res;
	}

	@Override
	public List<Statistics> getSerAct() {
		List<Statistics> res=null;
		Statistics statistics = new Statistics();
		// 得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		// 判断用户是不是超级管理员
		boolean isAdmin = user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员
			res= activityDao.getActivitySer(statistics);
		}
		else{
			// 如果不是超级管理员。获取用户所在的机构，（机构分为业务机构和管理机构）
			OrgUser orgUser = orgUserService.getOrgUserByUserId(user.getId());
			// 如果普通用户没有机构，看不到数据。
			if (orgUser != null) {
				// 获取组织机构Id
				Long key = orgUser.getOrgId();
				// 得到org对象
				Org org = orgService.selectByKey(key);
				// 获取机构类型
				Integer orgType = org.getType();
				// 获取机构级别
				Integer grade = org.getGrade();
				if (orgType == 1) {
					// 如果是管理机构
					if (grade == 1) {
						// 如果是省级管理机构
						res= activityDao.getActivitySer(statistics);
					}else{
						statistics.setCurOrgId(key);
						res= activityDao.getActivitySer(statistics);
					}
				}
				else{
					// 如果是业务机构
					statistics.setOrgId(key);
					res= activityDao.getActivitySer(statistics);
				}
			}
		}
		return res;
	}

	@Override
	public PageInfo<Statistics> selActivityByYear(Statistics statistics, int pageIndex, int pageSize) {
		String orderBy="";
		return activityDao.selActivityByYear(statistics, orderBy, pageIndex, pageSize);
	}
	
	@Override
	public PageInfo<Activity> getApplyingActivityByEntity(Activity activity, String orderBy,
			int pageIndex, int pageSize) {
		PageInfo<Activity> result=null;
		//得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		//判断用户是不是超级管理员
		boolean isAdmin= user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有活动登记信息.
			result = activityDao.findApplyingActivityByEntity(activity,orderBy, pageIndex, pageSize);
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
						result = activityDao.findApplyingActivityByEntity(activity, orderBy, pageIndex, pageSize);
					} 
					else {
						//显示本机构及下属机构
						activity.setParentOrgId(key);
						result = activityDao.findApplyingActivityByEntity(activity, orderBy, pageIndex, pageSize);
					}
				} else {
					//如果是业务机构，只能看到自己机构的数据。比如：如果是湖北省图书馆的，只能看到图书馆的数据。
					//根据机构Id查询得到当前机构下的所有的培训记录
					activity.setOrgId(key);
					result = activityDao.findApplyingActivityByEntity(activity, orderBy, pageIndex, pageSize);
				}
			}
		}
		return result;
	}

	@Override
	public PageInfo<Activity> getRecruitActivityList(Activity activity,String orderBy, int currPage, int pageSize) {
		PageInfo<Activity> result=null;
		//得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		//判断用户是不是超级管理员
		boolean isAdmin= user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有活动信息.
			result = activityDao.findRecruitActivityListByEntity(activity,orderBy, currPage, pageSize);
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
						result = activityDao.findRecruitActivityListByEntity(activity, orderBy, currPage, pageSize);
					} 
					else {
						//显示本机构及下属机构
						activity.setParentOrgId(key);
						result = activityDao.findRecruitActivityListByEntity(activity, orderBy, currPage, pageSize);
					}
				} else {
					//如果是业务机构，只能看到自己机构的数据。比如：如果是湖北省图书馆的，只能看到图书馆的数据。
					//根据机构Id查询得到当前机构下的所有的培训记录
					activity.setOrgId(key);
					result = activityDao.findRecruitActivityListByEntity(activity, orderBy, currPage, pageSize);
				}
			}
		}
		return result;
	}

	@Override
	public int saveActivityRecords(Activity activity, String imgUrls) {
		//临时文件路径
		String temporary= ConfigUtils.getConfigContentByName("PIC_TEMPORARY_PATH");
		//保存路径
		String storage = ConfigUtils.getConfigContentByName("UPLOADPATH");
		String saveUrls="";
		if (activity != null && StringUtils.isNotBlank(imgUrls)) {
			//得到数据库中的头像地址
			String[] imgUrl=imgUrls.split(",");
			for (int i = 0; i < imgUrl.length; i++) {
				if(StringUtils.isNotBlank(imgUrl[i])){
					if(i==0){
						saveUrls+=imgUrl[i].replaceAll("\\\\","\\\\\\\\");
					}else{
						saveUrls+=","+imgUrl[i].replaceAll("\\\\","\\\\\\\\");
					}
					String tempPicPath=temporary+File.separator+imgUrl[i];
					String realPicPath=storage+File.separator+imgUrl[i];
					File tempFile=new File(tempPicPath);
					File realStorage=new File(storage);
					if(tempFile.exists() && tempFile.isFile() && realStorage.isDirectory() && realStorage.exists()){
						//将修改后的图片从临时路径复制到保存路径下
						FileUtils.copyFile(tempPicPath,realPicPath);
					}else{
						return 0;
					}
					
				}
			}
			logger.debug("保存指定activityId["+activity.getId()+"]活动记录的图片urls:"+saveUrls);
			List<String> urlList= activity.getActivityImgList();
			if(urlList!=null && !urlList.isEmpty()){
				activity.setActivityImg(activity.getActivityImg()+","+saveUrls);
			}else{
				activity.setActivityImg(saveUrls);
			}
			return updateNotNull(activity);
		}else{
			return 0;
		}
	}

	@Override
	public boolean batchCancelActivitys(Long[] activityIds) throws TPSException {
		if(activityIds!=null && activityIds.length>0){
			int batchNum=activityIds.length;
			for (int i = 0; i < activityIds.length; i++) {
				Activity activity=selectByKey(activityIds[i]);
				if(activity.getStatus().equals(1) || activity.getStatus().equals(2) || activity.getStatus().equals(3)){
					activity.setStatus(8);
					if(updateNotNull(activity)>0){
						batchNum--;
					}
				}else{
					throw new TPSException("非法操作");
				}
			}
			if(batchNum==0){
				return true;
			}
			
		}
		return false;
	}

	@Override
	public boolean batchDelActivitys(Long[] activityIds) throws TPSException {
		if(activityIds!=null && activityIds.length>0){
			int batchNum=activityIds.length;
			for (int i = 0; i < activityIds.length; i++) {
				Activity activity=selectByKey(activityIds[i]);
				if(activity.getStatus().equals(1)){
					if(deleteByKeys(activityIds)==batchNum){
						return true;
					}
				}else{
					throw new TPSException("非法操作");
				}
			}
		}
		return false;
	}

	@Override
	public boolean batchPubActivitys(Long[] activityIds) throws TPSException {
		if(activityIds!=null && activityIds.length>0){
			int batchNum=activityIds.length;
			for (int i = 0; i < activityIds.length; i++) {
				Activity activity=selectByKey(activityIds[i]);
				if(activity.getStatus().equals(2)){
					activity.setStatus(3);
					if(updateNotNull(activity)>0){
						batchNum--;
					}
				}else{
					throw new TPSException("非法操作");
				}
			}
			if(batchNum==0){
				return true;
			}
			
		}
		return false;
	}

	@Override
	public boolean batchCommitActivitys(Long[] activityIds) throws TPSException {
		if(activityIds!=null && activityIds.length>0){
			int batchNum=activityIds.length;
			for (int i = 0; i < activityIds.length; i++) {
				Activity activity=selectByKey(activityIds[i]);
				if(activity.getStatus().equals(1)){
					activity.setStatus(2);
					if(updateNotNull(activity)>0){
						batchNum--;
					}
				}else{
					throw new TPSException("非法操作");
				}
			}
			if(batchNum==0){
				return true;
			}
			
		}
		return false;
	}

	@Override
	public int toRecruitStatus() {
		return activityDao.toRecruitStatus();
	}

	@Override
	public int toWaitDoStatus() {
		return activityDao.toWaitDoStatus();
	}

	@Override
	public int toDoingStatus() {
		return activityDao.toDoingStatus();
	}

	@Override
	public int toEndStatus() {
		return activityDao.toEndStatus();
	}
	
}
