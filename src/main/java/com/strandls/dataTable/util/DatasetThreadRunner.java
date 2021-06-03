package com.strandls.dataTable.util;

public class DatasetThreadRunner implements Runnable {

	private String datasetTitle = "";
	private DatasetDefaultGenerator defaultGenerator;

	public DatasetThreadRunner(String DatasetTitle, DatasetDefaultGenerator defaultGenerator) {
		this.datasetTitle = DatasetTitle;
		this.defaultGenerator = defaultGenerator;
	}

	@Override
	public void run() {
		defaultGenerator.configureDataSetDefault(datasetTitle);

	}
}