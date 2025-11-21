package com.example.pentagonUniv.domain.certificate.service;

import com.example.pentagonUniv._global.handler.exception.CustomRestfullException;
import com.example.pentagonUniv._global.utils.Define;
import com.example.pentagonUniv._global.utils.pdf.PdfUtil;
import com.example.pentagonUniv._global.utils.pdf.dto.EnrollmentCertificateData;
import com.example.pentagonUniv._global.utils.pdf.templates.EnrollmentCertificateTemplate;
import com.example.pentagonUniv.domain.certificate.CertificateService;
import com.example.pentagonUniv.domain.certificate.dto.CertificateDto;
import com.example.pentagonUniv.domain.student.dto.StudentInfoDto;
import com.example.pentagonUniv.domain.student.dto.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
@Slf4j
@Service
@RequiredArgsConstructor
public class EnrollmentCertificateService {
	private final StudentRepository studentRepository;
	private final CertificateService certificateService;

	/**
	 * 재학증명서 PDF 생성
	 * @param certificateId 증명서 ID
	 * @return PDF 바이트 배열
	 */
	@Transactional(readOnly = true)
	public byte[] generateEnrollmentCertificatePdf(Long certificateId) {
		try {
			// 증명서 조회
			CertificateDto certificate = certificateService.getCertificateById(certificateId);
			// 학생 정보 조회
			StudentInfoDto student = studentRepository.selectStudentInfoById(certificate.getStudentId().intValue());
			if (student == null) throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.NOT_FOUND);

			// PDF 데이터 생성
			EnrollmentCertificateData pdfData = EnrollmentCertificateData.builder()
					.studentName(student.getName())
					.studentId(String.valueOf(student.getId()))
					.birthDate(student.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
					.gender(student.getGender())
					.collegeName(student.getCollegeName())
					.deptName(student.getDeptName())
					.grade(student.getGrade())
					.semester(student.getSemester())
					.entranceDate(student.getEntranceDate().toLocalDate())
					.issuedDate(certificate.getIssuedDate())
					.build();

			// PDF 생성
			return PdfUtil.generatePdf(new EnrollmentCertificateTemplate(), pdfData);

		} catch (Exception e) {
			log.error("PDF 생성 실패", e);
			throw new CustomRestfullException("PDF 생성 중 오류가 발생했습니다: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
