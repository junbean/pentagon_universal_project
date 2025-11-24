package com.example.pentagonUniv.domain.professor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pentagonUniv.domain.dept.Department;
import com.example.pentagonUniv.domain.dept.DeptService;
import com.example.pentagonUniv.domain.professor.dto.ProfessorRequestDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequiredArgsConstructor
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService professorService;
    private final DeptService deptService;

    
    
}
