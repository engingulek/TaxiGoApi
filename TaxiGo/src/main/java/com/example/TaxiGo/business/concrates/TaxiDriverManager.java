package com.example.TaxiGo.business.concrates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaxiGo.business.abstracts.TaxiDriverService;
import com.example.TaxiGo.core.mappers.ModelMapperService;
import com.example.TaxiGo.core.request.TaxiDriverRequest;
import com.example.TaxiGo.core.response.CreateTaxiDriverResponse;
import com.example.TaxiGo.dataAccess.TaxiDriverRepository;
import com.example.TaxiGo.entities.TaxiDriver;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TaxiDriverManager implements TaxiDriverService {
    @Autowired
    private TaxiDriverRepository taxiDriverRepository;


    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public CreateTaxiDriverResponse createTaxiDriver(TaxiDriverRequest taxiDriverRequest) {
        TaxiDriver taxi_driver = new TaxiDriver(
            taxiDriverRequest.getImage(),
            taxiDriverRequest.getName(),
            taxiDriverRequest.getSurname(),
            taxiDriverRequest.getTaxi_plate(),
            taxiDriverRequest.getType(),
            taxiDriverRequest.getKm_price()
            );

            taxiDriverRepository.save(taxi_driver);
            TaxiDriver  created_taxi_driver = taxiDriverRepository.save(taxi_driver);
             CreateTaxiDriverResponse createTaxiDriverResponse = modelMapperService.forResponse()
      .map(created_taxi_driver, CreateTaxiDriverResponse.class);
            return   createTaxiDriverResponse;
    }
}
