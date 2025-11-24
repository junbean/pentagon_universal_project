package com.example.pentagonUniv.domain.professor.syllabus;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class HtmlRenderUtil {
    private final ApplicationContext context;

    public String renderJsp(String viewPath, Map<String, Object> model) {

        // 1) JSP를 렌더링할 실제 URL을 만든다
        StringBuilder url = new StringBuilder("http://localhost:80/");
        url.append(viewPath); // ex) professor/syllabusTemplate

        // 모델을 ?key=value 로 붙이기
        if (!model.isEmpty()) {
            url.append("?");
            model.forEach((key, value) -> {
                url.append(key)
                        .append("=")
                        .append(value != null ? value.toString() : "")
                        .append("&");
            });
            url.deleteCharAt(url.length() - 1); // 마지막 & 제거
        }

        // 2) RestTemplate 로 내부 HTTP 호출
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url.toString(), String.class);
    }
}
