package com.example.pentagonUniv.domain.certificate;

import com.example.pentagonUniv.domain.certificate.dto.CertificateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 증명서 REPO
 * @author yeram
 */
@Mapper
public interface CertificateRepository {
	// 증명서 저장
	int insert(Certificate cert);

	// 증명서 조회
	CertificateDto findById(Long id);

	// 학생별 증명서 목록 조회
	List<CertificateDto> findByStudentId(Long studentId);

	// 학생별 증명서 타입별 조회
	CertificateDto findByStudentIdAndType(Long studentId, String certificateType);
}
