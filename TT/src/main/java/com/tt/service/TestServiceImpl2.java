package com.tt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.domain.QuestionDTO;
import com.tt.domain.TestPaperDTO;
import com.tt.mapper.TestMapper2;

@Service
public class TestServiceImpl2 implements TestService2 {

    @Autowired
    private TestMapper2 testmapper;

    // 문제 등록
    @Override
    public int registerQuestion(QuestionDTO params) {
        int result = 0;

        result = testmapper.registerQuestion(params);

        return result;
    }

    // 문제 개수 체크
    @Override
    public int countQuestion() {
        int result = 0;

        result = testmapper.countQuestion();

        return result;
    }

    // 문제 전체 출력
    @Override
    public List<QuestionDTO> viewAllQuestion() {
        List<QuestionDTO> list = testmapper.viewAllQuestion();

        return list;
    }

    // 문제 상세 조회
    @Override
    public QuestionDTO detailQuestion(String qid) {

        return testmapper.detailQuestion(qid);

    }

    // 문제집 selectBox
    @Override
    public List<TestPaperDTO> selectBoxTestList() {

        List<TestPaperDTO> testlist = testmapper.selectBoxTestList();

        return testlist;
    }

    // 선생님 커리큘럼 데이터
    @Override
    public List<Map<String, String>> teacherCurri(String teacherId) {

        List<Map<String, String>> map = testmapper.teacherCurri(teacherId);

        return map;
    }

    // 시험지 생성
    @Override
    public int insertTestPaper(TestPaperDTO params) {
        int result = 0;

        result = testmapper.insertTestPaper(params);

        return result;
    }

    // 시험지 리스트 전체 조회
    @Override
    public List<TestPaperDTO> viewAllTestPaper() {
        List<TestPaperDTO> list = testmapper.viewAllTestPaper();

        return list;
    }

    // 문제 삭제
    @Override
    public int deleteQuestion(String qid) {
        int result = 0;

        QuestionDTO question = testmapper.detailQuestion(qid);

        if (question != null) {
            result = testmapper.deleteQuestion(qid);
        }

        return result;
    }

    // 시험 안의 문제 수
    @Override
    public int questionInTestPaper(String tid) {
        int result = 0;

        result = testmapper.questionInTestPaper(tid);

        return result;
    }

}
