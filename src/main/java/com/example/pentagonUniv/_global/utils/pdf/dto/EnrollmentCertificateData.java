package com.example.pentagonUniv._global.utils.pdf.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * 재학증명서 PDF 생성용 데이터 DTO
 *
 * @author yeram
 */
@Data
@Builder
public class EnrollmentCertificateData {
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
	private String purpose;            // 용도 (선택사항)
}
