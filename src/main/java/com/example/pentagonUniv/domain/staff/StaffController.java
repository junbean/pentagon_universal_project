package com.example.pentagonUniv.domain.staff;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pentagonUniv.domain.staff.dto.StaffRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    // 교지원 등록 페이지
    @GetMapping("/staffRegister")
    public String home() {
        return "staff/staffRegister";
    }
    
    @PostMapping("/staffRegister")
    public String staffRegister(StaffRequestDto dto) {
        staffService.createStaff(dto);
        return "redirect:/";
    }
}
