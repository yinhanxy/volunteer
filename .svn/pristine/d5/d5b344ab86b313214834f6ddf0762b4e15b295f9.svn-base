package com.topstar.volunteer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.RetreatTeamDao;
import com.topstar.volunteer.dao.VolunteerDao;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.entity.OrgUser;
import com.topstar.volunteer.entity.RetreatTeamDeal;
import com.topstar.volunteer.entity.TurnTeamDeal;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.RetreatTeamView;
import com.topstar.volunteer.model.TurnTeamView;
import com.topstar.volunteer.service.OrgService;
import com.topstar.volunteer.service.OrgUserService;
import com.topstar.volunteer.service.RetreatTeamService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;

@Service
public class RetreatTeamServiceImpl extends BaseServiceImpl<RetreatTeamDeal> implements RetreatTeamService {
	
	@Autowired
	private RetreatTeamDao retreatTeamDao;
	
	@Autowired
	private OrgUserService orgUserService;
	
	@Autowired
	private VolunteerDao volunteerDao;

	@Autowired
	private OrgService orgService;

	@Override
	public PageInfo<RetreatTeamView> getRetreatTeamApplysByEntity(Volunteer volunteer, String orderBy, int pageIndex,
			int pageSize) {
		PageInfo<RetreatTeamView> result =null;
		// 得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		// 判断用户是不是超级管理员
		boolean isAdmin = user.isAdmin();
		if (isAdmin) {
			// 如果是超级管理员，可以看到所有退队记录.
			result = retreatTeamDao.findRetreatTeamApplysByEntity(volunteer, orderBy, pageIndex, pageSize);
		}else{
			// 如果不是超级管理员。获取用户所在的机构，（机构分为业务机构和管理机构）
			OrgUser orgUser = orgUserService.getOrgUserByUserId(user.getId());
			// 如果用户没有机构，看不到数据。
			if (orgUser != null) {
				// 获取组织机构Id
				Long key = orgUser.getOrgId();
				// 得到org对象
				Org org = orgService.selectByKey(key);
				// 获取机构类型
				Integer orgType = org.getType();
				if (orgType == 1) {
					// 如果是管理机构，可以看到所管辖的所有机构的发布的数据。比如是文化厅的，可以看到所有数据。如果是武汉市文化局的，可以看到本市的所有数据。如果是XXX县的文化局，可以看到本县的数据。
					volunteer.setCurOrgId(key);
					result = retreatTeamDao.findRetreatTeamApplysByEntity(volunteer, orderBy, pageIndex, pageSize);
				}else {
					// 如果是业务机构，只能看到自己机构的数据。比如：如果是湖北省图书馆的，只能看到图书馆的数据。
					volunteer.setOrgId(key);
					result = retreatTeamDao.findRetreatTeamApplysByEntity(volunteer, orderBy, pageIndex, pageSize);
				}
			}
		}
		return result;
	}

	@Override
	public TurnTeamView getRetreatTeamApplyByVolunteerId(Long volunteerId) {
		return retreatTeamDao.findRetreatTeamApplyByVolunteerId(volunteerId);
	}

	@Override
	public TurnTeamDeal getRetreatTeamResultByVolunteerId(Long volunteerId) {
		return retreatTeamDao.findRetreatTeamResultByVolunteerId(volunteerId);
	}

	@Override
	public RetreatTeamView getRetreatTeamByVolunteerId(Long volunteerId) {
		return retreatTeamDao.getRetreatTeamByVolunteerId(volunteerId);
	}

	@Override
	public int editVolAndRetreatResult(RetreatTeamDeal retreatTeamDeal) throws Exception {
		String dealStatus=retreatTeamDeal.getDealResult();
		if (retreatTeamDeal!=null && dealStatus!=null) {
			Long dStatus=null;
			try{
				dStatus=Long.valueOf(dealStatus);
			}catch (Exception e) {
				throw new Exception("查看指定志愿者退队申请信息参数dealStatus不合法");
			}
			int dealRes=retreatTeamDao.updateNotNull(retreatTeamDeal);
			int volRes=0;
			if("1".equals(dealStatus)){
				volRes=volunteerDao.editRetreatTeamStatus(retreatTeamDeal.getVolId(), 5l);
			}else{
				volRes=volunteerDao.editRetreatTeamStatus(retreatTeamDeal.getVolId(), 2l);
			}
			return dealRes>volRes?volRes:dealRes;
		}
		return 0;
	}
	
}
