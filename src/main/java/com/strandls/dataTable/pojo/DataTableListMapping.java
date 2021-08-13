package com.strandls.dataTable.pojo;

import java.util.Date;

import com.strandls.user.pojo.UserIbp;

public class DataTableListMapping {

	private Long id;
	private String title;
	private String dataTableType;
	private String summary;

	private String taxonomicCoverageGroupIds;
	private Date temporalCoverageFromDate;
	private String temporalCoverageDateAccuracy;
	private String geographicalCoveragePlaceName;
	private UserIbp user;

	public DataTableListMapping(Long id, String title, String dataTableType, String summary,
			String taxonomicCoverageGroupIds, Date temporalCoverageFromDate, String temporalCoverageDateAccuracy,
			String geographicalCoveragePlaceName, UserIbp user) {
		super();
		this.id = id;
		this.title = title;
		this.dataTableType = dataTableType;
		this.summary = summary;
		this.taxonomicCoverageGroupIds = taxonomicCoverageGroupIds;
		this.temporalCoverageFromDate = temporalCoverageFromDate;
		this.temporalCoverageDateAccuracy = temporalCoverageDateAccuracy;
		this.geographicalCoveragePlaceName = geographicalCoveragePlaceName;
		this.user = user;
	}

	public DataTableListMapping() {
		super();
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

	public String getDataTableType() {
		return dataTableType;
	}

	public void setDataTableType(String dataTableType) {
		this.dataTableType = dataTableType;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTaxonomicCoverageGroupIds() {
		return taxonomicCoverageGroupIds;
	}

	public void setTaxonomicCoverageGroupIds(String taxonomicCoverageGroupIds) {
		this.taxonomicCoverageGroupIds = taxonomicCoverageGroupIds;
	}

	public Date getTemporalCoverageFromDate() {
		return temporalCoverageFromDate;
	}

	public void setTemporalCoverageFromDate(Date temporalCoverageFromDate) {
		this.temporalCoverageFromDate = temporalCoverageFromDate;
	}

	public String getTemporalCoverageDateAccuracy() {
		return temporalCoverageDateAccuracy;
	}

	public void setTemporalCoverageDateAccuracy(String temporalCoverageDateAccuracy) {
		this.temporalCoverageDateAccuracy = temporalCoverageDateAccuracy;
	}

	public String getGeographicalCoveragePlaceName() {
		return geographicalCoveragePlaceName;
	}

	public void setGeographicalCoveragePlaceName(String geographicalCoveragePlaceName) {
		this.geographicalCoveragePlaceName = geographicalCoveragePlaceName;
	}

	public UserIbp getUser() {
		return user;
	}

	public void setUser(UserIbp user) {
		this.user = user;
	}

}
