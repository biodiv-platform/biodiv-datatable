package com.strandls.dataTable.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;

import org.pac4j.core.profile.CommonProfile;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.strandls.dataTable.Headers;
import com.strandls.dataTable.dao.DataTableDAO;
import com.strandls.dataTable.dto.BulkDTO;
import com.strandls.dataTable.pojo.DataTable;
import com.strandls.dataTable.pojo.DataTableList;
import com.strandls.dataTable.pojo.DataTableListMapping;
import com.strandls.dataTable.pojo.DataTableWkt;
import com.strandls.dataTable.service.DataTableService;
import com.strandls.dataTable.util.LogActivities;
import com.strandls.dataTable.util.TokenGenerator;
import com.strandls.user.controller.UserServiceApi;
import com.strandls.user.pojo.UserIbp;
import com.strandls.userGroup.controller.UserGroupSerivceApi;
import com.strandls.userGroup.pojo.UserGroupCreateDatatable;
import com.strandls.userGroup.pojo.UserGroupDatatableFetch;
import com.strandls.userGroup.pojo.UserGroupDatatableMapping;
import com.strandls.userGroup.pojo.UserGroupIbp;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import com.strandls.activity.controller.ActivitySerivceApi;
import com.strandls.activity.pojo.Activity;
import com.strandls.activity.pojo.CommentLoggingData;
import com.strandls.activity.pojo.DataTableMailData;
import com.strandls.activity.pojo.MailData;
import com.strandls.activity.pojo.UserGroupMailData;
import com.strandls.authentication_utility.util.AuthUtil;

public class DataTableServiceImpl implements DataTableService {

	private final Logger logger = LoggerFactory.getLogger(DataTableServiceImpl.class);
	@Inject
	private DataTableDAO dataTableDao;

	@Inject
	private UserServiceApi userService;

	@Inject
	private UserGroupSerivceApi userGroupService;

	@Inject
	private DataTableHelper dataTableHelper;

	@Inject
	private WKTWriter wktWriter;

	@Inject
	private LogActivities logActivities;

	@Inject
	private ActivitySerivceApi activityService;

	@Inject
	private Headers headers;

	@Override
	public DataTableWkt show(Long dataTableId) {
		DataTable dataTable = null;
		try {
			dataTable = dataTableDao.findById(dataTableId);
			List<UserGroupIbp> userGroup = userGroupService.getDataTableUserGroup(dataTableId.toString());
			if (dataTable == null) {
				return null;
			}
			return showDataTableMapper(dataTable, userGroup);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public DataTableList dataTableList(String orderBy, Integer limit, Integer offset, String ugId) {
		List<DataTable> datatableList = new ArrayList<DataTable>();
		List<DataTableListMapping> dataTableMappingList = new ArrayList<DataTableListMapping>();
		DataTableList dataTableListData = new DataTableList();
		Long total = dataTableDao.findTotalDataTable();
		try {
			if (ugId != null && !ugId.isEmpty()) {
				UserGroupDatatableFetch userGroupDatatableFetch = new UserGroupDatatableFetch();
				userGroupDatatableFetch.setUserGroupId(Long.parseLong(ugId));
				userGroupDatatableFetch.setOffset(null);
				userGroupDatatableFetch.setLimit(null);

				UserGroupDatatableMapping res = userGroupService.getDataTablebyUserGroupId(userGroupDatatableFetch);
				if (res != null && res.getTotal() > 0) {
					List<Long> dataTableIds = res.getUserGroupDataTableList().stream()
							.map((item) -> item.getDataTableId()).collect(Collectors.toList());
					datatableList = dataTableDao.getDataTableListByIds("OBSERVATIONS", orderBy, dataTableIds, offset,
							limit);
					total = res.getTotal();
				}

			} else {
				datatableList = dataTableDao.getDataTableList("OBSERVATIONS", orderBy, limit, offset);

			}

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
	public MailData generateMailData(HttpServletRequest request, Long dataTableId) {
		try {
			MailData mailData = new MailData();
			CommonProfile profile = AuthUtil.getProfileFromRequest(request);
			String authorId = profile.getId();
			DataTableMailData dataTableMailData = new DataTableMailData();
			DataTable dataTable = dataTableDao.findById(dataTableId);
			dataTableMailData.setAuthorId(Long.parseLong(authorId));
			dataTableMailData.setCreatedOn(dataTable.getCreatedOn());
			dataTableMailData.setDataTableId(dataTableId);
			dataTableMailData.setTitle(dataTable.getTitle());
			dataTableMailData.setLocation(dataTable.getGeographicalCoveragePlaceName());

			List<UserGroupIbp> userGroup = userGroupService.getDataTableUserGroup(dataTable.getId().toString());
			List<UserGroupMailData> userGroupData = new ArrayList<>();
			if (userGroup != null && !userGroup.isEmpty()) {
				for (UserGroupIbp ugIbp : userGroup) {
					UserGroupMailData ugMailData = new UserGroupMailData();
					ugMailData.setId(ugIbp.getId());
					ugMailData.setIcon(ugIbp.getIcon());
					ugMailData.setName(ugIbp.getName());
					ugMailData.setWebAddress(ugIbp.getWebAddress());
					userGroupData.add(ugMailData);
				}
			}

			mailData.setDataTableMailData(dataTableMailData);
			mailData.setUserGroupData(userGroupData);

			return mailData;

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
			Long contributor = bulkDto.getContributors();
			DataTable dataTable = dataTableHelper.createDataTable(bulkDto, contributor);
			dataTable = dataTableDao.save(dataTable);
			List<UserGroupIbp> userGroup = userGroupService.getDataTableUserGroup(dataTable.getId().toString());

			TokenGenerator tokenGenerator = new TokenGenerator();

			String jwtString = tokenGenerator
					.generate(userService.getUser(dataTable.getPartyContributorId().toString()));

			userService = headers.addUserHeaders(userService, jwtString);
			userService.updateFollow("content.eml.Datatable", dataTable.getId().toString());

			MailData mailData = generateMailData(request, dataTable.getId());
			DataTableMailData datatableMailData = mailData.getDataTableMailData();
			datatableMailData.setAuthorId(contributor);

			logActivities.LogActivity(request.getHeader(HttpHeaders.AUTHORIZATION), null, dataTable.getId(),
					dataTable.getId(), "datatable", null, "Datatable created", mailData);
			return showDataTableMapper(dataTable, userGroup);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public DataTableWkt updateDataTable(HttpServletRequest request, DataTableWkt dataTable) {
		CommonProfile profile = AuthUtil.getProfileFromRequest(request);
		Long userId = Long.parseLong(profile.getId());
		try {
			DataTable result = dataTableDao.findById(dataTable.getId());
			if (userId.equals(result.getUploaderId()) && userId.equals(result.getId())) {
				DataTable parsedData = dataTableSerilizer(dataTable, result);
				result = dataTableDao.update(parsedData);
				List<UserGroupIbp> userGroup = userGroupService.getDataTableUserGroup(dataTable.getId().toString());
				return showDataTableMapper(result, userGroup);
			}
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
			if (datatable.getUploaderId() != null
					&& (datatable.getUploaderId().equals(userId) || userRole.contains("ROLE_ADMIN"))) {
				MailData maildata = generateMailData(request, dataTable.getId());
				dataTable.setIsRemoved(true);
				dataTableDao.update(dataTable);
				List<Long> ugLit = new ArrayList<Long>();
				UserGroupCreateDatatable ugDatatable = new UserGroupCreateDatatable();
				ugDatatable.setUserGroupIds(ugLit);
				userGroupService = headers.addUserGroupHeaders(userGroupService,
						request.getHeader(HttpHeaders.AUTHORIZATION));
				userGroupService.updateDatatableUserGroupMapping(dataTable.getId().toString(), ugDatatable);
				logActivities.LogActivity(request.getHeader(HttpHeaders.AUTHORIZATION), null, dataTable.getId(),
						dataTable.getId(), "datatable", null, "Datatable deleted", maildata);
				return "Observation Deleted Succesfully";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	private DataTableWkt showDataTableMapper(DataTable dt, List<UserGroupIbp> userGroup) {
		if (dt.getId() != null) {
			String wktData = wktWriter.write(dt.getGeographicalCoverageTopology());
			DataTableWkt datatableWkt = new DataTableWkt(dt.getId(), dt.getTitle(), dt.getCreatedOn(),
					dt.getIsRemoved(), dt.getLastRevised(), dt.getTaxonomicCoverageGroupIds(), dt.getBasisOfData(),
					dt.getuFileId(), dt.getUploaderId(), dt.getGeographicalCoverageGeoPrivacy(),
					dt.getGeographicalCoverageLatitude(), dt.getGeographicalCoverageLongitude(), dt.getDatasetId(),
					dt.getPartyAttributions(), dt.getGeographicalCoveragePlaceName(), dt.getSummary(),
					dt.getDataTableType(), wktData, dt.getTemporalCoverageDateAccuracy(), dt.getBasisOfRecord(),
					dt.getIsVerified(), dt.getDescription(), dt.getGeographicalCoverageLocationScale(), dt.getProject(),
					dt.getMethods(), dt.getTemporalCoverageFromDate(), dt.getFieldMapping(),
					dt.getTemporalCoverageToDate(), userGroup);

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

	@Override
	public Activity addDataTableComment(HttpServletRequest request, CommentLoggingData comment) {
		try {
			if (comment != null) {
				comment.setMailData(generateMailData(request, comment.getRootHolderId()));
				activityService = headers.addActivityHeaders(activityService,
						request.getHeader(HttpHeaders.AUTHORIZATION));
				Activity result = activityService.addComment("datatable", comment);
				return result;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Activity removeDatatableComment(HttpServletRequest request, CommentLoggingData comment, String commentId) {
		try {
			comment.setMailData(generateMailData(request, comment.getRootHolderId()));
			activityService = headers.addActivityHeaders(activityService, request.getHeader(HttpHeaders.AUTHORIZATION));
			return activityService.deleteComment("datatable", commentId, comment);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<UserGroupIbp> updateUserGroupDatatableMapping(HttpServletRequest request, Long datatableId,
			UserGroupCreateDatatable userGroups) {
		DataTable dataTable = new DataTable();
		try {
			dataTable = dataTableDao.findById(datatableId);
			if (dataTable != null) {
				userGroups.setTitle(dataTable.getTitle());
				userGroups.setCreatedOn(dataTable.getCreatedOn());
				userGroups.setLocation(dataTable.getGeographicalCoveragePlaceName());
				userGroups.setContributor(dataTable.getPartyContributorId().toString());
				userGroupService = headers.addUserGroupHeaders(userGroupService,
						request.getHeader(HttpHeaders.AUTHORIZATION));
				List<UserGroupIbp> result = userGroupService.updateDatatableUserGroupMapping(datatableId.toString(),
						userGroups);
				return result;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
