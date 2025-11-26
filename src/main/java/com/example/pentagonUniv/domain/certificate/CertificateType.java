package com.example.pentagonUniv.domain.certificate;

import lombok.Getter;

/**
 * 증명서 종류 ENUM
 * @author yeram
 */
@Getter
public enum CertificateType {
	ENROLLMENT_CERTIFICATE("재학증명서"),
	ACADEMIC_RECORD_CERTIFICATE("성적증명서"),
	TUITION_FEE_NOTICE_CERTIFICATE("등록금고지서"),
	GRADUATION_CERTIFICATE("졸업증명서");

	private final String displayName;

	CertificateType(String displayName) {
		this.displayName = displayName;
	}

}
