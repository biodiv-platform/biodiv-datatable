package com.strandls.dataTable.pojo;

import java.util.Date;

public class DataTableWkt {
	private Long id;
	private String title;
	private Date createdOn;
	private String description;
	private String summary;
	private String partyAttributions;

	private Boolean isDeleted;
	private Date lastRevised;
	private String taxonomicCoverageGroupIds;
	private String basisOfData;
	private Long uFileId;
	private Long uploaderId;
	private String dataTableType;
	private Long datasetId;

	private Boolean geographicalCoverageGeoPrivacy;
	private Double geographicalCoverageLatitude;
	private Double geographicalCoverageLongitude;
	private String geographicalCoverageTopology;
	private String geographicalCoveragePlaceName;
	private String geographicalCoverageLocationScale;
	private String project;
	private String methods;
	
	private String temporalCoverageDateAccuracy;
	private String basisOfRecord;
	private Boolean isVerified;
	private String fieldMapping;

	public DataTableWkt() {
		super();
	}
	public DataTableWkt(Long id, String title, Date createdOn, Boolean isDeleted, Date lastRevised,
			String taxonomicCoverageGroupIds, String basisOfData, Long uFileId, Long uploaderId,
			Boolean geographicalCoverageGeoPrivacy, Double geographicalCoverageLatitude,
			Double geographicalCoverageLongitude, Long datasetId, String partyAttributions,
			String geographicalCoveragePlaceName, String summary, String dataTableType,
			String geographicalCoverageTopology,String temporalCoverageDateAccuracy,String basisOfRecord,Boolean isVerified,
			String description,String geographicalCoverageLocationScale,String project,String methods,String fieldMapping) {
		super();
		this.id = id;
		this.title = title;
		this.createdOn = createdOn;
		this.isDeleted = isDeleted;
		this.lastRevised = lastRevised;
		this.taxonomicCoverageGroupIds = taxonomicCoverageGroupIds;
		this.basisOfData = basisOfData;
		this.uFileId = uFileId;
		this.uploaderId = uploaderId;
		this.geographicalCoverageGeoPrivacy = geographicalCoverageGeoPrivacy;
		this.geographicalCoverageLatitude = geographicalCoverageLatitude;
		this.geographicalCoverageLongitude = geographicalCoverageLongitude;
		this.geographicalCoverageTopology = geographicalCoverageTopology;
		this.datasetId = datasetId;
		this.partyAttributions = partyAttributions;
		this.geographicalCoveragePlaceName = geographicalCoveragePlaceName;
		this.summary = summary;
		this.dataTableType = dataTableType;
		this.temporalCoverageDateAccuracy = temporalCoverageDateAccuracy;
		this.basisOfRecord = basisOfData;
		this.isVerified = isVerified;
		this.description = description;
		this.geographicalCoverageLocationScale = geographicalCoverageLocationScale;
		this.project = project;
		this.methods = methods;
		this.fieldMapping = fieldMapping;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getLastRevised() {
		return lastRevised;
	}

	public void setLastRevised(Date lastRevised) {
		this.lastRevised = lastRevised;
	}

	public String getTaxonomicCoverageGroupIds() {
		return taxonomicCoverageGroupIds;
	}

	public void setTaxonomicCoverageGroupIds(String taxonomicCoverageGroupIds) {
		this.taxonomicCoverageGroupIds = taxonomicCoverageGroupIds;
	}

	public String getBasisOfData() {
		return basisOfData;
	}

	public void setBasisOfData(String basisOfData) {
		this.basisOfData = basisOfData;
	}

	public Long getUFileId() {
		return uFileId;
	}

	public void setUFileId(Long uFileId) {
		this.uFileId = uFileId;
	}

	public Long getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(Long uploaderId) {
		this.uploaderId = uploaderId;
	}

	public Boolean getGeographicalCoverageGeoPrivacy() {
		return geographicalCoverageGeoPrivacy;
	}

	public void setGeographicalCoverageGeoPrivacy(Boolean geographicalCoverageGeoPrivacy) {
		this.geographicalCoverageGeoPrivacy = geographicalCoverageGeoPrivacy;
	}

	public Double getGeographicalCoverageLatitude() {
		return geographicalCoverageLatitude;
	}

	public void setGeographicalCoverageLatitude(Double geographicalCoverageLatitude) {
		this.geographicalCoverageLatitude = geographicalCoverageLatitude;
	}

	public Double getGeographicalCoverageLongitude() {
		return geographicalCoverageLongitude;
	}

	public void setGeographicalCoverageLongitude(Double geographicalCoverageLongitude) {
		this.geographicalCoverageLongitude = geographicalCoverageLongitude;
	}

	public String getGeographicalCoverageTopology() {
		return geographicalCoverageTopology;
	}

	public void setGeographicalCoverageTopology(String geographicalCoverageTopology) {
		this.geographicalCoverageTopology = geographicalCoverageTopology;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPartyAttributions() {
		return partyAttributions;
	}

	public void setPartyAttributions(String partyAttributions) {
		this.partyAttributions = partyAttributions;
	}

	public Long getDatasetId() {
		return datasetId;
	}

	public void setDatasetId(Long datasetId) {
		this.datasetId = datasetId;
	}

	public String getGeographicalCoveragePlaceName() {
		return geographicalCoveragePlaceName;
	}

	public void setGeographicalCoveragePlaceName(String geographicalCoveragePlaceName) {
		this.geographicalCoveragePlaceName = geographicalCoveragePlaceName;
	}

	public String getDataTableType() {
		return dataTableType;
	}

	public void setDataTableType(String dataTableType) {
		this.dataTableType = dataTableType;
	}

	public String getBasisOfRecord() {
		return basisOfRecord;
	}

	public void setBasisOfRecord(String basisOfRecord) {
		this.basisOfRecord = basisOfRecord;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getTemporalCoverageDateAccuracy() {
		return temporalCoverageDateAccuracy;
	}

	public void setTemporalCoverageDateAccuracy(String temporalCoverageDateAccuracy) {
		this.temporalCoverageDateAccuracy = temporalCoverageDateAccuracy;
	}

	public String getFieldMapping() {
		return fieldMapping;
	}

	public void setFieldMapping(String fieldMapping) {
		this.fieldMapping = fieldMapping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGeographicalCoverageLocationScale() {
		return geographicalCoverageLocationScale;
	}

	public void setGeographicalCoverageLocationScale(String geographicalCoverageLocationScale) {
		this.geographicalCoverageLocationScale = geographicalCoverageLocationScale;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

}