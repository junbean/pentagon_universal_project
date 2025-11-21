package com.example.pentagonUniv.domain.professor.syllabus;

import com.example.pentagonUniv.domain.professor.syllabus.dto.SyllabusResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SyllabusRepository {

    public List<SyllabusResponseDto.SubjectListDto> findAllByProfessorId(Long professorId);
}
