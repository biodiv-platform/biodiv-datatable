package com.strandls.dataTable.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.pac4j.core.profile.CommonProfile;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.strandls.dataTable.dao.DataTableDAO;
import com.strandls.dataTable.dto.BulkDTO;
import com.strandls.dataTable.pojo.DataTable;
import com.strandls.dataTable.service.DataTableService;
import com.strandls.authentication_utility.util.AuthUtil;

public class DataTableServiceImpl implements DataTableService {

	private final Logger logger = LoggerFactory.getLogger(DataTableServiceImpl.class);
	@Inject
	private DataTableDAO dataTableDao;

	@Inject
	private DataTableHelper dataTableHelper;

	@Override
	public DataTable show(Long dataTableId) {
		DataTable dataTable = null;
		try {
			dataTable = dataTableDao.findById(dataTableId);
			if (dataTable == null) {
				return null;
			}
			return dataTable;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public DataTable createDataTable(HttpServletRequest request, BulkDTO bulkDto) {
		try {
			if (bulkDto == null) {
				return null;
			}
			CommonProfile profile = AuthUtil.getProfileFromRequest(request);
			Long userId = Long.parseLong(profile.getId());
			DataTable dataTable = dataTableHelper.createDataTable(bulkDto, userId);
			dataTable = dataTableDao.save(dataTable);
			return dataTable;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public DataTable updateDataTable(HttpServletRequest request, DataTable dataTable) {
		CommonProfile profile = AuthUtil.getProfileFromRequest(request);
		Long userId = Long.parseLong(profile.getId());
		DataTable result = dataTableDao.findById(dataTable.getId());
		if (userId == dataTable.getUploaderId() && userId == result.getId()) {
			result = dataTableDao.update(dataTable);
			return result;
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
				return "Observation Deleted Succesfully";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
