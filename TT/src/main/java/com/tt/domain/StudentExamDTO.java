package com.tt.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StudentExamDTO extends CommonDTO {
    // 문제Id
    private String questionId;
    // 수강생Id
    private String studentId;
    // 학생이 제출한 답
    private String studentAnswer;
    // 정답 여부
    private String correctOrNot;
    // 문제집Id
    private String testPaperId;
}
