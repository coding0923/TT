package com.tt.domain;

import java.sql.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AttendanceDTO extends CommonDTO {
    private Date attendanceDate;
    private String studentId;
    private String attendanceStatus;
    private String attendanceMemo;
}
