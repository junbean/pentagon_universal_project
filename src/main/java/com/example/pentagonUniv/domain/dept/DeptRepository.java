package com.example.pentagonUniv.domain.dept;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.pentagonUniv.domain.college.College;

@Mapper
public interface DeptRepository {

    // 학과 등록
    public void createDept(@Param("name") String name, @Param("collegeId") Long collegeId);

    // 전체 학과 목록
    public List<Department> findADept();

    // 단일 학과 조회
    public Department findByDept(Long deptId);
}
