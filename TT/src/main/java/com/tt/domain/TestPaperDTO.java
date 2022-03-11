package com.tt.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TestPaperDTO extends CommonDTO {

    // (임시) 문제집 이름
    private String testListName;

    // 시험지 아이디
    private String testPaperId;

    // 문제 아이디
    private String questionId;

    // 과목 코드
    private int subjectCode;

}
