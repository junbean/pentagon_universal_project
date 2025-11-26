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
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd.");

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
				.setFontSize(20)
				.setTextAlignment(TextAlignment.CENTER)
				.setMarginTop(10)
				.setMarginBottom(10);
		document.add(title);

		// ===== 1. 학생 기본 정보 테이블 (2행 4열) =====
		Table infoTable = new Table(UnitValue.createPercentArray(new float[]{15, 20, 15, 20, 15, 20, 15, 20}))
				.useAllAvailableWidth()
				.setMarginBottom(5); // 여백 줄임

// 첫 번째 행: 성명, 학번, 생년월일, 성별
		addCompactInfoCell(infoTable, "성명", certData.getStudentName(), font, boldFont);
		addCompactInfoCell(infoTable, "학번", certData.getStudentId(), font, boldFont);
		addCompactInfoCell(infoTable, "생년월일", certData.getBirthDate().format(DATE_FORMATTER), font, boldFont);
		addCompactInfoCell(infoTable, "성별", certData.getGender(), font, boldFont);

// 두 번째 행: 단과대학, 학과, 학년/학기, 입학일
		addCompactInfoCell(infoTable, "단과대학", certData.getCollegeName(), font, boldFont);
		addCompactInfoCell(infoTable, "학과", certData.getDeptName(), font, boldFont);
		addCompactInfoCell(infoTable, "학년/학기", certData.getGrade() + "학년 " + certData.getSemester() + "학기", font, boldFont);
		addCompactInfoCell(infoTable, "입학일", certData.getEntranceDate().format(DATE_FORMATTER), font, boldFont);

		document.add(infoTable);

// ===== 2. 성적 목록 2단 구조 =====
		if (certData.getRecords() != null && !certData.getRecords().isEmpty()) {
			Paragraph recordTitle = new Paragraph("[ 성적 현황 ]")
					.setFont(boldFont)
					.setFontSize(9)
					.setMarginTop(5)
					.setMarginBottom(5);
			document.add(recordTitle);

			// 과목 리스트를 절반으로 분할
			int totalRecords = certData.getRecords().size();
			int thirdSize = (totalRecords + 2) / 3;

			// 외부 테이블 (2단을 담을 컨테이너)
			Table containerTable = new Table(UnitValue.createPercentArray(new float[]{3,1,1,3,1,1,3,1,1}))
					.useAllAvailableWidth()
					.setBorder(new SolidBorder(ColorConstants.BLACK, 1))
					.setMarginBottom(10);
			for (int i = 0; i < 3; i++) {
				containerTable.addCell(new Cell()
						.add(new Paragraph("과목명").setFont(boldFont).setFontSize(8))
						.setBackgroundColor(ColorConstants.LIGHT_GRAY)
						.setTextAlignment(TextAlignment.CENTER)
						.setBorder(new SolidBorder(ColorConstants.BLACK, 1))
						.setPadding(2));

				containerTable.addCell(new Cell()
						.add(new Paragraph("성적").setFont(boldFont).setFontSize(8))
						.setBackgroundColor(ColorConstants.LIGHT_GRAY)
						.setTextAlignment(TextAlignment.CENTER)
						.setBorder(new SolidBorder(ColorConstants.BLACK, 1))
						.setPadding(2));

				containerTable.addCell(new Cell()
						.add(new Paragraph("학점").setFont(boldFont).setFontSize(8))
						.setBackgroundColor(ColorConstants.LIGHT_GRAY)
						.setTextAlignment(TextAlignment.CENTER)
						.setBorder(new SolidBorder(ColorConstants.BLACK, 1))
						.setPadding(2));
			}

			int end1 = Math.min(thirdSize, totalRecords);
			int end2 = Math.min(thirdSize * 2, totalRecords);
			for (int i = 0; i < thirdSize; i++) {
				// 첫 번째 단
				if (i < certData.getRecords().size()) {
					addRecordCells(containerTable, certData.getRecords().get(i), font);
				} else {
					addEmptyCells(containerTable);
				}

				// 두 번째 단
				int idx2 = end1 + i;
				if (idx2 < end2) {
					addRecordCells(containerTable, certData.getRecords().get(idx2), font);
				} else {
					addEmptyCells(containerTable);
				}

				// 세 번째 단
				int idx3 = end2 + i;
				if (idx3 < totalRecords) {
					addRecordCells(containerTable, certData.getRecords().get(idx3), font);
				} else {
					addEmptyCells(containerTable);
				}
			}
			document.add(containerTable);

			// 통계 정보 (기존 코드 유지, 여백만 조정)
			Table statsTable = new Table(UnitValue.createPercentArray(new float[]{25, 25, 25, 25}))
					.useAllAvailableWidth()
					.setMarginBottom(10);

			addStatsCell(statsTable, "총이수학점", font, boldFont);
			addStatsCell(statsTable, certData.getTotalCredits() + "학점", font, boldFont);
			addStatsCell(statsTable, "평점(GPA)", font, boldFont);
			addStatsCell(statsTable, String.format("%.2f/4.50", certData.getTotalGPA()), font, boldFont);

			document.add(statsTable);
		}

		// 증명 내용
		Paragraph content = new Paragraph("위 사람의 성적증명서임을 증명합니다.")
				.setFont(font)
				.setFontSize(14)
				.setTextAlignment(TextAlignment.CENTER)
				.setMarginTop(20)
				.setMarginBottom(30);
		document.add(content);

		// 발급일
		Paragraph issueDate = new Paragraph(certData.getIssuedDate().format(DATE_FORMATTER))
				.setFont(font)
				.setFontSize(12)
				.setTextAlignment(TextAlignment.CENTER)
				.setMarginBottom(20);
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

	// 2행 4열용 컴팩트 셀 추가 메서드
	private void addCompactInfoCell(Table table, String label, String value, PdfFont font, PdfFont boldFont) {
		String safeValue = (value != null) ? value : "";

		// 라벨 셀
		Cell labelCell = new Cell()
				.add(new Paragraph(label).setFont(boldFont).setFontSize(9))
				.setBackgroundColor(ColorConstants.LIGHT_GRAY)
				.setTextAlignment(TextAlignment.CENTER)
				.setPadding(5)
				.setBorder(new SolidBorder(ColorConstants.BLACK, 1));

		// 값 셀
		Cell valueCell = new Cell()
				.add(new Paragraph(safeValue).setFont(font).setFontSize(9))
				.setTextAlignment(TextAlignment.CENTER)
				.setPadding(5)
				.setBorder(new SolidBorder(ColorConstants.BLACK, 1));

		table.addCell(labelCell);
		table.addCell(valueCell);
	}

	private void addRecordCells(Table table, AcademicRecordCertificateData.AcademicRecordDto record, PdfFont font) {
		// 과목명
		table.addCell(new Cell()
				.add(new Paragraph(record.getSubjectName()).setFont(font).setFontSize(7))
				.setBorder(null)
				.setBorderLeft(new SolidBorder(ColorConstants.BLACK, 1))
				.setBorderRight(new SolidBorder(ColorConstants.BLACK, 1))
				.setPaddingLeft(10));

		// 성적
		table.addCell(new Cell()
				.add(new Paragraph(record.getGrade()).setFont(font).setFontSize(7))
				.setBorder(null)
				.setBorderLeft(new SolidBorder(ColorConstants.BLACK, 1))
				.setBorderRight(new SolidBorder(ColorConstants.BLACK, 1))
				.setPaddingLeft(10));

		// 학점
		table.addCell(new Cell()
				.add(new Paragraph(String.valueOf(record.getCredits())).setFont(font).setFontSize(7))
				.setBorder(null)
				.setBorderLeft(new SolidBorder(ColorConstants.BLACK, 1))
				.setBorderRight(new SolidBorder(ColorConstants.BLACK, 1))
				.setPaddingLeft(10));
	}

	private void addEmptyCells(Table table) {
		for (int i = 0; i < 3; i++) {
			table.addCell(new Cell().setBorder(null).setBorderLeft(new SolidBorder(ColorConstants.BLACK, 1))
					.setBorderRight(new SolidBorder(ColorConstants.BLACK, 1)).setPadding(1));
		}
	}

	// 통계 정보 행
	private void addStatsCell(Table table, String text, PdfFont font, PdfFont boldFont) {
		boolean isLabel = text.contains("총이수학점") || text.contains("평점");

		Cell cell = new Cell()
				.add(new Paragraph(text).setFont(isLabel ? boldFont : font).setFontSize(9))
				.setBackgroundColor(isLabel ? ColorConstants.LIGHT_GRAY : ColorConstants.WHITE)
				.setTextAlignment(TextAlignment.CENTER)
				.setPadding(4)
				.setBorder(new SolidBorder(ColorConstants.BLACK, 0.5f));
		table.addCell(cell);
	}
}