package com.example.pentagonUniv._global.utils.pdf.templates;

import com.example.pentagonUniv._global.utils.pdf.CertificateTemplate;
import com.example.pentagonUniv._global.utils.pdf.dto.EnrollmentCertificateData;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.time.format.DateTimeFormatter;

/**
 * 재학증명서 PDF 템플릿
 *
 * @author yeram
 */
public class EnrollmentCertificateTemplate implements CertificateTemplate {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

	@Override
	public void generateContent(Document document, PdfDocument pdfDoc, Object data) throws Exception {
		if (!(data instanceof EnrollmentCertificateData)) {
			throw new IllegalArgumentException("EnrollmentCertificateData 타입이 필요합니다.");
		}

		EnrollmentCertificateData certData = (EnrollmentCertificateData) data;

		// 한글 폰트 설정 - Windows 맑은 고딕 폰트 사용
		PdfFont font;
		PdfFont boldFont;

		try {
			// Windows 맑은 고딕 폰트 경로
			String fontPath = "c:/Windows/Fonts/malgun.ttf";
			String boldFontPath = "c:/Windows/Fonts/malgunbd.ttf";

			font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);
			boldFont = PdfFontFactory.createFont(boldFontPath, PdfEncodings.IDENTITY_H);
		} catch (Exception e) {
			// 폰트를 찾을 수 없을 경우 기본 폰트 사용 (한글 미지원)
			throw new RuntimeException("한글 폰트를 찾을 수 없습니다. Windows 맑은 고딕 폰트가 필요합니다.", e);
		}

		// 제목
		Paragraph title = new Paragraph("재 학 증 명 서")
				.setFont(boldFont)
				.setFontSize(24)
				.setTextAlignment(TextAlignment.CENTER)
				.setMarginTop(40)
				.setMarginBottom(30);
		document.add(title);

		// 학생 정보 테이블
		Table infoTable = new Table(UnitValue.createPercentArray(new float[]{30, 70}))
				.useAllAvailableWidth()
				.setMarginBottom(30);

		addInfoRow(infoTable, "성명", certData.getStudentName(), font);
		addInfoRow(infoTable, "학번", certData.getStudentId(), font);
		addInfoRow(infoTable, "생년월일", certData.getBirthDate().format(DATE_FORMATTER), font);
		addInfoRow(infoTable, "성별", certData.getGender(), font);
		addInfoRow(infoTable, "단과대학", certData.getCollegeName(), font);
		addInfoRow(infoTable, "학과", certData.getDeptName(), font);
		addInfoRow(infoTable, "학년", certData.getGrade() + "학년", font);
		addInfoRow(infoTable, "학기", certData.getSemester() + "학기", font);
		addInfoRow(infoTable, "입학일", certData.getEntranceDate().format(DATE_FORMATTER), font);

		document.add(infoTable);

		// 증명 내용
		Paragraph content = new Paragraph("위 사람은 본교에 재학 중임을 증명합니다.")
				.setFont(font)
				.setFontSize(14)
				.setTextAlignment(TextAlignment.CENTER)
				.setMarginTop(30)
				.setMarginBottom(50);
		document.add(content);

		// 발급일
		Paragraph issueDate = new Paragraph(certData.getIssuedDate().format(DATE_FORMATTER))
				.setFont(font)
				.setFontSize(12)
				.setTextAlignment(TextAlignment.CENTER)
				.setMarginBottom(40);
		document.add(issueDate);

		// 학교명
		Paragraph schoolName = new Paragraph("펜타곤 대학교 총장")
				.setFont(boldFont)
				.setFontSize(16)
				.setTextAlignment(TextAlignment.CENTER)
				.setMarginBottom(10);
		document.add(schoolName);

		// 직인 표시 (텍스트로 대체)
		Paragraph seal = new Paragraph("(직인생략)")
				.setFont(font)
				.setFontSize(10)
				.setTextAlignment(TextAlignment.CENTER)
				.setFontColor(ColorConstants.GRAY);
		document.add(seal);
	}

	/**
	 * 정보 테이블에 행 추가
	 */
	private void addInfoRow(Table table, String label, String value, PdfFont font) {
		// null 값 처리 - null이면 빈 문자열로 대체
		String safeValue = (value != null) ? value : "";

		Cell labelCell = new Cell()
				.add(new Paragraph(label).setFont(font).setFontSize(11))
				.setBackgroundColor(ColorConstants.LIGHT_GRAY)
				.setTextAlignment(TextAlignment.CENTER)
				.setPadding(8)
				.setBorder(new SolidBorder(ColorConstants.BLACK, 1));

		Cell valueCell = new Cell()
				.add(new Paragraph(safeValue).setFont(font).setFontSize(11))
				.setTextAlignment(TextAlignment.LEFT)
				.setPadding(8)
				.setBorder(new SolidBorder(ColorConstants.BLACK, 1));

		table.addCell(labelCell);
		table.addCell(valueCell);
	}
}

