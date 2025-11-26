package com.example.pentagonUniv.domain.certificate;

import com.example.pentagonUniv._global.handler.exception.CustomRestfullException;
import com.example.pentagonUniv._global.utils.Define;
import com.example.pentagonUniv.domain.certificate.dto.CertificateDto;
import com.example.pentagonUniv.domain.student.dto.StudentInfoDto;
import com.example.pentagonUniv.domain.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * 증명서 서비스
 * @author yeram
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CertificateService {
	private final CertificateRepository certificateRepository;
	private final StudentRepository studentRepository;

	// 증명서 저장 (발급)
	@Transactional
	public CertificateDto issueCertificate(Long studentId, String certificateType) {
		// What if student is null?
		StudentInfoDto student = studentRepository.selectStudentInfoById(studentId.intValue());
		if (student == null) throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.NOT_FOUND);
		log.info("=== 증명서 발급 시작 - 학생ID: {}, 증명서 종류:{}", studentId, certificateType);
		Certificate certificate = Certificate.builder()
				.studentId(studentId)
				.certificateType(certificateType)
				.issuedDate(LocalDate.now())
				.status("발급완료").build();
		int result = certificateRepository.insert(certificate);
		if (result == 0) throw new CustomRestfullException("증명서 발급 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		log.info("=== 증명서 발급 완료 - 증명서ID: {}", certificate.getId());
		return certificateRepository.findByStudentIdAndType(studentId, certificateType);
	}

	// 증명서 조회(ID)
	@Transactional(readOnly = true)
	public CertificateDto getCertificateById(Long certificateId) {
		log.debug("=== 증명서 조회: {}", certificateId);
		CertificateDto certificate = certificateRepository.findById(certificateId);
		if (certificate == null) throw new CustomRestfullException("증명서를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
		return certificate;
	}

	// 학생별 증명서 목록 조회
	@Transactional(readOnly = true)
	public List<CertificateDto> getCertificatesByStudentId(Long studentId) {
		List<CertificateDto> certs = certificateRepository.findByStudentId(studentId);
		log.info("=== 조회된 증명서: {}", certs);
		log.info("=== 조회된 증명서 개수: {}", certs.size());

		log.info("=== 증명서 목록 조회 시작 - studentId: {}", studentId);

		StudentInfoDto student = studentRepository.selectStudentInfoById(studentId.intValue());
		log.info("=== 학생 조회 결과: {}", student);

		if (student == null) throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.NOT_FOUND);
		return  certificateRepository.findByStudentId(studentId);
	}

	// 학생별 증명서 타입별 목록 조회
	@Transactional(readOnly = true)
	public CertificateDto getCertificateByStudentIdAndType(Long studentId, String certificateType) {
		StudentInfoDto student = studentRepository.selectStudentInfoById(studentId.intValue());
		if (student == null) throw new CustomRestfullException(Define.NOT_FOUND_ID, HttpStatus.NOT_FOUND);
		CertificateDto certificate = certificateRepository.findByStudentIdAndType(studentId, certificateType);
		if (certificate == null) throw new CustomRestfullException("증명서를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
		return certificate;
	}
}
