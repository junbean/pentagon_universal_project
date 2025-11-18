package com.example.pentagonUniv.domain.repository.model;

import lombok.Data;

import java.sql.Date;

/**
  * @FileName : Staff.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 교직원
  */
@Data
public class Staff {
	
	private Integer id;
	private String name;
	private Date birthDate;
	private String gender;
	private String address;
	private String tel;
	private String email;
	private Date hireDate;

}
