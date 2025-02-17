package com.example.TaxiGo.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TaxiGo.entities.TaxiDriver;

public interface TaxiDriverRepository extends JpaRepository<TaxiDriver,Integer> {
    
}
