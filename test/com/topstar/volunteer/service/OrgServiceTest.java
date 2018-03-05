package com.topstar.volunteer.service;


import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.base.BaseTest;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.entity.User;
import com.topstar.volunteer.model.OrgView;

public class OrgServiceTest extends BaseTest{

	@Autowired
	private OrgService orgService;
	
	@Test
	public void getOrgs(){
		Long parentId=0l;
		List<OrgView> orgViews=orgService.getAllOrgsByParentId(parentId);
		for (OrgView orgView : orgViews) {
			System.out.println(orgView);
		}
	}
	
	@Test
	public void getAllOrgList(){
		List<OrgView> orgViews=orgService.getAllOrgList();
		for (OrgView orgView : orgViews) {
			System.out.println(orgView);
		}
	}
	
	@Test
	public void getAllOrgListByParentId(){
		Long parentId=2l;
		List<OrgView> orgViews=orgService.getAllOrgsListByParentId(parentId);
		for (OrgView orgView : orgViews) {
			System.out.println(orgView);
		}
	}
	
	@Test
	public void getOrgListByAreaId(){
		List<OrgView> orgViewList=orgService.getOrgsListByAreaId(111l);
		for (OrgView orgView : orgViewList) {
			System.out.println(orgView);
		}
	}
	
	@Test
	public void getOrgsByParentId(){
		List<OrgView> orgViews=orgService.getOrgsByParentId(1l);
		for (OrgView orgView : orgViews) {
			System.out.println(orgView);
		}
	}
	
	@Test
	public void getUsersIncludeOrgId(){
		PageInfo<User> users=orgService.getAllUsersIncludeOrgId(2l,0, null, 10, 1);
		List<User> us=users.getList();
		for (User user : us) {
			System.out.println(user);
		}
	}
	
	@Test
	public void getUsersWithoutOrg(){
		PageInfo<User> userpage=orgService.getAllUsersIncludeOrgId(null,0,null,10, 1);
		List<User> users=userpage.getList();
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void getOrgByUserId(){
		Org org=orgService.getOrgByUserId(1l);
		System.out.println(org);
	}
	
	@Test
	public void getOrgListByFilter(){
		/*List<OrgView> orgViews=orgService.getOrgsListByAreaIdAndFilter(1l, 1, "局");
		for (OrgView orgView : orgViews) {
			System.out.println(orgView);
		}*/
		String regex="局{1,}+";
		System.out.println("文化局".matches(regex));
	}
	
	@Test
	public void getOrgViewsByFilter(){
		String orgName="";
		String summary="机构";
		String orderBy="CRT_TIME";
		PageInfo<OrgView> orgViewPage=orgService.getOrgsListByFilter(orgName, summary, orderBy, 1, 10);
		List<OrgView> orgViews=orgViewPage.getList();
		for (OrgView orgView : orgViews) {
			System.out.println(orgView);
		}
	}
	
	@Test
	public void isOrgOperateValid(){
		Org org=new Org();
		org.setAreaId(1l);
		Boolean isValid=orgService.isOrgOperateValid(org,false);
		System.out.println(isValid);
	}
}
