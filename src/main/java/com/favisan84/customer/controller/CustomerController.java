package com.favisan84.customer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/customers/")
@Api(value = "/customers/", tags = "Customers", description = "Customers API's")
public class CustomerController {

    @GetMapping(value = "/test")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Test OK"),
            @ApiResponse(code = 400, message = "Bad request!!"),
            @ApiResponse(code = 401, message = "No authorization"),
            @ApiResponse(code = 403, message = "Authorization denied"),
            @ApiResponse(code = 404, message = "No data found"),
            @ApiResponse(code = 500, message = "Internal Error")
    })
    public ResponseEntity test(){
         return ResponseEntity.ok().body("OK");

    }

}
