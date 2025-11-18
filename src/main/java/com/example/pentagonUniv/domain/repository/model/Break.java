package com.example.pentagonUniv.domain.repository.model;

import com.example.pentagonUniv._global.utils.DateUtil;
import lombok.Data;

import java.sql.Date;

/**
  * @FileName : BreakApp.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 11. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 휴학신청
  */
@Data
public class Break {
	
	private Integer id;
	private Integer studentId;
	private Integer studentGrade;
	private Integer fromYear;
	private Integer fromSemester;
	private Integer toYear;
	private Integer toSemester;
	private String type;
	private Date appDate;
	private String status;
	
	
	public String appDateFormat() {
		return DateUtil.dateFormat(appDate);
	}

}
