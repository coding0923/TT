package com.tt.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StudentTestDTO extends CommonDTO {
    // 회차
    public String testRound;
    // 문제Id
    public String testQuestionId;
    // 수강생Id
    public String studentId;
    // 학생이 제출한 답
    public String studentTestAnswer;
    // 정답 여부
    public String correctOrNot;
}
