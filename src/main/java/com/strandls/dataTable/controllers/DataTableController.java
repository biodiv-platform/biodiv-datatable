/**
 * 
 */
package com.strandls.dataTable.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.strandls.dataTable.ApiConstants;

import io.swagger.annotations.Api;

/**
 * @author Abhishek Rudra
 *
 * 
 */

@Api("Data Table Service")
@Path(ApiConstants.V1 + ApiConstants.SERVICES)
public class DataTableController {

	@GET
	@Path(ApiConstants.PING)
	@Produces(MediaType.TEXT_PLAIN)

	public Response getPong() {
		return Response.status(Status.OK).entity("PONG").build();
	}

}
