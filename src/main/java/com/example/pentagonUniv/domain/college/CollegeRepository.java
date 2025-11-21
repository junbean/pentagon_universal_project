package com.example.pentagonUniv.domain.college;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollegeRepository {

    // 단과 등록하기
    public void createCollege(String name);

    // 전체 단과 목록
    public List<College> findAColleges();

    // 단일 단과 조회
    public College findByCollege(Long collegeId);
}
