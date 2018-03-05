package com.topstar.volunteer.web.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.topstar.volunteer.entity.Area;
import com.topstar.volunteer.log.LoggerServer;
import com.topstar.volunteer.log.ObjectType;
import com.topstar.volunteer.log.OperateType;
import com.topstar.volunteer.model.AreaView;
import com.topstar.volunteer.service.AreaService;
import com.topstar.volunteer.util.ResultData;
import com.topstar.volunteer.util.StringTools;
import com.topstar.volunteer.validator.ValidatorUtil;
import com.topstar.volunteer.validator.group.Groups;
import com.topstar.volunteer.validator.message.ValidateMessage;
import com.topstar.volunteer.web.context.ActionContext;

@Controller
public class AreaController {
	
	private static Logger logger=LoggerFactory.getLogger(AreaController.class);
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping("/area/list.html")
	public String toDicList(HttpServletRequest request,HttpServletResponse response){
		return "/sys/area/list";
	}
	
	@RequestMapping(value="/area/list.do",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public @ResponseBody String loadAreas(HttpServletRequest request,HttpServletResponse response){
		ResultData data=new ResultData();
		try{
			List<AreaView> areas=areaService.getAllArea();
			LoggerServer.info(ObjectType.AREA,OperateType.SHOWAREALIST, "成功显示所有区域列表信息",null,null,true);
			if(areas!=null){
				data.setSuccess(true);
				data.put("list", areas);
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.AREA,OperateType.SHOWAREALIST, "显示所有区域列表信息操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("获取区域数据出现内部错误");
		}
		return data.toJSONString();
	}
	
	/**
	 * 跳转到添加区域页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/area/addArea.html")
	public String toAddArea(HttpServletRequest request,HttpServletResponse response){
		return "/sys/area/addArea";
	}
	
	/**
	 * 跳转到选择上级区域页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/area/selectArea.html")
	public String toSelectArea(HttpServletRequest request,HttpServletResponse response){
		return "/sys/area/selectArea";
	}
	
	/**
	 * 获取指定的上级的下辖区域列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/area/loadArea.do",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	public @ResponseBody String loadArea(HttpServletRequest request,HttpServletResponse response){
		Long parentId=ActionContext.getActionContext().getParameterAsLong("id");
		ResultData data=new ResultData();
		try{
			List<AreaView> areaViews=areaService.getAreasByParentId(parentId);
			LoggerServer.info(ObjectType.AREA,OperateType.SHOWSUBAREALIST, "成功显示指定区域下的子区域列表信息操作",null,null,true);
			if(areaViews!=null && areaViews.size()>0){
				data.setSuccess(true);
				data.put("areaViews", areaViews);
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.AREA,OperateType.SHOWSUBAREALIST, "显示指定区域下的子区域列表信息操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("发生内部错误");
		}
		return data.toJSONString();
	}
	
	/**
	 * 添加区域
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/area/addArea.do",produces="text/plain;charset=UTF-8",method=RequestMethod.POST)
	public @ResponseBody String addArea(HttpServletRequest request,HttpServletResponse response){
		Long parentId=ActionContext.getActionContext().getParameterAsLong("parentId");
		String name=ActionContext.getActionContext().getParameterAsString("name");
		String code=ActionContext.getActionContext().getParameterAsString("code");
		Integer type=ActionContext.getActionContext().getParameterAsInt("type");
		String remark=ActionContext.getActionContext().getParameterAsString("remark");
		ResultData data=new ResultData();
		if(-1==parentId){
			parentId=0l;
		}
		try{
			Area area=new Area();
			area.setName(name);
			area.setCode(code);
			area.setParentId(parentId);
			area.setType(type);
			area.setRemark(remark);
			
			List<ValidateMessage> errors =ValidatorUtil.validate(area, Groups.Add.class);
			if(null!=errors&&errors.size()>0){
				return ResultData.fail(errors).toJSONString();
			}
			
			if(areaService.existsWithAreaName(area.getName(), null)){
				data.setSuccess(false);
				data.setMessage("区域已存在");
				return data.toJSONString();
			}
			
			boolean isValid=areaService.isAreaOperateValid(area, parentId);
			if(!isValid){
				data.setSuccess(false);
				data.setMessage("区域上下级关系不合法");
				return data.toJSONString();
			}
			
			area.setRemark(StringTools.FormatStrFromInput(remark));
			boolean addArea=areaService.addArea(area);
			if(addArea){
				LoggerServer.info(ObjectType.AREA,OperateType.CREATEAREA, "成功进行添加区域操作【"+area+"】",null,null,true);
				data.setSuccess(true);
				data.setMessage("区域添加成功");
				return data.toJSONString();
			}else{
				LoggerServer.info(ObjectType.AREA,OperateType.CREATEAREA, "添加区域操作失败【"+area+"】",null,null,false);
				data.setSuccess(false);
				data.setMessage("区域添加失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.AREA,OperateType.CREATEAREA, "添加区域操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("发生内部错误");
		}
		return data.toJSONString();
	}
	
	//删除区域
	@RequestMapping(value="/area/delArea.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String delArea(HttpServletRequest reauest,HttpServletResponse response){
		ResultData data=new ResultData();
		Long areaId=ActionContext.getActionContext().getParameterAsLong("id");
		try{
			int delArea=areaService.delAreaAndSubArea(areaId);
			if(delArea>0){
				data.setSuccess(true);
				data.setMessage("删除成功");
				return data.toJSONString();
			}else if(delArea==-1){
				data.setSuccess(false);
				data.setMessage("省份地区不能删除");
				return data.toJSONString();
			}else{
				data.setSuccess(false);
				data.setMessage("删除失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.AREA,OperateType.DELAREA, "删除区域操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("发生内部错误");
		}
		return data.toJSONString();
	}
	
	//查看区域
	@RequestMapping(value="/area/viewArea.html")
	public String viewArea(HttpServletRequest reauest,HttpServletResponse response,ModelMap model){
		Long areaId=ActionContext.getActionContext().getParameterAsLong("id");
		if(areaId!=null && areaId>0){
			Area area=areaService.selectByKey(areaId);
			if(area!=null){
				Long parentId=area.getParentId();
				Area parentArea=null;
				if(area!=null && parentId > 0){
					parentArea=areaService.selectByKey(parentId);
				}
				AreaView areaView=new AreaView(area);
				if(parentArea!=null){
					areaView.setParentName(parentArea.getName());
				}
				model.addAttribute("area", areaView);
			}
		}
		return "/sys/area/viewArea";
	}
	
	//跳转到修改区域页面
	@RequestMapping(value="/area/editArea.html")
	public String toEditArea(HttpServletRequest reauest,HttpServletResponse response,ModelMap model){
		Long areaId=ActionContext.getActionContext().getParameterAsLong("id");
		if(areaId!=null && areaId>0){
			Area area=areaService.selectByKey(areaId);
			if(area!=null){
				Long parentId=area.getParentId();
				Area parentArea=null;
				if(area!=null && parentId > 0){
					parentArea=areaService.selectByKey(parentId);
				}
				AreaView areaView=new AreaView(area);
				if(parentArea!=null){
					areaView.setParentName(parentArea.getName());
				}
				model.addAttribute("area", areaView);
			}
		}
		return "/sys/area/editArea";
	}
	
	//保存修改区域信息
	@RequestMapping(value="/area/saveArea.do",produces="text/plain;charset=UTF-8")
	public @ResponseBody String updateArea(HttpServletRequest reauest,HttpServletResponse response){
		Long id=ActionContext.getActionContext().getParameterAsLong("id");
		Long parentId=ActionContext.getActionContext().getParameterAsLong("parentId");
		String name=ActionContext.getActionContext().getParameterAsString("name");
		String code=ActionContext.getActionContext().getParameterAsString("code");
		Integer type=ActionContext.getActionContext().getParameterAsInt("type");
		String remark=ActionContext.getActionContext().getParameterAsString("remark");
		ResultData data=new ResultData();
		try{
			Area area=new Area();
			area.setId(id);
			area.setName(name);
			area.setCode(code);
			area.setParentId(parentId);
			area.setType(type);
			area.setRemark(remark);
			
			List<ValidateMessage> errors =ValidatorUtil.validate(area, Groups.Update.class);
			if(null!=errors&&errors.size()>0){
				return ResultData.fail(errors).toJSONString();
			}
			
			if(areaService.existsWithAreaName(area.getName(), area.getId())){
				data.setSuccess(false);
				data.setMessage("区域已存在");
				return data.toJSONString();
			}
			
			boolean isValid=areaService.isAreaOperateValid(area, parentId);
			if(!isValid){
				data.setSuccess(false);
				data.setMessage("区域上下级关系不合法");
				return data.toJSONString();
			}
			area.setRemark(StringTools.FormatStrFromInput(remark));
			int updateArea=areaService.saveArea(area);
			if(updateArea>0){
				data.setSuccess(true);
				data.setMessage("区域信息保存成功");
				return data.toJSONString();
			}else{
				data.setSuccess(false);
				data.setMessage("区域信息保存失败");
				return data.toJSONString();
			}
		}catch (Exception e) {
			LoggerServer.error(ObjectType.AREA,OperateType.EDITAREA, "编辑保存区域操作发生错误【"+e.getMessage()+"】",null,null);
			data.setSuccess(false);
			data.setMessage("发生内部错误");
		}
		return data.toJSONString();
	}
	

	/**
	 * 跳转到添加下级区域页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/area/addSubArea.html")
	public String toAddSubArea(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Long areaId=ActionContext.getActionContext().getParameterAsLong("id");
		Area area=areaService.selectByKey(areaId);
		if(area!=null){
			model.addAttribute("area", area);
		}
		return "/sys/area/addSubArea";
	}
	
}
