package com.tt.service;

import java.util.List;
import java.util.Map;

import com.tt.domain.QuestionDTO;
import com.tt.domain.TestPaperDTO;

public interface TestService2 {

    // 문제수 확인
    public int countQuestion();

    // 문제 등록
    public int registerQuestion(QuestionDTO params);

    // 문제 목록 출력
    public List<QuestionDTO> viewAllQuestion();

    // 문제 상세 조회
    public QuestionDTO detailQuestion(String qid);

    // 시험지 selectBox 출력
    public List<TestPaperDTO> selectBoxTestList();

    // 커리큘럼 데이터 조회
    public List<Map<String, String>> teacherCurri(String teacherId);

}
