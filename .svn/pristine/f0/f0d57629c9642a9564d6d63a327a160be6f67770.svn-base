package com.topstar.volunteer.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.entity.Activity;
import com.topstar.volunteer.entity.Appendix;
import com.topstar.volunteer.entity.Channel;
import com.topstar.volunteer.entity.Document;
import com.topstar.volunteer.entity.Site;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.exception.TPSException;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.model.MenuView;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.service.ActivityService;
import com.topstar.volunteer.service.ChannelService;
import com.topstar.volunteer.service.DocumentService;
import com.topstar.volunteer.service.SiteService;
import com.topstar.volunteer.service.UserService;
import com.topstar.volunteer.service.VolunteerService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;
import com.topstar.volunteer.util.ConfigUtils;
import com.topstar.volunteer.util.JSONUtils;
import com.topstar.volunteer.util.ResultData;

/**
 * @ClassName:  IndexController   
 * @Description:TODO(首页)   
 * @date:   2017年9月7日 下午7:28:55   
 *
 */
@Controller
public class IndexController {
	
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChannelService channelService;
	
	@Autowired
	private VolunteerService volunteerService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping(value="/main.html")
	public String index(HttpServletRequest request,ModelMap map){
		BaseUser user = ShiroSessionMgr.getLoginUser();
		Volunteer volunteer = new Volunteer();
		volunteer.setStatus(1);//待审核
		Activity activity = new Activity();
		activity.setStatus(1);//待提交
		PageInfo<VolunteerView> VolPageInfo = volunteerService.findVolunteerCheckByEntity(volunteer, "", 1, 10);
		PageInfo<Activity> actPageInfo =activityService.findByEntity(activity, "", 1, 10);
		activity.setStatus(2);//待发布
		PageInfo<Activity> subActPageInfo =activityService.findByEntity(activity, "", 1, 10);
		
		Document doc = new Document();
		doc.setStatus(2);//待发布的文档
		Long douNum=0L;
		try {
			//用户可见的当前栏目和字栏目的栏目ID集合 ,综合计算最后结果集
			List<Long>  channelIds = new ArrayList<Long>();
			List<Long> roleChannelIds =  null;
//			if(!user.isAdmin()){
				List<Site> siteList= siteService.getAllSite();
				for (Site site : siteList) {
					//用户可见的所有栏目ID
					roleChannelIds = userService.getAllChannelIdsNotINRecycle(site.getId());
					channelIds = roleChannelIds;
					if(!user.isAdmin() && (channelIds == null || channelIds.isEmpty())){
						douNum=0L;
					}else if(user.isAdmin() || (channelIds != null && !channelIds.isEmpty())){
						PageInfo<Document> douPageInfo = documentService.list(channelIds,doc, "", 1, 10);
						douNum =douPageInfo.getTotal();
					}
				}
//			}else{
//				PageInfo<Document> douPageInfo = documentService.list(null,doc, "", 1, 10);
//				douNum=douPageInfo.getTotal();
//			}
		} catch (Exception e) {
			String message = "查询条件：[siteId="+doc.getSiteId()+",channelId="+doc.getChannelId()+",title="+doc.getTitle()+",status="+doc.getStatus()+"]";
			LoggerServer.error(ObjectType.DOCUMENT, OperateType.SHOWDOCUMENTLIST, "分页查询文档信息出错,"+e.getMessage(), -1L,message);
			logger.error("分页查询文档信息出错,mesasge="+message,e);
		}
		
		//得到通知公告文档
		List<Document> docNoticeList=null;
		try {
			docNoticeList= documentService.getDocNotice();
		} catch (TPSException e) {
			logger.error("获取通知公告文档出错",e);
		}
		
		map.addAttribute("docNoticeList", docNoticeList);
		Long checkVolNum=VolPageInfo.getTotal();
		Long subActNum=actPageInfo.getTotal();
		Long actNum=subActPageInfo.getTotal();
		map.addAttribute("checkVolNum", checkVolNum==null?0:checkVolNum);
		map.addAttribute("subActNum", subActNum==null?0:subActNum);
		map.addAttribute("actNum", actNum==null?0:actNum);
		map.addAttribute("douNum", douNum==null?0:douNum);
		return "/main";
	}
	
	
	@RequestMapping(value = "/menus.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String menus(HttpServletRequest request){
		BaseUser user = ShiroSessionMgr.getLoginUser();
		if(user != null){
			List<MenuView> list = userService.findMenusByUserId(user.getId());
			if( list != null && !list.isEmpty()){
				return JSONUtils.toJSONString(list);
			}
		}
		return JSONUtils.toJSONString(null);
	}
	
	/**
	 * 当没有权限访问资源时，系统的跳转提示请求
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/unauth.html")
	public String unauth(HttpServletRequest request){
		return "/unauth";
	}
	
	@RequestMapping(value = "/center.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String center(HttpServletRequest request){
		BaseUser user = ShiroSessionMgr.getLoginUser();
		ResultData data = new ResultData();
		if(user == null){
			data.setSuccess(false);
		}else{
			data.setSuccess(true);
		}
		return data.toJSONString();
	}
	
	/**
	 * 查看文档的详细信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/index/viewDocument.html")
	public String viewDocument(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		String docId = request.getParameter("docId");
		Long id = 0L;
		try {
			id = Long.parseLong(docId);
		} catch (Exception e) {
			id = 0L;
		}
		if (id.longValue() > 0) {
			try {
				Document doc = documentService.selectByKey(id);
				if(doc != null){
					
					/**
					 * 是否显示提交按钮
					 */
					boolean isCommit = false;
					/**
					 * 是否显示发布按钮
					 */
					boolean isPub = false;
					Long channelId = doc.getChannelId();
					String idStr=ConfigUtils.getConfigContentByName("channel_id");
					Long tzggId =Long.parseLong(idStr);
					if(channelId.longValue() != tzggId.longValue()){
						logger.error("查看文档[ID="+id+"]的详细信息出错,您没有权限查看此文档");
						return "/index/viewDocument";
					}
					Channel channel = channelService.selectByKey(channelId);
					request.setAttribute("doc", doc);
					request.setAttribute("channel", channel);
					request.setAttribute("isCommit", isCommit);
					request.setAttribute("isPub", isPub);
					List<Appendix> appendixList = documentService.selectAppendix(doc.getId());
					if(appendixList != null  && ! appendixList.isEmpty()){
						String fileInfo = documentService.fileInfoToXml(appendixList);
						request.setAttribute("fileInfo", fileInfo);
					}
				}
			} catch (Exception e) {
				logger.error("查看文档[ID="+id+"]的详细信息出错",e);
				LoggerServer.error(ObjectType.DOCUMENT, OperateType.SHOWDOCUMENT, "查看文档信息失败，"+e.getMessage(), id, "[文档ID="+id+"]");
			}
			
		}
		return "/index/viewDocument";
	}
	
}
