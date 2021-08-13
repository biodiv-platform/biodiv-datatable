package com.strandls.dataTable.pojo;

import java.util.List;

public class DataTableList {
	
	private List<DataTableListMapping> list;
	private Long count;
	
	public List<DataTableListMapping> getList() {
		return list;
	}
	public void setList(List<DataTableListMapping> list) {
		this.list = list;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}

}
