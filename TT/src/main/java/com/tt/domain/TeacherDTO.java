package com.tt.domain;

import java.sql.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherDTO {

    // 선생님 아이디
    private String teacherId;
    // 프로필 이미지
    private String teacherProfile;
    // 비밀번호
    private String teacherPassword;
    // 생년월일
    private Date teacherBirthdate;
    // 성별
    private String teacherGender;
    // 핸드폰
    private String teacherPhone;
    // 주소1
    private String teacherAddress1;
    // 주소2
    private String teacherAddress2;
    // 이름
    private String teacherName;
    // 이메일
    private String teacherEmail;
    // 탈퇴여부
    private String teacherDeleteYn;
    // 가입일
    private Date teacherRegdate;
    // 전공
    private String teacherMajor;
    // 주요과목
    private String teacherSubject;
    // 학원 아이디
    private String academyId;

}
