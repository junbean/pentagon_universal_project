package com.example.pentagonUniv.domain.breakapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pentagonUniv.domain.breakapp.BreakAction;
import com.example.pentagonUniv.domain.breakapp.BreakApp;
import com.example.pentagonUniv.domain.breakapp.BreakStatus;
import com.example.pentagonUniv.domain.breakapp.repository.BreakRepository;
import com.example.pentagonUniv.domain.breakapp.repository.StuStatRepository;
import com.example.pentagonUniv.domain.stustat.StuStat;
import com.example.pentagonUniv.domain.stustat.StuStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BreakAdminService {

  private final BreakRepository breakRepo;
  private final StuStatRepository statRepo;

  public List<BreakApp> pending() {
    return breakRepo.findByStatus(BreakStatus.PENDING);
  }

  @Transactional
  public void approve(Long appId) {
    BreakApp app = breakRepo.findById(appId);
    if (app == null) throw new IllegalArgumentException("신청이 존재하지 않습니다.");

    // Break 상태
    breakRepo.updateStatus(appId, BreakStatus.APPROVED);

    Date today = new Date();

    // 현재 구간 닫고 새 이력 열기
    statRepo.closeCurrent(app.getStudentId(), today);

    StuStat s = new StuStat();
    s.setStudentId(app.getStudentId());
    s.setFromDate(today);
    s.setToDate(null);
    s.setBreakAppId(appId);

    if (app.getAction() == BreakAction.LEAVE) {
      s.setStatus(StuStatus.LEAVE);
    } else {
      s.setStatus(StuStatus.ENROLLED);
}
    statRepo.insert(s);
  }

  @Transactional
  public void reject(Long appId) {
    breakRepo.updateStatus(appId, BreakStatus.REJECTED);
  }
}