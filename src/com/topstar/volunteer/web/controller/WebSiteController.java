package com.topstar.volunteer.web.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.entity.Site;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.ChannelView;
import com.topstar.volunteer.service.ChannelService;
import com.topstar.volunteer.service.DocumentService;
import com.topstar.volunteer.service.SiteService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.StringTools;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;

@Controller
public class WebSiteController {
	
	private static Logger logger = LoggerFactory.getLogger(WebSiteController.class);
	
	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private DocumentService documentService;
	
	@RequestMapping("/channel/list.html")
	public String toDicList(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		return "/webSite/channel/list";
	}
	
	@RequestMapping(value="/channel/allSites.do",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public @ResponseBody String loadSites(HttpServletRequest request,HttpServletResponse response){
		ResultData data=new ResultData();
		try{
			/*List<ChannelView> channels= channelService.getAllChannelBySiteId();*/
			List<ChannelView> channels= channelService.getAllChannelBySiteIdInCache();
			if(channels!=null){
				data.setSuccess(true);
				data.put("channels",channels);
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CHANNEL,OperateType.LOADSITESANDTOPCHANNEL, "站点下顶级栏目的查询操作失败【"+e.getMessage()+"】",null,null);
			logger.error("获取站点下的栏目信息列表出现内部错误",e);
			data.setSuccess(false);
			data.setMessage("获取站点下栏目数据出现内部错误");
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到选择父栏目页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/channel/selectChannel.html")
	public String toSelectArea(HttpServletRequest request,HttpServletResponse response){
		return "/webSite/channel/selectChannel";
	}
	
	/**
	 * 获取指定的上级的下栏目列表
	 * @param request
	 * @param response
	 * @return
	 *//*
	@RequestMapping(value="/channel/loadChannel.do",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	public @ResponseBody String loadChannel(HttpServletRequest request,HttpServletResponse response){
		Long parentId=ActionContext.getActionContext().getParameterAsLong("id");
		ResultData data=new ResultData();
		try{
			List<ChannelView>  channels=channelService.getChannelsByParentId(parentId);
			if(channels!=null && channels.size()>0){
				data.setSuccess(true);
				data.put("channels", channels);
				return data.toJSONString();
			}else{
				data.setSuccess(false);
				return data.toJSONString();
			}
		}catch (Exception e) {
			logger.error("获取指定栏目的子栏目信息列表失败",e);
			data.setSuccess(false);
			data.setMessage("发生内部错误");
		}
		return data.toJSONString();
	}*/
	
	/**
	 * 获取指定的上级的下栏目列表
	 * @param request
	 * @param response
	 * @return
	 */
	/*@RequestMapping(value="/channel/selectChannels.do",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	public @ResponseBody String selectChannels(HttpServletRequest request,HttpServletResponse response){
		Long parentId=ActionContext.getActionContext().getParameterAsLong("id");
		ResultData data=new ResultData();
		try{
			List<ChannelView>  channels=channelService.getChannelsByParentId(parentId);
			if(channels!=null && channels.size()>0){
				data.setSuccess(true);
				data.put("channels", channels);
				return data.toJSONString();
			}else{
				data.setSuccess(false);
				return data.toJSONString();
			}
		}catch (Exception e) {
			logger.error("获取指定栏目的子栏目信息列表失败",e);
			data.setSuccess(false);
			data.setMessage("发生内部错误");
		}
		return data.toJSONString();
	}*/
	
	/**
	 * 获取移动栏目时的栏目树
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/channel/channelList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String channelList(HttpServletRequest request,HttpServletResponse response){
		Long channelId = ActionContext.getActionContext().getParameterAsLong("channelId");
		LoggerServer.debug(ObjectType.CHANNEL,OperateType.SHOWMOVECHANNEL, "获取移动栏目时的栏目树的查询操作参数channelId为【"+channelId+"】",null,null,true);
		ResultData data = new ResultData();
		try {
			List<ChannelView> channels= channelService.getChannelMoveTree(channelId);
			/*List<ChannelView> channels= channelService.getAllChannels(channelId);*/
			if(channels!=null && channels.size()>0){
				data.setSuccess(true);
				data.put("channels",channels);
				return data.toJSONString();
			}else{
				data.setSuccess(false);
				data.setMessage("栏目结构为空");
				return data.toJSONString();
			}
		} catch (Exception e) {
			LoggerServer.error(ObjectType.CHANNEL,OperateType.SHOWMOVECHANNEL, "获取移动栏目时的栏目树失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("获取栏目结构时后台出错",e);
		}
		return data.toJSONString();
	}
	
	
	/**
	 * 获取指定栏目下的直接子栏目列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/channel/channelSelect.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String channelSelect(HttpServletRequest request,HttpServletResponse response){
		ResultData data = new ResultData();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String channelId = ActionContext.getActionContext().getParameterAsString("parentId");
		String channelCondition = ActionContext.getActionContext().getParameterAsString("channelCondition");
		Long parentId=null;
		try{
			parentId=Long.parseLong(channelId);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CHANNEL,OperateType.CHANNELSELECT, "指定栏目下的直接子栏目列表参数信息【"+channelId+"】不合法",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("栏目的参数parentId不合法",e);
			return data.toJSONString();
		}
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		String orderBy = "";
		
		try {
			PageInfo<Channel> pageInfo = channelService.findSubChannelsByParentId(parentId,channelCondition, orderBy, currPage, pageSize);
			data.setSuccess(true);
			data.put("page",pageInfo);
			data.put("parentId",parentId);
			return data.toJSONString();
		} catch (Exception e) {
			LoggerServer.error(ObjectType.CHANNEL,OperateType.CHANNELSELECT, "指定栏目下的直接子栏目列表失败【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage(e.getMessage());
			logger.error("分页查询子栏目出错,pageIndex:{},pageSize:{},parentId:{}",currPage,pageSize,parentId,e);
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到添加栏目页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/channel/addChannel.html")
	public String toAddChannel(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Long channelId=ActionContext.getActionContext().getParameterAsLong("channelId");
		List<ChannelView> channels=null;
		List<Site> sites=siteService.getAllSite();
		if(sites!=null)
			model.addAttribute("site", sites.get(0));
		if(channelId!=-1){
			Channel channel=channelService.selectByKey(channelId);
			if(channel!=null){
				channels=channelService.findChannelByParentId(channelId);
				model.addAttribute("channel", channel);
			}
		}else{
			if(channels==null ||channels.size()<1 ){
				channels=channelService.findTopChannelBySiteId();
			}
		}
		model.put("channels", channels);
		return "/webSite/channel/addChannel";
	}
	
	/**
	 * 获取指定栏目下的直接子栏目列表(返回json串数据)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/channel/getSubChannel.do",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	public @ResponseBody String getSubChannel(HttpServletRequest request,HttpServletResponse response){
		Long parentId=ActionContext.getActionContext().getParameterAsLong("id");
		ResultData data=new ResultData();
		try{
			List<ChannelView> channels=channelService.findChannelByParentId(parentId);
			data.put("channels", channels);
			data.setSuccess(true);
			return data.toJSONString();
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CHANNEL,OperateType.CHANNELSELECT, "指定栏目下的直接子栏目json串数据失败【"+e.getMessage()+"】",null,null);
			logger.error("获取栏目信息失败",e);
			data.setSuccess(false);
			data.setMessage("获取指定栏目的直接下级栏目时发生内部错误");
		}
		return data.toJSONString();
	}
	
	/**
	 * 添加栏目
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/channel/addChannel.do",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	public @ResponseBody String addChannel(HttpServletRequest request,HttpServletResponse response){
		Long siteId=ActionContext.getActionContext().getParameterAsLong("siteId");
		Long parentId=ActionContext.getActionContext().getParameterAsLong("parentChannelId");
		String channelName=ActionContext.getActionContext().getParameterAsString("channelName");
		String channelDesc=ActionContext.getActionContext().getParameterAsString("channelDesc");
		String previousChannel=ActionContext.getActionContext().getParameterAsString("previousChannel");
		ResultData data=new ResultData();
		try{
			Long previousId=Long.parseLong(previousChannel);
			
			Channel channel=new Channel();
			channel.setSiteId(siteId);
			channel.setChnlName(channelName);
			channel.setChnlDesc(channelDesc);
			channel.setParentId(parentId);
			
			BaseUser crtUser=ShiroSessionMgr.getLoginUser();
			channel.setCrTime(new Timestamp(new Date().getTime()));
			channel.setCrUser(crtUser.getUserName());
			channel.setStatus(1);
			channel.setOperUser(crtUser.getUserName());
			channel.setOperTime(new Timestamp(new Date().getTime()));
			
			List<ValidateMessage> errors =ValidatorUtil.validate(channel, Groups.Add.class);
			if(null!=errors&&errors.size()>0){
				return ResultData.fail(errors).toJSONString();
			}
			
			channel.setChnlDesc(StringTools.FormatStrFromInput(channelDesc));
			
			boolean isExist=channelService.existsWithChannelName(channelName, null);
			if(isExist){
				data.setSuccess(false);
				data.setMessage("栏目的唯一标识重复,请重新输入!");
				return data.toJSONString();
			}else{
				Map<String,Object> addChannel=channelService.addChannel(channel,previousId);
				if((boolean) addChannel.get("result")){
					LoggerServer.info(ObjectType.CHANNEL,OperateType.CREATECHANNEL, "添加栏目成功", channel.getId(),channel.getChnlName(),true);
					data.put("order", addChannel.get("order"));
					data.put("addChannelId", addChannel.get("channelId"));
					data.setSuccess(true);
					data.setMessage("栏目添加成功");
					return data.toJSONString();
				}else{
					LoggerServer.info(ObjectType.CHANNEL,OperateType.CREATECHANNEL, "添加栏目失败", channel.getId(),channel.getChnlName(),false);
					data.setSuccess(false);
					data.setMessage("栏目添加失败");
					return data.toJSONString();
				}
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CHANNEL,OperateType.CREATECHANNEL, "添加栏目信息失败【"+e.getMessage()+"】",null,null);
			logger.error("添加栏目信息失败",e);
			data.setSuccess(false);
			data.setMessage("添加栏目时发生内部错误");
		}
		return data.toJSONString();
	}
	
	//将栏目放入回收站
	@RequestMapping(value="/channel/channelRecycle.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String toRecycle(HttpServletRequest reauest,HttpServletResponse response){
		ResultData data=new ResultData();
		Long channelId=ActionContext.getActionContext().getParameterAsLong("channelId");
		try{
			boolean moveRecycle=channelService.saveChannelRecycle(channelId);
			if(moveRecycle){
				LoggerServer.info(ObjectType.CHANNEL,OperateType.DELCHANNEL, "成功将栏目放入回收站中栏目id为:"+channelId, channelId,null,true);
				data.setSuccess(true);
				data.setMessage("已放入回收站");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.CHANNEL,OperateType.DELCHANNEL, "栏目放入回收站操作失败,操作栏目id为:"+channelId, channelId,null,false);
				data.setSuccess(false);
				data.setMessage("操作失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CHANNEL,OperateType.DELCHANNEL, "栏目移动到回收站失败【"+e.getMessage()+"】",null,null);
			logger.error("栏目移动到回收站操作发生错误",e);
			data.setSuccess(false);
			data.setMessage("栏目移动到回收站时发生内部错误");
		}
		return data.toJSONString();
	}
	
	@RequestMapping("/channel/recycleList.html")
	public String toRecycleList(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String channelId=ActionContext.getActionContext().getParameterAsString("channelId");
		model.addAttribute("channelId", channelId);
		return "/webSite/channel/recycleList";
	}
	
	//查询回收站中栏目的信息列表
	@RequestMapping(value="/channel/recycleList.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String RecycleList(HttpServletRequest reauest,HttpServletResponse response){
		ResultData data=new ResultData();
		int currPage = ActionContext.getActionContext().getParameterAsInt("page");
		int pageSize = ActionContext.getActionContext().getParameterAsInt("rows");
		String channelName=ActionContext.getActionContext().getParameterAsString("channelName");
		String id=ActionContext.getActionContext().getParameterAsString("channelId");
		if(StringUtils.isBlank(channelName)){
			channelName=null;
		}
		Long channelId=null;
		try {
			channelId=Long.parseLong(id);
		} catch (Exception e) {
			data.setSuccess(false);
			data.setMessage("查询回收站栏目列表的参数不合法");
			return data.toJSONString();
		}
		if(currPage == 0){
			currPage = 1;
		}
		if(pageSize< 1){
			pageSize = 10;
		}
		String orderBy = "";
		
		try{
			PageInfo<Channel> recycleChannels=channelService.getRecycleChannels(channelId,channelName, orderBy, currPage, pageSize);
			if(recycleChannels!=null){
				data.setSuccess(true);
				data.put("page",recycleChannels);
				return data.toJSONString();
			}else{
				data.setSuccess(false);
				data.setMessage("查询回收站栏目列表失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CHANNEL,OperateType.SHOWRECYCLECHANNEL, "查询回收站中的栏目信息失败【"+e.getMessage()+"】",null,null);
			logger.error("查询回收站中的栏目信息时发生错误",e);
			data.setSuccess(false);
			data.setMessage("查询回收站栏目时发生内部错误");
		}
		return data.toJSONString();
	}
	
	//删除栏目
	@RequestMapping(value="/channel/delChannels.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String delChannels(HttpServletRequest reauest,HttpServletResponse response){
		ResultData data=new ResultData();
		String ids = ActionContext.getActionContext().getParameterAsString("channelIds");
		if(StringUtils.isBlank(ids)){
			LoggerServer.error(ObjectType.CHANNEL,OperateType.DROPCHANNEL, "彻底删除回收站中的栏目操作参数不合法",null,null);
			data.setSuccess(false);
			data.setMessage("删除栏目参数不能为空");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		Long[] channelKeys=new Long[keys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				long channelKey=Long.parseLong(keys[i]);
				channelKeys[i]=channelKey;
			}catch (Exception e) {
				LoggerServer.error(ObjectType.CHANNEL,OperateType.DROPCHANNEL, "彻底删除回收站中的栏目操作参数【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("删除栏目参数不合法,channelKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try{
			boolean delChannel=channelService.delChannelAndSubChnnel(channelKeys);
			if(delChannel){
				LoggerServer.info(ObjectType.CHANNEL,OperateType.DROPCHANNEL, "成功彻底删除回收站中的栏目,操作栏目id为:"+channelKeys, null,null,true);
				data.setSuccess(true);
				data.setMessage("删除成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.CHANNEL,OperateType.DROPCHANNEL, "彻底删除回收站中的栏目操作失败,操作栏目id为:"+channelKeys, null,null,false);
				data.setSuccess(false);
				data.setMessage("删除失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CHANNEL,OperateType.DROPCHANNEL, "彻底删除回收站中的栏目操作发生错误【"+e.getMessage()+"】",null,null);
			logger.error("删除栏目信息发生错误",e);
			data.setSuccess(false);
			data.setMessage("删除栏目时发生内部错误");
		}
		return data.toJSONString();
	}
	
	
	/**
	 *还原栏目 
	 */
	@RequestMapping(value="/channel/restoreChannels.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String restoreChannels(HttpServletRequest reauest,HttpServletResponse response){
		ResultData data=new ResultData();
		String ids = ActionContext.getActionContext().getParameterAsString("channelIds");
		if(StringUtils.isBlank(ids)){
			LoggerServer.error(ObjectType.CHANNEL,OperateType.RESTORECHANNEL, "还原栏目参数不合法",null,null);
			data.setSuccess(false);
			data.setMessage("还原栏目参数不能为空");
			return data.toJSONString();
		}
		String[] keys=ids.split(",");
		Long[] channelKeys=new Long[keys.length];
		for (int i=0;i<keys.length;i++) {
			try{
				long channelKey=Long.parseLong(keys[i]);
				channelKeys[i]=channelKey;
			}catch (Exception e) {
				LoggerServer.error(ObjectType.CHANNEL,OperateType.RESTORECHANNEL, "还原栏目参数【"+ids+"】不合法",null,null);
				data.setSuccess(false);
				data.setMessage("参数不合法");
				logger.error("还原栏目参数不合法,channelKeys:{}",ids,e);
				return data.toJSONString();
			}
		}
		try{
			boolean restoreChannel=channelService.restoreChannelAndSubChnnel(channelKeys);
			if(restoreChannel){
				LoggerServer.info(ObjectType.CHANNEL,OperateType.RESTORECHANNEL, "成功还原栏目操作,操作栏目id为:"+channelKeys, null,null,true);
				data.setSuccess(true);
				data.setMessage("栏目还原成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.CHANNEL,OperateType.RESTORECHANNEL, "还原栏目操作失败,操作栏目id为:"+channelKeys, null,null,false);
				data.setSuccess(false);
				data.setMessage("栏目还原失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CHANNEL,OperateType.RESTORECHANNEL, "还原栏目操作发生错误【"+e.getMessage()+"】",null,null);
			logger.error("还原栏目信息发生错误",e);
			data.setSuccess(false);
			data.setMessage("还原栏目时发生内部错误");
		}
		return data.toJSONString();
	}
	
	
	
	//跳转到修改栏目页面
	@RequestMapping(value="/channel/editChannel.html")
	public String toEditChannel(HttpServletRequest reauest,HttpServletResponse response,ModelMap model){
		Long channelId=ActionContext.getActionContext().getParameterAsLong("channelId");
		if(channelId!=null){
			Channel channel=channelService.selectByKey(channelId);
			Channel parentChannel=null;
			if(channel!=null){
				parentChannel=channelService.selectByKey(channel.getParentId());
				List<Channel> channels=channelService.getBroChannelsByParentId(channel);
				model.addAttribute("channels", channels);
				Site site=siteService.selectByKey(channel.getSiteId());
				model.addAttribute("site", site);
			}
			model.addAttribute("channel", channel);
			model.addAttribute("parentChannel", parentChannel);
		}
		return "/webSite/channel/editChannel";
	}
	
	//保存修改栏目信息
	@RequestMapping(value="/channel/saveChannel.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String saveChannel(HttpServletRequest reauest,HttpServletResponse response){
		Long id=ActionContext.getActionContext().getParameterAsLong("channelId");
		String name=ActionContext.getActionContext().getParameterAsString("channelName");
		String desc=ActionContext.getActionContext().getParameterAsString("channelDesc");
		String previousChannel=ActionContext.getActionContext().getParameterAsString("previousChannel");
		Long order=ActionContext.getActionContext().getParameterAsLong("order");
		Long parentId=ActionContext.getActionContext().getParameterAsLong("parentId");
		ResultData data=new ResultData();
		try{
			Long previousId=Long.parseLong(previousChannel);
			Channel channel=channelService.selectByKey(id);
			
			channel.setId(id);
			channel.setChnlName(name);
			channel.setChnlDesc(desc);
			channel.setOrder(order);
			channel.setParentId(parentId);
			BaseUser crtUser=ShiroSessionMgr.getLoginUser();
			channel.setOperUser(crtUser.getUserName());
			channel.setOperTime(new Timestamp(new Date().getTime()));
			List<ValidateMessage> errors =ValidatorUtil.validate(channel, Groups.Update.class);
			if(null!=errors&&errors.size()>0){
				return ResultData.fail(errors).toJSONString();
			}
			channel.setChnlDesc(StringTools.FormatStrFromInput(desc));
			boolean isExist=channelService.existsWithChannelName(name, id.toString());
			if(isExist){
				data.setSuccess(false);
				data.setMessage("栏目的唯一标识重复,请重新输入!");
				return data.toJSONString();
			}else{
				Map<String,Object> saveChannel=channelService.saveChannel(channel,previousId);
				if((boolean) saveChannel.get("result")){
					LoggerServer.info(ObjectType.CHANNEL,OperateType.EDITCHANNEL, "成功更新栏目信息,操作栏目id为:"+channel.getId(), channel.getId(),channel.getChnlName(),true);
					data.put("order", saveChannel.get("order"));
					data.setSuccess(true);
					data.setMessage("栏目信息修改成功");
					return data.toJSONString();
				}else{
					LoggerServer.info(ObjectType.CHANNEL,OperateType.EDITCHANNEL, "栏目信息更新操作失败,操作栏目id为:"+channel.getId(), channel.getId(),channel.getChnlName(),false);
					data.setSuccess(false);
					data.setMessage("栏目信息修改失败");
					return data.toJSONString();
				}
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CHANNEL,OperateType.EDITCHANNEL, "栏目信息更新操作发生错误【"+e.getMessage()+"】",null,null);
			logger.error("栏目信息更新发生错误",e);
			data.setSuccess(false);
			data.setMessage("更新栏目时发生内部错误");
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到栏目移动页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/channel/selectMoveChannel.html")
	public String selectChannel(HttpServletRequest request,HttpServletResponse response,ModelMap map){
		Long channelId=ActionContext.getActionContext().getParameterAsLong("channelId");
		map.addAttribute("channelId",channelId);
		return "/webSite/channel/selectMoveChannel";
	}
	
	/**
	 *移动栏目 
	 */
	@RequestMapping(value="/channel/moveChannel.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String moveChannels(HttpServletRequest reauest,HttpServletResponse response){
		ResultData data=new ResultData();
		String channelId= ActionContext.getActionContext().getParameterAsString("channelId");
		String parentChannelId= ActionContext.getActionContext().getParameterAsString("parentChannelId");
		if(StringUtils.isBlank(channelId) ||StringUtils.isBlank(parentChannelId)){
			LoggerServer.error(ObjectType.CHANNEL,OperateType.MOVECHANNEL, "移动栏目参数不能为空",null,null);
			data.setSuccess(false);
			data.setMessage("移动栏目参数不合法");
			return data.toJSONString();
		}
		Long srcChannelId=null;
		Long targetChannelId=null;
		
		try{
			srcChannelId=Long.parseLong(channelId);
			targetChannelId=Long.parseLong(parentChannelId);
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CHANNEL,OperateType.MOVECHANNEL, "移动栏目参数【"+channelId+","+parentChannelId+"】不合法",null,null);
			data.setSuccess(false);
			data.setMessage("参数不合法");
			logger.error("移动栏目参数不合法,srcChannelId与targetChannelId:{}",srcChannelId+targetChannelId,e);
			return data.toJSONString();
		}
		try{
			boolean moveChannel=channelService.moveChannelToChannel(srcChannelId, targetChannelId);
			if(moveChannel){
				LoggerServer.info(ObjectType.CHANNEL,OperateType.MOVECHANNEL, "成功移动栏目结构操作,栏目id["+srcChannelId+"]移动到栏目id["+targetChannelId+"]下", srcChannelId,null,true);
				data.setSuccess(true);
				data.setMessage("栏目移动成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.CHANNEL,OperateType.MOVECHANNEL, "移动栏目结构操作失败,栏目id["+srcChannelId+"]移动到栏目id["+targetChannelId+"]下", srcChannelId,null,false);
				data.setSuccess(false);
				data.setMessage("栏目移动失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.CHANNEL,OperateType.MOVECHANNEL, "移动栏目结构操作发生错误【"+e.getMessage()+"】",null,null);
			logger.error("移动栏目结构发生错误",e);
			data.setSuccess(false);
			data.setMessage("移动栏目结构时发生内部错误");
		}
		return data.toJSONString();
	}

}
