package com.example.pentagonUniv.domain.professor.dto;

import com.example.pentagonUniv.domain.user.dto.UserInfoDto;
import lombok.Data;

import java.sql.Date;

@Data
public class ProfessorInfoDto extends UserInfoDto {
	private Integer deptId;
	private Date hireDate;
}
