package com.example.pentagonUniv.domain.dept.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptRequestDto {
    private String name;
    private String collegeId;
}
