package com.example.pentagonUniv.domain.student.dto;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentRepository {
    // 학생 정보 단건 조회
    public StudentInfoDto selectStudentInfoById(Integer id);
}
