package com.example.pentagonUniv.domain.user.dto;

import com.example.pentagonUniv.domain.user.UserType;
import lombok.Data;

@Data
public class PrincipalDto {
    private Long id;
    private Long userNumber;
    private String password;
    private UserType userType;
    private String name;
}
