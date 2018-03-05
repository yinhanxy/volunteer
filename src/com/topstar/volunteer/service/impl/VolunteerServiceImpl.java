package com.topstar.volunteer.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.cache.ConfigCache;
import com.topstar.volunteer.dao.AreaDao;
import com.topstar.volunteer.dao.TrainVolDao;
import com.topstar.volunteer.dao.VolunteerDao;
import com.topstar.volunteer.entity.Area;
import com.topstar.volunteer.entity.Config;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.entity.OrgUser;
import com.topstar.volunteer.entity.TrainVol;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.service.OrgService;
import com.topstar.volunteer.service.OrgUserService;
import com.topstar.volunteer.service.VolunteerService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ConfigUtils;
import com.topstar.volunteer.util.FileUtils;
import com.topstar.volunteer.util.secrecy.MD5Util;
@Service
public class VolunteerServiceImpl extends BaseServiceImpl<Volunteer> implements VolunteerService {
	
	private static Logger logger = LoggerFactory.getLogger(VolunteerServiceImpl.class);
	
	@Autowired
	private ConfigCache configCache;
	
	@Autowired
	private VolunteerDao volunteerDao;
	
	@Autowired
	private TrainVolDao trainVolDao;
	
	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private OrgUserService orgUserService;
	
	@Autowired
	private OrgService orgService;
	
	public PageInfo<VolunteerView> findByEntity(Volunteer volunteer,String orderBy, int pageIndex, int pageSize){
		
		PageInfo<VolunteerView> result=null;
		//得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		//判断用户是不是超级管理员
		boolean isAdmin= user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有培训记录.
			result = volunteerDao.findByEntity(volunteer, orderBy, pageIndex, pageSize);
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
						result = volunteerDao.findByEntity(volunteer, orderBy, pageIndex, pageSize);
					} 
					else {
						//显示本机构及下属机构
						volunteer.setParentOrgId(key);
						result = volunteerDao.findByEntity(volunteer, orderBy, pageIndex, pageSize);
					}
				} else {
					//如果是业务机构，只能看到自己机构的数据。比如：如果是湖北省图书馆的，只能看到图书馆的数据。
					//根据机构Id查询得到当前机构下的所有的培训记录
					volunteer.setOrgId(key);
					result = volunteerDao.findByEntity(volunteer, orderBy, pageIndex, pageSize);
				}
			}
		}
		return result;
	}
	
	public PageInfo<VolunteerView> findVolunteerCheckByEntity(Volunteer volunteer,String orderBy, int pageIndex, int pageSize){
		PageInfo<VolunteerView> result=null;
		//得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		//判断用户是不是超级管理员
		boolean isAdmin= user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有培训记录.
			result = volunteerDao.findVolunteerCheckByEntity(volunteer, orderBy, pageIndex, pageSize);
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
						result = volunteerDao.findVolunteerCheckByEntity(volunteer, orderBy, pageIndex, pageSize);
					} 
					else {
						//显示本机构及下属机构
						volunteer.setParentOrgId(key);
						result = volunteerDao.findVolunteerCheckByEntity(volunteer, orderBy, pageIndex, pageSize);
					}
				} else {
					//如果是业务机构，只能看到自己机构的数据。比如：如果是湖北省图书馆的，只能看到图书馆的数据。
					//根据机构Id查询得到当前机构下的所有的培训记录
					volunteer.setOrgId(key);
					result = volunteerDao.findVolunteerCheckByEntity(volunteer, orderBy, pageIndex, pageSize);
				}
			}
		}
		return result;
		
	}
	
	/**
	 * 根据志愿者的主键值重置志愿者的密码为初始值密码
	 * @param ids 志愿者的主键值数组
	 * @return 是否重置密码操作成功，成功返回：0
	 * @throws TPSException 
	 */
	public boolean resetVolunteerPassword(long[] ids) throws TPSException{
		if(ids!=null && ids.length>0){
			Config defPassConfig=configCache.getConfigValueByName("DEFAULT_VOLUNTEERPASSWORD");
			if(defPassConfig!=null && StringUtils.isNoneBlank(defPassConfig.getContent())){
				String encodePass=MD5Util.encode(defPassConfig.getContent());
				int flag=0;
				flag=volunteerDao.setVolunteerPassword(ids, encodePass);
				if(flag>0){
					return true;
				}
				return false;
			}else{
				throw new TPSException("没有设置初始密码的系统配置");
			}

		}else{
			throw new TPSException("设置用户初始密码参数不合法");
		}
	}
	
	
	
	@Override
	public boolean addVols(List<Volunteer> volList,Long trainId) {
		if (volList!=null && volList.size()>0) {
			int rows = volList.size();
			BaseUser crtUser=ShiroSessionMgr.getLoginUser();
			if(crtUser==null){
				return false;
			}
			String crUser=crtUser.getUserName();
			for (Volunteer volunteer : volList) {
				Long volId=volunteerDao.selectVolId(volunteer.getIdcard());
				TrainVol trainVol =new TrainVol();
				trainVol.setTrainId(trainId);
				trainVol.setVolId(volId);
				trainVol.setCrUser(crUser);
				trainVol.setCrTime(new Date());
				int addResult=trainVolDao.insert(trainVol);
				if (addResult>0) {
					rows--;
				}
			}
			if(rows==0){
				return true;
			}
		}
		return false;
	}

	@Override
	public int updateVolsInfo(Volunteer vol) {
		//临时文件路径
		String temporary= ConfigUtils.getConfigContentByName("PIC_TEMPORARY_PATH");
		//保存路径
		String storage = ConfigUtils.getConfigContentByName("UPLOADPATH");
		if (vol != null && vol.getId() != null) {
			//得到数据库中的头像地址
			Volunteer preVolunteer=selectByKey(vol.getId());
			//修改后的头像地址
			String imgurl= vol.getImgUrl();
			String tempPicPath=temporary+File.separator+imgurl;
			String realPicPath=storage+File.separator+imgurl;
			logger.debug("保存数据库的头像相对地址为"+vol.getImgUrl());
			if(StringUtils.isNotBlank(imgurl)){
				vol.setImgUrl(imgurl.replaceAll("\\\\","\\\\\\\\"));
				String preImgurl=preVolunteer.getImgUrl();
				if(StringUtils.isNotBlank(preImgurl) && !preImgurl.equals(imgurl)) {
						//得到修改头像之前的头像地址
						String path=storage+File.separator+preImgurl;
						if(new File(path).exists()){
							//删除之前的头像文件
							FileUtils.deleteFiles(path);
						}
				}
				File tempFile=new File(tempPicPath);
				File realStorage=new File(storage);
				if(tempFile.exists() && tempFile.isFile() && realStorage.isDirectory() && realStorage.exists()){
					//将修改后的图片从临时路径复制到保存路径下
					FileUtils.copyFile(tempPicPath,realPicPath);
				}
			}else{
				vol.setImgUrl(null);
			}
			return updateNotNull(vol);
		}else{
			return 0;
		}
	}

	@Override
	public List<Statistics> statisticsShow() {
		List<Statistics> res= null;
		// 得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		// 判断用户是不是超级管理员
		boolean isAdmin = user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有一级区域的人数
			res=returnStcsVol();
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
						// 如果是省级管理机构，可以看到所有看到所有一级区域的人数.
						res=returnStcsVol();
					} else {
						Statistics statistics = new Statistics();
						statistics.setCurOrgId(key);
						res=volunteerDao.statisticsShow(statistics);
					}
				} else {
					// 如果是业务机构,可以看到本机构下的志愿者信息列表
					res= new ArrayList<Statistics>();
					Statistics statistics = new Statistics();
					statistics.setIstreatAgency(true);
					statistics.setCurOrgId(key);
					res.add(statistics);
//					res=volunteerDao.getVols(key, orderBy, page, rows);
				}
			}
		}
		return res;
	}

	@Override
	public List<Statistics> returnStcsVol() {
		List<Statistics> res= new ArrayList<Statistics>();
		List<Statistics> countyList= null;
		//城市名称
		String cityName="";
		//城市区域编码
		String cityCode="";
		//区,县级区域编码
		String countyCode="";
		//得到需匹配的市级名称及区域编码
		List<Area> cityList= areaDao.getAreas();
		Statistics statistics = new Statistics();
		//得到所有的区级志愿者信息集合
		countyList=volunteerDao.statisticsShow(statistics);
		for (Area area : cityList) {
			//志愿者人数
			int volNum=0;
			cityName=area.getName();
			cityCode=area.getCode().substring(0,4);
			for (Statistics stas : countyList) {
				countyCode=stas.getAreaCode().substring(0,4);
				if (cityCode.equals(countyCode)) {
					volNum+=stas.getVolNum();
				}
			}
			Statistics scs = new Statistics();
			scs.setAreaName(cityName);
			scs.setVolNum(volNum);
			res.add(scs);
		}
		return res;
	}

	@Override
	public PageInfo<Volunteer> getVols(Long orgId, String orderBy, int page, int rows) {
		return volunteerDao.getVols(orgId, orderBy, page, rows);
	}

	@Override
	public Statistics getVolInfo() {
		Statistics res = null;
		Statistics sts = new Statistics();
		// 得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		// 判断用户是不是超级管理员
		boolean isAdmin = user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有的人数信息
			res=volunteerDao.getVolInfo(sts);
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
						// 如果是省级管理机构，可以看到所有看到所有一级区域的人数.
						res=volunteerDao.getVolInfo(sts);
					} else {
						sts.setCurOrgId(key);
						res=volunteerDao.getVolInfo(sts);
					}
				} else {
					// 如果是业务机构,可以看到本机构下的志愿者信息列表
					sts.setOrgId(key);
					res=volunteerDao.getVolInfo(sts);
				}
			}
		}
		return res;
	}

	@Override
	public List<Statistics> getVolInfoList() {
		List<Statistics> res =null;
		Statistics sts = new Statistics();
		// 得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		// 判断用户是不是超级管理员
		boolean isAdmin = user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有的人数信息
			res=volunteerDao.getVolInfoList(sts);
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
						// 如果是省级管理机构，可以看到所有看到所有一级区域的人数.
						res=volunteerDao.getVolInfoList(sts);
					} else {
						sts.setCurOrgId(key);
						res=volunteerDao.getVolInfoList(sts);
					}
				} else {
					// 如果是业务机构,可以看到本机构下的志愿者信息列表
					sts.setOrgId(key);
					res=volunteerDao.getVolInfoList(sts);
				}
			}
		}
		return res;
	}

	@Override
	public PageInfo<Statistics> getVolStatis( String orderBy, int page, int rows) {
		PageInfo<Statistics> res=null;
		Statistics sts=new Statistics();
		// 得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		// 判断用户是不是超级管理员
		boolean isAdmin = user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有的人数信息
			res=volunteerDao.getVolStatis(sts, orderBy, page, rows);
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
						// 如果是省级管理机构，可以看到所有看到所有一级区域的人数.
						res=volunteerDao.getVolStatis(sts, orderBy, page, rows);
					} else {
						sts.setCurOrgId(key);
						res=volunteerDao.getVolStatis(sts, orderBy, page, rows);
					}
				} else {
					// 如果是业务机构,可以看到本机构下的志愿者信息列表
					sts.setOrgId(key);
					res=volunteerDao.getVolStatis(sts, orderBy, page, rows);
				}
			}
		}
		return res;
	}
	
}
