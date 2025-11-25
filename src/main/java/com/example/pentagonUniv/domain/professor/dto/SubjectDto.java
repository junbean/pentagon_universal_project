package com.example.pentagonUniv.domain.professor.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubjectDto {
    private Long id;
    private String name; // 강의명
    private Long professorId; // 교수번호(FK)
    private String roomId; // 강의실 id(FK)
    private Long deptId; // 학과번호(FK)
    private String type; // 강의 구분
    private Integer subYear; // 연도
    private Integer semester; // 학기
    private String subDay; // 요일
    private Integer startTime; // 시작 시간
    private Integer endTime; // 종료 시간
    private Integer grades; // 이수 학점
    private Integer capacity; // 수강 정원
    private Integer numOfStudent; // 현재 신청 인원
}
