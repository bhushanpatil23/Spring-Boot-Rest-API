package com.bhushan.RestAPI.Spring.Boot.Web.MVC.controllers;

import com.bhushan.RestAPI.Spring.Boot.Web.MVC.advices.ApiResponse;
import com.bhushan.RestAPI.Spring.Boot.Web.MVC.annotations.PrimeNumberValidation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/{number}")
    public ResponseEntity<ApiResponse<?>> checkNumberIsPrime(@PathVariable @PrimeNumberValidation Long number){
        return ResponseEntity.ok(new ApiResponse<>(number+" is prime"));
    }
}
