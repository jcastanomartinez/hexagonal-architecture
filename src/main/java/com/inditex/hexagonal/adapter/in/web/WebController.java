package com.inditex.hexagonal.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.hexagonal.application.PricesService;
import com.inditex.hexagonal.application.dto.PriceDto;
import com.inditex.hexagonal.application.dto.PriceRequestDto;
import com.inditex.hexagonal.domain.Price;
import com.inditex.hexagonal.port.in.web.WebPort;

import java.io.IOException;
import java.util.List;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class WebController implements WebPort {

    @Autowired
    private PricesService pricesService;
    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Autowired
    public WebController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

public ResponseEntity<List<PriceDto>> getProducts(@Parameter(in = ParameterIn.DEFAULT, description = "Filtering parameters", required=true, schema=@Schema()) @Valid @RequestBody PriceRequestDto priceRequestDto) {
    String accept = request.getHeader("Accept");
    if (accept != null && accept.contains("application/json")) {
        return new ResponseEntity<>(pricesService.getFilteredPrices(priceRequestDto),HttpStatus.OK);
    }

    return new ResponseEntity<List<PriceDto>>(HttpStatus.NOT_IMPLEMENTED);
}
}
