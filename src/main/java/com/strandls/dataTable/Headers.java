package com.strandls.dataTable;

import javax.ws.rs.core.HttpHeaders;
import com.strandls.resource.controllers.ResourceServicesApi;
import com.strandls.user.controller.UserServiceApi;

/**
 * 
 * @author vishnu
 *
 */
public class Headers {

	public ResourceServicesApi addResourceHeaders(ResourceServicesApi resourceService, String authHeader) {
		resourceService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return resourceService;
	}

	public UserServiceApi addUserHeaders(UserServiceApi userService, String authHeader) {
		userService.getApiClient().addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
		return userService;
	}

}
