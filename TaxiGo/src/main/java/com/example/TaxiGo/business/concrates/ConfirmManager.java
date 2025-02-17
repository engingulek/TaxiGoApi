package com.example.TaxiGo.business.concrates;


import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.TaxiGo.business.abstracts.ConfirmCodeService;
import com.example.TaxiGo.core.mappers.ModelMapperService;
import com.example.TaxiGo.core.request.CheckCodeRequest;
import com.example.TaxiGo.core.request.ConfirmCodeRequest;
import com.example.TaxiGo.core.response.ConfirmCodeResponse;
import com.example.TaxiGo.dataAccess.ConfirmRepository;
import com.example.TaxiGo.entities.ConfirmCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmManager implements ConfirmCodeService {


    @Autowired
    private ConfirmRepository confirmRepository;

    @Autowired
    private ModelMapperService modelMapperService;

    

  @Override

  public ResponseEntity<Boolean> createConfirmCode(ConfirmCodeRequest confirmCodeRequest) {
      Random random = new Random();
      String code = String.format("%05d", random.nextInt(100000));
      
      ConfirmCode co = new ConfirmCode(confirmCodeRequest.getPhoneNumber(), code);
      
      try {
          ConfirmCode created = confirmRepository.save(co);
          
          if (created == null || created.getId() == null) {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                   .body(false);
          }
          
          return ResponseEntity.status(HttpStatus.CREATED)
                               .body(true);
      } catch (Exception e) {

          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                               .body(false);
      }
  }
  


    @Override
    public boolean checkCode(CheckCodeRequest checkCodeRequest) {
      ConfirmCode confirmCode =   confirmRepository.findByPhoneNumber(checkCodeRequest.getPhoneNumber());
      ConfirmCodeResponse confirmCodeResponse = modelMapperService.forResponse()
      .map(confirmCode, ConfirmCodeResponse.class);
      return  confirmCodeResponse.getCode().equals(checkCodeRequest.getCode());
   
    }

    @Override
    public void deleteConfirmCode(String phoneNumber) {
      ConfirmCode confirmCode =  confirmRepository.findByPhoneNumber(phoneNumber);
      Integer id = Math.toIntExact(confirmCode.getId());
      confirmRepository.deleteById(id);
      
    }
    
}
