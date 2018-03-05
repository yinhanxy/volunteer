package com.topstar.volunteer.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.hyperic.sigar.CurrentProcessSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.cache.ChannelCache;
import com.topstar.volunteer.cache.LoginUserCache;
import com.topstar.volunteer.cache.RoleCache;
import com.topstar.volunteer.dao.ChannelDao;
import com.topstar.volunteer.dao.DocumentDao;
import com.topstar.volunteer.dao.RoleChannelDao;
import com.topstar.volunteer.dao.SiteDao;
import com.topstar.volunteer.dao.UserChannelDao;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.entity.Site;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.ChannelView;
import com.topstar.volunteer.service.ChannelService;
import com.topstar.volunteer.service.UserService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;

@Service
public class ChannelServiceImpl  extends BaseServiceImpl<Channel> implements ChannelService{

	@Autowired
	private ChannelDao channelDao;
	
	@Autowired
	private SiteDao siteDao;
	
	@Autowired
	private ChannelCache channelCache;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleCache roleCache;

	@Autowired
	private LoginUserCache userCache;
	
	@Autowired
	private RoleChannelDao roleChannelDao;
	
	@Autowired
	private UserChannelDao userChannelDao;
	
	@Autowired
	private DocumentDao documentDao;

	@Override
	public Channel selectByKey(Object key) {
		if(key instanceof Long){
			return channelCache.findById((Long) key);
		}else{
			return channelDao.selectByKey(key);
		}
	}

	/**
	 * 获取所有站点下的顶级栏目
	 * @return
	 */
	public List<ChannelView> findTopChannelBySiteId(){
//		List<Site> sites=siteDao.selectAllSite();
//		List<Channel> channels=new ArrayList<Channel>();
//		if(sites!=null && sites.size()>0){
//			for (Site site : sites) {
//				channels=channelDao.findTopChannelBySiteId(site.getId());
//				if(channels!=null){
//					Collections.sort(channels);
//				}
//			}
//		}
//		if(channels!=null && channels.size()>0){
//			return ChannelToChannelViews(channels);
//		}
		List<Channel> list = null;
		BaseUser baseUser = ShiroSessionMgr.getLoginUser();
		if(baseUser != null){
			if(baseUser.isAdmin()){
				List<Channel> channels = channelCache.getAllNotInRecycle();
				if(channels != null && !channels.isEmpty()){
					list = new ArrayList<Channel>();
					for(Channel channel : channels){
						if(channel.getParentId().longValue() == 0 && channel.getStatus().intValue()  == 1){
							list.add(channel);
						}
					}
				}
			}else{
				User user = userCache.findById(baseUser.getId());
				if(user != null){
					List<Long> channelIds = new ArrayList<Long>();
					
					///获取用户对应角色可见的栏目ID
					List<Long> roleIds = user.getRoleIdList();
					if(roleIds != null){
						for(Long roleId : roleIds){
							List<Long> tempChannelIds = roleCache.getChannelIds(roleId);
							if(tempChannelIds != null && !tempChannelIds.isEmpty()){
								channelIds.addAll(tempChannelIds);
							}
						}
					}
					 
					//获取用户本身可见的栏目ID
					 List<Long> userChannelIds = user.getChannelIds();
					 if(userChannelIds != null && !userChannelIds.isEmpty()){
						 channelIds.addAll(userChannelIds);
					 }
					 
					 //去除重复栏目ID
					 if(channelIds != null && !channelIds.isEmpty()){
						 for(int i=0 ;i<channelIds.size()-1;i++){
							 for(int j=channelIds.size()-1;j>i;j--){
								 if(channelIds.get(j).equals(channelIds.get(i))){
									 channelIds.remove(j);
						        }
						    }
						}
					 }
					 
					 if(channelIds != null && !channelIds.isEmpty()){
						 List<Channel> channels = new ArrayList<Channel>();
						 for(Long channelId : channelIds){
							 Channel channel = channelCache.findById(channelId);
							 if(channel != null && channel.getStatus().longValue() == 1){
								 channels.add(channel.clone());
							 }
						 }
						 list = new ArrayList<Channel>();
						 if( !channels.isEmpty()){
								Map<Long, Channel> map = new TreeMap<Long,Channel>();
								for(Channel channel :channels){
									if(channel != null){
										map.put(channel.getId(), channel);
									}
								}
								for(Channel channel : channels){
									Long parentId = channel.getParentId();
									Long tempParentId = parentId;
									
									while(tempParentId.longValue() > 0L && (map.get(tempParentId) == null ||
											map.get(tempParentId).getStatus().intValue() != 1)){
										Channel parentChannel = channelCache.findById(tempParentId);
										tempParentId = parentChannel.getParentId();
									}
									
									if(tempParentId.longValue() == 0L){
										list.add(channel.clone());
									}
								}
							}
					 }
				}
			}
		}
		
		List<ChannelView> channelViews = new ArrayList<ChannelView>();
		if(list != null && !list.isEmpty()){
			Collections.sort(list);
			for(Channel channel : list){
				ChannelView view = new ChannelView(channel);
				channelViews.add(view);
			}
		}
		return channelViews;
	}
	
	@Override
	public List<ChannelView> getAllChannelBySiteId() {
		List<ChannelView> allChannels=new ArrayList<ChannelView>();
		List<Site> sites=siteDao.selectAllSite();
		for (Site site : sites) {
			allChannels.add(new ChannelView(site.getId(), -1l, site.getSiteDesc(), true,true,true));
			List<Channel> channels=channelDao.findAllChannelsBySite(site.getId());
			allChannels.addAll(ChannelToChannelViews(channels));
		}
		return allChannels;
	}
	
	/**
	 * 获取站点下的所有栏目
	 * @return
	 */
	public List<ChannelView> getAllChannelBySiteIdInCache(){
		List<ChannelView> allChannels=new ArrayList<ChannelView>();
		List<Site> sites=siteDao.selectAllSite();
		for (Site site : sites) {
			allChannels.add(new ChannelView(0L-site.getId(), 0l, site.getSiteDesc(), true,true,true));
			List<Channel> channels= userService.getAllChannelNotInRecycle(site.getId());
			if(channels != null && !channels.isEmpty()){
				allChannels.addAll(ChannelToChannelViews(channels));
			}
		}
		return allChannels;
	}

	@Override
	public List<Channel> getParentChannelsByChannelId(Long channelId) {
		//TODO 获取指定栏目的父级栏目(包含自身,站点)
		//无法包含站点信息，请通过返回的栏目信息查询对应的站点信息
		List<Channel> channels = userService.getAllChannelNotInRecycle();
		if(channels != null && !channels.isEmpty()){
			List<Channel> list = new ArrayList<Channel>();
			Map<Long, Channel> map = new TreeMap<Long,Channel>();
			for(Channel channel :channels){
				if(channel != null){
					map.put(channel.getId(), channel);
				}
			}
			
			Channel currentChannel = map.get(channelId);
			if(currentChannel != null && currentChannel.getStatus().intValue() == 1){
				list.add(currentChannel);
				Long parentId = currentChannel.getParentId();
				if(parentId.longValue() > 0L){
					Channel parentChannel = map.get(parentId);
					while(parentChannel != null && parentChannel.getStatus().intValue() == 1){
						list.add(parentChannel.clone());
						parentId = parentChannel.getParentId();
						if(parentId.longValue() > 0){
							parentChannel = map.get(parentId);
						}else{
							break;
						}
					}
				}
				/*if(list != null && list.size() > 1){
					List<Channel> channelList = new ArrayList<Channel>();
					sortList(channelList,list,0);
					return channelList;
				}*/
				return list;
			}
		}
		return null;
	}

	/**
	 * 将栏目集合按父子栏目顺序排序
	 * @param resultList
	 * @param list
	 * @param id
	 */
	 private void sortList(List<Channel> resultList,List<Channel> list,long id) {  
        for (Channel channel :list) {
            if (channel.getParentId().longValue() == id) {
                resultList.add(channel);
                sortList(resultList,list,channel.getId());
            }
        }
    }
	 
	@Override
	public List<ChannelView> getChannelMoveTree(Long channelId) {
		List<Channel> parentChannels=new ArrayList<Channel>();
		List<Channel> allChannels=new ArrayList<Channel>();
		List<ChannelView> allChannelViews=new ArrayList<ChannelView>();
		Channel currentChannel=channelDao.selectByKey(channelId);
		if(currentChannel!=null){
			Site site=siteDao.selectByKey(currentChannel.getSiteId());
			if(site!=null){
				Channel siteChannel=new Channel(0L-site.getId(),site.getId(), 0l);
				//获取该栏目下所有的上级栏目
				List<Channel> channels =getParentChannelsByChannelId(channelId);
				if(channels!=null && !channels.isEmpty()){
					parentChannels.addAll(channels);
				}
				parentChannels.add(siteChannel);
				
				//获取该栏目所在的站点下的所有栏目
				/*allChannels.addAll(channelDao.findAllChannelsBySite(site.getId()));*/
				allChannels.addAll(userService.getAllChannelNotInRecycle(site.getId()));
				
				allChannelViews.add(new ChannelView(0L-site.getId(), 0l, site.getSiteDesc(), true,true,true));
				allChannelViews.addAll(ChannelToChannelViews(allChannels));
				
			}
		}
		Iterator<ChannelView> iterator=allChannelViews.iterator();
		//获取channelID下的所有子栏目节点id
		List<Long> childerenChannelIds=new ArrayList<Long>();
				this.findChildrenChannelsByChannelId(childerenChannelIds,channelId);
		while(iterator.hasNext()){
			ChannelView channelView=iterator.next();
			
			if(childerenChannelIds!=null && !childerenChannelIds.isEmpty()){
				if(childerenChannelIds.contains(channelView.getId())){
					iterator.remove();
					continue;
				}
			}
			if(channelView.getId().equals(parentChannels.get(0).getId())){
				channelView.setParent(false);
				channelView.setChkDisabled(true);
			}
			if(channelView.getId().equals(parentChannels.get(1).getId())){
				channelView.setParent(true);
				channelView.setOpen(true);
				channelView.setChkDisabled(true);
			}
		}
		return allChannelViews;
	}


	@Override
	public List<ChannelView> findChannelByParentId(Long parentId) {
		/*if(parentId!=null){
			List<Channel> channels=channelDao.findChannelByParentId(parentId);
			if(channels!=null && !channels.isEmpty()){
				channelViews=ChannelToChannelViews(channels);
			}
		}*/
		
		//TODO 获取指定栏目下用户可访问的直接子栏目列表
		List<Channel> channels = userService.getAllChannelNotInRecycle();
		List<ChannelView> channelViews = new ArrayList<ChannelView>();
		if(channels != null && !channels.isEmpty()){
			for(Channel channel : channels){
				if(channel.getParentId().longValue() == parentId.longValue() && channel.getStatus().intValue() == 1){
					ChannelView view = new ChannelView(channel);
					channelViews.add(view);
				}
			}
			if(channelViews!=null && !channelViews.isEmpty()){
				Collections.sort(channelViews);
			}
		}
		return channelViews;
	}

	@Override
	public PageInfo<Channel> findByChannelName(String channelName, String orderBy, int pageIndex, int pageSize) {
		return channelDao.findByChannelName(channelName, orderBy, pageIndex, pageSize);
	}

	@Override
	public List<Channel> getBroChannelsByParentId(Channel channel) {
		
		/*if (channel!=null) {
			Example example=new Example(Channel.class); 
			Criteria criteria=example.createCriteria();
			criteria.andEqualTo("parentId", channel.getParentId());
			criteria.andNotEqualTo("id", channel.getId());
			List<Channel> channels=selectByExample(example);
			if(channels!=null){
				Collections.sort(channels);
				return channels;
			}
		}*/
		
		//TODO 获取指定栏目中用户可访问的同父级的直接子栏目结构,兄弟栏目,不包括自己
		List<Channel> channels = userService.getAllChannelNotInRecycle();
		List<Channel> list = new ArrayList<Channel>();
		if(channels != null && !channels.isEmpty()){
			Map<Long, Channel> map = new TreeMap<Long,Channel>();
			for(Channel tempChannel :channels){
				if(tempChannel != null){
					map.put(tempChannel.getId(), tempChannel);
				}
			}
			Channel currentChannel = map.get(channel.getId());
			if(currentChannel != null && currentChannel.getStatus().intValue() == 1){
				Long parentId = currentChannel.getParentId();
				Channel parentChannel = map.get(parentId);
				boolean existParent = false;
				if(parentId.longValue() > 0L  && parentChannel != null && parentChannel.getStatus().intValue() == 1 ){
					existParent = true;
				}
				if(existParent){
					for(Channel tempChannel : channels){
						if(tempChannel.getParentId().longValue() == parentId.longValue()){
							if(tempChannel.getId().longValue() != channel.getId().longValue()){
								list.add(tempChannel);
							}
						}
					}
				}else{
					for(Channel tempChannel : channels){
						if(tempChannel.getParentId().longValue() == 0L){
							if(tempChannel.getId().longValue() != channel.getId().longValue()){
								list.add(tempChannel);
							}
						}
					}
				}
				if(list.size() > 1){
					Collections.sort(list);
				}
				return list;
			}
		}
		return null;
	}

	@Override
	public PageInfo<Channel> findSubChannelsByParentId(Long parentId, String channelCondition,String orderBy, int pageIndex, int pageSize) {
		return channelDao.findSubChannelsByParentId(parentId,channelCondition, orderBy, pageIndex, pageSize);
	}
	
	@Override
	public void getAllChannelsListByParentId(Long parentId,List<Channel> channelList,Long exculdeSpecialChannelId){
		if(channelList!=null){
			if(parentId!=null){
				if(exculdeSpecialChannelId!=null && parentId.equals(exculdeSpecialChannelId)){
					return ;
				}
				List<Channel> channels=channelDao.findChannelByParentId(parentId);
				if(channels!=null && channels.size()>0){
					Collections.sort(channels);
					for (Channel channel : channels) {
						channelList.add(channel);
						getAllChannelsListByParentId(channel.getId(),channelList,exculdeSpecialChannelId);
					}
					return ;
				}else{
					return ;
				}
			}
		}
		return ;
	}
	
	@Override
	public void getAllChannelsListByRecycleParentId(Long parentId,List<Channel> channelList){
		if(channelList!=null){
			if(parentId!=null){
				List<Channel> channels=channelDao.findChannelByRecycleParentId(parentId);
				if(channels!=null && channels.size()>0){
					for (Channel channel : channels) {
						channelList.add(channel);
						getAllChannelsListByRecycleParentId(channel.getId(),channelList);
					}
					return ;
				}else{
					return ;
				}
			}
		}
		return ;
	}
	
	@Override
	public void getAllStateChannelsListByParentId(Long parentId, List<Channel> channelList) {
		if(channelList!=null){
			if(parentId!=null){
				List<Channel> channels=channelDao.findAllStateChannelByParentId(parentId);
				if(channels!=null && channels.size()>0){
					for (Channel channel : channels) {
						channelList.add(channel);
						getAllStateChannelsListByParentId(channel.getId(),channelList);
					}
					return ;
				}else{
					return ;
				}
			}
		}
		return ;
	}

	/**
	 * 获取指定栏目的所有直接子栏目列表
	 * @param parentId 上级栏目id
	 * @return
	 */
	@Override
	public List<ChannelView> getChannelViewsByParentId(Long parentId){
		List<ChannelView> channelViewList=new ArrayList<ChannelView>();
		Channel channel=selectByKey(parentId);
		ChannelView currChannelView=null;
		if(channel!=null){
			currChannelView=new ChannelView(channel);
		}
		List<ChannelView> channelViews=null;
		if(parentId!=null){
			List<Channel> channels=channelDao.findChannelByParentId(parentId);
			if(channels!=null && currChannelView!=null){
				currChannelView.setParent(true);
			}
			channelViews=ChannelToChannelViews(channels);
			for (ChannelView subChannelView : channelViews) {
				List<Channel> subChannelViews=channelDao.findChannelByParentId(subChannelView.getId());
				if(subChannelViews!=null){
					subChannelView.setParent(true);
				}
			}
		}
		if(currChannelView!=null){
			channelViewList.add(currChannelView);
		}
		if(channelViews!=null){
			channelViewList.addAll(channelViews);
		}
		return channelViewList;
	}

	/**
	 * 获取所有站点下的顶级栏目
	 * @return
	 */
	public List<ChannelView> getSitesBySiteIdIncTopChannels(){
		List<Site> sites=siteDao.selectAllSite();
		
		List<ChannelView> channelViews=new ArrayList<ChannelView>();
		if(sites!=null && sites.size()>0){
			ChannelView siteChannelView=null;
			List<Channel> channels=new ArrayList<Channel>();
			List<ChannelView> topChannelViews=null;
			for (Site site : sites) {
				siteChannelView=new ChannelView(site.getId(), -1l, site.getSiteDesc(), false,false,true);
				channels=channelDao.findTopChannelBySiteId(site.getId());
				if(channels!=null && channels.size()>0){
					siteChannelView.setParent(true);
					siteChannelView.setOpen(true);
					topChannelViews=ChannelToChannelViews(channels);
					
				}
				if(siteChannelView!=null){
					channelViews.add(siteChannelView);
				}
				if(topChannelViews!=null){
					Collections.sort(topChannelViews);
					for (ChannelView channelView : topChannelViews) {
						channelView.setParent(true);
					}
					channelViews.addAll(topChannelViews);
				}
			}
			
		}
		if(channelViews!=null && channelViews.size()>0){
			return channelViews;
		}
		return null;
	}
	
	@Override
	public Map<String,Object> addChannel(Channel channel,Long previousId) {
		
		Long orderNo=null;
		Channel previousChannel=selectByKey(previousId);
		Long currOrder=-1l;
		Map<String,Object> addResult=new HashMap<String,Object>();
		addResult.put("result",false);
		try{
			if(previousId==-1){//移动到最前面
				channel.setOrder(1l);
				currOrder=1l;
				channelDao.moveForwardChannel(orderNo, previousId, channel.getParentId());
			}else{//向前移动
				currOrder=previousChannel.getOrder()+1;
				channel.setOrder(previousChannel.getOrder()+1);
				channelDao.moveForwardChannel(orderNo, previousChannel.getOrder(), channel.getParentId());
			}
			addResult.put("order", currOrder);
			int addChannel=channelDao.insert(channel);
			if(addChannel>0){
				//添加到缓存中
				channelCache.add(channel);
				addResult.put("result",true);
				addResult.put("channelId",channel.getId());
				return addResult;
			}
		}catch (Exception e) {
			throw new RuntimeException("添加栏目操作出错",e);
		}
		return addResult;
	}

	@Override
	public boolean saveChannelRecycle(Long channelId) {
		
		int flag=0;
		List<Channel> subChannels=new ArrayList<Channel>();
		try{
			if(channelId!=null){
				Channel currChannel=selectByKey(channelId);
				
				BaseUser crtUser=ShiroSessionMgr.getLoginUser();
				currChannel.setOperUser(crtUser.getUserName());
				currChannel.setOperTime(new Timestamp(new Date().getTime()));
				currChannel.setStatus(2);
				int delCurrChannel=updateNotNull(currChannel);
				if(delCurrChannel>0){
					//修改栏目状态，放到回收站
					channelCache.update(currChannel);
					
					if(channelDao.moveBackwardChannel(currChannel.getOrder(),null,currChannel.getParentId())<0){
						return false;
					}
					
					getAllChannelsListByParentId(currChannel.getId(),subChannels,null);
					if(subChannels!=null && subChannels.size()>0){
						flag=subChannels.size();
						int delChannel=0;
						for (Channel channel : subChannels) {
							channel.setOperUser(crtUser.getUserName());
							channel.setOperTime(new Timestamp(new Date().getTime()));
							channel.setStatus(3);
							delChannel=updateNotNull(channel);
							if(delChannel>0){
								channelCache.update(channel);
								flag--;
							}
						}
					}
				}else{
					return false;
				}
			}
		}catch (Exception e) {
			throw new RuntimeException("将栏目移动回收站操作出错",e);
		}
		return flag>0? false:true;
	}

	@Override
	public boolean delChannelAndSubChnnel(Long[] delChnnelIds) {
		int flag=0;
		List<Channel> subChannels=new ArrayList<Channel>();
		try{
			if(delChnnelIds!=null && delChnnelIds.length>0){
				for (int i = 0; i < delChnnelIds.length; i++) {
					Long delChnnelId = delChnnelIds[i];
					Channel currChannel=selectByKey(delChnnelId);
					int delCurrChannel=deleteByKey(currChannel.getId());
					if(delCurrChannel>0){
						//从缓存中删除栏目
						channelCache.delete(currChannel.getId());
						//将用户和角色可见的栏目关联信息删除
						roleChannelDao.deleteByChannelId(currChannel.getId());
						userChannelDao.deleteByChannelId(currChannel.getId());
						//删除栏目下的文档
						documentDao.deleteByChannelId(currChannel.getId());
						
						getAllStateChannelsListByParentId(delChnnelId,subChannels);
						if(subChannels!=null && subChannels.size()>0){
							flag=subChannels.size();
							int delChannel=0;
							for (Channel channel : subChannels) {
								delChannel=deleteByKey(channel.getId());
								if(delChannel>0){
									//从缓存中删除栏目
									channelCache.delete(channel.getId());
									//将用户和角色可见的栏目关联信息删除
									roleChannelDao.deleteByChannelId(channel.getId());
									userChannelDao.deleteByChannelId(channel.getId());
									//删除栏目下的文档
									documentDao.deleteByChannelId(channel.getId());
									
									flag--;
								}
							}
							if(flag>0){
								return false;
							}
						}
					}else{
						return false;
					}
				}
				
			}
		}catch (Exception e) {
			throw new RuntimeException("彻底删除栏目操作出现错误",e);
		}
		return true;
	}
	
	@Override
	public boolean restoreChannelAndSubChnnel(Long[] restoreChnnelIds) {
		int flag=0;
		List<Channel> subChannels=new ArrayList<Channel>();
		try{
			if(restoreChnnelIds!=null && restoreChnnelIds.length>0){
				for (int i = 0; i < restoreChnnelIds.length; i++) {
					Long restoreChnnelId = restoreChnnelIds[i];
					Channel currChannel=selectByKey(restoreChnnelId);
					
					if(channelDao.moveForwardChannel(null,currChannel.getOrder()-1,currChannel.getParentId())<0){
						return false;
					}
					BaseUser crtUser=ShiroSessionMgr.getLoginUser();
					currChannel.setOperUser(crtUser.getUserName());
					currChannel.setOperTime(new Timestamp(new Date().getTime()));
					currChannel.setStatus(1);
					int restoreCurrChannel=updateNotNull(currChannel);
					
					if(restoreCurrChannel>0){
						//还原栏目，更新缓存
						currChannel = selectByKey(currChannel.getId());
						channelCache.update(currChannel);
						getAllChannelsListByRecycleParentId(restoreChnnelId,subChannels);
						if(subChannels!=null && subChannels.size()>0){
							flag=subChannels.size();
							int restoreChannel=0;
							for (Channel channel : subChannels) {
								channel.setOperUser(crtUser.getUserName());
								channel.setOperTime(new Timestamp(new Date().getTime()));
								channel.setStatus(1);
								restoreChannel=updateNotNull(channel);
								if(restoreChannel>0){
									//还原栏目，更新缓存
									channelCache.update(channel);
									flag--;
								}
							}
							if(flag>0){
								return false;
							}
						}
					}else{
						return false;
					}
				}
			}
		}catch (Exception e) {
			throw new RuntimeException("还原栏目操作出现错误",e);
		}
		return true;
	}

	@Override
	public boolean moveChannelToChannel(Long srcChannelId, Long targetChannelId) {
		Channel currChannel=selectByKey(srcChannelId);
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		currChannel.setOperUser(crtUser.getUserName());
		currChannel.setOperTime(new Timestamp(new Date().getTime()));
		if(targetChannelId.longValue()<0){
			targetChannelId=0l;
		}
		currChannel.setParentId(targetChannelId);
		Long orderNo=channelDao.getMaxOrderNoByParentId(targetChannelId);
		if(orderNo!=null){
			currChannel.setOrder(orderNo.longValue()+1l);
		}
		try{
			int moveCurrChannel=updateNotNull(currChannel);
			if(moveCurrChannel>0){
				//更新缓存
				channelCache.update(currChannel);
				return true;
			}
		}catch (Exception e) {
			throw new RuntimeException("将id="+srcChannelId+"的栏目移动到id="+targetChannelId+"的栏目下的操作出现错误",e);
		}
		return false;
	}

	@Override
	public Map<String,Object> saveChannel(Channel channel,Long previousId) {
		Long orderNo=channel.getOrder();
		Channel previousChannel=selectByKey(previousId);
		Long currOrder=-1l;
		Map<String,Object> saveResult=new HashMap<String,Object>();
		saveResult.put("result",false);
		try{
			if(previousId==-1){//移动到最前面
				channel.setOrder(1l);
				currOrder=1l;
				channelDao.moveForwardChannel(orderNo, previousId, channel.getParentId());
			}else if(previousChannel.getOrder()>orderNo){//向后移动
				channel.setOrder(previousChannel.getOrder());
				currOrder=previousChannel.getOrder();
				channelDao.moveBackwardChannel(orderNo, previousChannel.getOrder(), channel.getParentId());
			}else{//向前移动
				currOrder=previousChannel.getOrder()+1;
				channel.setOrder(previousChannel.getOrder()+1);
				channelDao.moveForwardChannel(orderNo, previousChannel.getOrder(), channel.getParentId());
			}
			
			saveResult.put("order", currOrder);
			int saveChannel=channelDao.updateNotNull(channel);
			if(saveChannel>0){
				channelCache.update(channel);
				saveResult.put("result",true);
				return saveResult;
			}
		}catch (Exception e) {
			throw new RuntimeException("保存栏目操作出错", e);
		}
		return saveResult;
	}

	/**
	 * 获取当前栏目下的所有上级栏目
	 * @param channel 当前栏目
	 * @return
	 */
	@Override
	public void getParentChannelsWithCurrChannel(Channel channel,List<Channel> channels){
		if(channel!=null){
			Long siteId=channel.getSiteId();
			Long parentId=channel.getParentId();
			if(parentId.equals(0l)){
				Site site=siteDao.selectByKey(siteId);
				if(site!=null){
					channels.add(new Channel(site.getId(),site.getId(),-1l));
				}
				return ;
			}
			Channel parentChannel=selectByKey(parentId);
			if(parentChannel!=null){
				channels.add(parentChannel);
				getParentChannelsWithCurrChannel(parentChannel,channels);
				return ; 
			}
				
		}
		return ;
	}
	
	/**
	 * 将指定的栏目实体列表转换成用户页面展示的栏目实体列表
	 * @param org
	 * @return
	 */
	private List<ChannelView> ChannelToChannelViews(List<Channel> channels){
		if(channels!=null && channels.size()>0){
			List<ChannelView> channelViews=new ArrayList<ChannelView>();
			for (Channel channel : channels) {
				ChannelView channelView=new ChannelView(channel);
				channelViews.add(channelView);
			}
			return channelViews;
		}
		return null;
		
	}
	
	public List<ChannelView> getAllChannels(Long channelId){
		List<ChannelView> channels=new ArrayList<ChannelView>();
		List<ChannelView> topChannels=getSitesBySiteIdIncTopChannels();
		List<Channel> subChannel=new ArrayList<Channel>();
		if(topChannels!=null && topChannels.size()>0){
			for (ChannelView topChannel : topChannels) {
				channels.add(topChannel);
				if(topChannel.getId().equals(channelId)){
					continue;
				}
				if(topChannel.getpId()!=-1){
					getAllChannelsListByParentId(topChannel.getId(), subChannel,channelId);
					if(subChannel!=null && subChannel.size()>0){
						List<ChannelView> subChannelView=ChannelToChannelViews(subChannel);
						for (ChannelView channelView : subChannelView) {
							channelView.setOpen(false);
						}
						channels.addAll(subChannelView);
					}
					subChannel.clear();
				}
			}
		}
		Channel currChannel=selectByKey(channelId);
		List<Channel> parentChannels=new ArrayList<Channel>();
		if(currChannel!=null){
			getParentChannelsWithCurrChannel(currChannel, parentChannels);
		}
		List<Long> parentChannelIds=new ArrayList<Long>();
		if(parentChannels!=null && parentChannels.size()>0){
			for (Channel channel : parentChannels) {
				parentChannelIds.add(channel.getId());
			}
			for (ChannelView channel : channels) {
				if(parentChannelIds.contains(channel.getId())){
					if(0==parentChannelIds.indexOf(channel.getId())){
						channel.setChkDisabled(true);
					}
					channel.setOpen(true);
				}
				if(currChannel!=null && currChannel.getId().equals(channel.getId())){
					channel.setParent(false);
					channel.setChkDisabled(true);
				}
			}
		}
		return channels;
	}
	
	@Override
	public PageInfo<Channel> getRecycleChannels(Long channelId,String channelName, String orderBy, int pageIndex, int pageSize) {
		//TODO 获取指定栏目下用户可访问的状态为2的直接子栏目信息
		//从缓存中读取，无法使用orderBy 字段排序
		//return channelDao.getRecycleChannels(channelId,channelName, orderBy, pageIndex, pageSize);
		List<Channel> childrenChannels = new ArrayList<Channel>();
		BaseUser baseUser = ShiroSessionMgr.getLoginUser();
		if(baseUser != null){
			if(baseUser.isAdmin()){
				List<Channel> list = channelCache.getAll(2);
				for(Channel tempChannel : list){
					if(tempChannel.getParentId().longValue() == channelId.longValue()){
						if(StringUtils.isNotBlank(channelName)){
							if(tempChannel.getChnlName().indexOf(channelName) != -1){
								childrenChannels.add(tempChannel);
							}
						}else{
							childrenChannels.add(tempChannel);
						}
					}
				}
			}else{
				User user = userCache.findById(baseUser.getId());
				if(user != null){
					List<Long> channelIds = new ArrayList<Long>();
					
					///获取用户对应角色可见的栏目ID
					List<Long> roleIds = user.getRoleIdList();
					if(roleIds != null){
						for(Long roleId : roleIds){
							List<Long> tempChannelIds = roleCache.getChannelIds(roleId);
							if(tempChannelIds != null && !tempChannelIds.isEmpty()){
								channelIds.addAll(tempChannelIds);
							}
						}
					}
					 
					//获取用户本身可见的栏目ID
					 List<Long> userChannelIds = user.getChannelIds();
					 if(userChannelIds != null && !userChannelIds.isEmpty()){
						 channelIds.addAll(userChannelIds);
					 }
					 
					 //去除重复栏目ID
					 if(channelIds != null && !channelIds.isEmpty()){
						 for(int i=0 ;i<channelIds.size()-1;i++){
							 for(int j=channelIds.size()-1;j>i;j--){
								 if(channelIds.get(j).equals(channelIds.get(i))){
									 channelIds.remove(j);
						        }
						    }
						}
					 }
					 
					 if(channelIds != null && !channelIds.isEmpty()){
						 List<Channel> channels = new ArrayList<Channel>();
						 for(Long tempId : channelIds){
							 Channel channel = channelCache.findById(tempId);
							 if(channel != null && channel.getStatus().longValue() == 1){
								 channels.add(channel.clone());
							 }
						 }
						 
						 if( !channels.isEmpty()){
								Map<Long, Channel> map = new TreeMap<Long,Channel>();
								for(Channel channel :channels){
									if(channel != null){
										map.put(channel.getId(), channel);
									}
								}
								for(Channel channel : channels){
									Long parentId = channel.getParentId();
									Long tempParentId = parentId;
									
									while(tempParentId.longValue() > 0L && (map.get(tempParentId) == null || 
											map.get(tempParentId).getStatus().intValue() != 1)){
										Channel parentChannel = channelCache.findById(tempParentId);
										tempParentId = parentChannel.getParentId();
									}
									
									if(tempParentId.longValue() > 0L){
										if(tempParentId.longValue() != parentId.longValue()){
											channel.setParentId(tempParentId);
										}
									}else if(tempParentId.longValue() == 0L){
										channel.setParentId(0L);
									}
								}
								
								for(Channel channel: channels){
									if(channel.getParentId().longValue() == channelId.longValue() && channel.getStatus().intValue() == 2){
										if(StringUtils.isNotBlank(channelName)){
											if(channel.getChnlName().indexOf(channelName) != -1){
												childrenChannels.add(channel);
											}
										}else{
											childrenChannels.add(channel);
										}
									}
									
									
								}
								
								
							}
						 	
						 
						 	
					 }
					 
				}
			}
		}
		
		PageInfo<Channel> page = new PageInfo<Channel>();
		if(!childrenChannels.isEmpty()){
			Collections.sort(childrenChannels);
			int total = childrenChannels.size();
			int maxPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
			if(pageIndex < 1){
				pageIndex = 1;
			}else if(pageIndex > maxPage){
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
			
			int start = (pageIndex - 1) * pageSize;
			int end = pageSize * pageIndex;
			List<Channel> list = new ArrayList<Channel>();
			for(int i = start; i < end && i < total ; i++){
				Channel channel = childrenChannels.get(i);
				list.add(channel);
			}
			
			page.setList(list);
			page.setSize(list.size());
		}
		page.setPageSize(pageSize);
		page.setPageNum(pageIndex);
		page.setTotal(0);
		page.setPages(1);
		return page;
	}

	@Override
	public boolean existsWithChannelName(String channelName, String excludeKey) {

		Example example=new Example(Channel.class); 
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("chnlName", channelName);
		if (StringUtils.isNotEmpty(excludeKey)) {
			criteria.andNotEqualTo("id", excludeKey);
		}
		List<Channel> channels=selectByExample(example);
		if(channels!=null && channels.size()!=0){
			return true;
		}
		return false;
	}
	
	/**
	 * 通过栏目ID获取所有正常状态子栏目的栏目ID集合，包含自身
	 * @param channelId 
	 * @return
	 */
	public List<Long> getAllChildrenChannelIds(Long channelId){
		if(channelId != null && channelId.longValue() > 0){
			return channelDao.getAllChildrenChannelIds(channelId);
		}
		return null;
	}
	
	/**
	 * 获取指定栏目ID下的所有子栏目(不包含自身)
	 * @param channelId
	 * @return 栏目ID的List集合
	 */
	@Override
	public List<Long> findChildrenChannelsByChannelId(List<Long> channelIds,Long channelId) {
		//TODO 获取指定栏目的用户可访问的所有子栏目信息
		List<Channel> channels = userService.getAllChannelNotInRecycle();
		if(channels != null && !channels.isEmpty()){
			List<Channel> list = new ArrayList<Channel>();
			List<Channel> childrens = new ArrayList<Channel>();
			Map<Long, List<Channel>> map = new TreeMap<Long,List<Channel>>();
			for(Channel channel :channels){
				if(channel.getParentId().equals(channelId)){
					childrens.add(channel);
				}
			}
			if(childrens!=null && !childrens.isEmpty()){
				for (Channel channel : childrens) {
					channelIds.add(channel.getId());
					findChildrenChannelsByChannelId(channelIds, channel.getId());
				}
			}
			/*Channel currentChannel = map.get(channelId);*/
			/*Channel currentChannel = channelCache.findById(channelId);
			if(currentChannel != null && currentChannel.getStatus().intValue() == 1){
				Long parentId = currentChannel.getId();
				if(parentId.longValue() > 0L){
					List<Channel> chldrenChannels = map.get(parentId);
					while(parentChannel != null && parentChannel.getStatus().intValue() == 1){
						list.add(parentChannel.clone());
						parentId = parentChannel.getParentId();
						if(parentId.longValue() > 0){
							parentChannel = map.get(parentId);
						}else{
							break;
						}
					}
					if(chldrenChannels != null && !chldrenChannels.isEmpty()){
						for (Channel channel : chldrenChannels) {
							while(chldrenChannels != null && !chldrenChannels.isEmpty()){
								
									list.add(channel.clone());
									parentId = channel.getParentId();
									if(parentId.longValue() > 0){
										chldrenChannels = map.get(parentId);
									}else{
										break;
									}
								}
						}
					}
				}
				List<Long> channelIds = new ArrayList<Long>();
				if(list != null && list.size() > 1){
					List<Channel> channelList = new ArrayList<Channel>();
					sortList(channelList,list,0);
					
					for(Channel channel : channelList){
						channelIds.add(channel.getId());
					}
					return channelIds;
				}else if(list != null && list.size() == 1){
					channelIds.add(list.get(0).getId());
				}
				return channelIds;
			}*/
		}
		return null;
	}
}
