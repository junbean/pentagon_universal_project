package com.example.pentagonUniv.domain.user;

import com.example.pentagonUniv._global.handler.exception.CustomRestfullException;
import com.example.pentagonUniv._global.utils.Define;
import com.example.pentagonUniv.domain.dept.Department;
import com.example.pentagonUniv.domain.dept.DeptService;
import com.example.pentagonUniv.domain.professor.ProfessorService;
import com.example.pentagonUniv.domain.professor.dto.ProfessorRequestDto;
import com.example.pentagonUniv.domain.user.dto.LoginDto;
import com.example.pentagonUniv.domain.user.dto.PrincipalDto;
import com.example.pentagonUniv.domain.user.dto.UserInfoDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;



@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final HttpSession session;
    private final ProfessorService professorService;
    private final DeptService deptService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 메인 홈페이지
     */
    @GetMapping("/")
    public String home(Model model) {
        PrincipalDto principal = (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
        if (principal != null) {
            UserInfoDto userInfo = userService.findById(principal.getUserNumber());
            model.addAttribute("userInfo", userInfo);
        }
        return "main";
    }

    /**
     * 로그인 화면
     */
    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    /**
     * 로그인 처리
     */
    @PostMapping("/login")
    public String signInProc(@Valid LoginDto loginDto,
                             BindingResult bindingResult,
                             HttpServletResponse response,
                             HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                sb.append(error.getDefaultMessage()).append("\\n");
            });
            throw new CustomRestfullException(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        PrincipalDto principal = userService.login(loginDto);

        if ("on".equals(loginDto.getRememberId())) {
            Cookie cookie = new Cookie("id", loginDto.getUserNumber() + "");
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("id")) {
                        c.setMaxAge(0);
                        response.addCookie(c);
                        break;
                    }
                }
            }
        }
        session.setAttribute(Define.PRINCIPAL, principal);
        System.out.println("id : " + principal.getId() +
                            ", username : " + principal.getUserNumber() +
                            ", name : " + principal.getName() +
                            ", password : " + principal.getPassword() +
                            ", userType : " + principal.getUserType().name());

        return "redirect:/";
    }

    /**
     * 로그아웃
     */
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/login";
    }

    /**
     * 에러페이지
     */
    @GetMapping("/error")
    public String handleError() {
        return "/error/errorPage";
    }

    /**
     * 패스워드 팝업
     */
    @GetMapping("/guide")
    public String pop() {
        return "/user/passwordPop";
    }

    // 세션에서 현재 사용자의 정보를 가져오는 메서드입니다.
    private PrincipalDto getPrincipalFromSession() {
        return (PrincipalDto) session.getAttribute(Define.PRINCIPAL);
    }

    // 입력 유효성 검사에서 발생한 오류 메시지를 문자열로 반환하는 메서드입니다.
    private String getValidationErrors(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\\n"));
    }

    @GetMapping("/user/professorRegister")
    public String professor(Model model) {
        List<Department> dept = deptService.finaByAll();

        model.addAttribute("deptList",dept);
        return "professor/professorRegister";
    }

    @PostMapping("/user/professorRegister")
    public String createProfessor(ProfessorRequestDto dto) {
        
        professorService.createProfessor(dto);
        return "redirect:/";
    }
}
