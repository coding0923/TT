package com.tt.domain;

import lombok.Data;

@Data
public class AttendeeDTO extends commonDTO {
    private String studentId;
    private String participateStatus;
}
