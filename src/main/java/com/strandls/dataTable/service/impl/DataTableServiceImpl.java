package com.strandls.dataTable.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import org.pac4j.core.profile.CommonProfile;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.strandls.dataTable.dao.DataTableDAO;
import com.strandls.dataTable.pojo.DataTable;
import com.strandls.dataTable.pojo.ShowDataTable;
import com.strandls.dataTable.service.DataTableService;
import com.strandls.esmodule.controllers.EsServicesApi;
import com.strandls.esmodule.pojo.UserScore;
import com.strandls.naksha.controller.LayerServiceApi;
import com.strandls.naksha.pojo.ObservationLocationInfo;
import com.strandls.dataTable.Headers;
import com.strandls.observation.controller.ObservationServiceApi;
import com.strandls.observation.pojo.Observation;
import com.strandls.user.controller.UserServiceApi;
import com.strandls.user.pojo.UserIbp;
import com.strandls.userGroup.controller.UserGroupSerivceApi;
import com.strandls.userGroup.pojo.UserGroupIbp;

public class DataTableServiceImpl implements DataTableService {

	private final Logger logger = LoggerFactory.getLogger(DataTableServiceImpl.class);
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

	@Inject
	private LayerServiceApi layerServiceApi;

	@Inject
	private Headers headers;

	@Override
	public ShowDataTable show(Long dataTableId, Long offset, Long limit) {
		Map<String, String> authorScore = null;
		DataTable dataTable = null;
		UserIbp user = null;
		List<UserGroupIbp> userGroups = null;
		List<Observation> observationList = null;
		ObservationLocationInfo locationInfo = null;
		Long userId = null;
		ShowDataTable dataTableRes = new ShowDataTable();
		try {
			dataTable = dataTableDao.findById(dataTableId);
			if (dataTable == null) {
				return null;
			}
			userId = dataTable.getPartyUploaderId();
			user = userServiceApi.getUserIbp(userId.toString());
			userGroups = usergroupServiceApi.getObservationUserGroup(dataTableId.toString());
			observationList = observationServiceApi.getObservationDatatableId(dataTableId.toString(), limit.toString(),
					offset.toString());
			UserScore score = esService.getUserScore("eaf", "er", userId.toString(), "f");
			locationInfo = layerServiceApi.getLayerInfo(dataTable.getGeographicalCoverageLatitude().toString(),
					dataTable.getGeographicalCoverageLongitude().toString());
			dataTableRes.setAuthorInfo(user);
			dataTableRes.setDatatable(dataTable);
			dataTableRes.setLayerInfo(null);
			dataTableRes.setObservationList(observationList);
			dataTableRes.setLayerInfo(locationInfo);
			dataTableRes.setUserGroups(userGroups);
			if (!score.getRecord().isEmpty()) {
				authorScore = score.getRecord().get(0).get("details");
				dataTableRes.setAuthorScore(authorScore);
			}

			return dataTableRes;

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public String deleteDataTableById(HttpServletRequest request, CommonProfile profile, Long userId,
			Long dataTableId) {

		try {
			DataTable dataTable = dataTableDao.findById(dataTableId);
			if (dataTable == null) {
				return null;
			}
			JSONArray userRole = (JSONArray) profile.getAttribute("roles");
			DataTable datatable = dataTableDao.findById(dataTableId);
			if (datatable.getId() != null && (datatable.getId().equals(userId) || userRole.contains("ROLE_ADMIN"))) {
				dataTable.setDeleted(true);
				dataTableDao.update(dataTable);
				ObservationServiceApi observationService = headers.addObservationHeader(observationServiceApi,
						request.getHeader(HttpHeaders.AUTHORIZATION));
				observationService.deleteObservaionByDatatableId(dataTableId.toString());
				return "Observation Deleted Succesfully";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
