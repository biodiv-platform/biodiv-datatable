package com.strandls.dataTable.pojo;

import com.vividsolutions.jts.geom.Geometry;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dataset1", schema = "public")
public class Dataset {
	private long id;
	private long version;
	private long accessLicenseId;
	private String accessRights;
	private Date createdOn;
	private String customFields;
	private String description;
	private String externalId;
	private String externalUrl;
	private Integer featureCount;
	private Integer flagCount;
	private boolean geographicalCoverageGeoPrivacy;
	private double geographicalCoverageLatitude;
	private String geographicalCoverageLocationAccuracy;
	private String geographicalCoverageLocationScale;
	private double geographicalCoverageLongitude;
	private String geographicalCoveragePlaceName;
	private Geometry geographicalCoverageTopology;
	private boolean isDeleted;
	private Date lastRevised;
	private String methods;
	private String partyAttributions;
	private long partyContributorId;
	private long partyUploaderId;
	private String project;
	private Integer rating;
	private String taxonomicCoverageGroupIds;
	private Date temporalCoverageFromDate;
	private Date temporalCoverageToDate;
	private String title;
	private String viaCode;
	private String viaId;
	private String temporalCoverageDateAccuracy;
	private String summary;
	private Integer languageId;
	private Integer dataPackageId;
	private Integer uploaderId;
	private Integer uFIleId;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	@Column(name = "version", nullable = false)
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	
	@Column(name = "access_license_id",nullable = false)
	public long getAccessLicenseId() {
		return accessLicenseId;
	}

	public void setAccessLicenseId(long accessLicenseId) {
		this.accessLicenseId = accessLicenseId;
	}

	
	@Column(name = "access_rights")
	public String getAccessRights() {
		return accessRights;
	}

	public void setAccessRights(String accessRights) {
		this.accessRights = accessRights;
	}

	
	@Column(name = "created_on", nullable = false)
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	
	@Column(name = "custom_fields")
	public String getCustomFields() {
		return customFields;
	}

	public void setCustomFields(String customFields) {
		this.customFields = customFields;
	}

	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	@Column(name = "external_id")
	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	
	@Column(name = "external_url")
	public String getExternalUrl() {
		return externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	
	@Column(name = "feature_count", nullable = false)
	public Integer getFeatureCount() {
		return featureCount;
	}

	public void setFeatureCount(Integer featureCount) {
		this.featureCount = featureCount;
	}

	
	@Column(name = "flag_count", nullable = false)
	public Integer getFlagCount() {
		return flagCount;
	}

	public void setFlagCount(Integer flagCount) {
		this.flagCount = flagCount;
	}

	
	@Column(name = "geographical_coverage_geo_privacy", nullable = false)
	public boolean isGeographicalCoverageGeoPrivacy() {
		return geographicalCoverageGeoPrivacy;
	}

	public void setGeographicalCoverageGeoPrivacy(boolean geographicalCoverageGeoPrivacy) {
		this.geographicalCoverageGeoPrivacy = geographicalCoverageGeoPrivacy;
	}

	
	@Column(name = "geographical_coverage_latitude", nullable = false)
	public double getGeographicalCoverageLatitude() {
		return geographicalCoverageLatitude;
	}

	public void setGeographicalCoverageLatitude(double geographicalCoverageLatitude) {
		this.geographicalCoverageLatitude = geographicalCoverageLatitude;
	}

	
	@Column(name = "geographical_coverage_location_accuracy")
	public String getGeographicalCoverageLocationAccuracy() {
		return geographicalCoverageLocationAccuracy;
	}

	public void setGeographicalCoverageLocationAccuracy(String geographicalCoverageLocationAccuracy) {
		this.geographicalCoverageLocationAccuracy = geographicalCoverageLocationAccuracy;
	}

	
	@Column(name = "geographical_coverage_location_scale", nullable = false)
	public String getGeographicalCoverageLocationScale() {
		return geographicalCoverageLocationScale;
	}

	public void setGeographicalCoverageLocationScale(String geographicalCoverageLocationScale) {
		this.geographicalCoverageLocationScale = geographicalCoverageLocationScale;
	}

	
	@Column(name = "geographical_coverage_longitude", nullable = false)
	public double getGeographicalCoverageLongitude() {
		return geographicalCoverageLongitude;
	}

	public void setGeographicalCoverageLongitude(double geographicalCoverageLongitude) {
		this.geographicalCoverageLongitude = geographicalCoverageLongitude;
	}

	
	@Column(name = "geographical_coverage_place_name")
	public String getGeographicalCoveragePlaceName() {
		return geographicalCoveragePlaceName;
	}

	public void setGeographicalCoveragePlaceName(String geographicalCoveragePlaceName) {
		this.geographicalCoveragePlaceName = geographicalCoveragePlaceName;
	}

	
	@Column(name = "geographical_coverage_topology")
	public Geometry getGeographicalCoverageTopology() {
		return geographicalCoverageTopology;
	}

	public void setGeographicalCoverageTopology(Geometry geographicalCoverageTopology) {
		this.geographicalCoverageTopology = geographicalCoverageTopology;
	}

	
	@Column(name = "is_deleted", nullable = false)
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}

	
	@Column(name = "last_revised", nullable = false)
	public Date getLastRevised() {
		return lastRevised;
	}

	public void setLastRevised(Date lastRevised) {
		this.lastRevised = lastRevised;
	}

	
	@Column(name = "methods")
	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	
	@Column(name = "party_attributions")
	public String getPartyAttributions() {
		return partyAttributions;
	}

	public void setPartyAttributions(String partyAttributions) {
		this.partyAttributions = partyAttributions;
	}

	
	@Column(name = "party_contributor_id", nullable = false)
	public long getPartyContributorId() {
		return partyContributorId;
	}

	public void setPartyContributorId(long partyContributorId) {
		this.partyContributorId = partyContributorId;
	}

	
	@Column(name = "party_uploader_id", nullable = false)
	public long getPartyUploaderId() {
		return partyUploaderId;
	}

	public void setPartyUploaderId(long partyUploaderId) {
		this.partyUploaderId = partyUploaderId;
	}

	
	@Column(name = "project")
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	
	@Column(name = "rating", nullable = false)
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	
	@Column(name = "taxonomic_coverage_group_ids", nullable = false)
	public String getTaxonomicCoverageGroupIds() {
		return taxonomicCoverageGroupIds;
	}

	public void setTaxonomicCoverageGroupIds(String taxonomicCoverageGroupIds) {
		this.taxonomicCoverageGroupIds = taxonomicCoverageGroupIds;
	}

	
	@Column(name = "temporal_coverage_from_date", nullable = false)
	public Date getTemporalCoverageFromDate() {
		return temporalCoverageFromDate;
	}

	public void setTemporalCoverageFromDate(Date temporalCoverageFromDate) {
		this.temporalCoverageFromDate = temporalCoverageFromDate;
	}

	
	@Column(name = "temporal_coverage_to_date")
	public Date getTemporalCoverageToDate() {
		return temporalCoverageToDate;
	}

	public void setTemporalCoverageToDate(Date temporalCoverageToDate) {
		this.temporalCoverageToDate = temporalCoverageToDate;
	}

	
	@Column(name = "title", nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	@Column(name = "via_code")
	public String getViaCode() {
		return viaCode;
	}

	public void setViaCode(String viaCode) {
		this.viaCode = viaCode;
	}

	
	@Column(name = "via_id")
	public String getViaId() {
		return viaId;
	}

	public void setViaId(String viaId) {
		this.viaId = viaId;
	}

	
	@Column(name = "temporal_coverage_date_accuracy")
	public String getTemporalCoverageDateAccuracy() {
		return temporalCoverageDateAccuracy;
	}

	public void setTemporalCoverageDateAccuracy(String temporalCoverageDateAccuracy) {
		this.temporalCoverageDateAccuracy = temporalCoverageDateAccuracy;
	}

	
	@Column(name = "summary", nullable = false)
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	
	@Column(name = "language_id" ,nullable = false)
	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	
	@Column(name = "data_package_id",nullable = false)
	public Integer getDataPackageId() {
		return dataPackageId;
	}

	public void setDataPackageId(Integer dataPackageId) {
		this.dataPackageId = dataPackageId;
	}

	
	@Column(name = "uploader_id", nullable = false)
	public Integer getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(Integer uploaderId) {
		this.uploaderId = uploaderId;
	}

	
	@Column(name = "u_file_id", nullable = false)
	public Integer getuFIleId() {
		return uFIleId;
	}

	public void setuFIleId(Integer uFIleId) {
		this.uFIleId = uFIleId;
	}

}