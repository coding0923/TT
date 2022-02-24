package com.tt.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ClassDTO {
    private String className;
    private Date classStartDate;
    private Date classEndDate;
}
