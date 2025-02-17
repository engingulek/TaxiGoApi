package com.example.TaxiGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaxiGo.business.abstracts.TaxiDriverService;
import com.example.TaxiGo.core.request.TaxiDriverRequest;
import com.example.TaxiGo.core.response.CreateTaxiDriverResponse;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("api/taxiDriver/")
@AllArgsConstructor
@NoArgsConstructor
public class TaxiDriverController {
    
    @Autowired
    private TaxiDriverService taxiDriverService;


    @PostMapping("createDriver")
    public CreateTaxiDriverResponse createTaxiDriver(@RequestBody TaxiDriverRequest taxiDriverRequest){
        return taxiDriverService.createTaxiDriver(taxiDriverRequest);
    }

}
