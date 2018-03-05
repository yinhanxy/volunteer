package com.topstar.volunteer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.model.VolunteerView;
import com.topstar.volunteer.util.BaseMapper;

public interface VolunteerMapper extends BaseMapper<Volunteer>{
	
	/**
	 * 通过志愿者实体字段过滤查询志愿者名单信息列表
	 * @param volunteer
	 * @return
	 */
	List<VolunteerView> findByEntity(@Param("volunteer")Volunteer volunteer);

	/**
	 * 通过志愿者实体字段过滤查询志愿者考核信息列表
	 * @param volunteer
	 * @return
	 */
	List<VolunteerView> findVolunteerCheckByEntity(@Param("volunteer")Volunteer volunteer);
	
	/**
	 * 批量重置志愿者初始密码
	 * @param params
	 * @return
	 */
	public int setVolunteerPassword(Map<String,Object> params);
	
	/**
	 * 通过idCard查询得到志愿者Id
	 * @param idCard
	 * @return
	 */
	public Long selectVolId(@Param("idcard")String idcard);
	
	/**
	 * 根据志愿者Id修改退队管理状态
	 * @param volId
	 * @param retreatTeamStatus
	 * @return
	 */
	public int editRetreatTeamStatus(@Param("volId")Long volId,@Param("retreatTeamStatus")Long retreatTeamStatus);
	
	/**
	 * 志愿者统计页面信息
	 * @return
	 */
	public List<Statistics> statisticsShow(@Param("statistics")Statistics statistics);
	
	/**
	 * 根据机构trainId得到该机构下的用户列表
	 * @param trainId
	 * @param volunteer
	 * @return
	 */
	public List<Volunteer> getVolByOrgId(@Param("trainId")Long trainId,@Param("volunteer")Volunteer volunteer);
	
	/**
	 * 根据机构orgId得到该机构下的用户列表
	 * @param orgId
	 * @return
	 */
	List<Volunteer>  getVols(@Param("orgId")Long orgId);
	
	/**
	 * 得到志愿者比例信息(如,男女比例,政治面貌,学历信息)
	 * @param statistics
	 * @return
	 */
	public Statistics getVolInfo(@Param("statistics")Statistics statistics);
	
	/**
	 * 得到志愿者比例信息按照服务队划分
	 * @param statistics
	 * @return
	 */
	List<Statistics> getVolInfoList(@Param("statistics")Statistics statistics);
	
	/**
	 * 得到志愿者统计信息(名称,注册时间,服务时长,活动次数)
	 * @param statistics
	 * @return
	 */
	List<Statistics> getVolStatis(@Param("statistics")Statistics statistics);
}