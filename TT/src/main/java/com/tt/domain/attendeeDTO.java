package com.tt.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class attendeeDTO {
    private String studentId;
    private String classId;
    private String academyId;
    private String participateStatus;
    private String attendeeUpdateUser;
    private Date attendeeInsertDate;
    private Date attendeeUpdateDate;
}
