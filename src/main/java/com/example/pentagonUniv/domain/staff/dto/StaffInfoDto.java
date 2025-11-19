package com.example.pentagonUniv.domain.staff.dto;


import com.example.pentagonUniv.domain.user.dto.UserInfoDto;
import lombok.Data;

import java.util.Date;

@Data
public class StaffInfoDto extends UserInfoDto {
    private Date hireDate;
}
