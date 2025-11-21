package com.example.pentagonUniv.domain.professor.syllabus;

import com.example.pentagonUniv.domain.professor.syllabus.dto.SyllabusRequestDto;
import com.example.pentagonUniv.domain.professor.syllabus.dto.SyllabusResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SyllabusRepository {

    // 본인 강의 리스트 조회(교수)
    public List<SyllabusResponseDto.SubjectListDto> findAllByProfessorId(Long professorId);

    // 강의 계획서
    public void insertSyllabus(SyllabusRequestDto.CreateSyllabus syllabus);

    // 강의 계획서 조회(임시저장 데이터)
    public SyllabusRequestDto.CreateSyllabus findSyllabus(Long subjectId);
}
