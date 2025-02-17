package com.example.TaxiGo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaxiGo.business.abstracts.ConfirmCodeService;
import com.example.TaxiGo.core.request.CheckCodeRequest;
import com.example.TaxiGo.core.request.ConfirmCodeRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("api/confirmCode/")
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmCodeController {
    @Autowired
    private ConfirmCodeService codeService;

    @PostMapping("createConfirmCode") 
    public  ResponseEntity<?> createConfirmCode(@RequestBody ConfirmCodeRequest confirmCodeRequest) {
       ;
        return  codeService.createConfirmCode(confirmCodeRequest);
    }


    @PostMapping("checkCode")
    public boolean getConfirmCode(   
        @RequestBody CheckCodeRequest checkCode) {
        return codeService.checkCode(checkCode);
    }

    @DeleteMapping("deleteConfirmCode")
    public void deleteConfirmCode(@RequestParam String  phoneNumber) {

        codeService.deleteConfirmCode(phoneNumber);
    }


    



}
