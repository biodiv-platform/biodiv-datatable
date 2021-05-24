package com.strandls.dataTable.pojo;

import java.util.List;
import java.util.Map;

import com.strandls.naksha.pojo.ObservationLocationInfo;
import com.strandls.observation.pojo.Observation;
import com.strandls.user.pojo.UserIbp;
import com.strandls.userGroup.pojo.UserGroupIbp;
/**
 * 
 * @author vishnu
 *
 */

public class ShowDataTable {

	/**
	 * 
	 */
	private DataTable datatable;
	private List<UserGroupIbp> userGroups;
	private ObservationLocationInfo layerInfo;
	private UserIbp authorInfo;
	private Map<String, String> authorScore;
	private List<Observation> observationList;
	
	/**
	 * 
	 * @param id
	 * @param datatable
	 * @param userGroups
	 * @param layerInfo
	 * @param authorInfo
	 * @param authorScore
	 */
	public ShowDataTable(DataTable datatable, List<UserGroupIbp> userGroups, ObservationLocationInfo layerInfo,
			UserIbp authorInfo, Map<String, String> authorScore) {
		super();
		this.datatable = datatable;
		this.userGroups = userGroups;
		this.layerInfo = layerInfo;
		this.authorInfo = authorInfo;
		this.authorScore = authorScore;
	}

	public DataTable getDatatable() {
		return datatable;
	}

	public void setDatatable(DataTable datatable) {
		this.datatable = datatable;
	}

	public List<UserGroupIbp> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UserGroupIbp> userGroups) {
		this.userGroups = userGroups;
	}

	public ObservationLocationInfo getLayerInfo() {
		return layerInfo;
	}

	public void setLayerInfo(ObservationLocationInfo layerInfo) {
		this.layerInfo = layerInfo;
	}

	public UserIbp getAuthorInfo() {
		return authorInfo;
	}

	public void setAuthorInfo(UserIbp authorInfo) {
		this.authorInfo = authorInfo;
	}

	public Map<String, String> getAuthorScore() {
		return authorScore;
	}

	public void setAuthorScore(Map<String, String> authorScore) {
		this.authorScore = authorScore;
	}

	public List<Observation> getObservationList() {
		return observationList;
	}

	public void setObservationList(List<Observation> observationList) {
		this.observationList = observationList;
	}
	

}
