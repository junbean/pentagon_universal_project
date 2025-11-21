package com.example.pentagonUniv.domain.staff.dto;

import java.time.LocalDate;

import com.example.pentagonUniv.domain.user.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffRequestDto {
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
    private String password = "$2a$10$hEYFZOe.PbFbVRcPZymRKuCGMU2MilAn64ZGnIqPgfwWL7JyGeKKW";
    private LocalDate hireDate;
    private UserType userType = UserType.STAFF;
}
