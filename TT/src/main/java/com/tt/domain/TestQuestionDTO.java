package com.tt.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TestQuestionDTO extends CommonDTO {

    // 문제 ID
    private String testQuestionId;
    // 문제집 ID
    private String testListId;
    // 배점
    private int testQuestionScore;
    // 문제 내용
    private String testQuestionContent;
    // 문제의 답
    private String testQuestionAnswer;
    // 이미지
    private String testQuestionImage;
}
