package com.strandls.dataTable.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.pac4j.core.profile.CommonProfile;

import com.strandls.activity.pojo.Activity;
import com.strandls.activity.pojo.CommentLoggingData;
import com.strandls.activity.pojo.MailData;
import com.strandls.dataTable.dto.BulkDTO;
import com.strandls.dataTable.pojo.DataTableList;
import com.strandls.dataTable.pojo.DataTableWkt;
import com.strandls.userGroup.pojo.UserGroupCreateDatatable;
import com.strandls.userGroup.pojo.UserGroupIbp;

//import com.strandls.observation

public interface DataTableService {

	public DataTableWkt show(Long dataTableId);

	public DataTableList dataTableList(String orderBy, Integer limit, Integer offset, String ugId);

	public DataTableWkt createDataTable(HttpServletRequest request, BulkDTO bulkDto);

	public DataTableWkt updateDataTable(HttpServletRequest request, DataTableWkt dataTable);

	public String deleteDataTableById(HttpServletRequest request, CommonProfile profile, Long userId, Long dataTableId);

	public MailData generateMailData(HttpServletRequest request, Long dataTableId);

	public Activity addDataTableComment(HttpServletRequest request, CommentLoggingData comment);

	public List<UserGroupIbp> updateUserGroupDatatableMapping(HttpServletRequest request, Long datatableId,
			UserGroupCreateDatatable userGroups);

	public Activity removeDatatableComment(HttpServletRequest request, CommentLoggingData comment, String commentId);

}
