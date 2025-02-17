package com.example.TaxiGo.entities;


import jakarta.persistence.*;
import lombok.*;

@Table(name= "confirm_codes")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmCode {

    public ConfirmCode(String phoneNumber,String code) {
        this.phoneNumber = phoneNumber;
        this.code = code;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phoneNumber")
    private String phoneNumber;
    
    @Column(length = 6, name = "code")
    private String code;

}
