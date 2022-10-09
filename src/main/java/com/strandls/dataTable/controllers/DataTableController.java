/**
 * 
 */
package com.strandls.dataTable.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.pac4j.core.profile.CommonProfile;

import com.strandls.activity.pojo.Activity;
import com.strandls.activity.pojo.CommentLoggingData;
import com.strandls.authentication_utility.filter.ValidateUser;
import com.strandls.authentication_utility.util.AuthUtil;
import com.strandls.dataTable.ApiConstants;
import com.strandls.dataTable.dto.BulkDTO;
import com.strandls.dataTable.pojo.DataTableList;
import com.strandls.dataTable.pojo.DataTableWkt;
import com.strandls.dataTable.service.DataTableService;
import com.strandls.userGroup.pojo.UserGroupCreateDatatable;
import com.strandls.userGroup.pojo.UserGroupIbp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
	@ApiOperation(value = "fetch the datatable show page data", notes = "returns the datatable show page data", response = DataTableWkt.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "unable to fetch the data", response = String.class) })
	public Response showDataTable(@PathParam("dataTableId") String dataTableId) {
		try {
			Long datatableId = Long.parseLong(dataTableId);
			DataTableWkt result = dataTableService.show(datatableId);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

	@GET
	@Path(ApiConstants.LIST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ApiOperation(value = "Fetch the Datatable list", notes = "Returns the datatable list", response = DataTableList.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "unable to fetch the data", response = String.class) })

	public Response observationList(@DefaultValue("lastRevised") @QueryParam("sort") String sortOn,
			@DefaultValue("0") @QueryParam("offset") String Offset,
			@DefaultValue("") @QueryParam("userGroupId") String userGroupId,
			@DefaultValue("10") @QueryParam("limit") String Limit) {

		try {
			Integer offset = Integer.parseInt(Offset);
			Integer limit = Integer.parseInt(Limit);
			DataTableList result = dataTableService.dataTableList(sortOn, limit, offset, userGroupId);
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

	@POST
	@Path(ApiConstants.CREATE)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Creates the datatable", notes = "returns the datatable", response = DataTableWkt.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "unable to fetch the data", response = String.class) })
	public Response createDataTable(@Context HttpServletRequest request, @ApiParam("bulkDto") BulkDTO bukDto) {
		try {
			DataTableWkt result = dataTableService.createDataTable(request, bukDto);
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

	@PUT
	@Path(ApiConstants.UPDATE)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Updates the datatable", notes = "returns Updated datatable", response = DataTableWkt.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "unable to fetch the data", response = String.class) })
	public Response updateDataTable(@Context HttpServletRequest request,
			@ApiParam("dataTable") DataTableWkt dataTable) {
		try {

			DataTableWkt result = dataTableService.updateDataTable(request, dataTable);
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

	public Response deleteDataTable(@Context HttpServletRequest request, @PathParam("dataTableId") String dataTableId) {

		try {
			Long id = Long.parseLong(dataTableId);
			CommonProfile profile = AuthUtil.getProfileFromRequest(request);
			Long userId = Long.parseLong(profile.getId());
			String result = dataTableService.deleteDataTableById(request, profile, userId, id);
			return Response.status(Status.OK).entity(result).build();

		} catch (

		Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.COMMENT+ApiConstants.ADD)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser

	@ApiOperation(value = "Adds a comment", notes = "Return the current activity", response = Activity.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Unable to log a comment", response = String.class) })

	public Response addCommnet(@Context HttpServletRequest request,
			@ApiParam(name = "commentData") CommentLoggingData commentDatas) {
		try {
			Activity result = dataTableService.addDataTableComment(request, commentDatas);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

	@PUT
	@Path(ApiConstants.UPDATE + ApiConstants.DATATABLE + "/{datatableId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	@ValidateUser
	@ApiOperation(value = "Update the UserGroup Datatable Mapping", notes = "Returns the List of UserGroup Linked", response = UserGroupIbp.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Unable to Update the UserGroup Datatable Mapping", response = String.class) })

	public Response updateDatatableUserGroupMapping(@Context HttpServletRequest request,
			@PathParam("datatableId") String dataTableId,
			@ApiParam(name = "userGroupData") UserGroupCreateDatatable userGroupData) {
		try {
			Long datatableId = Long.parseLong(dataTableId);
			List<UserGroupIbp> result = dataTableService.updateUserGroupDatatableMapping(request, datatableId,
					userGroupData);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}
