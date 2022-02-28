package com.tt.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StudentTestDTO extends CommonDTO {
    // 회차
    private String testRound;
    // 문제Id
    private String testQuestionId;
    // 수강생Id
    private String studentId;
    // 학생이 제출한 답
    private String studentTestAnswer;
    // 정답 여부
    private String correctOrNot;
    // 문제집Id
    private String testListId;
}
