package com.strandls.dataTable.util;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class DataTableUtilModule extends AbstractModule {

	@Override
	protected void configure() {

		bind(TokenGenerator.class).in(Scopes.SINGLETON);
		bind(DatasetDefaultHelper.class).in(Scopes.SINGLETON);
		bind(DatasetThreadRunner.class).in(Scopes.SINGLETON);
		bind(DatasetDefaultGenerator.class).in(Scopes.SINGLETON);
	}
}
