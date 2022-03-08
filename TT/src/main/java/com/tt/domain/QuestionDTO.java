package com.tt.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionDTO extends CommonDTO {

    // 문제 ID
    private String questionId;
    // 배점
    private int questionScore;
    // 문제 내용
    private String questionContent;
    // 문제의 답
    private String questionAnswer;
    // 이미지
    private String questionImage;
}
