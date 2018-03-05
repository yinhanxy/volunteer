package com.topstar.volunteer.shiro.credentials;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.topstar.volunteer.model.LockUser;
import com.topstar.volunteer.util.secrecy.MD5Util;

/**
 * 自定义密码校验类
 * 如在1个小时内密码最多重试5次，如果尝试次数超过5次就锁定1小时
 * @author Administrator
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

	private final static Logger logger = LoggerFactory.getLogger(CustomCredentialsMatcher.class);
	
    private Cache<String, LockUser> passwordRetryCache;

    public CustomCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
    	UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String userName = (String) token.getPrincipal();
        LockUser user = passwordRetryCache.get(userName);
        if(user == null) {
        	user = new LockUser();
        	user.setUserName(userName);
        	user.setRetryCount(new AtomicInteger(0));
        	user.setLockDate(new Date());
            passwordRetryCache.put(userName, user);
        }
        if(user.getRetryCount().incrementAndGet() > 5) {
        	user.setLockDate(new Date());
        	passwordRetryCache.put(userName, user);
        	logger.warn("用户[userName={}]密码连续输入错误超过"+user.getRetryCount()+"次，锁定半小时！",userName);
			throw new ExcessiveAttemptsException("用户名: " + userName + " 密码连续输入错误超过"+user.getRetryCount()+"次，锁定半小时！"); 
        }
        //登录输入的密码
        String password = new String((char[])token.getCredentials());
        //系统存储的密码
        String userPwd = (String) info.getCredentials();
        boolean matches = false;
        try {
        	matches = MD5Util.checkPassword(password, userPwd);
		} catch (Exception e) {
			logger.error("用户[userName={}]密码加密校验出错",userName);
		}
        if(matches) {
            passwordRetryCache.remove(userName);
        }
        return matches;
    }
}
