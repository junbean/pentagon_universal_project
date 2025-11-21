package com.example.pentagonUniv.domain.staff;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pentagonUniv.domain.staff.dto.StaffRequestDto;
import com.example.pentagonUniv.domain.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StaffService {

    private final StaffRepository staffRepository;
    private final UserService userService;

    public void createStaff(StaffRequestDto dto){
        dto.setHireDate(LocalDate.now());
        
        LocalDate birthDate = LocalDate.of(
            Integer.parseInt(dto.getBirthYear()),
            Integer.parseInt(dto.getBirthMonth()),
            Integer.parseInt(dto.getBirthDay())
        );

        dto.setBirthDate(birthDate);

        dto.setUserNumber(userService.generateNewUserNumber(null, dto.getHireDate()));

        staffRepository.createStaff(dto);
    }
}
