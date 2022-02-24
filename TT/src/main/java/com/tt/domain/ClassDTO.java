package com.tt.domain;

import java.sql.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClassDTO extends CommonDTO {
    private String className;
    private Date classStartDate;
    private Date classEndDate;
}
