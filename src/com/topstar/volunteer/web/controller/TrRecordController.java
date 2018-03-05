package com.topstar.volunteer.web.controller;


import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Org;
import com.topstar.volunteer.entity.OrgUser;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.entity.TrRecord;
import com.topstar.volunteer.entity.TrainVol;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.service.OrgService;
import com.topstar.volunteer.service.OrgUserService;
import com.topstar.volunteer.service.SerTeamService;
import com.topstar.volunteer.service.TeamUserService;
import com.topstar.volunteer.service.TrRecordService;
import com.topstar.volunteer.service.TrainVolService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.StringTools;
import com.topstar.volunteer.util.annotations.Token;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * 培训记录控制器
 */
@Controller
public class TrRecordController {
	
	private static Logger logger = LoggerFactory.getLogger(TrRecordController.class);
	
	@Autowired
	private TrRecordService trRecordService;
	
	@Autowired
	private TrainVolService trainVolService;
	
	@Autowired
	private OrgUserService orgUserService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private SerTeamService serTeamService;
	
	@Autowired
	private TeamUserService teamUserServiece;
	
	/**
	 * 跳转到培训记录页面
	 * @param request
	 * @param response
	 * @param map
	 */
	@RequestMapping(value = "/record/list.html")
	public String toRecordList(HttpServletRequest request, HttpServletResponse response, ModelMap map){
		return "/record/train/list";
	}
	
	/**
	 * 培训记录列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/record/list.do", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String recordList(HttpServletRequest request, HttpServletResponse response){
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		//培训名称
		String trName = ActionContext.getActionContext().getParameterAsString("trName");
		//负责人
		String principal = ActionContext.getActionContext().getParameterAsString("principal");
		//所属培训记录名称
		String teamName = ActionContext.getActionContext().getParameterAsString("teamName");
		//培训状态
		int status = ActionContext.getActionContext().getParameterAsInt("status");
		if (currPage == 0 || currPage == -1) {
			currPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 10;
		}
		TrRecord trRecord = new TrRecord();
		trRecord.setTrName(trName);
		trRecord.setPrincipal(principal);
		trRecord.setTeamName(teamName);
		trRecord.setStatus(status);
		ResultData data = new ResultData();
		try {
			PageInfo<TrRecord> pageInfo = trRecordService.findByEntity(trRecord, currPage, pageSize);
			data.setSuccess(true);
			data.put("page", pageInfo);
			LoggerServer.info(ObjectType.TRRECORD, OperateType.SHOWTRRECORDLIST, "查看培训记录列表成功", null, null, true);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询后台用户出错,pageIndex:{},pageSize:{},trName:{},teamName:{}", currPage, pageSize,
					trName, teamName, e);
			LoggerServer.error(ObjectType.TRRECORD, OperateType.SHOWTRRECORDLIST, "查看培训记录失败", null, null);
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到添加培训记录页面
	 * @param request
	 * @param response
	 * @return
	 */
	@Token(save=true,customKey = "TrRecordController_addTrRecord")
	@RequestMapping(value = "/record/addTrRecord.html")
	public String toAddTrRecord(HttpServletRequest request, HttpServletResponse response) {
		return "/record/train/addTrRecord";
	}
	
	/**
	 * 跳转到查看培训记录页面
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/record/viewTrRecord.html")
	public String toViewTrRecord(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		//培训记录的Id
		String id = ActionContext.getActionContext().getParameterAsString("id");
		Long key = null;
		TrRecord trRecord=null;
		try {
			key = Long.valueOf(id);
		} catch (Exception e) {
			logger.error("查看培训记录信息的参数id:{}不合法", id, e);
		}
		try {
		    trRecord = trRecordService.selectByKey(key);
			map.addAttribute("trRecord", trRecord);
			LoggerServer.info(ObjectType.TRRECORD, OperateType.SHOWTRRECORD, "查看培训记录成功", trRecord.getId(), trRecord.getTrName(), true);
		} catch (Exception e) {
			LoggerServer.error(ObjectType.TRRECORD, OperateType.SHOWTRRECORD, "查看培训记录失败", trRecord.getId(), trRecord.getTrName());
		}
		return "/record/train/viewTrRecord";
	}
	
	
	/**
	 * 培训记录志愿者列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/record/viewTrRecord.do", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String viewTrRecord(HttpServletRequest request, HttpServletResponse response){
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		//培训记录的Id
		String id = ActionContext.getActionContext().getParameterAsString("id");
		if (currPage == 0) {
			currPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 10;
		}
		ResultData data = new ResultData();
		try {
			Long key = Long.valueOf(id);
			PageInfo<TrainVol> pageInfo = trainVolService.findByEntity(key, currPage, pageSize);
			logger.info("成功分页查询培训记录志愿者列表,pageIndex:{},pageSize:{},id:{}", currPage, pageSize,
					id, true);
			data.setSuccess(true);
			data.put("page", pageInfo);
			return data.toJSONString();
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询后台用户出错,pageIndex:{},pageSize:{},id:{}", currPage, pageSize,
					id, e);
			LoggerServer.error(ObjectType.TRAINVOl, OperateType.SHOWTRAINVOLLIST,"查看培训记录志愿者列表失败", null, null);
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到用户配置页面
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/record/personal.html")
	public String toPersonal(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		//培训记录的Id
		String id = ActionContext.getActionContext().getParameterAsString("id");
		Long key = null;
		TrRecord trRecord=null;
		try {
			key = Long.valueOf(id);
			trRecord = trRecordService.selectByKey(key);
			map.addAttribute("trRecord", trRecord);
		}catch (NumberFormatException e) {
			logger.error("查看培训记录信息的参数id:{}不合法", id, e);
			LoggerServer.error(ObjectType.TRRECORD, OperateType.SHOWTRRECORD, "查看培训记录信息的参数id:["+id+"]不合法", null, null);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.TRRECORD, OperateType.SHOWTRRECORD, "查看培训记录失败", trRecord.getId(), trRecord.getTrName());
		}
		return "/record/train/personal";
	}
	
	/**
	 * 跳转到上传志愿者页面
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/record/toUpLoad.html")
	public String toUpLoad(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		String id = ActionContext.getActionContext().getParameterAsString("tId");
		map.addAttribute("trainId", id);
		return "/record/train/showVols";
	}
	
	/**
	 * 跳转到编辑培训记录页面
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/record/editTrRecord.html")
	public String toEditTrRecord(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		//培训记录的Id
		String id = ActionContext.getActionContext().getParameterAsString("id");
		Long key = null;
		TrRecord trRecord=null;
		try {
			key = Long.valueOf(id);
			trRecord = trRecordService.selectByKey(key);
			map.addAttribute("trRecord", trRecord);
			LoggerServer.info(ObjectType.TRRECORD, OperateType.SHOWTRRECORD, "获取培训记录信息成功", trRecord.getId(), trRecord.getTrName(), true);
		}catch (NumberFormatException e) {
			logger.error("查看培训记录信息的参数id:{}不合法", id, e);
			LoggerServer.error(ObjectType.TRRECORD, OperateType.SHOWTRRECORD, "查看培训记录信息的参数id:["+id+"]不合法", null, null);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.TRRECORD, OperateType.SHOWTRRECORD, "获取培训记录信息失败", trRecord.getId(), trRecord.getTrName());
		}
		return "/record/train/editTrRecord";
	}
	
	/**
	 * 添加培训记录
	 * @param request
	 * @param response
	 * @param serTeam
	 * @return
	 */
	@Token(remove=true,customKey = "TrRecordController_addTrRecord")
	@RequestMapping(value = "/record/addTrRecord.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String addTrRecord(HttpServletRequest request, HttpServletResponse response, TrRecord trRecord) {
		ResultData data = new ResultData();
		BaseUser user = ShiroSessionMgr.getLoginUser();
		trRecord.setCrUser(user.getUserName());
		trRecord.setCrTime(new Date());
		//根据用户Id得到其所在服务队Id
		SerTeam serTeam= teamUserServiece.getSerTeamByUserId(user.getId());
		Long serTeamId=serTeam.getId();
		if (serTeamId==0 || serTeamId==null  ) {
			data.setSuccess(false);
			data.setMessage("当前用户没有服务队");
			data.setToken(request, "TrRecordController_addTrRecord");
			LoggerServer.error(ObjectType.TRRECORD, OperateType.CREATETRRECORD, "当前用户没有服务队", trRecord.getId(), trRecord.getTrName());
			return data.toJSONString();
		} 
		trRecord.setTeamId(serTeamId);
		//后台验证
		List<ValidateMessage> errors = ValidatorUtil.validate(trRecord, Groups.Add.class);
		if (null != errors && errors.size() > 0) {
			return ResultData.fail(errors).setToken(request, "TrRecordController_addTrRecord").toJSONString();
		}
		//去除培训记录名称中的特殊字符
		trRecord.setTrName(StringTools.FormatStrFromInput(trRecord.getTrName()));
		try {
			//判断培训名称是否已经存在于数据库中
			int exists = trRecordService.existsWithTrRecordName(trRecord.getTrName(),trRecord.getTeamId(), null);
			if (exists > 0) {
				data.setSuccess(false);
				data.setMessage("培训名称已存在");
				data.setToken(request, "TrRecordController_addTrRecord");
				return data.toJSONString();
			} else {
				boolean result = trRecordService.addTrRecord(trRecord);
				if (result) {
					data.setSuccess(true);
					data.setMessage("添加成功");
					LoggerServer.info(ObjectType.TRRECORD, OperateType.CREATETRRECORD, "新建培训记录信息成功", trRecord.getId(), trRecord.getTrName(), true);
					return data.toJSONString();
				} else {
					data.setSuccess(false);
					data.setMessage("添加失败");
					data.setToken(request, "TrRecordController_addTrRecord");
					LoggerServer.error(ObjectType.TRRECORD, OperateType.CREATETRRECORD, "新建培训记录失败", trRecord.getId(), trRecord.getTrName());
					return data.toJSONString();
				}
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("添加培训记录操作出现出错,name:{}", trRecord.getTrName(), e);
			LoggerServer.error(ObjectType.TRRECORD, OperateType.CREATETRRECORD, "添加培训记录操作出现出错", trRecord.getId(), trRecord.getTrName());
		}
		return data.toJSONString();
	}
	
	/**
	 * 批量删除培训记录
	 * @param reauest
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/record/delTrRecord.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String delTrRecord(HttpServletRequest reauest, HttpServletResponse response) {
		ResultData data = new ResultData();
		//要删除的培训记录ids
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		if (StringUtils.isBlank(ids)) {
			data.setSuccess(false);
			data.setMessage("删除培训记录参数不能为空");
			return data.toJSONString();
		}
		String[] keys = ids.split(",");
		long[] serTeamKeys = new long[keys.length];
		for (int i = 0; i < keys.length; i++) {
			try {
				long serTeamKey = Long.parseLong(keys[i]);
				serTeamKeys[i] = serTeamKey;
			} catch (Exception e) {
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("培训记录删除参数不合法,serTeamKeys:{}", ids, e);
				return data.toJSONString();
			}
		}
		try {
			Boolean delResult = trRecordService.deleteTrRecordKeys(serTeamKeys);
			if (delResult) {
				data.setSuccess(true);
				data.setMessage("培训记录删除成功");
				LoggerServer.info(ObjectType.TRRECORD, OperateType.DROPTRRECORD, "删除培训记录成功", null, null, true);
				return data.toJSONString();
			} else {
				data.setSuccess(false);
				data.setMessage("培训记录删除失败");
				LoggerServer.error(ObjectType.TRRECORD, OperateType.DROPTRRECORD, "删除培训记录失败", null, null);
				return data.toJSONString();
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("删除培训记录操作出现出错", e);
			LoggerServer.error(ObjectType.TRRECORD, OperateType.DROPTRRECORD, "删除培训记录失败", null, null);
		}
		return data.toJSONString();
	}
	
	/**
	 * 编辑培训记录页面
	 * @param request
	 * @param response
	 * @param serTeam
	 * @return
	 */
	@RequestMapping(value = "/record/editTrRecord.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String editTrRecord(HttpServletRequest request, HttpServletResponse response, TrRecord trRecord) {
		String key = ActionContext.getActionContext().getParameterAsString("id");
		ResultData data = new ResultData();
		Long id = null;
		TrRecord preTrRecord =null;
		try {
			id = Long.valueOf(key);
			preTrRecord=trRecordService.selectByKey(id);
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("修改培训记录信息失败");
			LoggerServer.error(ObjectType.TRRECORD, OperateType.EDITTRRECORD, "修改培训记录信息出错", id, "培训记录ID");
			return data.toJSONString();
		}
		//设置培训记录Id
		trRecord.setId(id);
		//后台验证
		List<ValidateMessage> errors = ValidatorUtil.validate(trRecord, Groups.Update.class);
		if (null != errors && errors.size() > 0) {
			return ResultData.fail(errors).toJSONString();
		}
		trRecord.setTrName(StringTools.FormatStrFromInput(trRecord.getTrName()));
		try {
			//判断修改的培训记录名称是否存在
			int exists = trRecordService.existsWithTrRecordName(trRecord.getTrName(), preTrRecord.getTeamId(),key);
			if (exists > 0) {
				data.setSuccess(false);
				data.setMessage("培训记录名称已存在");
				return data.toJSONString();
			} else {
				boolean result = trRecordService.updateTrRecord(trRecord);
				if (result) {
					data.setSuccess(true);
					data.setMessage("修改成功");
					LoggerServer.info(ObjectType.TRRECORD, OperateType.EDITTRRECORD, "修改培训记录信息成功【修改前："+preTrRecord.toString()+",修改后"+trRecord.toString()+"】", trRecord.getId(), trRecord.getTrName(), true);
					return data.toJSONString();
				} else {
					data.setSuccess(false);
					data.setMessage("修改失败");
					LoggerServer.error(ObjectType.TRRECORD, OperateType.EDITTRRECORD, "修改培训记录信息失败", trRecord.getId(), trRecord.getTrName());
					return data.toJSONString();
				}
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("修改培训记录操作出现出错,name:{}", trRecord.getTrName(), e);
			LoggerServer.error(ObjectType.TRRECORD, OperateType.EDITTRRECORD, "修改培训记录信息失败", trRecord.getId(), trRecord.getTrName());
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到添加志愿者页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/record/addVol.html")
	public String toAddTeamUser(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String id = ActionContext.getActionContext().getParameterAsString("tId");
		model.addAttribute("trainId", id);
		return "/record/train/addVol";
	}
	
	/**
	 * 显示可以被添加到培训记录的志愿者信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/record/showVols.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String showVols(HttpServletRequest request,HttpServletResponse response){
		String trainId = ActionContext.getActionContext().getParameterAsString("trainId");
		int selectType = ActionContext.getActionContext().getParameterAsInt("selectType");
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String selectName = ActionContext.getActionContext().getParameterAsString("selectName");
		ResultData data=new ResultData();
		//判断查询的条件
		if(selectType<0 || selectType >2){
			data.setSuccess(false);
			data.setMessage("查询的条件不合法");
			return data.toJSONString();
		}
		Long tId=null;
		String userName=null;
		String realName=null;
		if(!StringUtils.isBlank(trainId)){
			try{
				tId=Long.parseLong(trainId);
			}catch (Exception e) {
				logger.error("获取需要添加的志愿者列表的参数trainId:{}不合法",trainId,e);
				data.setSuccess(false);
				data.setMessage("查询的条件不合法");
				return data.toJSONString();
			}
		}
		if(currPage<0){
			currPage=1;
		}
		if (pageSize < 1) {
			pageSize = 10;
		}
		Volunteer vol =new Volunteer();
		if(selectType==1){
			userName=selectName;
			vol.setUserName(userName);
		}else if(selectType==2){
			realName=selectName;
			vol.setRealName(realName);
		}
		try{
			PageInfo<Volunteer> vols=trainVolService.getTrainVols(vol, tId, currPage, pageSize);
			
			data.setSuccess(true);
			data.put("page", vols);
			return data.toJSONString();
		}catch (Exception e) {
			logger.error("查看可以被添加的用户列表出现错误",e);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	/**
	 * 添加志愿者到服务队
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/record/addTrainVol.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addTrainVol(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		//要被添加到培训记录的志愿者ids
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		Long trainId= ActionContext.getActionContext().getParameterAsLong("trainId");
		if(StringUtils.isBlank(ids)){
			data.setSuccess(false);
			data.setMessage("添加的志愿者参数不能为空");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		List<Long> volKeys=new ArrayList<Long>();
		for (int i=0;i<keys.length;i++) {
			try{
				long volKey=Long.parseLong(keys[i]);
				volKeys.add(volKey);
			}catch (Exception e) {
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("添加的志愿者参数不合法,volKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try {
			Boolean addResult=trainVolService.addVolsWithTrainId(trainId, volKeys);
			if(addResult){
				data.setSuccess(true);
				data.setMessage("添加成功");
				LoggerServer.info(ObjectType.TRAINVOl, OperateType.CREATETRAINVOL, "添加志愿者到培训记录成功", null, null, true);
				return data.toJSONString();
			}else{
				data.setSuccess(false);
				data.setMessage("添加失败");
				LoggerServer.error(ObjectType.TRAINVOl, OperateType.CREATETRAINVOL, "添加志愿者到培训记录失败", null, null);
				return data.toJSONString();
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("将志愿者添加到培训记录中的操作出现出错",e);
			LoggerServer.error(ObjectType.TRAINVOl, OperateType.CREATETRAINVOL, "添加志愿者到培训记录失败", null, null);
		}
		return data.toJSONString();
	}
	
	/**
	 * 批量从培训记录中删除志愿者
	 * @param reauest
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/record/delTrainVol.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String delTeamUser(HttpServletRequest reauest, HttpServletResponse response) {
		ResultData data = new ResultData();
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		if (StringUtils.isBlank(ids)) {
			data.setSuccess(false);
			data.setMessage("删除志愿者参数不能为空");
			return data.toJSONString();
		}
		String[] keys = ids.split(",");
		long[] trainVolKeys = new long[keys.length];
		for (int i = 0; i < keys.length; i++) {
			try {
				long trainVolKey = Long.parseLong(keys[i]);
				trainVolKeys[i] = trainVolKey;
			} catch (Exception e) {
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("删除志愿者参数不合法,serTeamKeys:{}", ids, e);
				return data.toJSONString();
			}
		}
		try {
			Boolean delResult = trainVolService.deleteTrainVolKeys(trainVolKeys);
			if (delResult) {
				data.setSuccess(true);
				data.setMessage("志愿者删除成功");
				LoggerServer.info(ObjectType.TRAINVOl, OperateType.DROPTRAINVOL, "删除培训记录志愿者成功", null, null,true);
				return data.toJSONString();
			} else {
				data.setSuccess(false);
				data.setMessage("志愿者删除失败");
				LoggerServer.error(ObjectType.TRAINVOl, OperateType.DROPTRAINVOL,"删除培训记录志愿者失败", null, null);
				return data.toJSONString();
			}
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("删除志愿者操作出现出错", e);
			LoggerServer.error(ObjectType.TRAINVOl, OperateType.DROPTRAINVOL,"删除培训记录志愿者失败", null, null);
		}
		return data.toJSONString();
	}
	
	/**
	 * 根据用户的组织机构控制服务队的信息
	 * @param reauest
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/record/serTeamDisplay.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String serTeamDisplay(HttpServletRequest reauest, HttpServletResponse response){
		ResultData data = new ResultData();
		// 得到当前的登录用户
		BaseUser user = ShiroSessionMgr.getLoginUser();
		// 判断用户是不是超级管理员
		boolean isAdmin = user.isAdmin();
		if (isAdmin) {
			// 如果是超级管理员，可以进行操作.
			data.setSuccess(true);
			List<SerTeam> serTeamList = serTeamService.getSerTeamName(1l);
			data.put("serTeamList", serTeamList);
			return data.toJSONString();
		} 
		else{
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
				if (orgType == 1) {
					// 如果是管理机构，显示可管理的服务队
					List<SerTeam> serTeamList = serTeamService.getSerTeamName(key);
					data.setSuccess(true);
					data.put("serTeamList", serTeamList);
					return data.toJSONString();
				}
			}
			data.setSuccess(false);
			return data.toJSONString();
		}
		
	}
}
