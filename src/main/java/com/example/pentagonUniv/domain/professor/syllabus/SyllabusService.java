package com.example.pentagonUniv.domain.professor.syllabus;

import com.example.pentagonUniv.domain.professor.syllabus.dto.SyllabusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SyllabusService {
    private final SyllabusRepository syllabusRepository;

    // 본인 강의 리스트 전체 조회
    public List<SyllabusResponseDto.SubjectListDto> findAllSubjectByProfessorAndSyllabus(Long professorId){
        return syllabusRepository.findAllByProfessorId(professorId);
    }
}
