package com.tt.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CounsellingDTO extends CommonDTO {
    private int counsellingId;
    private String studentId;
    private String counsellingContent;
    private String teacherId;

}
