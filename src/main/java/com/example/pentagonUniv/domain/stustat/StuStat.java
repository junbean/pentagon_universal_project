package com.example.pentagonUniv.domain.stustat;


import java.util.Date;

import lombok.Data;

@Data
public class StuStat {

    private Long id;
    private Long studentId;
    private StuStatus status; // enum 적용
    private Date fromDate;
    private Date toDate; // null이면 현재 상태
    private Long breakAppId; // 어떤 신청서로 인해 바뀌었는지
}
