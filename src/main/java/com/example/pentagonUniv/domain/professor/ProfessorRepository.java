package com.example.pentagonUniv.domain.professor;

import org.apache.ibatis.annotations.Mapper;

import com.example.pentagonUniv.domain.professor.dto.ProfessorRequestDto;

@Mapper
public interface ProfessorRepository {
    public void createProfessor(ProfessorRequestDto dto);

    public ProfessorRequestDto findProfessor(Long professorId);
}
