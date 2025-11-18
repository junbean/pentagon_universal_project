package com.example.pentagonUniv.domain.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

/**
 * @FileName : CreateStudentDto.java
 * @Project : CyberUniversity
 * @Date : 2024. 3. 12.
 * @작성자 : 이준혁
 * @변경이력 :
 * @프로그램 설명 : 학생등록 DTO
 */
@Data
public class CreateStudentDto {
    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;
    private Date birthDate;
    private String gender;
    @NotEmpty
    private String address;
    @NotBlank
    private String tel;
    @Min(100)
    @Max(999)
    private Integer deptId;
    private Date entranceDate;
    @Email
    private String email;
    private String originFileName;
    private String uploadFileName;
    private MultipartFile profilImage;


}
