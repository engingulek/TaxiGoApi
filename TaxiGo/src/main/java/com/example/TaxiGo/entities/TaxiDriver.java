package com.example.TaxiGo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name= "taxi_driver")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TaxiDriver {
    
    public TaxiDriver(
        String image,
    String name,
    String surname,
    String  taxi_plate,
    String type,
    Double km_price ){
        this.image = image;
        this.name = name;
        this.surname = surname;
        this.taxi_plate = taxi_plate;
        this.type = type;
        this.km_price = km_price;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image")
    private String image;

     @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "taxi_plate")
    private String taxi_plate;

    @Column(name = "type")
    private String type;

    @Column(name = "km_price")
    private Double km_price;
}
