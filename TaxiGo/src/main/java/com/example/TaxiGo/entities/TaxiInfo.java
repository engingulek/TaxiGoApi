package com.example.TaxiGo.entities;


import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter 
@Setter 
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "taxiInfo")
public class TaxiInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "taxiTypeName")
    private String taxiTypeName;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "seatCount")
    private Integer seatCount;

    @Column(name = "kmPrice")
    private Double kmPrice;

    @Column(name = "free_state")
    private Boolean free_state;

    @Column(name = "driver_id")
    private Integer driverId;






}
