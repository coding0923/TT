package com.tt.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TestPaperDTO extends CommonDTO {

    // 시험지 아이디
    private String testPaperId;

    // 문제 아이디
    private String questionId;

    // 문제 점수
    private int questionScore;

    // 문제 내용
    private String questionContent;

    // 문제 이미지
    private String questionImage;

    // 문제 답안
    private String questionAnswer;

    // 과목 코드
    private int subjectCode;

}
