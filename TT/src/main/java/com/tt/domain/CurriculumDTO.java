package com.tt.domain;

import java.sql.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CurriculumDTO extends CommonDTO {

	private int subjectCode;

	private String subjectName;

	private String curriculumProc;

	private Date curriculumStartDate;

	private Date curriculumEndDate;

	private String teacherId;

}
