package com.tt.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class CurriculumDTO {

    private int subjectCode;

    private String curriculumProc;

    private Date curriculumStartDate;

    private Date curriculumEndDate;

    private String curriculumInsertUser;

    private String curriculumUpdateUser;

    private Date curriculumInsertDate;

    private Date curriculumUpdateDate;

}
