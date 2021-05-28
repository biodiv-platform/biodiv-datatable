/**
 * 
 */
package com.strandls.dataTable.controllers;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.pac4j.core.profile.CommonProfile;

import com.strandls.authentication_utility.util.AuthUtil;
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "fetch the datatable show page data", notes = "returns the datatable show page data", response = ShowDataTable.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "unable to fetch the data", response = String.class) })
	public Response showDataTable(@PathParam("dataTableId") String dataTableId,
			@DefaultValue("0") @QueryParam("offset") String Offset,
			@DefaultValue("10") @QueryParam("limit") String Limt) {
		try {
			Long datatableId = Long.parseLong(dataTableId);
			Long limit = Long.parseLong(Limt);
			Long offset = Long.parseLong(Offset);
			ShowDataTable result = dataTableService.show(datatableId, offset, limit);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

	@DELETE
	@Path("/{dataTableId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Remove  the datatable by id", notes = "returns the datatable by id", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "unable to delete datatable", response = String.class) })

	public Response deleteDataTable(@Context HttpServletRequest request,@PathParam("dataTableId")String dataTableId ) {

		try {
			Long id = Long.parseLong(dataTableId);
			CommonProfile profile = AuthUtil.getProfileFromRequest(request);
			Long userId = Long.parseLong(profile.getId());
			String result = dataTableService.deleteDataTableById(request,profile, userId, id);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}
