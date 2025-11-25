package com.example.pentagonUniv.domain.breakapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.pentagonUniv.domain.breakapp.service.BreakAdminService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/break")
public class BreakAdminController {

  private final BreakAdminService service;

  @GetMapping("/pending")
  public String pending(Model model) {
    var list = service.pending();
    model.addAttribute("list", list);
    return "/admin/breakPending";
  }

  @PostMapping("/{id}/approve")
  public String approve(@PathVariable(name = "id") Long id) {
    service.approve(id);
    return "redirect:/admin/break/pending";
  }

  @PostMapping("/{id}/reject")
  public String reject(@PathVariable(name = "id") Long id) {
    service.reject(id);
    return "redirect:/admin/break/pending";
  }
}
