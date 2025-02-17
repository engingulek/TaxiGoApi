package com.example.TaxiGo.dataAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TaxiGo.entities.ConfirmCode;

public interface ConfirmRepository extends JpaRepository<ConfirmCode,Integer> {
    ConfirmCode findByPhoneNumber(String phoneNumber);
}
