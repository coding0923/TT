package com.tt.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class CommonDTO {
    // 작성일
    public Date insertDate;
    // 수정일
    public Date updateDate;
    // 작성자
    public String insertUser;
    // 수정자
    public String updateUser;
    // 반ID
    public String classId;
    // 학원ID
    public String academyId;
}
