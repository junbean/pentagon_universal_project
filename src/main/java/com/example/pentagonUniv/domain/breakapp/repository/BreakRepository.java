package com.example.pentagonUniv.domain.breakapp.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.pentagonUniv.domain.breakapp.BreakApp;
import com.example.pentagonUniv.domain.breakapp.BreakStatus;

@Mapper
public interface BreakRepository {
    int insert(BreakApp app);
    List<BreakApp> findByStudentId(Long studentId);
    List<BreakApp> findByStatus(BreakStatus status);
    int updateStatus(@Param("id") Long id, @Param("status") BreakStatus status);
    BreakApp findById(Long id);
}
