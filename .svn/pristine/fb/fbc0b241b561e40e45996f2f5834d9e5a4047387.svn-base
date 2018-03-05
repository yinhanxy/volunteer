package com.topstar.volunteer.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.LockUser;
import com.topstar.volunteer.service.LockUserService;
import com.topstar.volunteer.shiro.cache.manager.SpringCacheManagerWrapper;
import com.topstar.volunteer.web.context.SpringContextHolder;

@Service
public class LockUserServiceImpl implements LockUserService {
	

	public PageInfo<LockUser> getList(String userName,int pageIndex,int pageSize){
		SpringCacheManagerWrapper cacheManager = (SpringCacheManagerWrapper) SpringContextHolder.getBean("shiroCacheManager");
		if(cacheManager == null){
			return null;
		}
		Cache<String, LockUser> passwordRetryCache = cacheManager.getCache("passwordRetryCache");
		if(passwordRetryCache == null){
			return null;
		}
		PageInfo<LockUser> page = new PageInfo<LockUser>();
		if(passwordRetryCache != null && !passwordRetryCache.keys().isEmpty()){
			List<LockUser> lockUsers = new ArrayList<LockUser>();
			Iterator<LockUser> lockUserIterator = passwordRetryCache.values().iterator();
			while(lockUserIterator.hasNext()){
				lockUsers.add(lockUserIterator.next());
			}
			
			if(StringUtils.isNotBlank(userName)){
				List<LockUser> tempUser = new ArrayList<LockUser>();
				for(LockUser user : lockUsers){
					String name = user.getUserName();
					if(name.indexOf(userName) != -1){
						tempUser.add(user);
					}
				}
				lockUsers = tempUser;
			}
			
			page.setTotal(lockUsers.size());
			
			int total = lockUsers.size();
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
			
			List<LockUser> users = new ArrayList<LockUser>();
			for(int i = start; i < lockUsers.size() && i < end;i++){
				LockUser user = lockUsers.get(i);
				if(user != null && user.getUserName() != null){
					users.add(user);
				}
			}
			page.setList(users);
			page.setSize(users.size());
		}
		return page;
	}
	
	/**
	 * 解锁用户
	 * @param userNames 用户名集合
	 * @return
	 */
	public int unlockUser(List<String> userNames){
		int row = 0;
		SpringCacheManagerWrapper cacheManager = (SpringCacheManagerWrapper) SpringContextHolder.getBean("shiroCacheManager");
		if(cacheManager == null){
			return row;
		}
		Cache<String, LockUser> passwordRetryCache = cacheManager.getCache("passwordRetryCache");
		if(passwordRetryCache == null){
			return row;
		}
		if(passwordRetryCache != null && !passwordRetryCache.keys().isEmpty()){
			if(userNames != null && !userNames.isEmpty()){
				for(String userName : userNames){
					try {
						passwordRetryCache.remove(userName);
						LoggerServer.info(ObjectType.LOCKUSER, OperateType.UNLOCKUSER, "解锁用户[userName="+userName+"]成功", 0L, userName, true);
					} catch (Exception e) {
						LoggerServer.error(ObjectType.LOCKUSER, OperateType.UNLOCKUSER, "解锁用户[userName="+userName+"]失败", 0L, userName);
						continue;
					}
					row ++;
				}
			}
		}
		return row;
	}
}
