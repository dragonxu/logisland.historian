/**
* NOTE: This class is auto generated by the swagger code generator program (2.3.1).
* https://github.com/swagger-api/swagger-codegen
* Do not edit the class manually.
*/
package com.hurence.logisland.historian.rest.v1.api;

import com.hurence.logisland.historian.rest.v1.model.BulkLoad;
import com.hurence.logisland.historian.rest.v1.model.Error;
import com.hurence.logisland.historian.rest.v1.model.Mesures;
import org.springframework.core.io.Resource;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-04-27T11:32:13.178+02:00")

@Api(value = "tags", description = "the tags API")
    public interface TagsApi {

            @ApiOperation(value = "create new tag", nickname = "addTagWithId", notes = "store a new tag", response = Tag.class, tags={ "tag", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "Tag successfuly created", response = Tag.class),
                @ApiResponse(code = 400, message = "Invalid ID supplied"),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/tags/{itemId}",
            method = RequestMethod.POST)
        ResponseEntity<Tag> addTagWithId(@ApiParam(value = "Tag resource to add" ,required=true )  @Valid @RequestBody Tag body,@ApiParam(value = "itemId to",required=true) @PathVariable("itemId") String itemId);


            @ApiOperation(value = "delete tag", nickname = "deleteTag", notes = "remove the corresponding Tag", response = Tag.class, tags={ "tag", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "Tag successfully removed", response = Tag.class),
                @ApiResponse(code = 400, message = "Invalid ID supplied"),
                @ApiResponse(code = 404, message = "Tag resource not found"),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/tags/{itemId}",
            method = RequestMethod.DELETE)
        ResponseEntity<Tag> deleteTag(@ApiParam(value = "id of the tag to be deleted",required=true) @PathVariable("itemId") String itemId);


            @ApiOperation(value = "get all saved tags", nickname = "getAllTags", notes = "retrieve all OPC tags", response = Tag.class, responseContainer = "List", tags={ "tag","opc", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "tags list", response = Tag.class, responseContainer = "List"),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/tags",
                produces = { "application/json" }, 
            method = RequestMethod.GET)
        ResponseEntity<List<Tag>> getAllTags(@ApiParam(value = "filter query (lucene syntax like fq=\"labels:opc AND datasources:win32\")") @Valid @RequestParam(value = "fq", required = false) String fq);


            @ApiOperation(value = "get tag", nickname = "getTag", notes = "get the corresponding Tag", response = Tag.class, tags={ "tag", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "tag", response = Tag.class),
                @ApiResponse(code = 404, message = "Tag resource not found"),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/tags/{itemId}",
                produces = { "application/json" }, 
            method = RequestMethod.GET)
        ResponseEntity<Tag> getTag(@ApiParam(value = "id of the tag to return",required=true) @PathVariable("itemId") String itemId);


            @ApiOperation(value = "get tag mesures", nickname = "getTagMesures", notes = "get the corresponding Tag mesures between start and end time", response = Mesures.class, tags={ "tag", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "tag", response = Mesures.class),
                @ApiResponse(code = 404, message = "Tag resource not found"),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/tags/{itemId}/mesures",
                produces = { "application/json" }, 
            method = RequestMethod.GET)
        ResponseEntity<Mesures> getTagMesures(@ApiParam(value = "id of the tag",required=true) @PathVariable("itemId") String itemId,@ApiParam(value = "date de début (borne inf) peut-être exprimée sous les formats suivants :   timestamp : 4578965   date-time : 2015-11-25T12:06:57.330Z   relatif   : NOW-30DAYS ", defaultValue = "NOW-5MINUTES") @Valid @RequestParam(value = "start", required = false, defaultValue="NOW-5MINUTES") String start,@ApiParam(value = "date de fin (borne sup) peut-être exprimée sous les formats suivants :   timestamp : 4578965   date-time : 2015-11-25T12:06:57.330Z   relatif   : NOW-30DAYS ", defaultValue = "NOW") @Valid @RequestParam(value = "end", required = false, defaultValue="NOW") String end,@ApiParam(value = "Multiple analyses, aggregations, and transformations are allowed per query. If so, Chronix will first execute the transformations in the order they occur. Then it executes the analyses and aggregations on the result of the chained transformations. For example the query:    max;min;trend;movavg:10,minutes;scale:4  is executed as follows:    Calculate the moving average   Scale the result of the moving average by 4   Calculate the max, min, and the trend based on the prior result. ") @Valid @RequestParam(value = "functions", required = false) String functions);


            @ApiOperation(value = "post tag mesures", nickname = "postTagMesures", notes = "post some new values", response = BulkLoad.class, tags={ "tag","mesure", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "BulkLoad result", response = BulkLoad.class),
                @ApiResponse(code = 404, message = "Tag resource not found"),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/tags/mesures",
                produces = { "application/json" }, 
            method = RequestMethod.POST)
        ResponseEntity<BulkLoad> postTagMesures(@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile content,@ApiParam(value = "the csv file content", defaultValue = ";") @Valid @RequestParam(value = "csv_delimiter", required = false, defaultValue=";") String csvDelimiter,@ApiParam(value = "valid values LONG (ms since 1970),   INSTANT (default java 8 instant),   'SDF-FORMAT' e.g dd.MM.yyyy HH:mm:ss.SSS ", defaultValue = "dd.MM.yyyy HH:mm:ss.SSS") @Valid @RequestParam(value = "date_format", required = false, defaultValue="dd.MM.yyyy HH:mm:ss.SSS") String dateFormat,@ApiParam(value = "valid values ENGLISH, GERMAN", defaultValue = "ENGLISH") @Valid @RequestParam(value = "number_format", required = false, defaultValue="ENGLISH") String numberFormat,@ApiParam(value = "") @Valid @RequestParam(value = "attribute_fields", required = false) String attributeFields,@ApiParam(value = "will discard all previously loaded data (use it with great care)", defaultValue = "false") @Valid @RequestParam(value = "clean_import", required = false, defaultValue="false") Boolean cleanImport);


            @ApiOperation(value = "update tag", nickname = "updateTag", notes = "update an existing tag", response = Tag.class, tags={ "tag","opc", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "Tag successfuly updated", response = Tag.class),
                @ApiResponse(code = 400, message = "Invalid ID supplied"),
                @ApiResponse(code = 404, message = "Tag resource not found") })
            @RequestMapping(value = "/api/v1/tags/{itemId}",
            method = RequestMethod.PUT)
        ResponseEntity<Tag> updateTag(@ApiParam(value = "itemId to be updated",required=true) @PathVariable("itemId") String itemId,@ApiParam(value = "new Tag definition" ,required=true )  @Valid @RequestBody Tag tag);

        }
