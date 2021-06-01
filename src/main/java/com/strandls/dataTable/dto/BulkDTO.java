package com.strandls.dataTable.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BulkDTO {
	
	 // title
    private String title;
    private String summary;
    private String description;
    private Date createdOn;

    // usage rights
    private Long licenseId;
    
    // taxonomic coverage
    private String sGroup;
    
    // party
    private Long contributors;
    private String attribution;


    // temporal coverage
    private String dateAccuracy;
    private Date observedFromDate;
    private Date observedToDate;

    // geographical coverage
    private String locationScale;
    private String locationAccuracy;
    private String observedAt;
    private String reverseGeocoded;
    private String wktString;
    private Double latitude;
    private Double longitude;

    // others
    private String project;
    private String methods;
    private String basisOfData;
//    private String basisOfRecord;

    // other fields related to bulk upload
    private Long dataset;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(Long licenseId) {
		this.licenseId = licenseId;
	}

	public Long getContributors() {
		return contributors;
	}

	public void setContributors(Long contributors) {
		this.contributors = contributors;
	}

	public String getAttribution() {
		return attribution;
	}

	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}

	public String getDateAccuracy() {
		return dateAccuracy;
	}

	public void setDateAccuracy(String dateAccuracy) {
		this.dateAccuracy = dateAccuracy;
	}

	public Date getObservedFromDate() {
		return observedFromDate;
	}

	public void setObservedFromDate(Date observedFromDate) {
		this.observedFromDate = observedFromDate;
	}

	public Date getObservedToDate() {
		return observedToDate;
	}

	public void setObservedToDate(Date observedToDate) {
		this.observedToDate = observedToDate;
	}

	public String getLocationScale() {
		return locationScale;
	}

	public void setLocationScale(String locationScale) {
		this.locationScale = locationScale;
	}

	public String getLocationAccuracy() {
		return locationAccuracy;
	}

	public void setLocationAccuracy(String locationAccuracy) {
		this.locationAccuracy = locationAccuracy;
	}

	public String getObservedAt() {
		return observedAt;
	}

	public void setObservedAt(String observedAt) {
		this.observedAt = observedAt;
	}

	public String getReverseGeocoded() {
		return reverseGeocoded;
	}

	public void setReverseGeocoded(String reverseGeocoded) {
		this.reverseGeocoded = reverseGeocoded;
	}

	public String getWktString() {
		return wktString;
	}

	public void setWktString(String wktString) {
		this.wktString = wktString;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getBasisOfData() {
		return basisOfData;
	}

	public void setBasisOfData(String basisOfData) {
		this.basisOfData = basisOfData;
	}

	public Long getDataset() {
		return dataset;
	}

	public void setDataset(Long dataset) {
		this.dataset = dataset;
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

	public String getsGroup() {
		return sGroup;
	}

	public void setsGroup(String sGroup) {
		this.sGroup = sGroup;
	}
    
    

}
