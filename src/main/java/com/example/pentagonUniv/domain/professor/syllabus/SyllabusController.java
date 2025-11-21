package com.example.pentagonUniv.domain.professor.syllabus;

import com.example.pentagonUniv.domain.professor.syllabus.dto.SyllabusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/professor/syllabus")
public class SyllabusController {

    private final SyllabusService syllabusService;

    @GetMapping("/{id}")
    public String subjectList(Model model, @PathVariable(name = "id") Long id){
        List<SyllabusResponseDto.SubjectListDto> list = syllabusService.findAllSubjectByProfessorAndSyllabus(id);

        System.out.println("=== 조회 결과 출력 ===");
        for (SyllabusResponseDto.SubjectListDto subject : list) {
            System.out.println(subject);
        }
        model.addAttribute("subjectList", list);
        return "/professor/subjectList";
    }

    @GetMapping("/create/{id}")
    public String syllabusForm(){
        return "/professor/syllabusForm";
    }
}
