package com.tt.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class curriculumDTO {

	private int subjectCode;

	private int classId;

	private String academyId;

	private String curriculumProc;

	private Date curriculumStartDate;

	private Date curriculumEndDate;

	private String curriculumInsertUser;

	private String curriculumUpdateUser;

	private Date curriculumInsertDate;

	private Date curriculumUpdateDate;

}
