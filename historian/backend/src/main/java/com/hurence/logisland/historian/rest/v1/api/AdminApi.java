/**
* NOTE: This class is auto generated by the swagger code generator program (2.3.1).
* https://github.com/swagger-api/swagger-codegen
* Do not edit the class manually.
*/
package com.hurence.logisland.historian.rest.v1.api;

import com.hurence.logisland.historian.rest.v1.model.Error;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-04-27T16:08:38.689+02:00")

@Api(value = "admin", description = "the admin API")
    public interface AdminApi {

            @ApiOperation(value = "create new dataset with some sample data", nickname = "sampleData", notes = "create new dataset with some sample data", response = Tag.class, responseContainer = "List", tags={ "tag","datasource","admin", })
            @ApiResponses(value = { 
                @ApiResponse(code = 200, message = "Sample tag list", response = Tag.class, responseContainer = "List"),
                @ApiResponse(code = 200, message = "unexpected error", response = Error.class) })
            @RequestMapping(value = "/api/v1/admin",
                produces = { "application/json" }, 
            method = RequestMethod.POST)
        ResponseEntity<List<Tag>> sampleData(@ApiParam(value = "do we flush previous entries ?", defaultValue = "false") @Valid @RequestParam(value = "flush", required = false, defaultValue="false") Boolean flush);

        }
