package com.example.pentagonUniv.domain.professor.dto;

import com.example.pentagonUniv.domain.user.dto.UserInfoDto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ProfessorInfoDto extends UserInfoDto {
	private Long deptId;
	private LocalDate hireDate;
}
