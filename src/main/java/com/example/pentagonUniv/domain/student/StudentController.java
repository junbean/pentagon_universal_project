package com.example.pentagonUniv.domain.student;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pentagonUniv._global.utils.PageUtil;
import com.example.pentagonUniv.domain.college.College;
import com.example.pentagonUniv.domain.college.CollegeService;
import com.example.pentagonUniv.domain.dept.Department;
import com.example.pentagonUniv.domain.dept.DeptService;
import com.example.pentagonUniv.domain.stu_state.StudentStatus;
import com.example.pentagonUniv.domain.student.dto.StudentInfoDto;
import com.example.pentagonUniv.domain.student.dto.StudentRequestDto;
import com.example.pentagonUniv.domain.user.UserService;
import com.example.pentagonUniv.domain.user.UserType;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final UserService userService;
    private final DeptService deptService;
    private final CollegeService collegeService;

    // 유저 등록 페이지 불러오기
    @GetMapping("/studentRegister")
    public String createStudent(Model model) {
        List<Department> dept = deptService.finaByAll();
        model.addAttribute("deptList", dept);
        return "student/studentRegister";
    }

    // 유저 등록
    @PostMapping("/studentRegister")
    public String createStudent(StudentRequestDto dto) {

        studentService.createStudent(dto);

        return "redirect:/";
    }

    // 유저 리스트 페이지
    @GetMapping("/studentList")
    public String studentList(Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        // 전체 학생 수
        int studentCount = userService.userCount(UserType.STUDENT);

        // 페이지 유틸
        PageUtil pageUtil = new PageUtil(page, studentCount, size, 10);

        List<College> colleges = collegeService.finaByAll();
        List<Department> departments = deptService.finaByAll();
        List<StudentStatus> studentStatus = Arrays.asList(StudentStatus.values());
        List<StudentInfoDto> studentInfoDtos = userService.findStudentAll(pageUtil.getOffset(), pageUtil.getPageSize());

        // 페이지 갯수
        model.addAttribute("page", pageUtil);
        // 단과 리스트
        model.addAttribute("collegeList", colleges);
        // 학과 리스트
        model.addAttribute("deptList", departments);
        // 학적 상태
        model.addAttribute("studentStatus", studentStatus);
        // 유저 상태
        model.addAttribute("studentList", studentInfoDtos);

        return "student/studentList";
    }

}
