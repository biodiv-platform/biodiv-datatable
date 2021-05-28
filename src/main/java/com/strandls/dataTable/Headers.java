package com.strandls.dataTable;

import javax.ws.rs.core.HttpHeaders;

import com.strandls.user.controller.UserServiceApi;
import com.strandls.userGroup.controller.UserGroupSerivceApi;
import  com.strandls.naksha.controller.LayerServiceApi;
import com.strandls.observation.controller.ObservationServiceApi;
import com.strandls.activity.controller.ActivitySerivceApi;

public class Headers {
	
	public UserServiceApi addUserHeaders(UserServiceApi userService, String authHeader) {
		userService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return userService;
	}

	public UserGroupSerivceApi addUserGroupHeader(UserGroupSerivceApi ugService, String authHeader) {
		ugService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return ugService;
	}
	
	public ObservationServiceApi addObservationHeader(ObservationServiceApi obService, String authHeader) {
		obService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return obService;
	}
	public LayerServiceApi addLayerGroupHeader (LayerServiceApi layerService, String authHeader) {
		
		layerService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return layerService;
	}
	
	public ActivitySerivceApi addActivityService (ActivitySerivceApi activityService ,String authHeader) {
		activityService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return activityService;
	}

}
