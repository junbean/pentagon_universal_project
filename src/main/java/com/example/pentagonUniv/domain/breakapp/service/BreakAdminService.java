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
        return breakRepo.findByStatus(BreakStatus.PENDING.name());
    }

    @Transactional
    public void approve(Long appId) {
        // 1) 신청 조회
        BreakApp app = breakRepo.findById(appId);
        if (app == null) {
            throw new IllegalArgumentException("신청이 존재하지 않습니다.");
        }

        // 2) 이미 처리된 신청인지 확인
        if (app.getStatus() != BreakStatus.PENDING) {
            throw new IllegalStateException("이미 처리된 신청입니다.");
        }

        // 3) 현재 학적 상태 조회
        StuStat current = statRepo.findCurrent(app.getStudentId());

        // 3-1) 휴학 승인 검증
        if (app.getAction() == BreakAction.LEAVE) {
            // 이미 휴학 상태인 학생에게 또 휴학 승인 X
            if (current != null && current.getStatus() == StuStatus.LEAVE) {
                throw new IllegalStateException("현재 휴학 상태인 학생에게 다시 휴학을 승인할 수 없습니다.");
            }
        }
        // 3-2) 복학 승인 검증
        else if (app.getAction() == BreakAction.RETURN) {
            // 휴학 상태가 아닌데 복학 승인 X
            if (current == null || current.getStatus() != StuStatus.LEAVE) {
                throw new IllegalStateException("현재 휴학 상태가 아닌 학생의 복학을 승인할 수 없습니다.");
            }
        }

        // 4) 기존 학적 이력 종료 (열려있는 구간만)
        Date today = new Date();
        if (current != null && current.getToDate() == null) {
            statRepo.closeCurrent(app.getStudentId(), today);
        }

        // 5) 새 학적 이력 추가
        StuStat s = new StuStat();
        s.setStudentId(app.getStudentId());
        s.setFromDate(today);
        s.setToDate(null);
        s.setBreakAppId(appId);

        if (app.getAction() == BreakAction.LEAVE) {
            s.setStatus(StuStatus.LEAVE);
        } else { // RETURN
            s.setStatus(StuStatus.ENROLLED);
        }

        statRepo.insert(s);

        // 6) 마지막으로 신청 상태를 APPROVED 로 변경
        breakRepo.updateStatus(appId, BreakStatus.APPROVED);
    }

    @Transactional
    public void reject(Long appId) {
        BreakApp app = breakRepo.findById(appId);
        if (app == null) {
            throw new IllegalArgumentException("신청이 존재하지 않습니다.");
        }

        if (app.getStatus() != BreakStatus.PENDING) {
            throw new IllegalStateException("이미 처리된 신청입니다.");
        }

        breakRepo.updateStatus(appId, BreakStatus.REJECTED);
    }
}
