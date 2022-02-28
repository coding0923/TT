package com.tt.domain;

import lombok.Data;

@Data
public class MarkingTestDTO {
    // 문제 아이디
    private String testQuestionId;
    // 문제 내용
    private String testQuestionContent;
    // 문제 답
    private String testQuestionAnswer;
    // 학생이 입력한 답
    private String studentTestAnswer;
    // 학생 아이디
    private String studentId;
}
