package com.strandls.dataTable.service;

import javax.servlet.http.HttpServletRequest;

import org.pac4j.core.profile.CommonProfile;

import com.strandls.dataTable.dto.BulkDTO;
import com.strandls.dataTable.pojo.DataTableWkt;

//import com.strandls.observation

public interface DataTableService {

	public DataTableWkt show(Long dataTableId);

	public DataTableWkt createDataTable(HttpServletRequest request, BulkDTO bulkDto);

	public DataTableWkt updateDataTable(HttpServletRequest request, DataTableWkt dataTable);

	public String deleteDataTableById(HttpServletRequest request, CommonProfile profile, Long userId, Long dataTableId);
}
