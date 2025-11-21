package com.example.pentagonUniv.domain.professor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {
    private Long id;
    private String name; // 강의명
    private Long professor_id; // 교수번호(FK)
    private String room_id; // 강의실 id(FK)
    private Long dept_id; // 학과번호(FK)
    private String type; // 강의 구분
    private Integer sub_year; // 연도
    private Integer semester; // 학기
    private String sub_day; // 요일
    private Integer start_time; // 시작 시간
    private Integer end_time; // 종료 시간
    private Integer grades; // 이수 학점
    private Integer capacity; // 수강 정원
    private Integer num_of_student; // 현재 신청 인원
}
