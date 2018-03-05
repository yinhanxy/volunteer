package com.topstar.volunteer.shiro.cache;

import java.io.Serializable;
import java.util.Map;

import com.topstar.volunteer.shiro.session.ShiroSession;

public interface ShiroSessionCache {

	public ShiroSession get(Serializable sessionId);
	
	public boolean add(Serializable sessionId,ShiroSession msession);
	
	public boolean remove(Serializable sessionId);
	
	public boolean isEmpty();
	
	public Map<Serializable,ShiroSession> getAll();
	
}
