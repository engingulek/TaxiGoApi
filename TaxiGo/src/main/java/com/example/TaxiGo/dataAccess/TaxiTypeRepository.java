package com.example.TaxiGo.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TaxiGo.entities.TaxiInfo;

public interface TaxiTypeRepository extends JpaRepository<TaxiInfo,Integer> {
    Optional<TaxiInfo> findByDriverId(Integer driver_id );
}
