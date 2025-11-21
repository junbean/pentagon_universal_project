package com.example.pentagonUniv.domain.user;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * packageName    : com.cyber.university.repository.model
 * fileName       : User
 * author         : 이준혁
 * date           : 2024/03/10
 * description    : 유저 모델
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/10          이준혁       최초 생성
 */
@Data
public class User {

    private Long id;
    private String userNumber;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String tel;
    private String email;
    private String password;
    private UserType userType;
}
