package com.example.pentagonUniv.domain.user.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String userNumber;
    private String name;
    private LocalDate birthDate;
    private String birthYear;
    private String birthMonth;
    private String birthDay;
    private String gender;
    private String address;
    private String tel;
    private String email;
    private String password;
    
}
