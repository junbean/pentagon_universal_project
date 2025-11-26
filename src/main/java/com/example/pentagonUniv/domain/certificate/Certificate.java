package com.example.pentagonUniv.domain.certificate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 증명서 엔티티
 * @author yeram
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certificate {
	private Long id;
	private Long studentId;
	private String certificateType;
	private LocalDate issuedDate;
	private String status; // 발급완료, 발급취소
	private LocalDateTime createdAt;
}
