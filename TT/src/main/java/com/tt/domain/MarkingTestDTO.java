package com.tt.domain;

import lombok.Data;

@Data
public class MarkingTestDTO {
    // 문제 아이디
    private String questionId;
    // 문제 내용
    private String questionContent;
    // 문제 답
    private String questionAnswer;
    // 학생이 입력한 답
    private String studentAnswer;
    // 학생 아이디
    private String studentId;
    // 시험지 ID
    private String testPaperId;
    // 선생님 ID
    private String teacherId;
}
