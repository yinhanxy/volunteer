package com.topstar.volunteer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.LoginUser;
import com.topstar.volunteer.service.LoginUserService;
import com.topstar.volunteer.service.UserService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;

@Service
public class LoginUserServiceImpl implements LoginUserService {

	@Autowired
	private UserService userService;
	
	public PageInfo<LoginUser> getList(String userName,int pageIndex,int pageSize){
		PageInfo<LoginUser> page = new PageInfo<>();
		List<LoginUser> loginUsers = ShiroSessionMgr.getLoinUsers();
		if(loginUsers != null && !loginUsers.isEmpty()){
			
			if(StringUtils.isNotBlank(userName)){
				List<LoginUser> tempUser = new ArrayList<LoginUser>();
				for(LoginUser user : loginUsers){
					String name = user.getUserName();
					String nickName = user.getNickName();
					if(name.indexOf(userName) != -1 || nickName.indexOf(userName) != -1){
						tempUser.add(user);
					}
				}
				loginUsers = tempUser;
			}
			
			page.setTotal(loginUsers.size());
			
			int total = loginUsers.size();
			int maxPage = total % pageSize == 0 ? total / pageSize: total /pageSize +1;
			if(pageIndex > maxPage){
				pageIndex = maxPage;
			}
			
			page.setPageSize(pageSize);
			page.setPageNum(pageIndex);
			page.setTotal(total);
			page.setPages(maxPage);
			
			if(pageIndex == 1){
				page.setIsFirstPage(true);
				page.setHasPreviousPage(false);
				if(maxPage > 1){
					page.setHasNextPage(true);
				}else{
					page.setIsLastPage(true);
				}
			}else if(pageIndex == maxPage && maxPage > 1){
				page.setIsLastPage(true);
				page.setHasPreviousPage(true);
				page.setHasNextPage(false);
			}
			
			
			int start = pageSize * (pageIndex - 1);
			int end = pageSize * pageIndex;
			
			List<LoginUser> users = new ArrayList<LoginUser>();
			for(int i = start; i < loginUsers.size() && i < end;i++){
				LoginUser user = loginUsers.get(i);
				if(user != null && user.getId() != null){
					users.add(user);
				}
			}
			page.setList(users);
			page.setSize(users.size());
		}
		return page;
	}
	
	/**
	 * 强制下线用户
	 * @param userIds
	 * @return
	 */
	public int logoutUser(List<Long> userIds){
		int row = 0;
		BaseUser user = ShiroSessionMgr.getLoginUser();
		if(userIds != null && !userIds.isEmpty()){
			for(Long userId : userIds){
				if(user.getId().longValue() != userId.longValue()){
					try {
						userService.removeUserToCache(userId);
						ShiroSessionMgr.kickUserByUserId(userId,true);
						LoggerServer.info(ObjectType.LOGINUSER, OperateType.LOGOUTUSER, "强制下线用户[useName="+user.getUserName()+"]成功",user.getId(), user.getUserName(), true);
						row ++;
					} catch (Exception e) {
						LoggerServer.error(ObjectType.LOGINUSER, OperateType.LOGOUTUSER, "强制下线用户[useName="+user.getUserName()+"]出错，"+e.getMessage(),user.getId(), user.getUserName());
						continue;
					}
					
				}
			}
		}
		return row;
	}
}
