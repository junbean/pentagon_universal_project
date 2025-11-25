package com.example.pentagonUniv.domain.professor.syllabus;

import com.example.pentagonUniv._global.utils.Define;
import com.example.pentagonUniv.domain.professor.dto.SubjectDto;
import com.example.pentagonUniv.domain.professor.syllabus.dto.SyllabusRequestDto;
import com.example.pentagonUniv.domain.professor.syllabus.dto.SyllabusResponseDto;
import com.example.pentagonUniv.domain.user.dto.PrincipalDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/professor/syllabus")
public class SyllabusController {

    private final SyllabusService syllabusService;

    @GetMapping("/{id}")
    public String subjectList(Model model, @PathVariable(name = "id") Long id){
        List<SyllabusResponseDto.SubjectListDto> list = syllabusService.findAllSubjectByProfessorAndSyllabus(id);

//        System.out.println("=== 조회 결과 출력 ===");
//        for (SyllabusResponseDto.SubjectListDto subject : list) {
//            System.out.println(subject);
//        }
        model.addAttribute("subjectList", list);
        return "/professor/subjectList";
    }

    // 강의 계획서 작성 폼 이동
    // 등록된 강의 계획서가 없으면 빈칸
    // 임시저장된 데이터가 있으면 채워진 채로 출력
    @GetMapping("/create/{id}")
    public String syllabusForm(Model model,
                               @PathVariable(name = "id") Long syllabusId){
        System.out.println(syllabusId);
        SyllabusRequestDto.CreateSyllabus syllabus = syllabusService.findBySubjectId(syllabusId);
        SubjectDto subject = syllabusService.findBySubject(syllabusId);

        System.out.println("------syllabus--------");
        System.out.println(syllabus);

        if(syllabus == null){
            model.addAttribute("syllabus", new SyllabusRequestDto.CreateSyllabus());
            model.addAttribute("subject", subject);
        }
        else {
            model.addAttribute("syllabus", syllabus);
            model.addAttribute("subject", subject);
        }

        return "/professor/syllabusForm";
    }

    @PostMapping("/create/{id}")
    public String syllabusCreate(Model model,
                                 SyllabusRequestDto.CreateSyllabus newSyllabus,
                                 HttpSession session,
                                 RedirectAttributes rttr){

        PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
        if(newSyllabus.getStatus().equalsIgnoreCase("TEMP")){
            syllabusService.createSyllabusByTEMP(newSyllabus);
            rttr.addFlashAttribute("message", "임시저장 되었습니다");
        }
        else{
            syllabusService.createSyllabusByCOMPLETE(newSyllabus, principal.getId());
            rttr.addFlashAttribute("message", "최종제출 되었습니다");
        }

        return "redirect:/professor/syllabus/" + principal.getId();
    }

    @GetMapping("/syllabusTemplate")
    public String syllabusTemplate(Model model,
                                   @RequestParam Map<String, Object> params) {

        params.forEach(model::addAttribute);
        return "/professor/syllabusTemplate"; // JSP 경로
    }

    @GetMapping("/view/{subjectId}")
    public void viewPdf(@PathVariable Long subjectId, HttpServletResponse response) throws IOException {
        String pdfUrl = syllabusService.getPdfPath(subjectId);
        response.sendRedirect(pdfUrl);  // /pdf/xxx.pdf 로 이동
    }
}
