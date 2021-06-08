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
import com.strandls.dataTable.pojo.DataTableWkt;
import com.strandls.dataTable.service.DataTableService;
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
	private DataTableHelper dataTableHelper;

	@Inject
	private WKTWriter wktWriter;

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
	public DataTableWkt createDataTable(HttpServletRequest request, BulkDTO bulkDto) {
		try {
			if (bulkDto == null) {
				return null;
			}
			CommonProfile profile = AuthUtil.getProfileFromRequest(request);
			Long userId = Long.parseLong(profile.getId());
			DataTable dataTable = dataTableHelper.createDataTable(bulkDto, userId);
			dataTable = dataTableDao.save(dataTable);
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
		if (userId == dataTable.getUploaderId() && userId == result.getId()) {
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

	private DataTableWkt showDataTableMapper(DataTable dt) {
		if (dt.getId() != null) {
			String wktData = wktWriter.write(dt.getGeographicalCoverageTopology());
			DataTableWkt datatableWkt = new DataTableWkt(dt.getId(), dt.getTitle(), dt.getCreatedOn(), dt.getDeleted(),
					dt.getLastRevised(), dt.getTaxonomicCoverageGroupIds(), dt.getBasisOfData(), dt.getuFileId(),
					dt.getUploaderId(), dt.getGeographicalCoverageGeoPrivacy(), dt.getGeographicalCoverageLatitude(),
					dt.getGeographicalCoverageLongitude(), wktData);

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
				prevState.setuFileId(dt.getuFileId());
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
