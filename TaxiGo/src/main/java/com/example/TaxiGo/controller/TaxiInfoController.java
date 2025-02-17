package com.example.TaxiGo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.TaxiGo.core.request.TaxiInfoRequest;
import com.example.TaxiGo.dataAccess.TaxiDriverRepository;
import com.example.TaxiGo.dataAccess.TaxiTypeRepository;
import com.example.TaxiGo.entities.TaxiDriver;
import com.example.TaxiGo.entities.TaxiInfo;
import com.example.TaxiGo.websocketConfig.TaxiGoWebSocketHandler;

@RestController
@RequestMapping("/api/taxiInfo")
public class TaxiInfoController {
   private final TaxiGoWebSocketHandler taxiGoWebSocketHandler;
   private final TaxiTypeRepository taxiTypeRepository;

   private final TaxiDriverRepository taxiDriverRepository;


    public TaxiInfoController (
        TaxiTypeRepository taxiTypeRepository,
        TaxiGoWebSocketHandler taxiGoWebSocketHandler,
        TaxiDriverRepository taxiDriverRepository
        ){
        this.taxiTypeRepository = taxiTypeRepository;
        this.taxiGoWebSocketHandler = taxiGoWebSocketHandler;
        this.taxiDriverRepository = taxiDriverRepository;

    }

    @GetMapping
    public List<TaxiInfo> getAllTaxiInfos() {
        List<TaxiInfo> taxiInfos = taxiTypeRepository.findAll();
        return taxiInfos;
    }

    @PutMapping("/updateLocation")
    public Boolean updateTaxiInfoLocation(@RequestBody TaxiInfoRequest taxiInfoRequest) {
        Optional<TaxiInfo> existingTaxiInfo = taxiTypeRepository.findByDriverId(taxiInfoRequest.getDriver_id());

        if(existingTaxiInfo.isPresent()){
            TaxiInfo taxiInfo = existingTaxiInfo.get();

            taxiInfo.setLatitude(taxiInfoRequest.getLatitude());
            taxiInfo.setLongitude(taxiInfoRequest.getLongitude());

             taxiTypeRepository.save(taxiInfo);
            List<TaxiInfo> taxiInfos = taxiTypeRepository.findAll();
            taxiGoWebSocketHandler.broadcastTaxiInfoList(taxiInfos);

            return true;
        }else{
            Optional<TaxiDriver> taxiDriver = taxiDriverRepository.findById(taxiInfoRequest.getDriver_id());
            
            
           if(taxiDriver.isPresent()){
            TaxiInfo taxiInfo = new TaxiInfo(
                null, 
            taxiDriver.get().getType(), 
            
            taxiInfoRequest.getLatitude(), 
            taxiInfoRequest.getLongitude(), 
            4, 
            taxiDriver.get().getKm_price(), 
            false, 
            taxiInfoRequest.getDriver_id());

            taxiTypeRepository.save(taxiInfo);
            List<TaxiInfo> taxiInfos = taxiTypeRepository.findAll();
            taxiGoWebSocketHandler.broadcastTaxiInfoList(taxiInfos);
            return true;

           }else{
            return false;
           }
        }
    }

 @PutMapping("/closeTaxiFreeState/{driver_id}")
public Boolean closeTaxiFreeState(@PathVariable Integer driver_id) {
    Optional<TaxiInfo> existingTaxiInfo = taxiTypeRepository.findByDriverId(driver_id);
    if (existingTaxiInfo.isPresent()) {
        TaxiInfo taxiInfo = existingTaxiInfo.get();
        taxiInfo.setFree_state(false);
        taxiTypeRepository.save(taxiInfo);
        List<TaxiInfo> taxiInfos = taxiTypeRepository.findAll();
        taxiGoWebSocketHandler.broadcastTaxiInfoList(taxiInfos);
        return true;
    } else {
        return false;
    }
}


@PutMapping("/openTaxiFreeState/{driver_id}")
public Boolean openTaxiFreeState(@PathVariable Integer driver_id) {
    Optional<TaxiInfo> existingTaxiInfo = taxiTypeRepository.findByDriverId(driver_id);
    if (existingTaxiInfo.isPresent()) {
        TaxiInfo taxiInfo = existingTaxiInfo.get();
        taxiInfo.setFree_state(true);
        taxiTypeRepository.save(taxiInfo);
        List<TaxiInfo> taxiInfos = taxiTypeRepository.findAll();
        taxiGoWebSocketHandler.broadcastTaxiInfoList(taxiInfos);
        return true;
    } else {
        return false;
    }
}
}
