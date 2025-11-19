package com.example.pentagonUniv.domain.user.dto;

import lombok.Data;

import java.util.Date;


@Data
public class UserInfoDto {
    private Long id;
    private String name;
    private Date birthDate;
    private String gender;
    private String address;
    private String tel;
    private String email;
    private String userType;
    private String originFileName;
    private String uploadFileName;

    public String setupProfileImage() {
        return uploadFileName == null ? "/img/profil.png" : "/images/uploads/" + uploadFileName;
    }
}