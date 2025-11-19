package com.example.pentagonUniv._global.config;

import lombok.RequiredArgsConstructor;
import com.example.pentagonUniv._global.handler.*;
import com.example.pentagonUniv._global.utils.Define;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * packageName    : com.cyber.university.config
 * fileName       : WebMvcConfig
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : WebMvcConfig
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final AuthInterceptor authIntercepter;
    private final UserRoleAuthIntercepterForProfessor authIntercepterForProfessor;
    private final UserRoleAuthIntercepterForStaff authIntercepterForStaff;
    private final UserRoleAuthIntercepterForStudent authIntercepterForStudent;
    private final AuthIntercepterForLogin authIntercepterForLogin;

    // @Autowired
    // private AuthIntercepterForMainPage authIntercepterForMainPage;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authIntercepter).addPathPatterns(Define.PATHS);
        registry.addInterceptor(authIntercepterForProfessor).addPathPatterns(Define.PROFESSOR_PATHS);
        registry.addInterceptor(authIntercepterForStaff).addPathPatterns(Define.STAFF_PATHS);
        registry.addInterceptor(authIntercepterForStudent).addPathPatterns(Define.STUDENT_PATHS);
        registry.addInterceptor(authIntercepterForLogin).addPathPatterns("/login");
//        registry.addInterceptor(authIntercepterForMainPage).addPathPatterns("/");
    }

    // 파일 리소스 등록
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/uploads/**")
                .addResourceLocations("file:///C:\\spring_upload\\cyberUniversity\\upload/");
//                .addResourceLocations("file:////Users/junhyuk/Documents/upload/");


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
