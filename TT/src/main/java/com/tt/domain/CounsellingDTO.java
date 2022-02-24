package com.tt.domain;

import lombok.Data;

@Data
public class CounsellingDTO {
    private int counsellingId;
    private String studentId;
    private String counsellingContent;
    private String teacherId;

}
