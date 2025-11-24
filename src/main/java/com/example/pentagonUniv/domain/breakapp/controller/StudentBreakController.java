package com.example.pentagonUniv.domain.breakapp.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.pentagonUniv._global.handler.exception.CustomRestfullException;
import com.example.pentagonUniv._global.utils.Define;
import com.example.pentagonUniv.domain.breakapp.dto.BreakApplyForm;
import com.example.pentagonUniv.domain.breakapp.service.BreakApplyService;
import com.example.pentagonUniv.domain.user.dto.PrincipalDto;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student/break")
public class StudentBreakController {

  private final HttpSession session;
  private final BreakApplyService service;

  @GetMapping("/apply")
  public String form(Model model) {
    model.addAttribute("form", new BreakApplyForm());
    return "student/breakApply"; // JSP
  }

  @PostMapping("/apply")
public String apply(@Valid @ModelAttribute("form") BreakApplyForm form, BindingResult br) {
    if (br.hasErrors())
        throw new CustomRestfullException("입력값을 확인하세요.", HttpStatus.BAD_REQUEST);

    var p = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
    service.apply(p.getId().longValue(), form);

    return "redirect:/student/break/list";
}

  @GetMapping("/list")
  public String list(Model model) {
    var p = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
    model.addAttribute("list", service.myApps(p.getId().longValue()));
    return "student/breakList"; // JSP
  }
}

