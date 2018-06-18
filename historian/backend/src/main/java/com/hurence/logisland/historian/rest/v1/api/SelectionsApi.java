/**
* NOTE: This class is auto generated by the swagger code generator program (2.3.1).
* https://github.com/swagger-api/swagger-codegen
* Do not edit the class manually.
*/
package com.hurence.logisland.historian.rest.v1.api;

import com.hurence.logisland.historian.rest.v1.model.Error;
import com.hurence.logisland.historian.rest.v1.model.Selection;
import com.hurence.logisland.historian.rest.v1.model.Tag;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-15T10:59:39.197+02:00")

@Api(value = "selections", description = "the selections API")
    public interface SelectionsApi {

            @ApiOperation(value = "create new Selection", nickname = "addSelectionWithId", notes = "store a new Selection", response = Selection.class, tags={ "selection", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "Selection successfuly created", response = Selection.class),
                @ApiResponse(code = 400, message = "Invalid ID supplied"),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/selections/{selectionId}",
            method = RequestMethod.POST)
        ResponseEntity<Selection> addSelectionWithId(@ApiParam(value = "Selection resource to add" ,required=true )  @Valid @RequestBody Selection body,@ApiParam(value = "selectionId to create",required=true) @PathVariable("selectionId") String selectionId);


            @ApiOperation(value = "delete Selection", nickname = "deleteSelection", notes = "remove the corresponding Selection", response = Selection.class, tags={ "selection", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "Selection successfully removed", response = Selection.class),
                @ApiResponse(code = 400, message = "Invalid ID supplied"),
                @ApiResponse(code = 404, message = "Selection resource not found"),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/selections/{selectionId}",
            method = RequestMethod.DELETE)
        ResponseEntity<Selection> deleteSelection(@ApiParam(value = "id of the Selection to be deleted",required=true) @PathVariable("selectionId") String selectionId);


            @ApiOperation(value = "get all tags from a selection", nickname = "getAllTagsFromSelection", notes = "retrieve all tags through this selection", response = Tag.class, responseContainer = "List", tags={ "selection","tag", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "Tag list", response = Tag.class, responseContainer = "List"),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/selections/{selectionId}/tags",
                produces = { "application/json" }, 
            method = RequestMethod.GET)
        ResponseEntity<List<Tag>> getAllTagsFromSelection(@ApiParam(value = "id of the selection",required=true) @PathVariable("selectionId") String selectionId);


            @ApiOperation(value = "get Selection", nickname = "getSelection", notes = "get the corresponding Selection", response = Selection.class, tags={ "selection", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "Selection found", response = Selection.class),
                @ApiResponse(code = 404, message = "Selection resource not found"),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/selections/{selectionId}",
                produces = { "application/json" }, 
            method = RequestMethod.GET)
        ResponseEntity<Selection> getSelection(@ApiParam(value = "id of the Selection to return",required=true) @PathVariable("selectionId") String selectionId);


            @ApiOperation(value = "update Selection", nickname = "updateSelection", notes = "update an existing Selection", response = Selection.class, tags={ "selection", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "Selection successfuly updated", response = Selection.class),
                @ApiResponse(code = 400, message = "Invalid ID supplied"),
                @ApiResponse(code = 404, message = "Selection resource not found") })
            @RequestMapping(value = "/api/v1/selections/{selectionId}",
            method = RequestMethod.PUT)
        ResponseEntity<Selection> updateSelection(@ApiParam(value = "selectionId to be updated",required=true) @PathVariable("selectionId") String selectionId,@ApiParam(value = "new Selection definition" ,required=true )  @Valid @RequestBody Selection selection);

        }
