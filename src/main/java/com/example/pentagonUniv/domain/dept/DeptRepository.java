package com.example.pentagonUniv.domain.dept;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.pentagonUniv.domain.college.College;
import com.example.pentagonUniv.domain.dept.dto.DeptRequestDto;

@Mapper
public interface DeptRepository {

    // 학과 등록
    public void createDept(DeptRequestDto dto);

    // 전체 학과 목록
    public List<Department> findADept();

    // 단일 학과 조회
    public Department findByDept(Long deptId);
}
