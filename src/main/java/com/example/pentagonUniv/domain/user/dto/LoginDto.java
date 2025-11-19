package com.example.pentagonUniv.domain.user.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class LoginDto {

    @Min(100000)
    @Max(2147483646)
    private Long userNumber;
    @Size(min = 6, max = 20, message = "패스워드는 6~20자 사이여야합니다.")
    private String password;
    private String rememberId;
}
