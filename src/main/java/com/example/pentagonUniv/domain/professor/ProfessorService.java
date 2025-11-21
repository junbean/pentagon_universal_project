package com.example.pentagonUniv.domain.professor;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pentagonUniv.domain.professor.dto.ProfessorRequestDto;
import com.example.pentagonUniv.domain.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final UserService userService;

    @Transactional
    public void createProfessor(ProfessorRequestDto dto){
        dto.setHireDate(LocalDate.now());

        LocalDate birthDate = LocalDate.of(
            Integer.parseInt(dto.getBirthYear()),
            Integer.parseInt(dto.getBirthMonth()),
            Integer.parseInt(dto.getBirthDay())
        );

        dto.setBirthDate(birthDate);

        // 고유 번호 생성
        dto.setUserNumber(userService.generateNewUserNumber(dto.getDeptId(), dto.getHireDate()));
        professorRepository.createProfessor(dto);
    }
    
}
