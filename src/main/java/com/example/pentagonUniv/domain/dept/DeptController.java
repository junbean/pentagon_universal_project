package com.example.pentagonUniv.domain.dept;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pentagonUniv.domain.college.College;
import com.example.pentagonUniv.domain.college.CollegeService;
import com.example.pentagonUniv.domain.dept.dto.DeptRequestDto;


@Controller
@RequiredArgsConstructor
@RequestMapping("/dept")
public class DeptController {
    private final CollegeService collegeService;
    private final DeptService deptService;

    @GetMapping("/deptRegister")
    public String home(Model model) {
        List<College> collegies = collegeService.finaByAll();
        model.addAttribute("collegeList", collegies);
        return "dept/deptRegister";
    }

    @PostMapping("/deptRegister")
    public String createDept(DeptRequestDto dto) {
        deptService.createDept(dto);
        return "redirect:/";
    }
    
}
