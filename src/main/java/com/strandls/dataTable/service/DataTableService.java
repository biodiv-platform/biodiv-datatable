package com.strandls.dataTable.service;

import javax.servlet.http.HttpServletRequest;

import org.pac4j.core.profile.CommonProfile;

import com.strandls.dataTable.pojo.ShowDataTable;

public interface DataTableService {

	public ShowDataTable show(Long dataTableId, Long offset, Long limit);
	
	public String deleteDataTableById(HttpServletRequest request, CommonProfile profile, Long userId,
			Long dataTableId);
}
