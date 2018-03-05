package com.topstar.volunteer.shiro.realm;

import java.util.Arrays;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.service.UserService;

public class UserRealm extends AuthorizingRealm {
	
	private static Logger logger = LoggerFactory.getLogger(UserRealm.class);

	public static final String REALM_NAME = "userRealm";
	
	@Autowired
	private UserService userService;
	
	public String getName(){
		return REALM_NAME;
	}
	
	/**
	 * 获取用户的角色和权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		BaseUser baseUser = (BaseUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> roleNames = userService.findRoleNames(baseUser.getId());
		if(roleNames != null && !roleNames.isEmpty()){
			logger.debug("roleNames:"+Arrays.toString(roleNames.toArray()));
		}
		authorizationInfo.setRoles(roleNames);
		
		Set<String> permissions = userService.findPermissions(baseUser.getId());
        authorizationInfo.setStringPermissions(permissions);
        
        if(permissions != null && !permissions.isEmpty()){
			logger.debug("permissions:"+Arrays.toString(permissions.toArray()));
		}
        baseUser.setRoles(roleNames);
		baseUser.setPermissions(permissions);
        return authorizationInfo;
	}

	/**
	 * 认证用户信息，登录回调方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal();
		User user = userService.findByUserName(userName);
		if(user == null) {
            throw new UnknownAccountException("["+userName+"]用户不存在");//没找到帐号
        }
		BaseUser baseUser = new BaseUser(user);
		return new SimpleAuthenticationInfo(baseUser, user.getUserPwd(), getName());
	}

}
