package com.inditex.hexagonal.port.in.web;

import com.inditex.hexagonal.application.dto.PriceDto;
import com.inditex.hexagonal.application.dto.PriceRequestDto;
import java.util.List;

import com.inditex.hexagonal.domain.Price;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-02-21T14:40:20.771623259Z[GMT]")
@Validated
public interface WebPort {
    @Operation(summary = "Get prices filtered", description = "Get prices filtered", tags={ "prices" })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Price.class))),

        @ApiResponse(responseCode = "405", description = "Invalid input") })
    @RequestMapping(value = "/Prices",
        produces = { "application/json", "application/xml" },
        consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" },
        method = RequestMethod.POST)
     ResponseEntity<List<PriceDto>> getProducts(@Parameter(in = ParameterIn.DEFAULT, description = "Filtering parameters", required=true, schema=@Schema()) @Valid @RequestBody PriceRequestDto body);
}
