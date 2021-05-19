package com.strandls.dataTable.pojo;

import com.vividsolutions.jts.geom.Geometry;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "data_table", schema = "public")
public class DataTable {
    private Long id;
    private Long version;
    private Long accessLicenseId;
    private String accessRights;
    private Boolean agreeTerms;
    private Long checklistId;
    private String columns;
    private Date createdOn;
    private String customFields;
    private String dataTableType;
    private Long datasetId;
    private String description;
    private String externalId;
    private String externalUrl;
    private Integer featureCount;
    private Integer flagCount;
    private Boolean geographicalCoverageGeoPrivacy;
    private Double geographicalCoverageLatitude;
    private String geographicalCoverageLocationAccuracy;
    private String geographicalCoverageLocationScale;
    private Double geographicalCoverageLongitude;
    private String geographicalCoveragePlaceName;
    private Geometry geographicalCoverageTopology;
    private Boolean isDeleted;
    private Date lastRevised;
    private String methods;
    private String partyAttributions;
    private Long partyContributorId;
    private Long partyUploaderId;
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
    private String basisOfData;
    private Long imagesFileId;
    private Long languageId;
    private Long traitValueFileId;
    private Long uploadLogId;
    private Long uFileId;
    private Long uploaderId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "version")
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Basic
    @Column(name = "access_license_id")
    public Long getAccessLicenseId() {
        return accessLicenseId;
    }

    public void setAccessLicenseId(Long accessLicenseId) {
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
    @Column(name = "agree_terms")
    public Boolean getAgreeTerms() {
        return agreeTerms;
    }

    public void setAgreeTerms(Boolean agreeTerms) {
        this.agreeTerms = agreeTerms;
    }

    @Basic
    @Column(name = "checklist_id")
    public Long getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }

    @Basic
    @Column(name = "columns")
    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    @Basic
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
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
    @Column(name = "data_table_type")
    public String getDataTableType() {
        return dataTableType;
    }

    public void setDataTableType(String dataTableType) {
        this.dataTableType = dataTableType;
    }

    @Basic
    @Column(name = "dataset_id")
    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
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
    public Integer getFeatureCount() {
        return featureCount;
    }

    public void setFeatureCount(Integer featureCount) {
        this.featureCount = featureCount;
    }

    @Basic
    @Column(name = "flag_count")
    public Integer getFlagCount() {
        return flagCount;
    }

    public void setFlagCount(Integer flagCount) {
        this.flagCount = flagCount;
    }

    @Basic
    @Column(name = "geographical_coverage_geo_privacy")
    public Boolean getGeographicalCoverageGeoPrivacy() {
        return geographicalCoverageGeoPrivacy;
    }

    public void setGeographicalCoverageGeoPrivacy(Boolean geographicalCoverageGeoPrivacy) {
        this.geographicalCoverageGeoPrivacy = geographicalCoverageGeoPrivacy;
    }

    @Basic
    @Column(name = "geographical_coverage_latitude")
    public Double getGeographicalCoverageLatitude() {
        return geographicalCoverageLatitude;
    }

    public void setGeographicalCoverageLatitude(Double geographicalCoverageLatitude) {
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
    public Double getGeographicalCoverageLongitude() {
        return geographicalCoverageLongitude;
    }

    public void setGeographicalCoverageLongitude(Double geographicalCoverageLongitude) {
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
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Basic
    @Column(name = "last_revised")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastRevised() {
        return lastRevised;
    }

    public void setLastRevised(Date lastRevised) {
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
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTemporalCoverageFromDate() {
        return temporalCoverageFromDate;
    }

    public void setTemporalCoverageFromDate(Date temporalCoverageFromDate) {
        this.temporalCoverageFromDate = temporalCoverageFromDate;
    }

    @Basic
    @Column(name = "temporal_coverage_to_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTemporalCoverageToDate() {
        return temporalCoverageToDate;
    }

    public void setTemporalCoverageToDate(Date temporalCoverageToDate) {
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
    @Column(name = "basis_of_data", columnDefinition = "varchar(255) default 'PRIMARY_OBSERVATION'")
    public String getBasisOfData() {
        return basisOfData;
    }

    public void setBasisOfData(String basisOfData) {
        this.basisOfData = basisOfData;
    }

    @Basic
    @Column(name = "images_file_id")
    public Long getImagesFileId() {
        return imagesFileId;
    }

    public void setImagesFileId(Long imagesFileId) {
        this.imagesFileId = imagesFileId;
    }

    @Basic
    @Column(name = "language_id")
    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    @Basic
    @Column(name = "trait_value_file_id")
    public Long getTraitValueFileId() {
        return traitValueFileId;
    }

    public void setTraitValueFileId(Long traitValueFileId) {
        this.traitValueFileId = traitValueFileId;
    }

    @Basic
    @Column(name = "u_file_id", nullable = false)
    public Long getuFileId() {
        return uFileId;
    }

    public void setuFileId(Long uFileId) {
        this.uFileId = uFileId;
    }

    @Basic
    @Column(name = "upload_log_id")
    public Long getUploadLogId() {
        return uploadLogId;
    }

    public void setUploadLogId(Long uploadLogId) {
        this.uploadLogId = uploadLogId;
    }

    @Basic
    @Column(name = "uploader_id", nullable = false)
    public Long getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(Long uploaderId) {
        this.uploaderId = uploaderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataTable dataTable = (DataTable) o;
        return Objects.equals(id, dataTable.id) && Objects.equals(version, dataTable.version) && Objects.equals(accessLicenseId, dataTable.accessLicenseId) && agreeTerms == dataTable.agreeTerms && Objects.equals(featureCount, dataTable.featureCount) && Objects.equals(flagCount, dataTable.flagCount) && geographicalCoverageGeoPrivacy == dataTable.geographicalCoverageGeoPrivacy && Double.compare(dataTable.geographicalCoverageLatitude, geographicalCoverageLatitude) == 0 && Double.compare(dataTable.geographicalCoverageLongitude, geographicalCoverageLongitude) == 0 && isDeleted == dataTable.isDeleted && Objects.equals(partyContributorId, dataTable.partyContributorId) && Objects.equals(partyUploaderId, dataTable.partyUploaderId) && Objects.equals(rating, dataTable.rating) && Objects.equals(accessRights, dataTable.accessRights) && Objects.equals(checklistId, dataTable.checklistId) && Objects.equals(columns, dataTable.columns) && Objects.equals(createdOn, dataTable.createdOn) && Objects.equals(customFields, dataTable.customFields) && Objects.equals(dataTableType, dataTable.dataTableType) && Objects.equals(description, dataTable.description) && Objects.equals(externalId, dataTable.externalId) && Objects.equals(externalUrl, dataTable.externalUrl) && Objects.equals(geographicalCoverageLocationAccuracy, dataTable.geographicalCoverageLocationAccuracy) && Objects.equals(geographicalCoverageLocationScale, dataTable.geographicalCoverageLocationScale) && Objects.equals(geographicalCoveragePlaceName, dataTable.geographicalCoveragePlaceName) && Objects.equals(geographicalCoverageTopology, dataTable.geographicalCoverageTopology) && Objects.equals(lastRevised, dataTable.lastRevised) && Objects.equals(methods, dataTable.methods) && Objects.equals(partyAttributions, dataTable.partyAttributions) && Objects.equals(project, dataTable.project) && Objects.equals(taxonomicCoverageGroupIds, dataTable.taxonomicCoverageGroupIds) && Objects.equals(temporalCoverageFromDate, dataTable.temporalCoverageFromDate) && Objects.equals(temporalCoverageToDate, dataTable.temporalCoverageToDate) && Objects.equals(title, dataTable.title) && Objects.equals(viaCode, dataTable.viaCode) && Objects.equals(viaId, dataTable.viaId) && Objects.equals(temporalCoverageDateAccuracy, dataTable.temporalCoverageDateAccuracy) && Objects.equals(summary, dataTable.summary) && Objects.equals(basisOfData, dataTable.basisOfData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, accessLicenseId, accessRights, agreeTerms, checklistId, columns, createdOn, customFields, dataTableType, description, externalId, externalUrl, featureCount, flagCount, geographicalCoverageGeoPrivacy, geographicalCoverageLatitude, geographicalCoverageLocationAccuracy, geographicalCoverageLocationScale, geographicalCoverageLongitude, geographicalCoveragePlaceName, geographicalCoverageTopology, isDeleted, lastRevised, methods, partyAttributions, partyContributorId, partyUploaderId, project, rating, taxonomicCoverageGroupIds, temporalCoverageFromDate, temporalCoverageToDate, title, viaCode, viaId, temporalCoverageDateAccuracy, summary, basisOfData);
    }
}
