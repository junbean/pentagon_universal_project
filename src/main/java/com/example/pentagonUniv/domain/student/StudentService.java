package com.example.pentagonUniv.domain.student;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pentagonUniv.domain.dept.Department;
import com.example.pentagonUniv.domain.dept.DeptRepository;
import com.example.pentagonUniv.domain.stu_state.StudentStateRepository;
import com.example.pentagonUniv.domain.stu_state.dto.StudentStateRequestDto;
import com.example.pentagonUniv.domain.student.dto.StudentRequestDto;
import com.example.pentagonUniv.domain.user.UserRepository;
import com.example.pentagonUniv.domain.user.UserService;
import com.example.pentagonUniv.domain.user.UserType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentStateRepository stateRepository;
    private final UserRepository userRepository;
    private final DeptRepository deptRepository;

    // 학생등록
    @Transactional
    public void createStudent(StudentRequestDto dto) {
        // 현재 시간 기준으로 등록
        dto.setEntranceDate(LocalDate.now());

        // 없으면 터짐

        Department department = deptRepository.findByDept(dto.getDeptId());

        // 학과 아이디 등록
        dto.setDeptId(department.getId());

        // 기본 비밀번호로 등록 123123
        dto.setPassword("$2a$10$hEYFZOe.PbFbVRcPZymRKuCGMU2MilAn64ZGnIqPgfwWL7JyGeKKW");

        dto.setUserNumber(generateNewUserNumber(dto.getDeptId(), dto.getEntranceDate()));

        studentRepository.createStudent(dto);

        // 학적 상태에도 등록
        StudentStateRequestDto stateRequestDto = new StudentStateRequestDto();

        // 학생 등록
        stateRequestDto.setStudentId(dto.getId());

        // 학적 등록
        stateRepository.createStuState(stateRequestDto);

    }

    // 고유 번호 증가
    public String generateNewUserNumber(Long deptId, LocalDate entranceDate) {
        String yearPrefix = String.valueOf(entranceDate.getYear());
        String deptCode = String.format("%03d", deptId);

        int maxSequence = userRepository.findMaxSequence(yearPrefix, deptCode);

        int newSequence = maxSequence + 1;

        return yearPrefix + deptCode + String.format("%03d", newSequence);
    }
}
