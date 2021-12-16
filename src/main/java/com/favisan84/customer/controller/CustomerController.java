package com.favisan84.customer.controller;

import com.favisan84.customer.config.SwaggerConfig;
import com.favisan84.customer.model.dto.CustomerDTO;
import com.favisan84.customer.model.entity.CustomerEntity;
import com.favisan84.customer.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import static com.favisan84.customer.config.SwaggerConfig.*;

@Slf4j
@RestController
@RequestMapping("customers")
@Api(value = "customers", tags = CUSTOMER_TAG)
public class CustomerController {

    @Autowired
    private RestTemplate client;

    @Autowired
    private CustomerService service;

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Customer created"),
            @ApiResponse(code = 400, message = "Bad request!!"),
            @ApiResponse(code = 401, message = "No authorization"),
            @ApiResponse(code = 403, message = "Authorization denied"),
            @ApiResponse(code = 500, message = "Internal Error")
    })
    @PostMapping(value = "/")
    public ResponseEntity<CustomerDTO> signup(@Valid @RequestBody CustomerDTO customer){
        log.info("Signing up new customer: {}", customer.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.signup(customer));
    }

    @GetMapping(value = "/{email}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer found!"),
            @ApiResponse(code = 400, message = "Bad request!!"),
            @ApiResponse(code = 401, message = "No authorization"),
            @ApiResponse(code = 403, message = "Authorization denied"),
            @ApiResponse(code = 404, message = "No data found"),
            @ApiResponse(code = 500, message = "Internal Error")
    })

    public ResponseEntity<CustomerDTO> findByEmail(@PathVariable("email") String email){
        log.info("Searching customer by email: {}", email);
        return ResponseEntity.ok().body(service.findByEmail(email));
    }

}
