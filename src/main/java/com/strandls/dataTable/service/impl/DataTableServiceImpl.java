package com.strandls.dataTable.service.impl;

import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.strandls.dataTable.dao.DataSetDAO;
import com.strandls.dataTable.dao.DataTableDAO;
import com.strandls.dataTable.pojo.DataTable;
import com.strandls.dataTable.pojo.ShowDataTable;
import com.strandls.dataTable.service.DataTableService;
import com.strandls.esmodule.controllers.EsServicesApi;
import com.strandls.esmodule.pojo.UserScore;
import com.strandls.observation.controller.ObservationServiceApi;
import com.strandls.user.controller.UserServiceApi;
import com.strandls.user.pojo.UserIbp;
import com.strandls.userGroup.controller.UserGroupSerivceApi;
import com.strandls.userGroup.pojo.UserGroup;
import com.strandls.userGroup.pojo.UserGroupIbp;

public class DataTableServiceImpl implements DataTableService {
	@Inject
	private DataTableDAO dataTableDao;
	
	@Inject
	private UserServiceApi userServiceApi;
	
	@Inject 
	private UserGroupSerivceApi usergroupServiceApi;
	
	@Inject 
	private ObservationServiceApi observationServiceApi;
	
	@Inject
	private EsServicesApi esService;
	

	@Override
	public ShowDataTable show(Long dataTableId,Long offset,Long limit) {
		Map<String, String> authorScore = null;
		DataTable dataTable  = null;
		UserIbp user = null;
		List<UserGroupIbp> userGroups = null;
		dataTable  = dataTableDao.findById(dataTableId);
		Long userId = null;
		userId = dataTable.getPartyUploaderId();
		user = userServiceApi.getUserIbp(userId.toString());
		userGroups = usergroupServiceApi.getObservationUserGroup(dataTableId.toString());
		
		UserScore score = esService.getUserScore("eaf", "er", userId.toString(), "f");
		if (!score.getRecord().isEmpty()) {
			authorScore = score.getRecord().get(0).get("details");
		}
		return null;
	}

}
