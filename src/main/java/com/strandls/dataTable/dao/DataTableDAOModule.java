package com.strandls.dataTable.dao;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class DataTableDAOModule  extends AbstractModule{

	@Override
	protected void configure() {
		bind(DataTableDAO.class).in(Scopes.SINGLETON);
		bind(DataSetDAO.class).in(Scopes.SINGLETON);
	}
}
