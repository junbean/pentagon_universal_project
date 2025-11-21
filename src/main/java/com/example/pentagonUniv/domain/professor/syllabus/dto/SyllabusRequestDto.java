package com.example.pentagonUniv.domain.professor.syllabus.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class SyllabusRequestDto {

    @Setter
    @Getter
    @ToString
    public static class CreateSyllabus{
        private Long subject_id; // PK / FK
        private String overview; // 수업개요
        private String objective; // 강의목표
        private String textbook; // 교재
        private String status; // 임시저장, 최종제출 상태
        private String pdf_path; // pdf 저장 경로
        private String week1; // 주차 수업계획
        private String week2;
        private String week3;
        private String week4;
        private String week5;
        private String week6;
        private String week7;
        private String week8;
        private String week9;
        private String week10;
        private String week11;
        private String week12;
        private String week13;
        private String week14;
        private String week15;
    }
}
