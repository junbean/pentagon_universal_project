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
import com.example.pentagonUniv.domain.stustat.StuStatus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BreakApplyService {

  private final BreakRepository breakRepo;
  private final StuStatRepository statRepo;

@Transactional
public void apply(Long studentId, BreakApplyForm form) {

    // 현재 학적 상태
    var current = statRepo.findCurrent(studentId);

    // 1. 액션별 학기/기간 검증
    if (form.getAction() == BreakAction.LEAVE) {
        
        // 종료 연/학기 필수
        if (form.getToYear() == null || form.getToSemester() == null) {
            throw new CustomRestfullException(
                    "휴학 종료 연도와 학기를 모두 입력하세요.",
                    HttpStatus.BAD_REQUEST
            );
        }

        int startKey = form.getFromYear() * 10 + form.getFromSemester();
        int endKey   = form.getToYear()   * 10 + form.getToSemester();

        if (startKey > endKey) {
            throw new CustomRestfullException(
                    "시작 학기가 종료 학기보다 이후일 수 없습니다.",
                    HttpStatus.BAD_REQUEST
            );
        }

        // 이미 휴학 상태면 휴학 신청 불가
        if (current != null && current.getStatus() == StuStatus.LEAVE) {
            throw new CustomRestfullException(
                    "현재 휴학 중인 상태입니다. 복학 후 다시 휴학 신청이 가능합니다.",
                    HttpStatus.BAD_REQUEST
            );
        }

        // 같은 학생의 휴학(LEAVE) 중, 승인/대기 상태이고, 기간이 겹치는 신청이 있는지 조회
        var overlapped = breakRepo.findOverlappingLeave(
                studentId,
                form.getFromYear(), form.getFromSemester(),
                form.getToYear(),   form.getToSemester()
        );
        if (!overlapped.isEmpty()) {
            throw new CustomRestfullException(
                    "해당 기간에 이미 휴학 신청(또는 승인)이 존재합니다.",
                    HttpStatus.BAD_REQUEST
            );
        }

    } else { // RETURN

        // 복학은 "복학할 학기"만 필수
        if (form.getFromYear() == null || form.getFromSemester() == null) {
            throw new CustomRestfullException(
                    "복학할 연도와 학기를 입력하세요.",
                    HttpStatus.BAD_REQUEST
            );
        }

        // 현재 휴학 상태가 아니면 복학 불가
        if (current == null || current.getStatus() != StuStatus.LEAVE) {
            throw new CustomRestfullException(
                    "현재 휴학 상태가 아니어서 복학 신청이 불가합니다.",
                    HttpStatus.BAD_REQUEST
            );
        }

        // DB NOT NULL 때문에, 비어 있으면 from과 동일하게 세팅
        if (form.getToYear() == null) {
            form.setToYear(form.getFromYear());
        }
        if (form.getToSemester() == null) {
            form.setToSemester(form.getFromSemester());
        }
    }

    // 2. DB 저장
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
