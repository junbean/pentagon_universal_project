package com.example.pentagonUniv.domain.professor.syllabus;

import com.example.pentagonUniv.domain.professor.dto.SubjectDto;
import com.example.pentagonUniv.domain.professor.syllabus.dto.SyllabusRequestDto;
import com.example.pentagonUniv.domain.professor.syllabus.dto.SyllabusResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SyllabusRepository {

    // 본인 강의 리스트 조회(교수)
    public List<SyllabusResponseDto.SubjectListDto> findAllByProfessorId(Long professorId);

    // 강의 계획서 저장
    public void insertSyllabus(SyllabusRequestDto.CreateSyllabus syllabus);

    // 강의 계획서 조회(임시저장 데이터)
    public SyllabusRequestDto.CreateSyllabus findSyllabus(Long subjectId);

    // 강의 조회
    public SubjectDto findSubject(Long subjectId);

    // 강의 계획서 업데이트
    public void updateSyllabus(SyllabusRequestDto.CreateSyllabus syllabus);

    // 강의 계획서 존재 여부(count)
    public int existsSyllabus(Long syllabusId);

    // 강의 계획서 주소 update
    public void updatePdfPath(@Param("syllabusId")Long syllabusId, @Param("pdfPath") String pdfPath);

    public String findPdfPath(Long syllabusId);
}
