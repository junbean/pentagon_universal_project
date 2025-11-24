package com.example.pentagonUniv.domain.student;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pentagonUniv.domain.dept.Department;
import com.example.pentagonUniv.domain.dept.DeptService;
import com.example.pentagonUniv.domain.student.dto.StudentRequestDto;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final DeptService deptService;

    // 유저 등록 페이지 불러오기
    @GetMapping("/studentRegister")
    public String createStudent(Model model) {
        List<Department> dept = deptService.finaByAll();
        model.addAttribute("deptList",dept);
        return "student/studentRegister";
    }

    // 유저 등록
    @PostMapping("/studentRegister")
    public String createStudent(StudentRequestDto dto) {

        studentService.createStudent(dto);

        return "redirect:/";
    }

}
