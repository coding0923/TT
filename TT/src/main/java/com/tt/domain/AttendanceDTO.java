package com.tt.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class AttendanceDTO extends commonDTO {
    private Date attendanceDate;
    private String studentId;
    private String attendanceStatus;
    private String attendanceMemo;
}
