/**
* NOTE: This class is auto generated by the swagger code generator program (2.3.1).
* https://github.com/swagger-api/swagger-codegen
* Do not edit the class manually.
*/
package com.hurence.logisland.historian.rest.v1.api;

import com.hurence.logisland.historian.rest.v1.model.DataFlow;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-19T09:33:39.714+02:00")

@Api(value = "dataflows", description = "the dataflows API")
    public interface DataflowsApi {

            @ApiOperation(value = "Push the configuration of running dataflows.", nickname = "notifyDataflowConfiguration", notes = "In order to ensure business continuity, Logisland will contact the third party application in order to push a snapshot of the current configuration. The endpoint will be called: - On a regular basis (according to logisland configuration). - Each time the a dataflow or a pipeline configuration change has been applied. This service can be seen as well as a liveness ping.", tags={ "dataflow", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "The server should return HTTP 200 OK. By the way, the response is ignored by Logisland since the operation has a *fire and forget* nature. ") })
            @RequestMapping(value = "/api/v1/dataflows/{dataflowName}",
            method = RequestMethod.POST)
        ResponseEntity<Void> notifyDataflowConfiguration(@ApiParam(value = "the dataflow name (aka the logisland job name)",required=true) @PathVariable("dataflowName") String dataflowName,@ApiParam(value = "logisland job id (aka the engine name)",required=true) @PathVariable("jobId") String jobId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody DataFlow dataflow);


            @ApiOperation(value = "Retrieves the configuration for a specified dataflow", nickname = "pollDataflowConfiguration", notes = "A dataflow is a set of services and streams allowing a data flowing from one or more sources, being transformed and reach one or more destinations (sinks). Logisland will call this endpoint to know which configuration should be run.  This endpoint also supports HTTP caching (Last-Updated, If-Modified-Since) as per RFC 7232, section 3.3", response = DataFlow.class, tags={ "dataflow", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "Return the dataflow configuration. On logisland side, the following will happen: - At dataflow level:   - Fully reconfigure a dataflow (stop and then start) if nothing is running (initial state) or if lastUpdated is fresher than the one of the already running dataflow.     In this case be aware that old stream and services will be destroyed and     new ones will be created.   - Do nothing otherwise (keep running the active dataflow) - At pipeline level:   - The processor chain will be fully reconfigured if and only if the pipeline lastUpdated is fresher than the lastUpdated known by the system.   In any case the stream is never stopped.", response = DataFlow.class),
                @ApiResponse(code = 304, message = "Nothing has been modified since the last call. In this case the body content will be completely ignored (hence the server can answer with an empty body to save network and resources). "),
                @ApiResponse(code = 404, message = "Not found (the server probably does not handle this dataflow)"),
                @ApiResponse(code = 200, message = "Unexpected error") })
            @RequestMapping(value = "/api/v1/dataflows/{dataflowName}",
            method = RequestMethod.GET)
        ResponseEntity<DataFlow> pollDataflowConfiguration(@ApiParam(value = "the dataflow name (aka the logisland job name)",required=true) @PathVariable("dataflowName") String dataflowName,@ApiParam(value = "Timestamp of last response" ) @RequestHeader(value="If-Modified-Since", required=false) String ifModifiedSince);

        }