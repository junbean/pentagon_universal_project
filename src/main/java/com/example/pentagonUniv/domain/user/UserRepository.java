package com.example.pentagonUniv.domain.user;

import com.example.pentagonUniv.domain.user.dto.PrincipalDto;
import com.example.pentagonUniv.domain.student.dto.StudentInfoDto;
import com.example.pentagonUniv.domain.user.dto.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

    // 로그인용
    public PrincipalDto selectByUserNumber(Long userNumber);

    // id 이용해서 user_tb에 insert
    public int insertToUser(User user);

    public UserInfoDto findById(Long userId);

    public StudentInfoDto selectStudentInfoById(Long id);

    // 현재 까지 입학한 년도 계산
    int findMaxSequence(@Param("year") String year, @Param("deptCode") String deptCode);

}
