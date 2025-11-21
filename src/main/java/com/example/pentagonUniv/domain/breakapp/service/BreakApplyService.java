package com.example.pentagonUniv.domain.breakapp.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pentagonUniv._global.handler.exception.CustomRestfullException;
import com.example.pentagonUniv.domain.breakapp.BreakAction;
import com.example.pentagonUniv.domain.breakapp.BreakApp;
import com.example.pentagonUniv.domain.breakapp.dto.BreakApplyForm;
import com.example.pentagonUniv.domain.breakapp.repository.BreakRepository;
import com.example.pentagonUniv.domain.breakapp.repository.StuStatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BreakApplyService {

  private final BreakRepository breakRepo;
  private final StuStatRepository statRepo;

  @Transactional
  public void apply(Long studentId, BreakApplyForm form) {
    // 상태 검증(선택)
    var current = statRepo.findCurrent(studentId);
    if (form.getAction() == BreakAction.LEAVE) {
      if (current != null && "휴학".equals(current.getStatus()))
        throw new CustomRestfullException("이미 휴학 상태입니다.", HttpStatus.BAD_REQUEST);
    } else { // RETURN
      if (current == null || !"휴학".equals(current.getStatus()))
        throw new CustomRestfullException("현재 휴학 상태가 아니어서 복학 신청이 불가합니다.", HttpStatus.BAD_REQUEST);
    }

    BreakApp app = new BreakApp();
    app.setStudentId(studentId);
    app.setAction(form.getAction());
    app.setReasonType(form.getReasonType());
    app.setReasonText(form.getReasonText());
    app.setFromYear(form.getFromYear());
    app.setFromSemester(form.getFromSemester());
    app.setToYear(form.getToYear());
    app.setToSemester(form.getToSemester());
    breakRepo.insert(app);
  }

  public List<BreakApp> myApps(Long studentId) {
    return breakRepo.findByStudentId(studentId);
  }
}
