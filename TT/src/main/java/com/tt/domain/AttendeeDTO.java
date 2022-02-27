package com.tt.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AttendeeDTO extends CommonDTO {
    private String studentId;
    private String participateStatus;
    private String attendeeMemo;
}
