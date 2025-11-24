package com.example.pentagonUniv.domain.breakapp.dto;

import com.example.pentagonUniv.domain.breakapp.BreakAction;
import com.example.pentagonUniv.domain.breakapp.BreakReasonType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BreakApplyForm {

    @NotNull(message = "휴학/복학을 선택하세요.")
    private BreakAction action; // LEAVE | RETURN

    @NotNull(message = "사유를 선택하세요.")
    private BreakReasonType reasonType; // GENERAL | MILITARY...

    @Size(max = 255)
    private String reasonText;

    @NotNull(message = "시작 연도는 필수입니다.")
    @Min(value = 2000) @Max(value = 2100)
    private Integer fromYear;

    @NotNull(message = "시작 학기를 입력하세요.")
    @Min(1) @Max(2)
    private Integer fromSemester;

    @NotNull(message = "종료 연도는 필수입니다.")
    @Min(value = 2000) @Max(value = 2100)
    private Integer toYear;

    @NotNull(message = "종료 학기를 입력하세요.")
    @Min(1) @Max(2)
    private Integer toSemester;
}
