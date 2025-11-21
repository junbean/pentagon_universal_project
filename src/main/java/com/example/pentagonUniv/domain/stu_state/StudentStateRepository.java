package com.example.pentagonUniv.domain.stu_state;

import org.apache.ibatis.annotations.Mapper;

import com.example.pentagonUniv.domain.stu_state.dto.StudentStateRequestDto;

@Mapper
public interface StudentStateRepository {

    // 학적 상태 등록
    public void createStuState(StudentStateRequestDto dto);
}
