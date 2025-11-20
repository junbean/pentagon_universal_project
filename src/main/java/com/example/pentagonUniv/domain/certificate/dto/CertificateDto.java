package com.example.pentagonUniv.domain.certificate.dto;

import com.example.pentagonUniv.domain.certificate.Certificate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateDto {
	private Long id;
	private Long studentId;
	private String certificateType;
	private LocalDate issuedDate;
	private String status; // 발급완료, 발급취소
	private LocalDateTime createdAt;

	public static CertificateDto fromEntity(Certificate cert) {
		return CertificateDto.builder()
				.id(cert.getId())
				.studentId(cert.getStudentId())
				.certificateType(cert.getCertificateType())
				.issuedDate(cert.getIssuedDate())
				.status(cert.getStatus())
				.createdAt(cert.getCreatedAt())
				.build();
	}
}
