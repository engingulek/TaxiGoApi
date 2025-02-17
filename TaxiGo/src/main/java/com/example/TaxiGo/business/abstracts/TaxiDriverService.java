package com.example.TaxiGo.business.abstracts;

import com.example.TaxiGo.core.request.TaxiDriverRequest;
import com.example.TaxiGo.core.response.CreateTaxiDriverResponse;

public interface TaxiDriverService {
    CreateTaxiDriverResponse createTaxiDriver(TaxiDriverRequest taxiDriverRequest);
    
}