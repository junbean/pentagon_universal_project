package com.example.pentagonUniv.domain.repository.model;

import lombok.Data;

import java.sql.Date;

/**
  * @FileName : StuStat.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 학적상태
  */

@Data
public class StuStat {
	private Integer id;
	private Integer studentId;
	private String status;
	private Date fromDate;
	private Date toDate;
}
