package com.example.pentagonUniv.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FindIdFormDto {

    @NotBlank
    private String name;
    @Email
    private String email;
    private String userRole;

}
