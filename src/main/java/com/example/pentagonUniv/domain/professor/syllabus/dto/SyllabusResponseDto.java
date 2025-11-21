package com.example.pentagonUniv.domain.professor.syllabus.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class SyllabusResponseDto {

    @Setter
    @Getter
    @ToString
    public static class SubjectListDto{
        private Long id;
        private String name; // 강의명
        private Long professor_id; // 교수번호(FK)
        private Long dept_id; // 학과번호(FK)
        private String type; // 강의 구분
        private Integer subYear; // 연도
        private Integer semester; // 학기
        private String subDay; // 요일
        private Integer grades; // 이수 학점
        private boolean syllabus; // 강의계획서 유무
    }
}
