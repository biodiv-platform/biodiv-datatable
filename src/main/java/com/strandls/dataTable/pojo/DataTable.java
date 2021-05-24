package com.strandls.dataTable.pojo;

import com.vividsolutions.jts.geom.Geometry;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

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
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "version", nullable = false)
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Column(name = "access_license_id", nullable = false)
    public Long getAccessLicenseId() {
        return accessLicenseId;
    }

    public void setAccessLicenseId(Long accessLicenseId) {
        this.accessLicenseId = accessLicenseId;
    }

    @Column(name = "access_rights")
    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    @Column(name = "agree_terms", nullable = false)
    public Boolean getAgreeTerms() {
        return agreeTerms;
    }

    public void setAgreeTerms(Boolean agreeTerms) {
        this.agreeTerms = agreeTerms;
    }

    @Column(name = "checklist_id")
    public Long getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }

    @Column(name = "columns", nullable = false)
    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    
    @Column(name = "created_on",nullable = false, length = 29)
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

    @Column(name = "data_table_type", nullable = false)
    public String getDataTableType() {
        return dataTableType;
    }

    public void setDataTableType(String dataTableType) {
        this.dataTableType = dataTableType;
    }

    
    @Column(name = "dataset_id")
    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
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
    public Boolean getGeographicalCoverageGeoPrivacy() {
        return geographicalCoverageGeoPrivacy;
    }

    public void setGeographicalCoverageGeoPrivacy(Boolean geographicalCoverageGeoPrivacy) {
        this.geographicalCoverageGeoPrivacy = geographicalCoverageGeoPrivacy;
    }

    @Column(name = "geographical_coverage_latitude", nullable = false)
    public Double getGeographicalCoverageLatitude() {
        return geographicalCoverageLatitude;
    }

    public void setGeographicalCoverageLatitude(Double geographicalCoverageLatitude) {
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
    public Double getGeographicalCoverageLongitude() {
        return geographicalCoverageLongitude;
    }

    public void setGeographicalCoverageLongitude(Double geographicalCoverageLongitude) {
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
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Column(name = "last_revised", nullable = false, length = 29)
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

    @Column(name = "rating",nullable = false)
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    
    @Column(name = "taxonomic_coverage_group_ids",nullable = false)
    public String getTaxonomicCoverageGroupIds() {
        return taxonomicCoverageGroupIds;
    }

    public void setTaxonomicCoverageGroupIds(String taxonomicCoverageGroupIds) {
        this.taxonomicCoverageGroupIds = taxonomicCoverageGroupIds;
    }

    
    @Column(name = "temporal_coverage_from_date",nullable = false, length = 29)
    public Date getTemporalCoverageFromDate() {
        return temporalCoverageFromDate;
    }

    public void setTemporalCoverageFromDate(Date temporalCoverageFromDate) {
        this.temporalCoverageFromDate = temporalCoverageFromDate;
    }

    
    @Column(name = "temporal_coverage_to_date",nullable = false, length = 29)
    public Date getTemporalCoverageToDate() {
        return temporalCoverageToDate;
    }

    public void setTemporalCoverageToDate(Date temporalCoverageToDate) {
        this.temporalCoverageToDate = temporalCoverageToDate;
    }

    
    @Column(name = "title",nullable = false)
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

    @Column(name = "summary",nullable = false)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Column(name = "basis_of_data", columnDefinition = "varchar(255)")
    @ColumnDefault("PRIMARY_OBSERVATION")
    public String getBasisOfData() {
        return basisOfData;
    }

    public void setBasisOfData(String basisOfData) {
        this.basisOfData = basisOfData;
    }

    
    @Column(name = "images_file_id")
    public Long getImagesFileId() {
        return imagesFileId;
    }

    public void setImagesFileId(Long imagesFileId) {
        this.imagesFileId = imagesFileId;
    }

    @Column(name = "language_id", nullable = false)
    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    
    @Column(name = "trait_value_file_id")
    public Long getTraitValueFileId() {
        return traitValueFileId;
    }

    public void setTraitValueFileId(Long traitValueFileId) {
        this.traitValueFileId = traitValueFileId;
    }

    @Column(name = "u_file_id", nullable = false)
    public Long getuFileId() {
        return uFileId;
    }

    public void setuFileId(Long uFileId) {
        this.uFileId = uFileId;
    }

    
    @Column(name = "upload_log_id")
    public Long getUploadLogId() {
        return uploadLogId;
    }

    public void setUploadLogId(Long uploadLogId) {
        this.uploadLogId = uploadLogId;
    }

    @Column(name = "uploader_id", nullable = false)
    public Long getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(Long uploaderId) {
        this.uploaderId = uploaderId;
    }
}
