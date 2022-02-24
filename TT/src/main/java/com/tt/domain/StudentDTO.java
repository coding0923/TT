package com.tt.domain;

import java.sql.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StudentDTO extends CommonDTO {

    // 학생 아이디
    private String studentId;
    // 프로필 이미지
    private String studentProfile;
    // 비밀번호
    private String studentPassword;
    // 생년월일
    private Date studentBirthdate;
    // 성별
    private String studentGender;
    // 핸드폰
    private String studentPhone;
    // 주소1
    private String studentAddress1;
    // 주소2
    private String studentAddress2;
    // 이름
    private String studentName;
    // 이메일
    private String studentEmail;
    // 탈퇴여부
    private String studentDeleteYn;
    // 가입일
    private Date studentRegdate;
}
