package com.strandls.dataTable;

import com.strandls.activity.controller.ActivityServiceApi;
import com.strandls.resource.controllers.ResourceServicesApi;
import com.strandls.user.controller.UserServiceApi;
import com.strandls.userGroup.controller.UserGroupServiceApi;

import jakarta.ws.rs.core.HttpHeaders;

/**
 *
 * @author vishnu
 *
 */
public class Headers {

	public ActivityServiceApi addActivityHeaders(ActivityServiceApi activityService, String authHeader) {
		activityService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return activityService;
	}

	public ResourceServicesApi addResourceHeaders(ResourceServicesApi resourceService, String authHeader) {
		resourceService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return resourceService;
	}

	public UserServiceApi addUserHeaders(UserServiceApi userService, String authHeader) {
		userService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return userService;
	}

	public UserGroupServiceApi addUserGroupHeaders(UserGroupServiceApi userGroupService, String authHeader) {
		userGroupService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return userGroupService;
	}

}
