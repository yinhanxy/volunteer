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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.CheckTime;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.service.CheckTimeService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;

/**
 * 证书年检时间管理控制器
 * @author TRS
 *
 */
@Controller
public class CheckTimeController {
	
	private static Logger logger=LoggerFactory.getLogger(CheckTimeController.class);
	
	@Autowired
	private CheckTimeService checkTimeService;
	
	@RequestMapping("/cert/checkTimeList.html")
	public String toCheckTimeList(HttpServletRequest request,HttpServletResponse response){
		return "/sys/checkTime/list";
	}
	
	@RequestMapping(value="/cert/checkTimeList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String checkTimeList(HttpServletRequest request,HttpServletResponse response){
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String year = ActionContext.getActionContext().getParameterAsString("year");
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		
		CheckTime checkTime=new CheckTime();
		checkTime.setYear(year);
		
		ResultData data=new ResultData();
		try{
			PageInfo<CheckTime> checkTimePage=checkTimeService.loadCheckTimePage(checkTime, currPage, pageSize);
			data.setSuccess(true);
			data.put("page", checkTimePage);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CHECKTIME,OperateType.SHOWCHECKTIMELIST, "分页显示证书年检时间配置列表信息操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到添加年检时间页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/cert/addCheckTime.html")
	public String toAddCheckTime(HttpServletRequest request,HttpServletResponse response){
		return "/sys/checkTime/addCheckTime";
	}
	
	@RequestMapping(value="/cert/addCheckTime.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addCheckTime(HttpServletRequest request ,HttpServletResponse response,CheckTime checkTime){
		ResultData data=new ResultData();
		
		List<ValidateMessage> errors =ValidatorUtil.validate(checkTime, Groups.Add.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		
		checkTime.setCrtUser(ShiroSessionMgr.getLoginUser().getUserName());
		checkTime.setCrtTime(new Date());
		String year=checkTime.getYear();
		try{
			int exists=checkTimeService.existsWithCheckYear(year, null);
			if(exists==0){
				int addCheckTime=checkTimeService.insert(checkTime);
				if(addCheckTime>0){
					data.setSuccess(true);
					data.setMessage("添加成功");
					return data.toJSONString();
				}else{
					data.setSuccess(false);
					data.setMessage("添加失败");
					return data.toJSONString();
				}
			}else{
				data.setSuccess(false);
				data.setMessage("该年份已存在");
				return data.toJSONString();
			}
			
		}catch(Exception e){
			LoggerServer.error(ObjectType.CHECKTIME,OperateType.CREATECHECKTIME, "新增证书年检时间操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("新增证书年检时间操作出现错误");
			logger.error("新增证书年检时间操作出现内部错误",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到编辑年检时间配置信息页面
	 * @param request
	 * @param response
	 * @return
	 * @throws TPSException 
	 */
	@RequestMapping(value="/cert/editCheckTime.html")
	public String toEditCheckTime(HttpServletRequest request,HttpServletResponse response,ModelMap map) throws TPSException{
		String editCheckTimeId = ActionContext.getActionContext().getParameterAsString("ctid");
		
		if(!StringUtils.isBlank(editCheckTimeId)){
			Long checkTimeId=null;
			try{
				checkTimeId=Long.valueOf(editCheckTimeId);
			}catch (Exception e) {
				LoggerServer.error(ObjectType.CHECKTIME,OperateType.EDITCHECKTIME, "跳转到编辑年检时间配置信息操作参数id【"+editCheckTimeId+"】不合法",null,null);
				throw new TPSException("不合法");
			}
			try{
				CheckTime checkTime=checkTimeService.selectByKey(checkTimeId);
				map.addAttribute("checkTime", checkTime);
			}catch(Exception e){
				LoggerServer.error(ObjectType.CHECKTIME,OperateType.EDITCHECKTIME, "跳转到编辑年检时间配置信息操作发生错误【"+e.getMessage()+"】",null,null);
				logger.error("编辑参数ctId:{}配置信息操作出错",checkTimeId,e);
			}
		}	
		return "/sys/checkTime/editCheckTime";
	}
	
	/**
	 * 编辑保存年检时间配置信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/cert/editCheckTime.do",produces="text/plain;charset=UTF-8",method = RequestMethod.POST)
	public @ResponseBody String editCheckTime(HttpServletRequest request,HttpServletResponse response,CheckTime checkTime){
		ResultData data=new ResultData();
		
		List<ValidateMessage> errors =ValidatorUtil.validate(checkTime, Groups.Update.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		checkTime.setCrtUser(ShiroSessionMgr.getLoginUser().getUserName());
		checkTime.setCrtTime(new Date());
		
		try{
			int updateCheckTime=checkTimeService.updateNotNull(checkTime);
			if(updateCheckTime>0){
				data.setSuccess(true);
				data.setMessage("保存成功");
				return data.toJSONString();
			}else{
				data.setSuccess(false);
				data.setMessage("保存失败");
				return data.toJSONString();
			}
		}catch(Exception e){
			LoggerServer.error(ObjectType.CHECKTIME,OperateType.EDITCHECKTIME, "编辑年检时间配置信息操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("年检时间配置修改操作出现错误");
			logger.error("年检时间配置修改操作出现内部错误",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 批量删除年检时间配置信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/cert/delCheckTime.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String delCheckTime(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		if(StringUtils.isBlank(ids)){
			LoggerServer.error(ObjectType.CHECKTIME,OperateType.DELCHECKTIME, "删除年检时间配置信息操作参数ids不能为空",null,null);
			data.setSuccess(false);
			data.setMessage("删除年检时间参数不能为空");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		Long[] checkTimeKeys=new Long[keys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				long checkTimeKey=Long.parseLong(keys[i]);
				checkTimeKeys[i]=checkTimeKey;
			}catch (Exception e) {
				LoggerServer.error(ObjectType.CHECKTIME,OperateType.DELCHECKTIME, "删除年检时间配置信息操作参数ids【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("年检时间删除参数不合法,checkTimeKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try {
			Boolean delResult=checkTimeService.deleteCheckTime(checkTimeKeys);
			if(delResult){
				data.setSuccess(true);
				data.setMessage("删除成功");
				return data.toJSONString();
			}else{
				data.setSuccess(false);
				data.setMessage("删除失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.CHECKTIME,OperateType.DELCHECKTIME, "删除年检时间配置信息操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("删除年检时间操作出现出错",e);
		}
		return data.toJSONString();
	}
	
}
