package com.example.pentagonUniv.domain.student.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.pentagonUniv.domain.user.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {
    private Long id;
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
    private Long deptId;
    private Integer grade;
    private Integer semester;
    private LocalDate entranceDate;
    private UserType userType = UserType.STUDENT;
}
