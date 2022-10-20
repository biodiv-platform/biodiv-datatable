package com.strandls.dataTable;

import javax.ws.rs.core.HttpHeaders;

import com.strandls.activity.controller.ActivitySerivceApi;
import com.strandls.resource.controllers.ResourceServicesApi;
import com.strandls.user.controller.UserServiceApi;
import com.strandls.userGroup.controller.UserGroupSerivceApi;

/**
 * 
 * @author vishnu
 *
 */
public class Headers {

	public ActivitySerivceApi addActivityHeaders(ActivitySerivceApi activityService, String authHeader) {
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

	public UserGroupSerivceApi addUserGroupHeaders(UserGroupSerivceApi userGroupService, String authHeader) {
		userGroupService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return userGroupService;
	}

}
