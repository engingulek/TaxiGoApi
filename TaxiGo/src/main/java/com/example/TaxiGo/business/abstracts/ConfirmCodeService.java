package com.example.TaxiGo.business.abstracts;


import org.springframework.http.ResponseEntity;

import com.example.TaxiGo.core.request.CheckCodeRequest;
import com.example.TaxiGo.core.request.ConfirmCodeRequest;

public interface ConfirmCodeService {
    ResponseEntity<?>  createConfirmCode(ConfirmCodeRequest confirmCodeRequest);
    boolean checkCode(CheckCodeRequest checkCodeRequest);
    void deleteConfirmCode(String phoneNumber);
}
