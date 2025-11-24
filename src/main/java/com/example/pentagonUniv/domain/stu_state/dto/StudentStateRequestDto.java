package com.example.pentagonUniv.domain.stu_state.dto;

import java.time.LocalDate;

import com.example.pentagonUniv.domain.stu_state.StudentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentStateRequestDto {
    private Long studentId;
    private StudentStatus status;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Long breakAppId;
}
