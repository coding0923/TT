package com.tt.domain;

import java.sql.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherDTO extends CommonDTO {
    // 선생님ID
    private String teacherId;
    // 비밀번호
    private String teacherPassword;
    // 선생님이름
    private String teacherName;
    // 전공
    private String teacherMajor;
    // 주요과목
    private String teacherSubject;
    // 가입일
    private Date teacherRegistrationDate;
    // 탈퇴여부
    private String teacherDeleteYn;

}
