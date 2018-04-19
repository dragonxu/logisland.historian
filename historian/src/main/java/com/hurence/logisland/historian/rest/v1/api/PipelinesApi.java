/**
* NOTE: This class is auto generated by the swagger code generator program (2.3.1).
* https://github.com/swagger-api/swagger-codegen
* Do not edit the class manually.
*/
package com.hurence.logisland.historian.rest.v1.api;

import com.hurence.logisland.historian.rest.v1.model.Error;
import com.hurence.logisland.historian.rest.v1.model.Pipeline;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-04-19T11:39:12.580+02:00")

@Api(value = "pipelines", description = "the pipelines API")
    public interface PipelinesApi {

            @ApiOperation(value = "create new pipeline", nickname = "addPipelineWithId", notes = "store a new pipeline configuration if valid", response = Pipeline.class, tags={ "pipeline", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "Pipeline successfuly created", response = Pipeline.class),
                @ApiResponse(code = 400, message = "Invalid ID supplied"),
                @ApiResponse(code = 404, message = "Pipeline not found"),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/pipelines/{pipelineId}/stop",
            method = RequestMethod.POST)
        ResponseEntity<Pipeline> addPipelineWithId(@ApiParam(value = "Pipeline configuration to add to the store" ,required=true )  @Valid @RequestBody Pipeline body,@ApiParam(value = "PipelineId to add to the store",required=true) @PathVariable("pipelineId") String pipelineId);


            @ApiOperation(value = "delete pipeline", nickname = "deletePipeline", notes = "remove the corresponding Pipeline definition and stop if its currently running", response = Pipeline.class, tags={ "pipeline", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "pipeline successfully removed", response = Pipeline.class),
                @ApiResponse(code = 400, message = "Invalid ID supplied"),
                @ApiResponse(code = 404, message = "Pipeline not found"),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/pipelines/{pipelineId}",
            method = RequestMethod.DELETE)
        ResponseEntity<Pipeline> deletePipeline(@ApiParam(value = "id of the pipeline to return",required=true) @PathVariable("pipelineId") String pipelineId);


            @ApiOperation(value = "get all pipelines", nickname = "getAllPipelines", notes = "retrieve all pipelines (retrieve only summary fields)", response = Pipeline.class, responseContainer = "List", tags={ "pipeline", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "pipeline configuration list", response = Pipeline.class, responseContainer = "List"),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/pipelines",
            method = RequestMethod.GET)
        ResponseEntity<List<Pipeline>> getAllPipelines();


            @ApiOperation(value = "get pipeline", nickname = "getPipeline", notes = "get the corresponding Pipeline definition", response = Pipeline.class, tags={ "pipeline", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "pipeline definition", response = Pipeline.class),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/pipelines/{pipelineId}",
                produces = { "application/json", "text/plain" }, 
            method = RequestMethod.GET)
        ResponseEntity<Pipeline> getPipeline(@ApiParam(value = "id of the pipeline to return",required=true) @PathVariable("pipelineId") String pipelineId);


            @ApiOperation(value = "start pipeline", nickname = "startPipeline", notes = "start the corresponding Pipeline definition", response = Pipeline.class, tags={ "pipeline", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "pipeline successfuly started", response = Pipeline.class),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/pipelines/{pipelineId}/start",
            method = RequestMethod.POST)
        ResponseEntity<Pipeline> startPipeline(@ApiParam(value = "id of the pipeline to return",required=true) @PathVariable("pipelineId") String pipelineId);


            @ApiOperation(value = "update pipeline", nickname = "updatePipeline", notes = "update an existing pipeline configuration if valid", response = Pipeline.class, tags={ "pipeline", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "Pipeline successfuly created", response = Pipeline.class) })
            @RequestMapping(value = "/api/v1/pipelines/{pipelineId}",
            method = RequestMethod.PUT)
        ResponseEntity<Pipeline> updatePipeline(@ApiParam(value = "Pipeline to add to the store",required=true) @PathVariable("pipelineId") String pipelineId,@ApiParam(value = "Pipeline to add to the store" ,required=true )  @Valid @RequestBody Pipeline pipeline);

        }
