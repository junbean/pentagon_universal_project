package com.example.pentagonUniv.domain.college;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pentagonUniv._global.handler.exception.CustomRestfullException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CollegeService {
    private final CollegeRepository collegeRepository;

    // 단과 등록
    @Transactional
    public void createColleage(String name) {
        log.info("단과 대학 이름 : {}", name);
        collegeRepository.createCollege(name);
    }

    // 전체 단과 조회
    public List<College> finaByAll() {
        return collegeRepository.findAColleges();
    }

    // 단과 단일 조회
    public College findByCollege(Long collegeId) {

        College college = collegeRepository.findByCollege(collegeId);
        if (college == null) {
            throw new CustomRestfullException("등록된 단과를 찾을수없습니다.", HttpStatus.NOT_FOUND);
        }
        return college;
    }

}
