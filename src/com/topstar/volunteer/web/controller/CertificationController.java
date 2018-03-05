package com.topstar.volunteer.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.CertCheck;
import com.topstar.volunteer.entity.Certification;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.service.CertCheckService;
import com.topstar.volunteer.service.CertificationService;
import com.topstar.volunteer.service.CheckTimeService;
import com.topstar.volunteer.service.VolunteerCertficationService;
import com.topstar.volunteer.service.VolunteerService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;


@Controller
public class CertificationController {
	
	@Autowired
	private VolunteerCertficationService volunteerCertificationService;
	
	@Autowired
	private VolunteerService volunteerService;
	
	@Autowired
	private CertificationService certificationService;
	
	@Autowired
	private CertCheckService certCheckService;
	
	@Autowired
	private CheckTimeService checkTimeService;
	
	@RequestMapping(value="/cert/list.html")
	public String toVolunteer(HttpServletRequest request){
		
		return "/volunteer/certification/list";
	}
	
	//添加证书
	@RequestMapping(value = "/cert/addCert.html")
	public String toAddCert(HttpServletRequest request,ModelMap map){
		return "/volunteer/certification/addCert";
	}
	
	//跳转到证书授予的志愿者的选择页面
	@RequestMapping(value = "/cert/selectVolunteer.html")
	public String toSelectVolunteer(HttpServletRequest request,ModelMap map){
		return "/volunteer/certification/selectVolunteer";
	}
	
	//获取尚没有证书的志愿者信息列表
	@RequestMapping(value="/cert/selectVolunteer.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String selectVolunteer(HttpServletRequest request,HttpServletResponse response){
		Volunteer volunteer= new Volunteer();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		String selectType = ActionContext.getActionContext().getParameterAsString("selectType");
		String selectName = ActionContext.getActionContext().getParameterAsString("selectName");
		Long serviceTeam = ActionContext.getActionContext().getParameterAsLong("serviceTeam");
		if(currPage == 0){
			currPage = 1;
		}
		int pageSize=0;
		if(pageSize< 1){
			pageSize = 10;
		}
		if("2".equals(selectType)){
			volunteer.setIdcard(selectName);
		}else{
			volunteer.setUserName(selectName);
		}
		volunteer.setServiceTeam(serviceTeam);
		
		String orderBy = "REG_TIME DESC,USER_NAME";
		ResultData data = new ResultData();
		try {
			PageInfo<VolunteerView> pageInfo = volunteerCertificationService.getVolunteerWithoutCertByEntity(volunteer, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SELECTVOLUNTEER, "成功跳转到授予志愿者证书中志愿者选择页面,查询条件为"+volunteer, null,null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SELECTVOLUNTEER, "未成功跳转到授予志愿者证书中志愿者选择页面,查询条件为"+volunteer+",错误信息【"+e.getMessage()+"】", null, null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	/**
	 * 添加志愿者证书记录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/cert/addCert.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addCert(HttpServletRequest request,HttpServletResponse response,Certification certification){
		ResultData data = new ResultData();
		certification.setIsUse("0");
		List<ValidateMessage> errors =ValidatorUtil.validate(certification, Groups.Add.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		try {
			int addCertification=certificationService.checkNewCert(certification);
			if (addCertification == 2) {
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.CREATECERTIFICATION, "成功授予志愿者证书", certification.getId(),certification.getUserName(),true);
				data.setSuccess(true);
				data.setMessage("志愿者证书成功添加");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.CREATECERTIFICATION, "授予志愿者证书失败", certification.getVolunteerId(),certification.getUserName(),false);
				data.setSuccess(false);
				data.setMessage("志愿者证书添加失败");
				return data.toJSONString();
			}
			
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.CREATECERTIFICATION, "授予志愿者证书发生错误,错误信息【"+e.getMessage()+"】", certification.getVolunteerId(), certification.getUserName());
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	//查看志愿者证书信息
	@RequestMapping(value = "/cert/viewVolunteerCert.html")
	public String toViewVolunteer(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		String teamName= ActionContext.getActionContext().getParameterAsString("teamName");
		Long volunteerId=null;
		try {
			volunteerId=Long.valueOf(id);
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEERCERT, "查看志愿者证书参数信息【"+id+"】不合法", null,null);
			throw new TPSException("不合法");
		}
		try{
			VolunteerView  volunteerView=volunteerCertificationService.getVolunteerCertByVolunteerId(volunteerId);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEERCERT, "成功查看志愿者证书信息", volunteerView.getId(),volunteerView.getUserName(),true);
			map.addAttribute("volunteerView", volunteerView);
			map.addAttribute("teamName", teamName);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEERCERT, "查看志愿者证书信息发生错误【"+e.getMessage()+"】", volunteerId,null);
		}
		
		return "/volunteer/certification/viewVolunteerCert";
	}
	
	/**
	 * 分页查看证书年度审核情况信息列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/cert/certChecklist.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String certChecklist(HttpServletRequest request,HttpServletResponse response){
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String id= ActionContext.getActionContext().getParameterAsString("certId");
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 5;
		}
		
		String orderBy = "check_year DESC";
		ResultData data = new ResultData();
		Long certId=null;
		try {
			certId=Long.parseLong(id); 
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWCERTCHECK, "查看证书年度审核情况参数信息不合法【"+id+"】",null,null);
			data.setSuccess(false);
			data.setMessage("不合法");
			return data.toJSONString();
		}
		try {
			PageInfo<CertCheck> pageInfo = certCheckService.getCertChecksByCertId(certId, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWCERTCHECK, "成功查看证书年度审核情况信息", certId, null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWCERTCHECK, "查看证书年度审核情况发生错误,错误信息【"+e.getMessage()+"】", certId, null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	//查看志愿者证书年检管理信息
	@RequestMapping(value ="/cert/setVolunteerCert.html")
	public String toSetVolunteerCert(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		String teamName= ActionContext.getActionContext().getParameterAsString("teamName");
		Long volunteerId=null;
		try {
			volunteerId=Long.valueOf(id);
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEERCERT, "查看志愿者证书年检管理信息参数不合法【"+id+"】",null,null);
			throw new TPSException("不合法");
		}
		try{
			VolunteerView  volunteerView=volunteerCertificationService.getVolunteerCertByVolunteerId(volunteerId);
			String checkYear=checkTimeService.allowCertYearCheck(volunteerView.getCertId());
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEERCERT, "成功查看志愿者证书年检管理信息", volunteerId, null,true);
			map.addAttribute("volunteerView", volunteerView);
			map.addAttribute("teamName", teamName);
			map.addAttribute("checkYear", checkYear);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.SHOWVOLUNTEERCERT, "查看志愿者证书年检管理信息失败【"+e.getMessage()+"】",volunteerId,null);
		}
		return "/volunteer/certification/setVolunteerCert";
	}
	
	//跳转到添加证书年度考核登记页面
	@RequestMapping(value = "/cert/addCertCheck.html")
	public String toAddCertCheck(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		String certId= ActionContext.getActionContext().getParameterAsString("certId");
		String checkYear= ActionContext.getActionContext().getParameterAsString("checkYear");
		Long volunteerId=null;
		try {
			volunteerId=Long.valueOf(id);
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.CREATECERTCHECK, "跳转到证书新增年度考核登记页面的参数信息不合法【"+id+"】",null,null);
			throw new TPSException("不合法");
		}
		try{
			Volunteer volunteer=volunteerService.selectByKey(volunteerId);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.CREATECERTCHECK, "成功跳转到证书新增年度考核登记页面", volunteerId, null,true);
			map.addAttribute("volunteer", volunteer);
			map.addAttribute("certId", certId);
			map.addAttribute("checkYear", checkYear);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.CREATECERTCHECK, "跳转到证书新增年度考核登记页面失败【"+e.getMessage()+"】",volunteerId,null);
		}
		return "/volunteer/certification/addCertCheck";
	}
	
	/**
	 * 添加证书年度考核登记信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/cert/addCertCheck.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addCertCheck(HttpServletRequest request,HttpServletResponse response,CertCheck certCheck){
		ResultData data = new ResultData();
		
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		certCheck.setOpertUser(crtUser.getUserName());
		certCheck.setOpertTime(new Date());
		List<ValidateMessage> errors =ValidatorUtil.validate(certCheck, Groups.Add.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		
		try {
			String checkYear=checkTimeService.allowCertYearCheck(Long.toString(certCheck.getCertId()));
			if(StringUtils.isNotBlank(checkYear)){
				int addCertCheck=certCheckService.insert(certCheck);
				if(addCertCheck>0){
					LoggerServer.info(ObjectType.VOLUNTEER,OperateType.CREATECERTCHECK, "成功新增证书年度考核登记信息", null, null,true);
					data.setSuccess(true);
					data.setMessage("证书年度审核成功");
					return data.toJSONString();
				}else{
					LoggerServer.info(ObjectType.VOLUNTEER,OperateType.CREATECERTCHECK, "新增证书年度考核登记信息失败", null, null,false);
					data.setSuccess(false);
					data.setMessage("证书年度审核失败");
					return data.toJSONString();
				}
			}else{
				LoggerServer.error(ObjectType.VOLUNTEER,OperateType.CREATECERTCHECK, "该证书本年份已完成年检登记,证书certId:"+certCheck.getCertId(),null,null);
				data.setSuccess(false);
				data.setMessage("该证书本年份已完成年检登记");
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.CREATECERTCHECK, "新增证书年度考核登记信息失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	//跳转到编辑证书年度考核登记结果页面
	@RequestMapping(value = "/cert/editCertCheck.html")
	public String toEditCertCheck(HttpServletRequest request,ModelMap map) throws TPSException{
		String id= ActionContext.getActionContext().getParameterAsString("volunteerId");
		String checkId= ActionContext.getActionContext().getParameterAsString("certCheckId");
		Long volunteerId=null;
		Long certCheckId=null;
		try{
			volunteerId=Long.parseLong(id);
			certCheckId=Long.parseLong(checkId);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.EDITCERTCHECK, "跳转到编辑证书年度考核登记页面的参数信息不合法【"+id+","+checkId+"】",null,null);
			throw new TPSException("不合法");
		}
		try{
			CertCheck certCheck=certCheckService.selectByKey(certCheckId);
			Volunteer volunteer=volunteerService.selectByKey(volunteerId);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.EDITCERTCHECK, "跳转到编辑证书年度考核登记页面成功【"+certCheck+"】",certCheckId,null,true);
			map.addAttribute("volunteer", volunteer);
			if(certCheck!=null){
				map.addAttribute("certCheck", certCheck);
				map.addAttribute("certCheckId", certCheck.getId());
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.EDITCERTCHECK, "跳转到编辑证书年度考核登记页面失败【"+e.getMessage()+"】",certCheckId,null);
		}
		return "/volunteer/certification/editCertCheck";
	}
	
	/**
	 * 保存证书年度考核登记修改的信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/cert/saveCertCheck.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String saveCertCheck(HttpServletRequest request,HttpServletResponse response,CertCheck certCheck){
		ResultData data = new ResultData();
		
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		certCheck.setOpertUser(crtUser.getUserName());
		certCheck.setOpertTime(new Date());;
		List<ValidateMessage> errors =ValidatorUtil.validate(certCheck, Groups.Update.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		
		try {
			int updateCertCheck=certCheckService.updateNotNull(certCheck);
			if(updateCertCheck>0){
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.EDITCERTCHECK, "成功修改证书年度考核登记信息", null, null,true);
				data.setSuccess(true);
				data.setMessage("成功修改志愿者证书年度登记信息");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.EDITCERTCHECK, "修改证书年度考核登记信息失败", null, null,false);
				data.setSuccess(false);
				data.setMessage("修改志愿者证书年度登记信息操作失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.EDITCERTCHECK, "编辑证书年度考核登记信息失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	/**
	 * 删除指定证书年度考核登记的信息
	 * @param request
	 * @param response
	 * @return
	 * @throws TPSException 
	 */
	@RequestMapping(value="/cert/delCertCheck.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String delCertCheck(HttpServletRequest request,HttpServletResponse response) throws TPSException{
		ResultData data = new ResultData();
		String checkId= ActionContext.getActionContext().getParameterAsString("certCheckId");
		Long certCheckId=null;
		try {
			certCheckId=Long.parseLong(checkId);
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.DELCERTCHECK, "删除指定证书年度考核登记参数信息不合法【"+checkId+"】",null,null);
			throw new TPSException("不合法");
		}
		try {
			int delCertCheck=certCheckService.deleteByKey(certCheckId);
			if(delCertCheck>0){
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.DELCERTCHECK, "成功删除证书年度考核登记信息", null, null,true);
				data.setSuccess(true);
				data.setMessage("删除成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.VOLUNTEER,OperateType.DELCERTCHECK, "删除证书年度考核登记信息失败", null, null,false);
				data.setSuccess(false);
				data.setMessage("删除失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.DELCERTCHECK, "删除指定证书年度考核登记信息失败【"+e.getMessage()+"】",certCheckId,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	/**
	 * 志愿者证书审核名单信息列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/cert/list.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String volunteerCertList(HttpServletRequest request,HttpServletResponse response){
		Volunteer volunteer= new Volunteer();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String realName = ActionContext.getActionContext().getParameterAsString("realName");
		String idCard = ActionContext.getActionContext().getParameterAsString("idCard");
		Integer status = ActionContext.getActionContext().getParameterAsInt("status");
		String tele = ActionContext.getActionContext().getParameterAsString("tele");
		Long serviceTeam = ActionContext.getActionContext().getParameterAsLong("serviceTeam");
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		
		volunteer.setRealName(realName);
		volunteer.setMobile(tele);
		volunteer.setServiceTeam(serviceTeam);
		volunteer.setIdcard(idCard);
		volunteer.setStatus(status);
		String orderBy = "oper_time DESC,v.USER_NAME";
		ResultData data = new ResultData();
		try {
			PageInfo<VolunteerView> pageInfo = volunteerCertificationService.getVolunteerCertsByEntity(volunteer, orderBy, currPage, pageSize);
			LoggerServer.info(ObjectType.VOLUNTEER,OperateType.VOLUNTEERCERTLIST, "成功查询志愿者证书审核名单信息列表,查询条件为"+volunteer, null,null,true);
			data.setSuccess(true);
			data.put("page",pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.VOLUNTEERCERTLIST, "查询志愿者证书审核名单信息列表失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	
	/**
	 * 批量删除志愿者证书
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/vols/delVolunteerCerts.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String delVolunteerCerts(HttpServletRequest request,HttpServletResponse response){
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		ResultData data = new ResultData();
		if (StringUtils.isBlank(ids)) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.DELVOLUNTEERCERTS, "批量删除志愿者证书参数信息不能为空",null,null);
			data.setSuccess(false);
			data.put("message", "删除志愿者证书操作参数不合法");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		long[] volunteerCertKeys=new long[keys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				Long volunteerCertKey=Long.parseLong(keys[i]);
				volunteerCertKeys[i]=volunteerCertKey;
			}catch (Exception e) {
				LoggerServer.error(ObjectType.VOLUNTEER,OperateType.DELVOLUNTEERCERTS, "批量删除志愿者证书参数信息不合法【"+ids+"】",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				return data.toJSONString();
			}
		}
		try {
			boolean delVolunteerCert=certificationService.batchDelVolunteerCert(volunteerCertKeys);
			if(delVolunteerCert){
				data.setSuccess(true);
				data.setMessage("成功删除志愿者证书");
				return data.toJSONString();
			}else{
				data.setSuccess(false);
				data.setMessage("删除志愿者证书操作失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.VOLUNTEER,OperateType.DELVOLUNTEERCERTS, "批量删除志愿者证书参数信息失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
}
