package com.example.pentagonUniv.domain.student.dto;

import com.example.pentagonUniv.domain.stu_state.StudentStatus;
import com.example.pentagonUniv.domain.user.dto.UserInfoDto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentInfoDto extends UserInfoDto {
	private String userNumber;
	private Long deptId;
	private Integer grade;
	private Integer semester;
	private LocalDate entranceDate;
	private LocalDate graduationDate;
	private StudentStatus studentStatus;
	private String deptName;
	private String collegeName;
}
