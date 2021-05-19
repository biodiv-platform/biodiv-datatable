package com.strandls.dataTable.pojo;


import com.vividsolutions.jts.geom.Geometry;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "dataset1", schema = "public")
public class Dataset {
	private long id;
	private long version;
	private long accessLicenseId;
	private String accessRights;
	private Timestamp createdOn;
	private String customFields;
	private String description;
	private String externalId;
	private String externalUrl;
	private int featureCount;
	private int flagCount;
	private boolean geographicalCoverageGeoPrivacy;
	private double geographicalCoverageLatitude;
	private String geographicalCoverageLocationAccuracy;
	private String geographicalCoverageLocationScale;
	private double geographicalCoverageLongitude;
	private String geographicalCoveragePlaceName;
	private Geometry geographicalCoverageTopology;
	private boolean isDeleted;
	private Timestamp lastRevised;
	private String methods;
	private String partyAttributions;
	private long partyContributorId;
	private long partyUploaderId;
	private String project;
	private int rating;
	private String taxonomicCoverageGroupIds;
	private Timestamp temporalCoverageFromDate;
	private Timestamp temporalCoverageToDate;
	private String title;
	private String viaCode;
	private String viaId;
	private String temporalCoverageDateAccuracy;
	private String summary;
	private int languageId;
	private int dataPackageId;
	private int uploaderId;
	private int uFIleId;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "version")
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Basic
	@Column(name = "access_license_id")
	public long getAccessLicenseId() {
		return accessLicenseId;
	}

	public void setAccessLicenseId(long accessLicenseId) {
		this.accessLicenseId = accessLicenseId;
	}

	@Basic
	@Column(name = "access_rights")
	public String getAccessRights() {
		return accessRights;
	}

	public void setAccessRights(String accessRights) {
		this.accessRights = accessRights;
	}

	@Basic
	@Column(name = "created_on")
	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	@Basic
	@Column(name = "custom_fields")
	public String getCustomFields() {
		return customFields;
	}

	public void setCustomFields(String customFields) {
		this.customFields = customFields;
	}

	@Basic
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic
	@Column(name = "external_id")
	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	@Basic
	@Column(name = "external_url")
	public String getExternalUrl() {
		return externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	@Basic
	@Column(name = "feature_count")
	public int getFeatureCount() {
		return featureCount;
	}

	public void setFeatureCount(int featureCount) {
		this.featureCount = featureCount;
	}

	@Basic
	@Column(name = "flag_count")
	public int getFlagCount() {
		return flagCount;
	}

	public void setFlagCount(int flagCount) {
		this.flagCount = flagCount;
	}

	@Basic
	@Column(name = "geographical_coverage_geo_privacy")
	public boolean isGeographicalCoverageGeoPrivacy() {
		return geographicalCoverageGeoPrivacy;
	}

	public void setGeographicalCoverageGeoPrivacy(boolean geographicalCoverageGeoPrivacy) {
		this.geographicalCoverageGeoPrivacy = geographicalCoverageGeoPrivacy;
	}

	@Basic
	@Column(name = "geographical_coverage_latitude")
	public double getGeographicalCoverageLatitude() {
		return geographicalCoverageLatitude;
	}

	public void setGeographicalCoverageLatitude(double geographicalCoverageLatitude) {
		this.geographicalCoverageLatitude = geographicalCoverageLatitude;
	}

	@Basic
	@Column(name = "geographical_coverage_location_accuracy")
	public String getGeographicalCoverageLocationAccuracy() {
		return geographicalCoverageLocationAccuracy;
	}

	public void setGeographicalCoverageLocationAccuracy(String geographicalCoverageLocationAccuracy) {
		this.geographicalCoverageLocationAccuracy = geographicalCoverageLocationAccuracy;
	}

	@Basic
	@Column(name = "geographical_coverage_location_scale")
	public String getGeographicalCoverageLocationScale() {
		return geographicalCoverageLocationScale;
	}

	public void setGeographicalCoverageLocationScale(String geographicalCoverageLocationScale) {
		this.geographicalCoverageLocationScale = geographicalCoverageLocationScale;
	}

	@Basic
	@Column(name = "geographical_coverage_longitude")
	public double getGeographicalCoverageLongitude() {
		return geographicalCoverageLongitude;
	}

	public void setGeographicalCoverageLongitude(double geographicalCoverageLongitude) {
		this.geographicalCoverageLongitude = geographicalCoverageLongitude;
	}

	@Basic
	@Column(name = "geographical_coverage_place_name")
	public String getGeographicalCoveragePlaceName() {
		return geographicalCoveragePlaceName;
	}

	public void setGeographicalCoveragePlaceName(String geographicalCoveragePlaceName) {
		this.geographicalCoveragePlaceName = geographicalCoveragePlaceName;
	}

	@Basic
	@Column(name = "geographical_coverage_topology")
	public Geometry getGeographicalCoverageTopology() {
		return geographicalCoverageTopology;
	}

	public void setGeographicalCoverageTopology(Geometry geographicalCoverageTopology) {
		this.geographicalCoverageTopology = geographicalCoverageTopology;
	}

	@Basic
	@Column(name = "is_deleted")
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}

	@Basic
	@Column(name = "last_revised")
	public Timestamp getLastRevised() {
		return lastRevised;
	}

	public void setLastRevised(Timestamp lastRevised) {
		this.lastRevised = lastRevised;
	}

	@Basic
	@Column(name = "methods")
	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	@Basic
	@Column(name = "party_attributions")
	public String getPartyAttributions() {
		return partyAttributions;
	}

	public void setPartyAttributions(String partyAttributions) {
		this.partyAttributions = partyAttributions;
	}

	@Basic
	@Column(name = "party_contributor_id")
	public long getPartyContributorId() {
		return partyContributorId;
	}

	public void setPartyContributorId(long partyContributorId) {
		this.partyContributorId = partyContributorId;
	}

	@Basic
	@Column(name = "party_uploader_id")
	public long getPartyUploaderId() {
		return partyUploaderId;
	}

	public void setPartyUploaderId(long partyUploaderId) {
		this.partyUploaderId = partyUploaderId;
	}

	@Basic
	@Column(name = "project")
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	@Basic
	@Column(name = "rating")
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Basic
	@Column(name = "taxonomic_coverage_group_ids")
	public String getTaxonomicCoverageGroupIds() {
		return taxonomicCoverageGroupIds;
	}

	public void setTaxonomicCoverageGroupIds(String taxonomicCoverageGroupIds) {
		this.taxonomicCoverageGroupIds = taxonomicCoverageGroupIds;
	}

	@Basic
	@Column(name = "temporal_coverage_from_date")
	public Timestamp getTemporalCoverageFromDate() {
		return temporalCoverageFromDate;
	}

	public void setTemporalCoverageFromDate(Timestamp temporalCoverageFromDate) {
		this.temporalCoverageFromDate = temporalCoverageFromDate;
	}

	@Basic
	@Column(name = "temporal_coverage_to_date")
	public Timestamp getTemporalCoverageToDate() {
		return temporalCoverageToDate;
	}

	public void setTemporalCoverageToDate(Timestamp temporalCoverageToDate) {
		this.temporalCoverageToDate = temporalCoverageToDate;
	}

	@Basic
	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Basic
	@Column(name = "via_code")
	public String getViaCode() {
		return viaCode;
	}

	public void setViaCode(String viaCode) {
		this.viaCode = viaCode;
	}

	@Basic
	@Column(name = "via_id")
	public String getViaId() {
		return viaId;
	}

	public void setViaId(String viaId) {
		this.viaId = viaId;
	}

	@Basic
	@Column(name = "temporal_coverage_date_accuracy")
	public String getTemporalCoverageDateAccuracy() {
		return temporalCoverageDateAccuracy;
	}

	public void setTemporalCoverageDateAccuracy(String temporalCoverageDateAccuracy) {
		this.temporalCoverageDateAccuracy = temporalCoverageDateAccuracy;
	}

	@Basic
	@Column(name = "summary")
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Basic
	@Column(name = "language_id" ,nullable = false)
	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	@Basic
	@Column(name = "data_package_id",nullable = false)
	public int getDataPackageId() {
		return dataPackageId;
	}

	public void setDataPackageId(int dataPackageId) {
		this.dataPackageId = dataPackageId;
	}

	@Basic
	@Column(name = "uploader_id", nullable = false)
	public int getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(int uploaderId) {
		this.uploaderId = uploaderId;
	}

	@Basic
	@Column(name = "u_file_id", nullable = false)
	public int getuFIleId() {
		return uFIleId;
	}

	public void setuFIleId(int uFIleId) {
		this.uFIleId = uFIleId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Dataset dataset = (Dataset) o;
		return id == dataset.id && version == dataset.version && accessLicenseId == dataset.accessLicenseId
				&& featureCount == dataset.featureCount && flagCount == dataset.flagCount
				&& geographicalCoverageGeoPrivacy == dataset.geographicalCoverageGeoPrivacy
				&& Double.compare(dataset.geographicalCoverageLatitude, geographicalCoverageLatitude) == 0
				&& Double.compare(dataset.geographicalCoverageLongitude, geographicalCoverageLongitude) == 0
				&& isDeleted == dataset.isDeleted && partyContributorId == dataset.partyContributorId
				&& partyUploaderId == dataset.partyUploaderId && rating == dataset.rating
				&& Objects.equals(accessRights, dataset.accessRights) && Objects.equals(createdOn, dataset.createdOn)
				&& Objects.equals(customFields, dataset.customFields)
				&& Objects.equals(description, dataset.description) && Objects.equals(externalId, dataset.externalId)
				&& Objects.equals(externalUrl, dataset.externalUrl)
				&& Objects.equals(geographicalCoverageLocationAccuracy, dataset.geographicalCoverageLocationAccuracy)
				&& Objects.equals(geographicalCoverageLocationScale, dataset.geographicalCoverageLocationScale)
				&& Objects.equals(geographicalCoveragePlaceName, dataset.geographicalCoveragePlaceName)
				&& Objects.equals(geographicalCoverageTopology, dataset.geographicalCoverageTopology)
				&& Objects.equals(lastRevised, dataset.lastRevised) && Objects.equals(methods, dataset.methods)
				&& Objects.equals(partyAttributions, dataset.partyAttributions)
				&& Objects.equals(project, dataset.project)
				&& Objects.equals(taxonomicCoverageGroupIds, dataset.taxonomicCoverageGroupIds)
				&& Objects.equals(temporalCoverageFromDate, dataset.temporalCoverageFromDate)
				&& Objects.equals(temporalCoverageToDate, dataset.temporalCoverageToDate)
				&& Objects.equals(title, dataset.title) && Objects.equals(viaCode, dataset.viaCode)
				&& Objects.equals(viaId, dataset.viaId)
				&& Objects.equals(temporalCoverageDateAccuracy, dataset.temporalCoverageDateAccuracy)
				&& Objects.equals(summary, dataset.summary);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, version, accessLicenseId, accessRights, createdOn, customFields, description,
				externalId, externalUrl, featureCount, flagCount, geographicalCoverageGeoPrivacy,
				geographicalCoverageLatitude, geographicalCoverageLocationAccuracy, geographicalCoverageLocationScale,
				geographicalCoverageLongitude, geographicalCoveragePlaceName, geographicalCoverageTopology, isDeleted,
				lastRevised, methods, partyAttributions, partyContributorId, partyUploaderId, project, rating,
				taxonomicCoverageGroupIds, temporalCoverageFromDate, temporalCoverageToDate, title, viaCode, viaId,
				temporalCoverageDateAccuracy, summary);
	}

}
