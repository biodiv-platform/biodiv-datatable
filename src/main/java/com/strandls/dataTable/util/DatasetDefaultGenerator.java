package com.strandls.dataTable.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strandls.dataTable.Headers;
import com.strandls.dataTable.dao.DataSetDAO;
import com.strandls.dataTable.pojo.Dataset;
import com.strandls.resource.controllers.ResourceServicesApi;
import com.strandls.resource.pojo.UFile;
import com.strandls.resource.pojo.UFileCreateData;
import com.strandls.user.controller.UserServiceApi;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

public class DatasetDefaultGenerator {
	
	private final Logger logger = LoggerFactory.getLogger(DatasetDefaultGenerator.class);

	@Inject
	private DataSetDAO datasetDao;

	@Inject
	private ResourceServicesApi resourceApi;
	@Inject
	private Headers headers;

	@Inject
	private UserServiceApi userService;

	
	public void configureDataSetDefault(String title) {

		try {
			Dataset res = null;
			res = datasetDao.findDataSetByTitle(title);
			if (res == null) {
				String topleft = "";
				String bottomright = "";
				String GeoCoverageLat = "";
				String GeoCoverageLon = "";
				String GeoCoveragePlacename = "";
				UFile uFile = null;
				InputStream in = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("config.properties");

				Properties properties = new Properties();
				try {
					properties.load(in);
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
				String uploadDir = "/app/data/biodiv/content" + File.separatorChar + "datasets/"
						+ UUID.randomUUID().toString();

				topleft = properties.getProperty("topLeft");
				bottomright = properties.getProperty("bottomRight");
				GeoCoverageLat = properties.getProperty("geoCoverageLat");
				GeoCoverageLon = properties.getProperty("geoCoverageLon");
				GeoCoveragePlacename = properties.getProperty("geoCoveragePlacename");
				String point1[] = topleft.split(",");
				String point2[] = bottomright.split(",");
				File destinationFile = new File(uploadDir);
				destinationFile.mkdir();
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}

				// create uFile

				UFileCreateData createUFileData = new UFileCreateData();
				createUFileData.setWeight(0);
				createUFileData.setSize("" + destinationFile.length() + "");
				createUFileData.setPath(destinationFile.getAbsolutePath().replaceFirst("/app/data/biodiv/content", ""));
				TokenGenerator tokenGenerator = new TokenGenerator();
				String jwtString = tokenGenerator.generate(userService.getUser("1"));
				resourceApi = headers.addResourceHeaders(resourceApi, jwtString);
				uFile = resourceApi.createUFile(createUFileData);

				// set topology from bounds
				Coordinate[] cords = new Coordinate[] {
						new Coordinate(Double.parseDouble(point1[0]), Double.parseDouble(point1[1])),
						new Coordinate(Double.parseDouble(point2[0]), Double.parseDouble(point1[1])),
						new Coordinate(Double.parseDouble(point2[0]), Double.parseDouble(point2[1])),
						new Coordinate(Double.parseDouble(point1[0]), Double.parseDouble(point2[1])),
						new Coordinate(Double.parseDouble(point1[0]), Double.parseDouble(point1[1])), };

				GeometryFactory geofactory = new GeometryFactory(new PrecisionModel(), 4326);

				Geometry topology = geofactory.createPolygon(geofactory.createLinearRing(cords));

				Dataset standaloneDataset = new Dataset();
				standaloneDataset.setTitle(title);
				standaloneDataset.setSummary("");
				standaloneDataset.setVersion(0L);
				standaloneDataset.setRating(0);
				standaloneDataset.setFeatureCount(0);
				standaloneDataset.setFeatureCount(0);
				standaloneDataset.setCustomFields("{}");
				standaloneDataset.setuFIleId(uFile != null ? uFile.getId().intValue() : 1);

				standaloneDataset.setUploaderId(1);
				standaloneDataset.setDataPackageId(5136151);
				standaloneDataset.setLanguageId(205);

				standaloneDataset.setPartyContributorId(1L);
				standaloneDataset.setPartyUploaderId(1L);
				standaloneDataset.setDeleted(false);
				standaloneDataset.setAccessLicenseId(822L);

				standaloneDataset.setCreatedOn(new Timestamp(new Date().getTime()));
				standaloneDataset.setLastRevised(new Timestamp(new Date().getTime()));

				standaloneDataset.setTaxonomicCoverageGroupIds("839");
				standaloneDataset.setTemporalCoverageDateAccuracy("Unknown");
				standaloneDataset.setTemporalCoverageFromDate(new Timestamp(new Date().getTime()));
				standaloneDataset.setTemporalCoverageToDate(new Timestamp(new Date().getTime()));
				// geographical data
				standaloneDataset.setGeographicalCoverageGeoPrivacy(false);
				standaloneDataset.setGeographicalCoverageLatitude(Double.parseDouble(GeoCoverageLat));
				standaloneDataset.setGeographicalCoverageLongitude(Double.parseDouble(GeoCoverageLon));
				standaloneDataset.setGeographicalCoveragePlaceName(GeoCoveragePlacename);
				standaloneDataset.setGeographicalCoverageTopology(topology);
				standaloneDataset.setGeographicalCoverageLocationScale("APPROXIMATE");

				datasetDao.save(standaloneDataset);
				System.out.println("standalone databse added");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());

		}

	}

}
