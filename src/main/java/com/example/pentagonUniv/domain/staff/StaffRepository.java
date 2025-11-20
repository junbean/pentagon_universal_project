package com.example.pentagonUniv.domain.staff;

import com.example.pentagonUniv.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StaffRepository {

    public void createStaff(User staff);
}
