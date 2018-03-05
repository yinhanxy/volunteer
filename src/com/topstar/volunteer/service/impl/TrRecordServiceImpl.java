package com.topstar.volunteer.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.TrRecordDao;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.entity.OrgUser;
import com.topstar.volunteer.entity.TrRecord;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.service.OrgService;
import com.topstar.volunteer.service.OrgUserService;
import com.topstar.volunteer.service.TrRecordService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class TrRecordServiceImpl extends BaseServiceImpl<TrRecord> implements TrRecordService{

	@Autowired
	private TrRecordDao trRecordDao;
	
	@Autowired
	private OrgUserService orgUserService;
	
	@Autowired
	private OrgService orgService;
	
	@Override
	public PageInfo<TrRecord> findByEntity(TrRecord trRecord, int page, int rows) {
		String orderBy="tr.CR_TIME DESC";
		PageInfo<TrRecord> result=null;
		//得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		//判断用户是不是超级管理员
		boolean isAdmin= user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有培训记录.
			result = trRecordDao.findByEntity(trRecord, orderBy, page, rows);
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
						result = trRecordDao.findByEntity(trRecord, orderBy, page, rows);
					} 
					else {
						//显示本机构及下属机构
						trRecord.setCurOrgId(key);
						result = trRecordDao.findByEntity(trRecord, orderBy, page, rows);	
					}
				} else {
					//如果是业务机构，只能看到自己机构的数据。比如：如果是湖北省图书馆的，只能看到图书馆的数据。
					//根据机构Id查询得到当前机构下的所有的培训记录
					trRecord.setOrgId(key);
					result = trRecordDao.findByEntity(trRecord, orderBy, page, rows);
				}
			}
		}
		return result;
	}

	@Override
	public int existsWithTrRecordName(String name, Long teamId, String excludeKey) {
			Example example=new Example(TrRecord.class); 
			Criteria criteria=example.createCriteria();
			criteria.andEqualTo("trName", name);
			criteria.andEqualTo("teamId", teamId);
			if (StringUtils.isNotEmpty(excludeKey)) {
				criteria.andNotEqualTo("id", excludeKey);
			}
			List<TrRecord> trRecord= selectByExample(example);
			if(trRecord!=null && trRecord.size()!=0){
				return 1;
			}
			return 0;
	}

	@Override
	public boolean addTrRecord(TrRecord trRecord) {
		if (trRecord != null) {
			int addRow = insert(trRecord);
			if (addRow > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateTrRecord(TrRecord trRecord) throws TPSException {
		if(trRecord !=null && trRecord.getId()!=null){
			int updateRow=updateNotNull(trRecord);
			if(updateRow>0){
				return true;
			}
		}
		else{
			throw new TPSException("更新的培训信息参数不合法");
		}
		return false;
	}

	@Override
	public boolean deleteTrRecordKeys(long[] trRecordKeys) {
		if(trRecordKeys!=null && trRecordKeys.length>0){
			TrRecord trRecord=new TrRecord();
			int rows=trRecordKeys.length;
			int flag=0;
			for (int i = 0; i < trRecordKeys.length; i++) {
				trRecord.setId(trRecordKeys[i]);
				flag=deleteByKey(trRecord);
				if(flag>0){
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
	public PageInfo<TrRecord> getTrRecordsByVolunteerId(Long volunteerId,String orderBy,int page, int rows) {
		if(volunteerId!=null){
			return trRecordDao.findTrRecordsByVolunteerId(volunteerId,orderBy,page,rows);
		}
		return null;
	}

	@Override
	public PageInfo<Statistics> selTrRecordByYear(String orderBy,int page, int rows) {
		PageInfo<Statistics> result=null;
		Statistics statistics=new Statistics();
		//得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		//判断用户是不是超级管理员
		boolean isAdmin= user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有培训记录.
			result = trRecordDao.selTrRecordByYear(statistics,orderBy,page,rows);
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
						result = trRecordDao.selTrRecordByYear(statistics,orderBy,page,rows);
					} 
					else {
						//显示本机构及下属机构
						statistics.setCurOrgId(key);
						result = trRecordDao.selTrRecordByYear(statistics,orderBy,page,rows);	
					}
				} else {
					//如果是业务机构，只能看到自己机构的数据。比如：如果是湖北省图书馆的，只能看到图书馆的数据。
					//根据机构Id查询得到当前机构下的所有的培训记录
					statistics.setOrgId(key);
					result = trRecordDao.selTrRecordByYear(statistics,orderBy,page,rows);
				}
			}
		}
		return result;
	}

	@Override
	public PageInfo<TrRecord> getTrRecordStatis(String orderBy,int page, int rows) {
		PageInfo<TrRecord> result=null;
		TrRecord trRecord=new TrRecord();
		//得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		//判断用户是不是超级管理员
		boolean isAdmin= user.isAdmin();
		if (isAdmin) {
			//如果是超级管理员，可以看到所有培训记录.
			result = trRecordDao.getTrRecordStatis(trRecord,orderBy,page,rows);
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
						result = trRecordDao.getTrRecordStatis(trRecord,orderBy,page,rows);
					} 
					else {
						//显示本机构及下属机构
						trRecord.setCurOrgId(key);
						result = trRecordDao.getTrRecordStatis(trRecord,orderBy,page,rows);	
					}
				} else {
					//如果是业务机构，只能看到自己机构的数据。比如：如果是湖北省图书馆的，只能看到图书馆的数据。
					//根据机构Id查询得到当前机构下的所有的培训记录
					trRecord.setOrgId(key);
					result = trRecordDao.getTrRecordStatis(trRecord,orderBy,page,rows);
				}
			}
		}
		return result;
	}

}
