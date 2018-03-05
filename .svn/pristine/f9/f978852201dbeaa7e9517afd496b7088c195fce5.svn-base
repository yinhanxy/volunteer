package com.topstar.volunteer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.topstar.volunteer.entity.SerTeam;
import com.topstar.volunteer.model.Statistics;
import com.topstar.volunteer.util.BaseMapper;

public interface SerTeamMapper extends BaseMapper<SerTeam> {
	
	List<SerTeam> findByEntity(@Param("serteam")SerTeam serteam);
	
	List<SerTeam> getSerTeamName(Long orgId);
	
	/**
	 * 得到服务队名称及其志愿者数量 
	 * 如：[["武汉市群艺大队","20"],["武汉市美术馆大队","30"]]
	 * @param statistics
	 * @return 
	 */
	List<Statistics> getSerTeamAndVolNum(@Param("statistics")Statistics statistics);

	/**
	 * 得到各区域的服务队数量
	 * 如:[["洪山区","5"],["青山区","3"]]
	 * @param statistics
	 * @return
	 */
	List<Statistics> getSerByArea(@Param("statistics")Statistics statistics);
	
	/**
	 * 得到服务队的名称及其对应时长
	 * @param statistics
	 * @return
	 */
	List<Statistics> getNameAndHour(@Param("statistics")Statistics statistics);
	
	/**
	 * 根据机构Id查询该机构的服务队数量(一个机构只有一个服务队)
	 * @param orgId
	 * @return
	 */
	int judgeSerByOrg(@Param("orgId")Long orgId);
	
	
}