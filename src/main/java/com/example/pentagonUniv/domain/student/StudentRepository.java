package com.example.pentagonUniv.domain.student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.pentagonUniv.domain.student.dto.StudentInfoDto;
import com.example.pentagonUniv.domain.student.dto.StudentRequestDto;

@Mapper
public interface StudentRepository {
    // 학생 정보 단건 조회
    public StudentInfoDto selectStudentInfoById(Integer id);

    // 학생 등록 하기
    public void createStudent(StudentRequestDto dto);
}
