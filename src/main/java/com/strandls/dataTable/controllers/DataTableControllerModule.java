/**
 * 
 */
package com.strandls.dataTable.controllers;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * @author Abhishek Rudra
 *
 * 
 */
public class DataTableControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DataTableController.class).in(Scopes.SINGLETON);
	}
}
