package com.example.pentagonUniv.domain.breakapp.repository;


import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.pentagonUniv.domain.stustat.StuStat;

@Mapper
public interface StuStatRepository {
    int insert (StuStat s);
    StuStat findCurrent(Long studentId); //최신(form_date desc)1건
    int closeCurrent(@Param("studentId") Long studentId, @Param("toDate") Date toDate);
}
