package com.tt.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SubjectDTO extends CommonDTO {

    private int subjectCode;

    private String subjectName;

    private String subjectNcsNumber;

    private int subjectNcsLevel;

}
