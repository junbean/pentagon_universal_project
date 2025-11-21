package com.example.pentagonUniv._global.utils.pdf.templates;

import com.example.pentagonUniv._global.utils.pdf.CertificateTemplate;
import com.example.pentagonUniv._global.utils.pdf.dto.AcademicRecordCertificateData;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.time.format.DateTimeFormatter;

/**
 * 성적증명서 PDF 템플릿
 * @author yeram
 */
public class AcademicRecordCertificateTemplate implements CertificateTemplate {
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

	@Override
	public void generateContent(Document document, PdfDocument pdfDoc, Object data) throws Exception {
		if(!(data instanceof AcademicRecordCertificateData)) {
			throw new IllegalArgumentException("성적증명서 데이터 타입이 필요합니다");
		}

		AcademicRecordCertificateData certData = (AcademicRecordCertificateData) data;
		PdfFont font;
		PdfFont boldFont;

		try {
			String fontPath = "c:/Windows/Fonts/malgun.ttf";
			String boldFontPath = "c:/Windows/Fonts/malgunbd.ttf";
			font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);
			boldFont = PdfFontFactory.createFont(boldFontPath, PdfEncodings.IDENTITY_H);
		} catch (Exception e) {
			throw new RuntimeException("한글 폰트를 찾을 수 없습니다. Windows 맑은 고딕 폰트가 필요합니다.", e);
		}

		// 제목
		Paragraph title = new Paragraph("성 적 증 명 서")
				.setFont(boldFont)
				.setFontSize(24)
				.setTextAlignment(TextAlignment.CENTER)
				.setMarginTop(40)
				.setMarginBottom(30);
		document.add(title);

		// 학생 기본 정보 테이블
		Table infoTable = new Table(UnitValue.createPercentArray(new float[]{30, 70}))
				.useAllAvailableWidth()
				.setMarginBottom(30);

		addInfoRow(infoTable, "성명", certData.getStudentName(), font);
		addInfoRow(infoTable, "학번", certData.getStudentId(), font);
		addInfoRow(infoTable, "생년월일", certData.getBirthDate().format(DATE_FORMATTER), font);
		addInfoRow(infoTable, "성별", certData.getGender(), font);
		addInfoRow(infoTable, "단과대학", certData.getCollegeName(), font);
		addInfoRow(infoTable, "학과", certData.getDeptName(), font);
		addInfoRow(infoTable, "학년/학기", certData.getGrade() + "학년 " + certData.getSemester() + "학기", font);
		addInfoRow(infoTable, "입학일", certData.getEntranceDate().format(DATE_FORMATTER), font);

		document.add(infoTable);

		// 성적 목록 테이블 (과목별)
		if (certData.getRecords() != null && !certData.getRecords().isEmpty()) {
			Paragraph recordTitle = new Paragraph("[ 성적 현황 ]")
					.setFont(boldFont)
					.setFontSize(12)
					.setMarginTop(20)
					.setMarginBottom(10);
			document.add(recordTitle);

			Table recordTable = new Table(UnitValue.createPercentArray(new float[]{50, 20, 30}))
					.useAllAvailableWidth()
					.setMarginBottom(20);

			// 헤더 행
			addHeaderCell(recordTable, "과목명", boldFont);
			addHeaderCell(recordTable, "성적", boldFont);
			addHeaderCell(recordTable, "이수학점", boldFont);

			// 데이터 행
			for (AcademicRecordCertificateData.AcademicRecordDto record : certData.getRecords()) {
				addDataCell(recordTable, record.getSubjectName(), font);
				addDataCell(recordTable, record.getGrade(), font);
				addDataCell(recordTable, record.getCredits() + "학점", font);
			}

			document.add(recordTable);

			// 통계 정보
			Table statsTable = new Table(UnitValue.createPercentArray(new float[]{50, 50}))
					.useAllAvailableWidth()
					.setMarginBottom(30);

			addStatsRow(statsTable, "총 이수학점", certData.getTotalCredits() + "학점", font);
			addStatsRow(statsTable, "평점(GPA)", String.format("%.2f / 4.50", certData.getTotalGPA()), font);

			document.add(statsTable);
		}

		// 증명 내용
		Paragraph content = new Paragraph("위 사람의 성적증명서임을 증명합니다.")
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

		// 학교명 및 직인
		Paragraph schoolName = new Paragraph("펜타곤 대학교 총장")
				.setFont(boldFont)
				.setFontSize(16)
				.setTextAlignment(TextAlignment.CENTER)
				.setMarginBottom(10);
		document.add(schoolName);

		Paragraph seal = new Paragraph("(직인생략)")
				.setFont(font)
				.setFontSize(10)
				.setTextAlignment(TextAlignment.CENTER)
				.setFontColor(ColorConstants.GRAY);
		document.add(seal);
	}

	// 기본 정보 행 추가
	private void addInfoRow(Table table, String label, String value, PdfFont font) {
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

	// 성적 목록 헤더 셀
	private void addHeaderCell(Table table, String text, PdfFont font) {
		Cell cell = new Cell()
				.add(new Paragraph(text).setFont(font).setFontSize(11))
				.setBackgroundColor(ColorConstants.LIGHT_GRAY)
				.setTextAlignment(TextAlignment.CENTER)
				.setPadding(8)
				.setBorder(new SolidBorder(ColorConstants.BLACK, 1));
		table.addCell(cell);
	}

	// 성적 목록 데이터 셀
	private void addDataCell(Table table, String text, PdfFont font) {
		String safeText = (text != null) ? text : "";
		Cell cell = new Cell()
				.add(new Paragraph(safeText).setFont(font).setFontSize(11))
				.setTextAlignment(TextAlignment.CENTER)
				.setPadding(8)
				.setBorder(new SolidBorder(ColorConstants.BLACK, 1));
		table.addCell(cell);
	}

	// 통계 정보 행
	private void addStatsRow(Table table, String label, String value, PdfFont font) {
		Cell labelCell = new Cell()
				.add(new Paragraph(label).setFont(font).setFontSize(11))
				.setBackgroundColor(ColorConstants.LIGHT_GRAY)
				.setTextAlignment(TextAlignment.CENTER)
				.setPadding(8)
				.setBorder(new SolidBorder(ColorConstants.BLACK, 1));

		Cell valueCell = new Cell()
				.add(new Paragraph(value).setFont(font).setFontSize(11))
				.setTextAlignment(TextAlignment.CENTER)
				.setPadding(8)
				.setBorder(new SolidBorder(ColorConstants.BLACK, 1));

		table.addCell(labelCell);
		table.addCell(valueCell);
	}
}