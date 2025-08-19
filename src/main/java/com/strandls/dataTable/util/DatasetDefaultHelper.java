package com.strandls.dataTable.util;

import jakarta.inject.Inject;

public class DatasetDefaultHelper {

	@Inject
	DatasetDefaultGenerator defaultGenerator;

	public void createDefaultDataSetRecord(String defaultDatasetTitle) {
		DatasetThreadRunner defaultDatasetThread = new DatasetThreadRunner(defaultDatasetTitle, defaultGenerator);
		Thread thread = new Thread(defaultDatasetThread);
		thread.start();
	}
}
