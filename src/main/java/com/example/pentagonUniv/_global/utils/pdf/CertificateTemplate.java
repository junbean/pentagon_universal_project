package com.example.pentagonUniv._global.utils.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;

/**
 * PDF 증명서 템플릿 인터페이스
 * Strategy 패턴을 사용하여 각 증명서 타입별 PDF 생성 로직을 분리
 *
 * @author yeram
 */
public interface CertificateTemplate {
	/**
	 * PDF 문서에 증명서 내용을 작성
	 *
	 * @param document iText Document 객체
	 * @param pdfDoc iText PdfDocument 객체
	 * @param data 증명서 생성에 필요한 데이터
	 */
	void generateContent(Document document, PdfDocument pdfDoc, Object data) throws Exception;
}
