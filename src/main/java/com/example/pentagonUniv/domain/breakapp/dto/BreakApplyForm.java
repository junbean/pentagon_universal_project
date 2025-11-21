package com.example.pentagonUniv.domain.breakapp.dto;

import com.example.pentagonUniv.domain.breakapp.BreakAction;
import com.example.pentagonUniv.domain.breakapp.BreakReasonType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BreakApplyForm {

    @NotNull
    private BreakAction action; // LEAVE | RETURN

    private BreakReasonType reasonType; // GENERAL | MILITARY...

    private String reasonText;

    @NotNull
    private Integer fromYear;

    @NotNull
    @Min(1) @Max(2)
    private Integer fromSemester;

    @NotNull
    private Integer toYear;

    @NotNull
    @Min(1) @Max(2)
    private Integer toSemester;
}
