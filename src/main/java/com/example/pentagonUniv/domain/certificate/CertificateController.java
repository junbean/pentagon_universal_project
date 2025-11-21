package com.example.pentagonUniv.domain.certificate;

import com.example.pentagonUniv._global.utils.Define;
import com.example.pentagonUniv.domain.certificate.dto.CertificateDto;
import com.example.pentagonUniv.domain.certificate.service.EnrollmentCertificateService;
import com.example.pentagonUniv.domain.user.dto.PrincipalDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 증명서 Controller
 *
 * @author yeram
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/certificate")
public class CertificateController {
	private final CertificateService certificateService;
	private final EnrollmentCertificateService enrollmentCertificateService;
	private final HttpSession session;

	// 증명서 발급 페이지
	@GetMapping("/issue")
	public String issuePage(Model model) {
		PrincipalDto principal = getPrincipalFromSession();
		model.addAttribute("principalId", principal.getId());
		return "certificate/certificateIssue";
	}

	// 증명서 목록 페이지
	@GetMapping("/list")
	public String listPage(Model model) {
		PrincipalDto principal = getPrincipalFromSession();
		List<CertificateDto> certificates = certificateService.getCertificatesByStudentId(principal.getId());
		model.addAttribute("certificates", certificates);
		return "certificate/certificateList";
	}

	// 증명서 발급 API
	@PostMapping("/api/issue")
	@ResponseBody
	public ResponseEntity<?> issueCertificate(@RequestParam String certificateType) {
		try {
			PrincipalDto principal = getPrincipalFromSession();
			CertificateDto certificate = certificateService.issueCertificate(principal.getId(), certificateType);
			Map<String, Object> res = new HashMap<>();
			res.put("success", true);
			res.put("message", "증명서 발급 완료!");
			res.put("data", certificate);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			log.error("증명서 발급 실패", e);
			Map<String, Object> res = new HashMap<>();
			res.put("success", false);
			res.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}

	// 증명서 조회 API
	@GetMapping("/api/{id}")
	@ResponseBody
	public ResponseEntity<?> getCertificate(@PathVariable Long id) {
		try {
			CertificateDto certificate = certificateService.getCertificateById(id);
			Map<String, Object> res = new HashMap<>();
			res.put("success", true);
			res.put("data", certificate);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			log.error("증명서 조회 실패!", e);
			Map<String, Object> res = new HashMap<>();
			res.put("success", false);
			res.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}
	}

	// 학생별 증명서 목록 API
	@GetMapping("/api/list/{studentId}")
	@ResponseBody
	public ResponseEntity<?> getCertificateList(@PathVariable Long studentId) {
		try {
			List<CertificateDto> certificates = certificateService.getCertificatesByStudentId(studentId);
			Map<String, Object> res = new HashMap<>();
			res.put("success", true);
			res.put("data", certificates);
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			log.error("증명서 목록 조회 실패", e);
			Map<String, Object> res = new HashMap<>();
			res.put("success", false);
			res.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}
	}

	/**
	 * 재학증명서 PDF 다운로드
	 *
	 * @param id 증명서 ID
	 * @return PDF 파일
	 */
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> downloadCertificate(@PathVariable Long id) {
		try {
			// 증명서 조회
			CertificateDto certificate = certificateService.getCertificateById(id);

			// 증명서 타입에 따라 다른 PDF 생성 (현재는 재학증명서만)
			byte[] pdfBytes;
			String fileName;

			if ("재학증명서".equals(certificate.getCertificateType())) {
				pdfBytes = enrollmentCertificateService.generateEnrollmentCertificatePdf(id);
				fileName = "재학증명서_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".pdf";
			} else {
				throw new IllegalArgumentException("지원하지 않는 증명서 타입입니다: " + certificate.getCertificateType());
			}

			// 파일명 인코딩 (한글 파일명 처리)
			String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");

			// HTTP 헤더 설정
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("attachment", encodedFileName);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

			return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

		} catch (Exception e) {
			log.error("PDF 다운로드 실패", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	private PrincipalDto getPrincipalFromSession() {
		return (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
	}
}
