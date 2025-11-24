package com.example.pentagonUniv.domain.staff;

import com.example.pentagonUniv.domain.staff.dto.StaffRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StaffRepository {

    public void createStaff(StaffRequestDto dto);
}
