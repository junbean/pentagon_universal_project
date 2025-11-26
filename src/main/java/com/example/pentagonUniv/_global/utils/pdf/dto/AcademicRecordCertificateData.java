package com.example.pentagonUniv._global.utils.pdf.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class AcademicRecordCertificateData {
	private String studentName;        // 학생 이름
	private String studentId;          // 학번
	private LocalDate birthDate;       // 생년월일
	private String gender;             // 성별
	private String collegeName;        // 단과대학
	private String deptName;           // 학과명
	private Integer grade;             // 학년
	private Integer semester;          // 학기
	private LocalDate entranceDate;    // 입학일
	private LocalDate issuedDate;      // 발급일
	private List<AcademicRecordDto> records; // 성적 목록
	private Double totalGPA;                 // 평점
	private Integer totalCredits;            // 총 이수학점
	@Data
	@Builder
	public static class AcademicRecordDto {
		private String subjectName; // 과목명
		private String grade;       // 성적
		private Integer credits;    // 이수학점
	}
}
