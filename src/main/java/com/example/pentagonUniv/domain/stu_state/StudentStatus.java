package com.example.pentagonUniv.domain.stu_state;

public enum StudentStatus {

    ENROLLED("재적"),
    LEAVE("휴학");

    private final String displayName;

    StudentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
