package com.example.pentagonUniv.domain.certificate;

import com.example.pentagonUniv.domain.certificate.dto.CertificateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
	CertificateDto findById(@Param("id") Long id);

	// 학생별 증명서 목록 조회
	List<CertificateDto> findByStudentId(@Param("studentId") Long studentId);

	// 학생별 증명서 타입별 조회
	CertificateDto findByStudentIdAndType(@Param("studentId") Long studentId, @Param("certificateType") String certificateType);
}
