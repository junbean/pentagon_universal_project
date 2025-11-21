package com.example.pentagonUniv.domain.breakapp;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class BreakApp {
  private Long id;
  private Long studentId;
  private BreakAction action;       // 휴학/복학
  private BreakReasonType reasonType;  // 사유 분류
  private String reasonText;        // 상세 사유
  private Integer fromYear;
  private Integer fromSemester;     // 1 or 2
  private Integer toYear;
  private Integer toSemester;       // 1 or 2
  private Timestamp appDate;        // default CURRENT_TIMESTAMP
  private BreakStatus status;       // PENDING/APPROVED/REJECTED
}
