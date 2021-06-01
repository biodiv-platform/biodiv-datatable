package com.strandls.dataTable.service.impl;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.strandls.dataTable.service.DataTableService;

/**
 * 
 * @author vishnu
 *
 */
public class DataTableServiceModule extends AbstractModule{

	@Override
	protected void configure() 	{
		
		bind(DataTableService.class).to(DataTableServiceImpl.class).in(Scopes.SINGLETON);
		bind(DataTableHelper.class).in(Scopes.SINGLETON);
	}
}
