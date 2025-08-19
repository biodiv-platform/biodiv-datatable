/**
 *
 */
package com.strandls.dataTable.controllers;

import java.util.List;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 *
 * @author vishnu
 *
 */

@Tag(name = "Data Table Service", description = "All operations related to Data Tables")
@Path(ApiConstants.V1 + ApiConstants.SERVICES)
public class DataTableController {
	@Inject
	private DataTableService dataTableService;

	@GET
	@Path(ApiConstants.PING)
	@Produces(MediaType.TEXT_PLAIN)
	@Operation(summary = "Ping", description = "Checks if the service is up and running")
	public Response getPong() {
		return Response.status(Status.OK).entity("PONG").build();
	}

	@GET
	@Path(ApiConstants.SHOW + "/{dataTableId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "fetch the datatable show page data", description = "returns the datatable show page data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully fetched the data", content = @Content(schema = @Schema(implementation = DataTableWkt.class))),
			@ApiResponse(responseCode = "400", description = "unable to fetch the data", content = @Content(schema = @Schema(implementation = String.class))) })
	public Response showDataTable(
			@Parameter(description = "The ID of the datatable to fetch") @PathParam("dataTableId") String dataTableId) {
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
	@Operation(summary = "Fetch the Datatable list", description = "Returns the datatable list")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully fetched the list", content = @Content(schema = @Schema(implementation = DataTableList.class))),
			@ApiResponse(responseCode = "400", description = "unable to fetch the data", content = @Content(schema = @Schema(implementation = String.class))) })
	public Response observationList(
			@Parameter(description = "Field to sort on") @DefaultValue("lastRevised") @QueryParam("sort") String sortOn,
			@Parameter(description = "Starting offset for the list") @DefaultValue("0") @QueryParam("offset") String Offset,
			@Parameter(description = "Filter by user group ID") @DefaultValue("") @QueryParam("userGroupId") String userGroupId,
			@Parameter(description = "Number of items to return") @DefaultValue("10") @QueryParam("limit") String Limit) {

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
	@Operation(summary = "Creates the datatable", description = "returns the datatable")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Datatable created successfully", content = @Content(schema = @Schema(implementation = DataTableWkt.class))),
			@ApiResponse(responseCode = "400", description = "unable to create the data", content = @Content(schema = @Schema(implementation = String.class))) })
	public Response createDataTable(@Context HttpServletRequest request,
			@RequestBody(description = "DTO for bulk datatable creation", required = true, content = @Content(schema = @Schema(implementation = BulkDTO.class))) BulkDTO bukDto) {
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
	@Operation(summary = "Updates the datatable", description = "returns Updated datatable")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Datatable updated successfully", content = @Content(schema = @Schema(implementation = DataTableWkt.class))),
			@ApiResponse(responseCode = "400", description = "unable to update the data", content = @Content(schema = @Schema(implementation = String.class))) })
	public Response updateDataTable(@Context HttpServletRequest request,
			@RequestBody(description = "The datatable to update", required = true, content = @Content(schema = @Schema(implementation = DataTableWkt.class))) DataTableWkt dataTable) {
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
	@Operation(summary = "Remove the datatable by id", description = "returns the status of the deletion")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Datatable deleted successfully", content = @Content(schema = @Schema(implementation = String.class))),
			@ApiResponse(responseCode = "400", description = "unable to delete datatable", content = @Content(schema = @Schema(implementation = String.class))) })
	public Response deleteDataTable(@Context HttpServletRequest request,
			@Parameter(description = "The ID of the datatable to delete") @PathParam("dataTableId") String dataTableId) {

		try {
			Long id = Long.parseLong(dataTableId);
			CommonProfile profile = AuthUtil.getProfileFromRequest(request);
			Long userId = Long.parseLong(profile.getId());
			String result = dataTableService.deleteDataTableById(request, profile, userId, id);
			return Response.status(Status.OK).entity(result).build();

		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path(ApiConstants.COMMENT + ApiConstants.ADD)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser
	@Operation(summary = "Adds a comment", description = "Return the current activity")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Comment added successfully", content = @Content(schema = @Schema(implementation = Activity.class))),
			@ApiResponse(responseCode = "400", description = "Unable to log a comment", content = @Content(schema = @Schema(implementation = String.class))) })
	public Response addComment(@Context HttpServletRequest request,
			@RequestBody(description = "The comment data", required = true, content = @Content(schema = @Schema(implementation = CommentLoggingData.class))) CommentLoggingData commentDatas) {
		try {
			Activity result = dataTableService.addDataTableComment(request, commentDatas);
			return Response.status(Status.OK).entity(result).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}

	}

	@POST
	@Path(ApiConstants.DELETE + ApiConstants.COMMENT + "/{commentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ValidateUser
	@Operation(summary = "Deletes a comment", description = "Return the current activity")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Comment deleted successfully", content = @Content(schema = @Schema(implementation = Activity.class))),
			@ApiResponse(responseCode = "400", description = "Unable to delete a comment", content = @Content(schema = @Schema(implementation = String.class))) })
	public Response deleteComment(@Context HttpServletRequest request,
			@RequestBody(description = "The comment data for deletion context", required = true, content = @Content(schema = @Schema(implementation = CommentLoggingData.class))) CommentLoggingData commentDatas,
			@Parameter(description = "The ID of the comment to delete") @PathParam("commentId") String commentId) {
		try {
			Activity result = dataTableService.removeDatatableComment(request, commentDatas, commentId);
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
	@Operation(summary = "Update the UserGroup Datatable Mapping", description = "Returns the List of UserGroup Linked")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Mapping updated successfully", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserGroupIbp.class)))),
			@ApiResponse(responseCode = "400", description = "Unable to Update the UserGroup Datatable Mapping", content = @Content(schema = @Schema(implementation = String.class))) })
	public Response updateDatatableUserGroupMapping(@Context HttpServletRequest request,
			@Parameter(description = "The ID of the datatable to map") @PathParam("datatableId") String dataTableId,
			@RequestBody(description = "The user group data for mapping", required = true, content = @Content(schema = @Schema(implementation = UserGroupCreateDatatable.class))) UserGroupCreateDatatable userGroupData) {
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
