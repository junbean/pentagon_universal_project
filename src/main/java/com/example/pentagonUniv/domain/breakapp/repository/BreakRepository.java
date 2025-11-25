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
    List<BreakApp> findByStatus(@Param("status") String status);
    int updateStatus(@Param("id") Long id, @Param("status") BreakStatus status);
    BreakApp findById(Long id);
    // 기간 중복 금지
    List<BreakApp> findOverlappingLeave(
            @Param("studentId") Long studentId,
            @Param("fromYear") Integer fromYear,
            @Param("fromSemester") Integer fromSemester,
            @Param("toYear") Integer toYear,
            @Param("toSemester") Integer toSemester
    );
    // 마지막 승인된 휴학 신청 한 건
    BreakApp findLastApprovedLeave(@Param("studentId") Long studentId);
}
