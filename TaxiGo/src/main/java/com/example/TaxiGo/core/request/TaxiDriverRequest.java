package com.example.TaxiGo.core.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaxiDriverRequest {
    
    private String image;


    private String name;

    
    private String surname;

    
    private String taxi_plate;

    
    private String type;

    
    private Double km_price;
}
