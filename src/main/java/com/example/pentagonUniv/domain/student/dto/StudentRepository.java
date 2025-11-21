package com.example.pentagonUniv.domain.student.dto;

import com.example.pentagonUniv._global.utils.pdf.dto.AcademicRecordCertificateData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentRepository {
    // 학생 정보 단건 조회
    public StudentInfoDto selectStudentInfoById(Integer id);

	List<AcademicRecordCertificateData.AcademicRecordDto> selectStudentAcademicRecords(@Param("studentId") Long studentId);
}
