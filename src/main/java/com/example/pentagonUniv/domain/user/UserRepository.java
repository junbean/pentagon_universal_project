package com.example.pentagonUniv.domain.user;

import com.example.pentagonUniv.domain.user.dto.PrincipalDto;
import com.example.pentagonUniv.domain.professor.dto.ProfessorInfoDto;
import com.example.pentagonUniv.domain.staff.dto.StaffInfoDto;
import com.example.pentagonUniv.domain.student.dto.StudentInfoDto;
import com.example.pentagonUniv.domain.user.dto.UserInfoDto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

    // 로그인용
    public PrincipalDto selectByUserNumber(String userNumber);

    // id 이용해서 user_tb에 insert
    public int insertToUser(User user);

    public UserInfoDto findById(Long userId);

    // 현재 까지 입학한 년도 계산
    Integer findMaxSequence(@Param("year") String year);

    // 유저 정보 조회
    public List<StudentInfoDto> findByAllStudentInfo();
    public List<ProfessorInfoDto> findByAllProfessorInfo();
    public List<StaffInfoDto> findByAllStaffInfo();

}
