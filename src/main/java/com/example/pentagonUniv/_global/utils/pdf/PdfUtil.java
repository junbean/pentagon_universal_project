package com.example.pentagonUniv._global.utils.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.ByteArrayOutputStream;

/**
 * PDF 생성 유틸리티 클래스
 *
 * @author yeram
 */
public class PdfUtil {

	/**
	 * 증명서 템플릿을 사용하여 PDF를 생성하고 바이트 배열로 반환
	 *
	 * @param template 증명서 템플릿 (Strategy 패턴)
	 * @param data 증명서 데이터
	 * @return PDF 파일의 바이트 배열
	 * @throws Exception PDF 생성 중 오류 발생 시
	 */
	public static byte[] generatePdf(CertificateTemplate template, Object data) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		// PDF 문서 생성
		PdfWriter writer = new PdfWriter(byteArrayOutputStream);
		PdfDocument pdfDoc = new PdfDocument(writer);
		Document document = new Document(pdfDoc);

		// 템플릿을 사용하여 내용 생성
		template.generateContent(document, pdfDoc, data);

		// 문서 닫기
		document.close();

		return byteArrayOutputStream.toByteArray();
	}
}

