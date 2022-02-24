package com.tt.domain;

import java.sql.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TeachingHistoryDTO extends CommonDTO {

    private String teacherId;

    private Date teachingStartDate;

    private Date teachingEndDate;

    private String teachingStatus;
}
