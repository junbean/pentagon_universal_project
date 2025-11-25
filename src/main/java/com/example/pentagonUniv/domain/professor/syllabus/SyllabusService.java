package com.example.pentagonUniv.domain.professor.syllabus;

import com.example.pentagonUniv.domain.dept.Department;
import com.example.pentagonUniv.domain.dept.DeptRepository;
import com.example.pentagonUniv.domain.professor.ProfessorRepository;
import com.example.pentagonUniv.domain.professor.dto.ProfessorRequestDto;
import com.example.pentagonUniv.domain.professor.dto.SubjectDto;
import com.example.pentagonUniv.domain.professor.syllabus.dto.SyllabusRequestDto;
import com.example.pentagonUniv.domain.professor.syllabus.dto.SyllabusResponseDto;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SyllabusService {
    private final SyllabusRepository syllabusRepository;
    private final ProfessorRepository professorRepository;
    private final HtmlRenderUtil htmlRenderUtil;
    private final DeptRepository deptRepository;

    // 본인 강의 리스트 전체 조회
    public List<SyllabusResponseDto.SubjectListDto> findAllSubjectByProfessorAndSyllabus(Long professorId){
        return syllabusRepository.findAllByProfessorId(professorId);
    }
    
    // 강의 계획서 등록
    public void createSyllabusByTEMP(SyllabusRequestDto.CreateSyllabus syllabus){
        // 존재하면 업데이트 아니면 새로 등록
        if(syllabusRepository.existsSyllabus(syllabus.getSubjectId()) > 0){
            syllabusRepository.updateSyllabus(syllabus);
        }
        else{
            syllabusRepository.insertSyllabus(syllabus);
        }
    }

    public void createSyllabusByCOMPLETE(SyllabusRequestDto.CreateSyllabus syllabus, Long userId){
        SubjectDto subject = syllabusRepository.findSubject(syllabus.getSubjectId());
        ProfessorRequestDto professor = professorRepository.findProfessor(userId);

        Department department = deptRepository.findByDept(subject.getDeptId());

        // 1. status COMPLETE로 설정
        syllabus.setStatus("COMPLETE");

        // 2. 기존 존재 여부 확인 후 저장/업데이트
        if (syllabusRepository.existsSyllabus(syllabus.getSubjectId()) > 0) {
            syllabusRepository.updateSyllabus(syllabus);

        } else {
            syllabusRepository.insertSyllabus(syllabus);
        }

        try {
            // 3. HTML 렌더링을 위한 Model 생성
            Map<String, Object> model = new HashMap<>();
            model.put("subjectName", subject.getName());
            model.put("professorName", professor.getName());
            model.put("overview", syllabus.getOverview());
            model.put("objective", syllabus.getObjective());
            model.put("textbook", syllabus.getTextbook());

            model.put("departmentName", department.getName());
            model.put("lectureType", subject.getType());
            model.put("year", subject.getSubYear());
            model.put("semester", subject.getSemester());
            model.put("dayOfWeek", subject.getSubDay());
            model.put("startTime", subject.getStartTime());
            model.put("endTime", subject.getEndTime());
            model.put("credit", subject.getGrades());
            model.put("capacity", subject.getCapacity());


            model.put("week1", syllabus.getWeek1());
            model.put("week2", syllabus.getWeek2());
            model.put("week3", syllabus.getWeek3());
            model.put("week4", syllabus.getWeek4());
            model.put("week5", syllabus.getWeek5());
            model.put("week6", syllabus.getWeek6());
            model.put("week7", syllabus.getWeek7());
            model.put("week8", syllabus.getWeek8());
            model.put("week9", syllabus.getWeek9());
            model.put("week10", syllabus.getWeek10());
            model.put("week11", syllabus.getWeek11());
            model.put("week12", syllabus.getWeek12());
            model.put("week13", syllabus.getWeek13());
            model.put("week14", syllabus.getWeek14());
            model.put("week15", syllabus.getWeek15());

            // 4. JSP 템플릿을 HTML 문자열로 렌더링
            String html = htmlRenderUtil.renderJsp(
                    "/professor/syllabus/syllabusTemplate",
                    model
            );

            // 5. PDF 저장 경로 생성
            String dirPath = "src/main/resources/static/pdf/";
            String fileName = syllabus.getSubjectId() + "_syllabus.pdf";

            String pdfFullPath = dirPath + fileName;   // 실제 저장 파일 경로
            String pdfUrl = "/pdf/" + fileName;        // DB에 저장할 URL 경로

            // 6. HTML => PDF 변환
            try (OutputStream os = new FileOutputStream(pdfFullPath)) {
                PdfRendererBuilder builder = new PdfRendererBuilder();
                builder.useFastMode();
                builder.withHtmlContent(html, null);

                builder.useFont(
                        new File("src/main/resources/static/fonts/NotoSansKR-Regular.ttf"),
                        "NotoSansKR-Regular"
                );

                builder.useFont(
                        new File("src/main/resources/static/fonts/NotoSansKR-Bold.ttf"),
                        "NotoSansKR-Medium"
                );

                builder.useFont(
                        new File("src/main/resources/static/fonts/NotoSansKR-Medium.ttf"),
                        "NotoSansKR-Bold"
                );

                builder.toStream(os);
                builder.run();
            }

            // 7. DB에 pdf_path 저장
            syllabusRepository.updatePdfPath(
                    syllabus.getSubjectId(),
                    pdfUrl
            );

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("PDF 생성 실패", e);
        }

    }

    // 강의 계획서 조회
    public SyllabusRequestDto.CreateSyllabus findBySubjectId(Long subjectId){
        return syllabusRepository.findSyllabus(subjectId);
    }

    // 강의 조회
    public SubjectDto findBySubject(Long subjectId){
        return syllabusRepository.findSubject(subjectId);
    }


    public String getPdfPath(Long subjectId){
       return syllabusRepository.findPdfPath(subjectId);
    }
}
