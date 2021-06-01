package com.strandls.dataTable.service.impl;

import javax.inject.Inject;

import com.strandls.dataTable.dao.DataSetDAO;
import com.strandls.dataTable.dto.BulkDTO;
import com.strandls.dataTable.pojo.DataTable;
import com.strandls.dataTable.pojo.Dataset;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataTableHelper {

	@Inject
	private DataSetDAO datasetDao;

	private final Logger logger = LoggerFactory.getLogger(DataTableHelper.class);

	public DataTable createDataTable(BulkDTO bulkDto, Long userId) {
		DataTable dataTable = new DataTable();
		dataTable.setAccessLicenseId(bulkDto.getLicenseId());
		dataTable.setAccessRights(null);
		dataTable.setAgreeTerms(true);
		dataTable.setChecklistId(null);
		dataTable.setColumns(new ArrayList<String>().toString()); // ask
		dataTable.setCreatedOn(bulkDto.getCreatedOn());
		dataTable.setCustomFields(new HashMap<String, String>().toString()); // ask
		dataTable.setDataTableType("OBSERVATIONS");
		dataTable.setDeleted(false);
		dataTable.setDescription(bulkDto.getDescription());
		dataTable.setExternalId(null);
		dataTable.setExternalUrl(null);
		dataTable.setFeatureCount(0);
		dataTable.setFlagCount(0);

		// geo fields
		dataTable.setGeographicalCoverageGeoPrivacy(false);
		dataTable.setGeographicalCoverageLatitude(bulkDto.getLatitude());
		dataTable.setGeographicalCoverageLongitude(bulkDto.getLongitude());
		dataTable.setGeographicalCoverageLocationAccuracy(bulkDto.getLocationAccuracy());
		dataTable.setGeographicalCoverageLocationScale(bulkDto.getLocationScale());
		dataTable.setGeographicalCoveragePlaceName(bulkDto.getObservedAt());

		GeometryFactory geofactory = new GeometryFactory(new PrecisionModel(), 4326);
		WKTReader wktRdr = new WKTReader(geofactory);
		if (!bulkDto.getWktString().isEmpty()) {
			try {
				Geometry geoBoundary = wktRdr.read(bulkDto.getWktString());
				dataTable.setGeographicalCoverageTopology(geoBoundary);
			} catch (ParseException e) {
				createPointTopology(geofactory, bulkDto, dataTable);
				logger.error(e.getMessage());
			}

		} else {
			createPointTopology(geofactory, bulkDto, dataTable);

		}

		Dataset dataset = datasetDao.findDataSetByTitle("standalone_dataset");
		Long datasetid = bulkDto.getDataset() != null ? bulkDto.getDataset() : dataset.getId();
		dataTable.setDatasetId(datasetid);
		dataTable.setImagesFileId(null);
		dataTable.setLanguageId(205L);
		dataTable.setLastRevised(bulkDto.getCreatedOn());
		dataTable.setMethods(bulkDto.getMethods());
		dataTable.setPartyAttributions(bulkDto.getAttribution());
		dataTable.setPartyUploaderId(userId);
		dataTable.setPartyContributorId(bulkDto.getContributors()); // only one contributor
		dataTable.setProject(bulkDto.getProject());
		dataTable.setRating(0);
		dataTable.setSummary(bulkDto.getSummary());
		dataTable.setTaxonomicCoverageGroupIds(bulkDto.getsGroup());
		dataTable.setTemporalCoverageDateAccuracy(bulkDto.getDateAccuracy());
		dataTable.setTemporalCoverageFromDate(bulkDto.getObservedFromDate());
		dataTable.setTemporalCoverageToDate(bulkDto.getObservedToDate());
		dataTable.setTitle(bulkDto.getTitle());
		dataTable.setTraitValueFileId(null);
		dataTable.setuFileId(1L); // uFile table id
		dataTable.setVersion(2L);
		dataTable.setViaCode(null);
		dataTable.setViaId(null);
		dataTable.setUploadLogId(null);
		dataTable.setUploaderId(userId);
		dataTable.setBasisOfData(bulkDto.getBasisOfData());

		System.out.println("\n***** DataTable Prepared *****\n");
		System.out.println(dataTable.toString());
		return dataTable;
	}

	private void createPointTopology(GeometryFactory geofactory, BulkDTO  bulkDto,
			DataTable dataTable) {

		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		double latitude = Double.parseDouble(df.format(bulkDto.getLatitude()));
		double longitude = Double.parseDouble(df.format(bulkDto.getLongitude()));
		Coordinate c = new Coordinate(longitude, latitude);
		Geometry topology = geofactory.createPoint(c);
		dataTable.setGeographicalCoverageTopology(topology);
	}
}
