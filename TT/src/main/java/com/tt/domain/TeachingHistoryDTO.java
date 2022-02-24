package com.tt.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class TeachingHistoryDTO {

    private String teacherId;

    private Date teachingStartDate;

    private Date teachingEndDate;

    private String teachingStatus;
}
