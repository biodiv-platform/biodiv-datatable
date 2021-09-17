package com.strandls.dataTable.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import org.pac4j.core.profile.CommonProfile;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.strandls.dataTable.dao.DataTableDAO;
import com.strandls.dataTable.dto.BulkDTO;
import com.strandls.dataTable.pojo.DataTable;
import com.strandls.dataTable.pojo.DataTableList;
import com.strandls.dataTable.pojo.DataTableListMapping;
import com.strandls.dataTable.pojo.DataTableWkt;
import com.strandls.dataTable.service.DataTableService;
import com.strandls.dataTable.util.LogActivities;
import com.strandls.user.controller.UserServiceApi;
import com.strandls.user.pojo.UserIbp;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import com.strandls.authentication_utility.util.AuthUtil;

public class DataTableServiceImpl implements DataTableService {

	private final Logger logger = LoggerFactory.getLogger(DataTableServiceImpl.class);
	@Inject
	private DataTableDAO dataTableDao;

	@Inject
	private UserServiceApi userService;

	@Inject
	private DataTableHelper dataTableHelper;

	@Inject
	private WKTWriter wktWriter;

	@Inject
	private LogActivities logActivities;

	@Override
	public DataTableWkt show(Long dataTableId) {
		DataTable dataTable = null;
		try {
			dataTable = dataTableDao.findById(dataTableId);
			if (dataTable == null) {
				return null;
			}
			return showDataTableMapper(dataTable);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public DataTableList dataTableList(String orderBy, Integer limit, Integer offset) {
		List<DataTable> datatableList = new ArrayList<DataTable>();
		List<DataTableListMapping> dataTableMappingList = new ArrayList<DataTableListMapping>();
		DataTableList dataTableListData = new DataTableList();
		Long total = dataTableDao.findTotalDataTable();
		try {
			datatableList = dataTableDao.getDataTableList("OBSERVATIONS",orderBy, limit, offset);

			if (datatableList.isEmpty()) {
				return dataTableListData;
			}

			datatableList.forEach((dt) -> {
				UserIbp user = null;
				try {
					user = userService.getUserIbp(dt.getUploaderId().toString());
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				DataTableListMapping mapperData = new DataTableListMapping(dt.getId(), dt.getTitle(),
						dt.getDataTableType(), dt.getSummary(), dt.getTaxonomicCoverageGroupIds(),
						dt.getTemporalCoverageFromDate(), dt.getTemporalCoverageDateAccuracy(),
						dt.getGeographicalCoveragePlaceName(), user);

				dataTableMappingList.add(mapperData);
			});

			dataTableListData.setList(dataTableMappingList);
			dataTableListData.setCount(total);

			return dataTableListData;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return dataTableListData;
	}

	@Override
	public DataTableWkt createDataTable(HttpServletRequest request, BulkDTO bulkDto) {
		try {
			if (bulkDto == null) {
				return null;
			}
			CommonProfile profile = AuthUtil.getProfileFromRequest(request);
			Long userId = Long.parseLong(profile.getId());
			DataTable dataTable = dataTableHelper.createDataTable(bulkDto, userId);
			dataTable = dataTableDao.save(dataTable);
			logActivities.LogActivity(request.getHeader(HttpHeaders.AUTHORIZATION), null, dataTable.getId(),
					dataTable.getId(), "datatable", null, "Datatable created", null);
			return showDataTableMapper(dataTable);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public DataTableWkt updateDataTable(HttpServletRequest request, DataTableWkt dataTable) {
		CommonProfile profile = AuthUtil.getProfileFromRequest(request);
		Long userId = Long.parseLong(profile.getId());
		DataTable result = dataTableDao.findById(dataTable.getId());
		if (userId.equals(result.getUploaderId()) && userId.equals(result.getId())) {
			DataTable parsedData = dataTableSerilizer(dataTable, result);
			result = dataTableDao.update(parsedData);
			return showDataTableMapper(result);
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
			if (datatable.getUploaderId() != null && (datatable.getUploaderId().equals(userId) || userRole.contains("ROLE_ADMIN"))) {
				dataTable.setIsRemoved(true);
				dataTableDao.update(dataTable);
				return "Observation Deleted Succesfully";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	private DataTableWkt showDataTableMapper(DataTable dt) {
		if (dt.getId() != null) {
			String wktData = wktWriter.write(dt.getGeographicalCoverageTopology());
			DataTableWkt datatableWkt = new DataTableWkt(dt.getId(), dt.getTitle(), dt.getCreatedOn(), dt.getIsRemoved(),
					dt.getLastRevised(), dt.getTaxonomicCoverageGroupIds(), dt.getBasisOfData(), dt.getuFileId(),
					dt.getUploaderId(), dt.getGeographicalCoverageGeoPrivacy(), dt.getGeographicalCoverageLatitude(),
					dt.getGeographicalCoverageLongitude(), dt.getDatasetId(), dt.getPartyAttributions(),
					dt.getGeographicalCoveragePlaceName(), dt.getSummary(), dt.getDataTableType(), wktData,
					dt.getTemporalCoverageDateAccuracy(), dt.getBasisOfRecord(), dt.getIsVerified(),
					dt.getDescription(), dt.getGeographicalCoverageLocationScale(), dt.getProject(), dt.getMethods(),
					dt.getTemporalCoverageFromDate(), dt.getFieldMapping());

			return datatableWkt;
		}
		return null;

	}

	private DataTable dataTableSerilizer(DataTableWkt dt, DataTable prevState) {
		try {
			if (dt.getId() != null) {
				GeometryFactory geofactory = new GeometryFactory(new PrecisionModel(), 4326);
				WKTReader wktRdr = new WKTReader(geofactory);
				Geometry geoBoundary = wktRdr.read(dt.getGeographicalCoverageTopology());
				prevState.setTitle(dt.getTitle());
				prevState.setCreatedOn(dt.getCreatedOn());
				prevState.setLastRevised(dt.getLastRevised());
				prevState.setTaxonomicCoverageGroupIds(dt.getTaxonomicCoverageGroupIds());
				prevState.setBasisOfData(dt.getBasisOfData());
				prevState.setuFileId(dt.getUFileId());
				prevState.setUploaderId(dt.getUploaderId());
				prevState.setGeographicalCoverageGeoPrivacy(dt.getGeographicalCoverageGeoPrivacy());
				prevState.setGeographicalCoverageLatitude(dt.getGeographicalCoverageLatitude());
				prevState.setGeographicalCoverageLongitude(dt.getGeographicalCoverageLongitude());
				prevState.setGeographicalCoverageTopology(geoBoundary);
				return prevState;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;

	}

}
