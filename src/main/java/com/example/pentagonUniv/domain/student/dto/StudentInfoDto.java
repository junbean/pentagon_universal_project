package com.example.pentagonUniv.domain.student.dto;

import com.example.pentagonUniv.domain.user.dto.UserInfoDto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class StudentInfoDto extends UserInfoDto {
	private Integer deptId;
	private Integer grade;
	private Integer semester;
	private LocalDate entranceDate;
	private LocalDate graduationDate;
	private String deptName;
	private String collegeName;
}
