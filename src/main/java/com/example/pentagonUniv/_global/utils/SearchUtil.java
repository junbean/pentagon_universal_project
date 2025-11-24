package com.example.pentagonUniv._global.utils;

import com.example.pentagonUniv.domain.stu_state.StudentStatus;

import lombok.Data;

@Data
public class SearchUtil {
        private String keyWord;
        private Long collegeId;
        private Long detpId;
        private Integer grade;
        private String semester;
        private StudentStatus studentStatus;
}
