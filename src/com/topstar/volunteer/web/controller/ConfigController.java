package com.topstar.volunteer.web.controller;


import java.util.List;
import java.util.regex.Pattern;

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
import com.topstar.volunteer.entity.Config;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.service.ConfigService;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.StringTools;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;

@Controller
public class ConfigController {
	
	private static Logger logger=LoggerFactory.getLogger(ConfigController.class);
	
	@Autowired
	private ConfigService configService;
	
	@RequestMapping("/config/list.html")
	public String toDicList(HttpServletRequest request,HttpServletResponse response){
		return "/sys/config/list";
	}
	
	@RequestMapping(value="/config/configList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String configList(HttpServletRequest request,HttpServletResponse response){
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String name = ActionContext.getActionContext().getParameterAsString("name");
		String type= ActionContext.getActionContext().getParameterAsString("type");
		String remark = ActionContext.getActionContext().getParameterAsString("remark");
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		
		Config config=new Config();
		if(!StringUtils.isBlank(type)){
			config.setType(type);
		}
		config.setName(name);
		config.setRemark(remark);
		
		ResultData data=new ResultData();
		try{
			PageInfo<Config> configPage=configService.loadConfigPage(config, currPage, pageSize);
			data.setSuccess(true);
			data.put("page", configPage);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CONFIG,OperateType.SHOWCONFIGLIST, "分页显示系统配置列表信息操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询系统配置信息出错,pageIndex:{},pageSize:{},configName:{},remark:{}",currPage,pageSize,name,remark,e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到添加配置页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/config/addConfig.html")
	public String toAddConfig(HttpServletRequest request,HttpServletResponse response){
		return "/sys/config/addConfig";
	}
	
	@RequestMapping(value="/config/addConfig.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String addConfig(HttpServletRequest request ,HttpServletResponse response,Config config){
		ResultData data=new ResultData();
		String name=config.getName();
		
		List<ValidateMessage> errors =ValidatorUtil.validate(config, Groups.Add.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		config.setType(StringTools.filterForHTMLValue(config.getType().trim()));
		config.setRemark(StringTools.FormatStrFromInput(config.getRemark()));
		config.setContent(StringTools.FormatStrFromInput(config.getContent()));
		try{
			int exists=configService.existsWithConfigName(name, null);
			if(exists==0){
				boolean addConfig=configService.addConfig(config);
				if(addConfig){
					data.setSuccess(true);
					data.setMessage("配置添加成功");
					return data.toJSONString();
				}else{
					data.setSuccess(false);
					data.setMessage("配置添加失败");
					return data.toJSONString();
				}
			}else{
				data.setSuccess(false);
				data.setMessage("配置名称已存在,请重新输入");
				return data.toJSONString();
			}
			
		}catch(Exception e){
			LoggerServer.error(ObjectType.CONFIG,OperateType.CREATECONFIG, "创建系统配置操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("配置添加操作出现错误");
			logger.error("配置添加操作出现内部错误",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到查看配置信息页面
	 * @param request
	 * @param response
	 * @return
	 * @throws TPSException 
	 */
	@RequestMapping(value="/config/viewConfig.html")
	public String toViewConfig(HttpServletRequest request,HttpServletResponse response,ModelMap model ) throws TPSException{
		String id = ActionContext.getActionContext().getParameterAsString("vid");
		Long configId=null;
		try{
			configId=Long.valueOf(id);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CONFIG,OperateType.SHOWCONFIG, "查看系统配置信息操作参数id【"+id+"】不合法",null,null);
			throw new TPSException("不合法");
		}
		try{
			Config config=configService.selectByKey(configId);
			model.addAttribute("config", config);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CONFIG,OperateType.SHOWCONFIG, "查看系统配置信息操作发生错误【"+e.getMessage()+"】",null,null);
			logger.error("查看参数vid:{}的配置信息出错",id,e);
		}
		return "/sys/config/viewConfig";
	}
	
 	
	/**
	 * 跳转到编辑配置信息页面
	 * @param request
	 * @param response
	 * @return
	 * @throws TPSException 
	 */
	@RequestMapping(value="/config/editConfig.html")
	public String toEditConfig(HttpServletRequest request,HttpServletResponse response,ModelMap map) throws TPSException{
		String editConfigId = ActionContext.getActionContext().getParameterAsString("vid");
		
		if(!StringUtils.isBlank(editConfigId)){
			Long configId=null;
			try{
				configId=Long.valueOf(editConfigId);
			}catch (Exception e) {
				LoggerServer.error(ObjectType.CONFIG,OperateType.EDITCONFIG, "跳转到编辑系统配置信息操作参数id【"+editConfigId+"】不合法",null,null);
				throw new TPSException("不合法");
			}
			try{
				Config config=configService.selectByKey(configId);
				map.addAttribute("config", config);
				LoggerServer.info(ObjectType.CONFIG,OperateType.EDITCONFIG, "跳转到编辑系统配置信息",null,null,true);
			}catch(Exception e){
				LoggerServer.error(ObjectType.CONFIG,OperateType.EDITCONFIG, "跳转到编辑系统配置信息操作发生错误【"+e.getMessage()+"】",null,null);
				logger.error("编辑参数vid:{}配置信息操作出错",editConfigId,e);
			}
		}	
		return "/sys/config/editConfig";
	}
	
	/**
	 * 编辑保存配置信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/config/editConfig.do",produces="text/plain;charset=UTF-8",method = RequestMethod.POST)
	public @ResponseBody String editConfig(HttpServletRequest request,HttpServletResponse response,Config config){
		ResultData data=new ResultData();
		
		List<ValidateMessage> errors =ValidatorUtil.validate(config, Groups.Update.class);
		if(null!=errors&&errors.size()>0){
			return ResultData.fail(errors).toJSONString();
		}
		config.setType(StringTools.filterForHTMLValue(config.getType()));
		config.setRemark(StringTools.FormatStrFromInput(config.getRemark()));
		config.setContent(StringTools.FormatStrFromInput(config.getContent()));
		
		try{
			boolean updateConfig=configService.updateConfig(config);
			if(updateConfig){
				data.setSuccess(true);
				data.setMessage("配置保存成功");
				return data.toJSONString();
			}else{
				data.setSuccess(false);
				data.setMessage("配置保存失败");
				return data.toJSONString();
			}
		}catch(Exception e){
			LoggerServer.error(ObjectType.CONFIG,OperateType.EDITCONFIG, "编辑系统配置信息操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("配置修改操作出现错误");
			logger.error("配置修改操作出现内部错误",e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 批量删除配置信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/config/delConfig.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String delConfig(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		String ids = ActionContext.getActionContext().getParameterAsString("ids");
		if(StringUtils.isBlank(ids)){
			LoggerServer.error(ObjectType.CONFIG,OperateType.DELCONFIG, "删除系统配置信息操作参数ids不能为空",null,null);
			data.setSuccess(false);
			data.setMessage("删除配置参数不能为空");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		Long[] configKeys=new Long[keys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				long configKey=Long.parseLong(keys[i]);
				configKeys[i]=configKey;
			}catch (Exception e) {
				LoggerServer.error(ObjectType.CONFIG,OperateType.DELCONFIG, "删除系统配置信息操作参数ids【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("配置删除参数不合法,configKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try {
			Boolean delResult=configService.deleteConfig(configKeys);
			if(delResult){
				LoggerServer.info(ObjectType.CONFIG,OperateType.DELCONFIG, "成功删除系统配置信息",null,null,true);
				data.setSuccess(true);
				data.setMessage("配置删除成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.CONFIG,OperateType.DELCONFIG, "删除系统配置信息操作失败",null,null,false);
				data.setSuccess(false);
				data.setMessage("配置删除失败");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.CONFIG,OperateType.DELCONFIG, "删除系统配置信息操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("删除配置操作出现出错",e);
		}
		return data.toJSONString();
	}
	
	//加载所有配置类型数据
	@RequestMapping(value="config/getConfigTypes.do",produces="text/plain;chrset=UTF-8")
	public @ResponseBody String loadConfigTypes(HttpServletRequest request ,HttpServletResponse response){
		ResultData data=new ResultData();
		List<String> types= configService.getConfigType();
		if(types!=null  && types.size()>0){
			data.setSuccess(true);
			data.put("typeList", types);
			return data.toJSONString();
		}else{
			LoggerServer.error(ObjectType.CONFIG,OperateType.SHOWCONFIGTYPE, "获取配置类型操作发生错误",null,null);
			data.setSuccess(false);
			return data.toJSONString();
		}
	}
}
