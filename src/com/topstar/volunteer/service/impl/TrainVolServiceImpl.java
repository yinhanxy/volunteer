package com.topstar.volunteer.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.topstar.volunteer.dao.TrainVolDao;
import com.topstar.volunteer.dao.VolunteerDao;
import com.topstar.volunteer.entity.TrRecord;
import com.topstar.volunteer.entity.TrainVol;
import com.topstar.volunteer.entity.Volunteer;
import com.topstar.volunteer.model.BaseUser;
import com.topstar.volunteer.service.TrainVolService;
import com.topstar.volunteer.shiro.session.ShiroSessionMgr;

@Service
public class TrainVolServiceImpl extends BaseServiceImpl<TrainVol> implements TrainVolService{

	@Autowired
	private TrainVolDao trainVolDao;
	
	@Autowired
	private VolunteerDao volunteerDao;

	@Override
	public PageInfo<TrainVol> findByEntity(Long trainId, int page, int rows) {
		String orderBy="tv.CR_TIME";
		return trainVolDao.findByEntity(trainId, orderBy, page, rows);
	}

	@Override
	public PageInfo<Volunteer> getTrainVols(Volunteer vol, Long trainId, int page, int rows) {
		String orderBy="REG_TIME";
		PageInfo<Volunteer> vols=volunteerDao.getVolByOrgId(trainId, orderBy, vol, page, rows);
		List<Volunteer> volList=vols.getList();
		if(volList!=null && volList.size()>0){
			for (Volunteer v : volList) {
				List<Long> volunteerIds=trainVolDao.findVolIdsByTrainId(trainId);
				if(volunteerIds!=null && volunteerIds.size()>0 &&  trainId!=null && volunteerIds.contains(v.getId())){
					v.setTrainIdList(volunteerIds);
				}
			}
			
		}
		return vols;
	}

	@Override
	public boolean addVolsWithTrainId(Long trainId, List<Long> volIds) {
		if(trainId ==null || trainId<0){
			return false;
		}
		BaseUser crtUser=ShiroSessionMgr.getLoginUser();
		if(crtUser==null){
			return false;
		}
		String crUser=crtUser.getUserName();
		if(volIds!=null && volIds.size()>0){
			int flag=volIds.size();
			for (Long volId : volIds) {
				TrainVol trainVol =new TrainVol();
				trainVol.setTrainId(trainId);
				trainVol.setVolId(volId);
				trainVol.setCrUser(crUser);
				trainVol.setCrTime(new Date());
				int addTrainVol=trainVolDao.insert(trainVol);
				if(addTrainVol>0){
					flag--;
				}
			}
			if(flag==0){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteTrainVolKeys(long[] trainVolKeys) {
		if(trainVolKeys!=null && trainVolKeys.length>0){
			TrainVol trainVol=new TrainVol();
			int rows=trainVolKeys.length;
			int flag=0;
			for (int i = 0; i < trainVolKeys.length; i++) {
				trainVol.setId(trainVolKeys[i]);
				flag=deleteByKey(trainVol);
				if(flag>0){
					rows--;
				}
			}
			if(rows==0){
				return true;
			}
		}
		return false;
	}

}
