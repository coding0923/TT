package com.tt.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class commonDTO {
    // 작성일
    private Date insertDate;
    // 수정일
    private Date updateDate;
    // 작성자
    private String insertUser;
    // 수정자
    private String updateUser;
    // 반ID
    private String classId;
    // 학원ID
    private String academyId;
}
