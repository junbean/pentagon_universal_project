package com.example.pentagonUniv.domain.college;

import com.example.pentagonUniv._global.utils.Define;
import com.example.pentagonUniv.domain.user.UserService;
import com.example.pentagonUniv.domain.user.dto.PrincipalDto;
import com.example.pentagonUniv.domain.user.dto.UserInfoDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/college")
public class CollegeController {

    private final UserService userService;
    private final CollegeService collegeService;
    private final HttpSession session;

    // 단과 등록 페이지 불러오기
    @GetMapping("/collegeRegister")
    public String home(Model model) {
        PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
        if (principal != null) {
            UserInfoDto userInfo = userService.findById(principal.getId());
            model.addAttribute("userInfo", userInfo);
        }
        return "college/collegeRegister";
    }

    // 단과 등록 하기
    @PostMapping("/collegeRegister")
    public String createCollege(String name) {
        log.info("단과 대학 이름 : {}", name);
        collegeService.createColleage(name);

        return "redirect:/";
    }

    // // 단과 리스트 불러오기
    // @GetMapping("/list")
    // public String findByAll(Model model) {
    // List<College> collegies = collegeService.finaByAll();
    // model.addAttribute("collegies", collegies);
    // return "redirect:/";
    // }
}
