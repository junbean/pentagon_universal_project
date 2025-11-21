package com.example.pentagonUniv.domain.dept;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pentagonUniv._global.handler.exception.CustomRestfullException;
import com.example.pentagonUniv.domain.college.College;
import com.example.pentagonUniv.domain.dept.dto.DeptRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeptService {
    private final DeptRepository deptRepositoryl;

    // 학과 등록
    @Transactional
    public void createDept(DeptRequestDto dto) {
        deptRepositoryl.createDept(dto);
    }

    // 전체 학과 조회
    public List<Department> finaByAll() {
        return deptRepositoryl.findADept();
    }

    // 단과 학과 조회
    public Department findByCollege(Long deptId) {

        Department department = deptRepositoryl.findByDept(deptId);
        if (department == null) {
            throw new CustomRestfullException("등록된 학과를 찾을수없습니다.", HttpStatus.NOT_FOUND);
        }
        return department;
    }
}
