package com.example.pentagonUniv.domain.student;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.pentagonUniv.domain.student.dto.StudentInfoDto;
import com.example.pentagonUniv.domain.student.dto.StudentRequestDto;
import com.example.pentagonUniv.domain.user.UserType;

@Mapper
public interface StudentRepository {
    // 학생 정보 단건 조회
    public StudentInfoDto selectStudentInfoById(Integer id);

    public List<StudentInfoDto> findByAllStudent(UserType userType);

    // 학생 등록 하기
    public void createStudent(StudentRequestDto dto);
}
