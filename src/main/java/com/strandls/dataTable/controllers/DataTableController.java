/**
 * 
 */
package com.strandls.dataTable.controllers;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.strandls.dataTable.ApiConstants;
import com.strandls.dataTable.pojo.ShowDataTable;
import com.strandls.dataTable.service.DataTableService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author vishnu
 *
 */

@Api("Data Table Service")
@Path(ApiConstants.V1 + ApiConstants.SERVICES)
public class DataTableController {
	@Inject
	private DataTableService dataTableService;

	@GET
	@Path(ApiConstants.PING)
	@Produces(MediaType.TEXT_PLAIN)

	public Response getPong() {
		return Response.status(Status.OK).entity("PONG").build();
	}

	@GET
	@Path(ApiConstants.SHOW + "/{dataTableId}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "fetch the datatable show page data", notes = "returns the datatable show page data", response = ShowDataTable.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "unable to fetch the data", response = String.class) })
	public Response showDataTable(@PathParam("dataTableId") String dataTableId) {
		try {
			Long datatableId = Long.parseLong(dataTableId);
			ShowDataTable result = dataTableService.show(datatableId);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

}
