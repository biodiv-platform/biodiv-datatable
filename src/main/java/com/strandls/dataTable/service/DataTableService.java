package com.strandls.dataTable.service;

import javax.servlet.http.HttpServletRequest;

import org.pac4j.core.profile.CommonProfile;

import com.strandls.dataTable.dto.BulkDTO;
import com.strandls.dataTable.pojo.DataTable;

//import com.strandls.observation

public interface DataTableService {

	public DataTable show(Long dataTableId);

	public DataTable createDataTable(HttpServletRequest request, BulkDTO bulkDto);

	public DataTable updateDataTable(HttpServletRequest request, DataTable dataTable);

	public String deleteDataTableById(HttpServletRequest request, CommonProfile profile, Long userId, Long dataTableId);
}
