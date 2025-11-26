package com.example.pentagonUniv.domain.certificate.service;

import com.example.pentagonUniv._global.handler.exception.CustomRestfullException;
import com.example.pentagonUniv._global.utils.Define;
import com.example.pentagonUniv._global.utils.pdf.PdfUtil;
import com.example.pentagonUniv._global.utils.pdf.dto.AcademicRecordCertificateData;
import com.example.pentagonUniv._global.utils.pdf.templates.AcademicRecordCertificateTemplate;
import com.example.pentagonUniv.domain.certificate.CertificateService;
import com.example.pentagonUniv.domain.certificate.dto.CertificateDto;
import com.example.pentagonUniv.domain.student.dto.StudentInfoDto;
import com.example.pentagonUniv.domain.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AcademicRecordService {
	private final StudentRepository studentRepository;
	private final CertificateService certificateService;

	// 학생 성적 목록 조회
	@Transactional(readOnly = true)
	public List<AcademicRecordCertificateData.AcademicRecordDto> getStudentAcademicRecords(Long studentId) {
		log.info("=== 학생 성적 조회 시작 - 학생ID: {}", studentId);
		List<AcademicRecordCertificateData.AcademicRecordDto> records = studentRepository.selectStudentAcademicRecords(studentId);
		log.info("=== 조회된 성적 수: {}", records.size());
		return records;
	}

	// GPA 계산
	public Double calculateGPA(List<AcademicRecordCertificateData.AcademicRecordDto> records) {
		if (records == null || records.isEmpty()) return 0.0;
		Map<String, Double> gradeMap = Map.of(
				"A", 4.5,
				"B", 4.0,
				"C", 3.5,
				"D", 3.0,
				"F", 0.0
		);
		double totalGradePoints = 0;
		int totalCredits = 0;

		for (AcademicRecordCertificateData.AcademicRecordDto record : records) {
			Double gradeValue = gradeMap.getOrDefault(record.getGrade(), 0.0);
			int credits = (record.getCredits() != null) ? record.getCredits() : 0;
			totalGradePoints += gradeValue * credits;
			totalCredits += credits;
		}
		if (totalCredits == 0) return 0.0;
		return Math.round((totalGradePoints / totalCredits) * 100.0) / 100.0;
	}

	// 총 이수학점 계산
	public Integer calculateTotalCredits(List<AcademicRecordCertificateData.AcademicRecordDto> records) {
		if (records == null || records.isEmpty()) return 0;
		return records.stream().mapToInt(r -> (r.getCredits() != null) ? r.getCredits() : 0)
				.sum();
	}

	// 성적증명서 PDF 생성
	@Transactional(readOnly = true)
	public byte[] generateAcademicRecords(Long certificateId) {
		try {
			CertificateDto certificate = certificateService.getCertificateById(certificateId);
			StudentInfoDto student = studentRepository.selectStudentInfoById(certificate.getStudentId().intValue());
			if (student == null) throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.NOT_FOUND);

			List<AcademicRecordCertificateData.AcademicRecordDto> records = getStudentAcademicRecords(certificate.getStudentId());
			Double gpa = calculateGPA(records);
			Integer totalCredits = calculateTotalCredits(records);

			log.info("=== 성적증명서 생성 - 학생ID: {}, GPA: {}, 총학점:{}", certificate.getStudentId(), gpa, totalCredits);

			AcademicRecordCertificateData pdfData = AcademicRecordCertificateData.builder()
					.studentName(student.getName())
					.studentId(String.valueOf(student.getUserNumber()))
					.birthDate(student.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
					.gender(student.getGender())
					.collegeName(student.getCollegeName())
					.deptName(student.getDeptName())
					.grade(student.getGrade())
					.semester(student.getSemester())
					.entranceDate(student.getEntranceDate())
					.issuedDate(certificate.getIssuedDate())
					.records(records)
					.totalGPA(gpa)
					.totalCredits(totalCredits)
					.build();
			return PdfUtil.generatePdf(new AcademicRecordCertificateTemplate(), pdfData);
		} catch (Exception e) {
			log.error("성적증명서 PDF 생성 실패", e);
			throw new CustomRestfullException("성적증명서 PDF 생성 중 오류가 발생했어요: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
