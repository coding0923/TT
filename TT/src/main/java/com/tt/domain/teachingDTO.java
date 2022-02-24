package com.tt.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class teachingDTO {

	private int classId;

	private String academyId;

	private String teacherId;

	private Date teachingStartDate;

	private Date teachingEndDate;

	private String teachingState;
}
