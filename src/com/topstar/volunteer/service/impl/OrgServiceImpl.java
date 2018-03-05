package com.topstar.volunteer.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.cache.OrgCache;
import com.topstar.volunteer.dao.AreaDao;
import com.topstar.volunteer.dao.OrgDao;
import com.topstar.volunteer.dao.OrgUserDao;
import com.topstar.volunteer.dao.UserDao;
import com.topstar.volunteer.entity.Area;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.entity.OrgUser;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.OrgView;
import com.topstar.volunteer.service.OrgService;
import com.topstar.volunteer.service.OrgUserService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.PropertiesUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.orderbyhelper.OrderByHelper;

@Service
public class OrgServiceImpl  extends BaseServiceImpl<Org> implements OrgService{

	@Autowired
	private OrgDao orgDao;
	
	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private OrgUserDao orgUserDao;
	
	@Autowired
	private OrgCache orgCache;
	
	@Autowired
	private OrgUserService orgUserService;

	@Autowired
	private OrgService orgService;
	
	
	@Override
	public Org selectByKey(Object key) {
		if(key instanceof Long){
			return orgCache.findById((Long) key);
		}else{
			return orgDao.selectByKey(key);
		}
	}

	/**
	 * 得到所有的无递归关系机构信息列表
	 * @return
	 */
	@Override
	public List<OrgView> getAllOrgList() {
		List<OrgView> orgViewList=new ArrayList<OrgView>();
		Long rootKey=Long.parseLong(PropertiesUtil.getInstance().getPropertyValueAsString("orgRootParentId", "0"));
		Org org=selectByKey(rootKey);
		OrgView currOrgView=null;
		if(org!=null){
			currOrgView=new OrgView(org);
		}
		List<OrgView> orgViews=getAllOrgsByParentId(rootKey);
		if(orgViews!=null && currOrgView!=null){
			currOrgView.setParent(true);
		}
		List<OrgView> orgSubViewList=new ArrayList<OrgView>();
		orgViewsList(orgViews,orgSubViewList);
		for (OrgView orgView : orgSubViewList) {
			orgView.setOrgList(null);
		}
		if(currOrgView!=null){
			orgViewList.add(currOrgView);
		}
		orgViewList.addAll(orgSubViewList);
		return orgViewList;
	}

	/**
	 * 获取指定机构下的无递归关系的所有下级机构信息列表(包含当前机构信息)
	 * @param parentId 上级机构id
	 * @return
	 */
	@Override
	public List<OrgView> getAllOrgsListByParentId(Long parentId){
		List<OrgView> orgViewList=new ArrayList<OrgView>();
		Org org=selectByKey(parentId);
		OrgView currOrgView=null;
		if(org!=null){
			currOrgView=new OrgView(org);
		}
		List<OrgView> orgViews=getAllOrgsByParentId(parentId);
		if(orgViews!=null && currOrgView!=null){
			currOrgView.setParent(true);
		}
		List<OrgView> orgSubViewList=new ArrayList<OrgView>();
		orgViewsList(orgViews,orgSubViewList);
		for (OrgView orgView : orgSubViewList) {
			orgView.setOrgList(null);
		}
		if(currOrgView!=null){
			orgViewList.add(currOrgView);
		}
		orgViewList.addAll(orgSubViewList);
		return orgViewList;
	}
	
	/**
	 * 获取指定机构的具有递归层级关系的所有下级机构信息列表
	 * @param parentId 上级机构id
	 * @return
	 */
	@Override
	public List<OrgView> getAllOrgsByParentId(Long parentId) {
		List<Org> orgs=orgDao.findOrgByParentId(parentId);
		if(orgs!=null){
			List<OrgView> orgViews=orgsToOrgViews(orgs);
			if(orgViews!=null){
				for (OrgView orgView : orgViews) {
					Long orgId=orgView.getId();
					List<OrgView> subOrgViews=getAllOrgsByParentId(orgId);
					if(subOrgViews==null){
						continue;
					}else{
						orgView.setParent(true);
						orgView.setOrgList(subOrgViews);
					}
				}
				return orgViews;
			}
		}
		return null;
	}

	@Override
	public List<OrgView> getOrgsByParentId(Long parentId) {
		List<OrgView> orgViewList=new ArrayList<OrgView>();
		Long rootKey=Long.parseLong(PropertiesUtil.getInstance().getPropertyValueAsString("orgRootParentId", "0"));
		Org org=selectByKey(parentId);
		OrgView currOrgView=null;
		if(org!=null){
			currOrgView=new OrgView(org);
			if(rootKey==org.getParentId()){
				currOrgView.setOpen(true);
			}
		}
		List<OrgView> orgViews=null;
		if(parentId!=null){
			List<Org> orgs=orgDao.findOrgByParentId(parentId);
			if(orgs!=null && currOrgView!=null){
				currOrgView.setParent(true);
			}
			orgViews=orgsToOrgViews(orgs);
			for (OrgView subOrgView : orgViews) {
				List<Org> subOrgViews=orgDao.findOrgByParentId(subOrgView.getId());
				if(subOrgViews!=null){
					subOrgView.setParent(true);
				}
			}
		}
		if(currOrgView!=null){
			orgViewList.add(currOrgView);
		}
		if(orgViews!=null){
			orgViewList.addAll(orgViews);
		}
		return orgViewList;
	}

	/**
	 * 根据指定区域获取区域下的无递归关系的所有机构信息列表
	 * @param areaId 区域Id
	 * @return
	 */
	public List<OrgView> getOrgsListByAreaId(Long areaId){
		List<Org> orgs=null;
		// 得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		// 判断用户是不是超级管理员
		boolean isAdmin = user.isAdmin();
		if (isAdmin) {
			orgs= orgDao.getOrgsByAreaId(areaId,-1L);
		}else{
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
					// 如果是管理机构，可以看到所管辖的所有机构的发布的数据。比如是文化厅的，可以看到所有数据。如果是武汉市文化局的，可以看到本市的所有数据。如果是XXX县的文化局，可以看到本县的数据。
					if (grade == 1) {
						// 如果是省级管理机构，可以看到所有记录.
						orgs= orgDao.getOrgsByAreaId(areaId,key);
					} else {
						//显示本机构及下属机构
						orgs= orgDao.getOrgsByAreaId(areaId,key);
					}
				}else{
					// 如果是业务机构，只能看到自己机构的数据。比如：如果是湖北省图书馆的，只能看到图书馆的数据。
					orgs= orgDao.getOrgsByAreaId(areaId,key);
				}
			}
		}
		List<OrgView> res= orgsToOrgViews(orgs);
		return res;
//		if (areaId==2) {
//			List<Org> orgs= orgDao.getOrgsByAreaId(areaId);
//			List<OrgView> res= orgsToOrgViews(orgs);
//			return res;
//		}else{
//		
//		List<Area> areas=areaDao.findAreaChildersByParentId(areaId);
//		Map<Long,List<OrgView>> map=new TreeMap<Long,List<OrgView>>();
//		List<OrgView> orgViews=new ArrayList<OrgView>();
//		if(areas!=null && !areas.isEmpty()){
//			for (Area area : areas) {
//				List<Org> orgs=orgDao.getAllOrgsByAreaId(area.getId());
//				if(orgs!=null && !orgs.isEmpty()){
//					Iterator<List<OrgView>> it=map.values().iterator();
//					boolean flag=true;
//					while (it.hasNext()) {
//						List<OrgView> list = (List<OrgView>) it.next();
//						if(list.containsAll(orgsToOrgViews(orgs))){
//							flag=false;
//							break;
//						}
//						if(orgsToOrgViews(orgs).containsAll(list)){
//							it.remove();
//						}
//					}
//					if(flag){
//						map.put(orgs.get(0).getId(), orgsToOrgViews(orgs));
//					}
//				}
//			}
//		}
//		
//		for (Long id: map.keySet()) {
//			orgViews.addAll(map.get(id));
//		}
//		if(orgViews!=null && orgViews.size()>0){
//			return orgViews; 
//		}
//		}
//		return null;
	}
	
	@Override
	public boolean addOrg(Org org) {
		org.setCrtTime(new Timestamp(new Date().getTime()));
		int addOrg=orgDao.insertNotNull(org);
		if(addOrg>0){
			orgCache.add(org);
			return true;
		}
		return false;
	}

	@Override
	public boolean delOrgAndSubOrg(Long delOrgId) {
		int flag=0;
		if(delOrgId!=null){
			List<OrgView> orgViews=getAllOrgsListByParentId(delOrgId);
			if(orgViews!=null && orgViews.size()>0){
				flag=orgViews.size();
				int delOrg=0;
				for (OrgView orgView : orgViews) {
					delOrg=deleteByKey(orgView.getId());
					orgCache.delete(orgView.getId());
					if(delOrg>0){
						flag--;
					}
				}
			}
		}
		return flag>0? false:true;
	}

	@Override
	public int saveOrg(Org org) {
		if(org!=null && null !=org.getId()){
			int updateOrg=orgDao.updateNotNull(org);
			orgCache.update(org);
			if(updateOrg>0){
				return 1;
			}
		}
		return 0;
	}

	@Override
	public boolean isOrgOperateValid(Org operateOrg,boolean isInclude) {
		Long areaId=operateOrg.getAreaId();
		if(areaId!=null && areaId>0){
			Long orgId=orgDao.findOrgByAreaId(areaId);
			if(orgId!=null && 1==operateOrg.getType()){
				if(isInclude && orgId.equals(operateOrg.getId())){
					return true;
				}
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 将指定的机构实体列表转换成用户页面展示的机构实体列表
	 * @param org
	 * @return
	 */
	private List<OrgView> orgsToOrgViews(List<Org> orgs){
		if(orgs!=null && orgs.size()>0){
			List<OrgView> orgViews=new ArrayList<OrgView>();
			for (Org org : orgs) {
				OrgView orgView=new OrgView(org);
				orgViews.add(orgView);
			}
			return orgViews;
		}
		return null;
		
	}
	
	/**
	 * 将具有层级结构的递归机构实体列表转换成无递归机构实体列表
	 * @param org
	 * @return
	 */
	private void orgViewsList(List<OrgView> orgs,List<OrgView> orgViews){
		if(orgViews!=null){
			if(orgs!=null && orgs.size()>0){
				for (OrgView orgView : orgs) {
					orgViews.add(orgView);
					List<OrgView> subOrgViews=orgView.getOrgList();
					if(subOrgViews!=null && subOrgViews.size()>0){
						orgViewsList(subOrgViews,orgViews);
					}else{
						continue;
					}
				}
			}
		}
		
	}
	
	@Override
	public PageInfo<User> getUsersByGivenOrgId(Long orgId,int selectType,String selectName, int pageNum,int pageIndex) {
		if(orgId<1){
			orgId=null;
		}
		if(selectType<0 || selectType >2 ){
			selectType=0;
		}
		String userName=null;
		String realName=null;
		
		if(selectType==1){
			userName=selectName;
		}else if(selectType==2){
			realName=selectName;
		}
		return userDao.findUsersByOrgId(orgId, userName, realName, pageNum, pageIndex);
	}
	
	@Override
	public PageInfo<User> getAllUsersIncludeOrgId(Long orgId,int selectType,String selectName,int pageNum, int pageIndex) {
		User user=new User();
		if(selectType==1){
			user.setUserName(selectName);
		}else if(selectType==2){
			user.setRealName(selectName);
		}
		String orderBy = "CR_TIME DESC,USER_NAME";
		/*PageInfo<User> users=userDao.findByEntity(user, orderBy, pageIndex, pageNum);
		List<User> userList=users.getList();
		if(userList!=null && userList.size()>0){
			for (User u : userList) {
				Long userOrgId=orgUserDao.findOrgIdByUserId(u.getId());
				if(userOrgId!=null && userOrgId==orgId){
					u.setOrgId(userOrgId);
				}
			}
			
		}*/
		PageInfo<User> users=userDao.findByEntityWithoutOrg(user, orderBy, pageIndex, pageNum);
		return users;
	}

	@Override
	public Org getOrgByUserId(Long userId) {
		return orgDao.findOrgByUserId(userId);
	}
	
	/**
	 * 根据区域和过滤条件得到指定的区域信息列表
	 * @param areaId
	 * @param selectType 过滤条件 1:对机构名称进行过滤  2:对机构备注进行过滤
	 * @param selectContent 过滤的内容
	 * @return
	 */
	public List<OrgView> getOrgsListByAreaIdAndFilter(Long areaId,int selectType,String selectContent){
		List<OrgView> orgViews=getOrgsListByAreaId(areaId);
		List<OrgView> orgViewsResult=null;
		if(selectType<0 || selectType>2 || StringUtils.isBlank(selectContent)){
			return orgViews;
		}
		String regex=selectContent+"{1,}";
		if(orgViews!=null && orgViews.size()>0){
			orgViewsResult=new ArrayList<OrgView>();
			for (OrgView orgView : orgViews) {
				if(selectType==1){
					if(orgView .getName().matches(regex)){
						orgViewsResult.add(orgView);
					}
				}else{
					if(orgView .getSummary().matches(regex)){
						orgViewsResult.add(orgView);
					}
				}
			}
		}
		return orgViewsResult;
	}
	
	/**
	 * 根据机构名称和说明对机构进行模糊查询
	 * @param orgName 查询的机构名称
	 * @param summary 查询的机构说明
	 * @param orderBy 查询排序条件
	 * @param pageIndex 当前页码
	 * @param pageSize 每页显示记录数
	 * @return
	 */
	public PageInfo<OrgView> getOrgsListByFilter(String orgName,String summary,String orderBy, int pageIndex, int pageSize){
		// 得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		// 判断用户是不是超级管理员
		boolean isAdmin = user.isAdmin();
		Long orgId=null;
		if (isAdmin) {
			orgId=null;
		}else {
			// 如果不是超级管理员。获取用户所在的机构，（机构分为业务机构和管理机构）
			OrgUser orgUser = orgUserService.getOrgUserByUserId(user.getId());
			// 如果普通用户没有机构，看不到数据。
			if (orgUser != null) {
				// 获取组织机构Id
				Long key = orgUser.getOrgId();
				// 得到org对象
				Org org1 = orgService.selectByKey(key);
				// 获取机构类型
				Integer orgType = org1.getType();
				// 获取机构级别
				Integer grade = org1.getGrade();
				if (orgType == 1) {
					// 如果是管理机构，可以看到所管辖的所有机构的发布的数据。比如是文化厅的，可以看到所有数据。如果是武汉市文化局的，可以看到本市的所有数据。如果是XXX县的文化局，可以看到本县的数据。
					if (grade == 1) {
						orgId = 1l;
						}else {
							orgId = org1.getId();
						}
					}
				}
			}
		PageInfo<OrgView> orgViewPage=orgDao.findEntity(orgName, summary, orgId, orderBy, pageIndex, pageSize);
		return orgViewPage;
	}

	@Override
	public List<Long> getOrgIdLists(Long orgId) {
		return orgDao.getOrgIdLists(orgId);
	}
	
	@Override
	public boolean existsWithOrgName(String orgName, Long excludeKey) {
		
		Example example=new Example(Org.class); 
		Criteria criteria=example.createCriteria();
		criteria.andEqualTo("name", orgName);
		if (excludeKey!=null) {
			criteria.andNotEqualTo("id", excludeKey);
		}
		List<Org> orgs=selectByExample(example);
		if(orgs!=null && orgs.size()!=0){
			return true;
		}
		return false;
	}
}
