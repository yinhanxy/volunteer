package com.topstar.volunteer.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.TeamUserDao;
import com.topstar.volunteer.dao.UserDao;
import com.topstar.volunteer.entity.OrgUser;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.entity.TeamUser;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.service.OrgUserService;
import com.topstar.volunteer.service.TeamUserService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;

@Service
public class TeamUserServiceImpl extends BaseServiceImpl<TeamUser> implements TeamUserService{

	@Autowired
	private TeamUserDao teamUserDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OrgUserService orgUserService;

	@Override
	public PageInfo<User> findByTeamUser(User user,TeamUser teamUser, String orderBy, int page, int rows) {
		return teamUserDao.findByTeamUser(user,teamUser, orderBy, page, rows);
	}

	@Override
	public PageInfo<User> findByUserTeamID(User uesr, Long teamId, int page, int rows) {
		String orderBy="regTime DESC";
		return teamUserDao.findByUserTeamID(uesr, teamId, orderBy, page, rows);
	}

	@Override
	public boolean addUsersWithTeamId(Long teamId, List<Long> userIds) {
		if(teamId ==null || teamId<0){
			return false;
		}
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		if(crtUser==null){
			return false;
		}
		String crUser=crtUser.getUserName();
		if(userIds!=null && userIds.size()>0){
			int flag=userIds.size();
			for (Long userId : userIds) {
				TeamUser teamUser=new TeamUser();
				teamUser.setUserId(userId);
				teamUser.setTeamId(teamId);
				teamUser.setCrUser(crUser);
				teamUser.setCrTime(new Date());
				int addTeamUser=teamUserDao.insert(teamUser);
				if(addTeamUser>0){
					flag--;
				}
			}
			if(flag==0){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delTeamUser(List<Long> userIds, Long teamId) {
		if(teamId ==null || teamId<0){
			return false;
		}
		if(userIds!=null && userIds.size()>0){
			int flag=userIds.size();
			for (Long userId : userIds) {
				boolean delTeamUser=teamUserDao.delTeamUser(userId, teamId);
				if(delTeamUser){
					flag--;
				}
			}
			if(flag==0){
				return true;
			}
		}
		return false;
	}

	@Override
	public PageInfo<User> getUserTeams(User users, Long teamId ,int page, int rows) {
		PageInfo<User> result= null;
		String orderBy="";
		// 得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		// 判断用户是不是超级管理员
		boolean isAdmin = user.isAdmin();
		if (isAdmin) {
			// 如果是超级管理员,可以看到所有的用户列表信息
			result=userDao.getUsersBySer(users, orderBy, page, rows);
		} else {
			// 如果不是超级管理员。获取用户所在的机构，（机构分为业务机构和管理机构）
			OrgUser orgUser = orgUserService.getOrgUserByUserId(user.getId());
			// 如果普通用户没有机构，看不到数据。
			if (orgUser != null) {
				// 获取组织机构Id
				Long key = orgUser.getOrgId();
				result=userDao.getUsersByOrgId(key, users, page, rows);
			}
		}
		List<User> userList=result.getList();
		if(userList!=null && userList.size()>0){
			for (User u : userList) {
				List<Long> teamIds=teamUserDao.findTeamIdsByUserId(u.getId());
				if(teamIds!=null && teamIds.size()>0 &&  teamId!=null && teamIds.contains(teamId)){
					u.setTeamIdList(teamIds);
				}
			}
		}
		return result;
	}


	/**
	 * 根据用户ID获取服务队信息
	 * @param userId
	 * @return
	 */
	public SerTeam getSerTeamByUserId(Long userId){
		SerTeam res=null;
		// 得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		// 判断用户是不是超级管理员
		boolean isAdmin = user.isAdmin();
		if (isAdmin) {
			// 如果是超级管理员
			res = new SerTeam();
			res.setId(7l);
		}else{
			res=teamUserDao.findByUserId(userId);
		}
		return res;
	}
}
