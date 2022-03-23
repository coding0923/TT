package com.tt.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.domain.MarkingTestDTO;
import com.tt.domain.QuestionDTO;
import com.tt.domain.StudentExamDTO;
import com.tt.domain.TestPaperDTO;
import com.tt.mapper.TestMapper;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testmapper;

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
    public List<TestPaperDTO> selectBoxTestPaper() {

        List<TestPaperDTO> testlist = testmapper.selectBoxTestPaper();

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

    // 문제 수정
    @Override
    public int updateQuestion(QuestionDTO params) {
        int result = 0;

        result = testmapper.updateQuestion(params);

        return result;
    }

    // 학생 시험 풀러 가기
    @Override
    public List<QuestionDTO> solveTest(String tid) {
        List<QuestionDTO> list = Collections.emptyList();

        list = testmapper.solveTest(tid);

        return list;
    }

    // 학생 답안 제출 여부 확인
    @Override
    public int checkSubmitAnswer(HashMap<String, String> ids) {
        int result = 0;

        result = testmapper.checkSubmitAnswer(ids);

        return result;
    }

    // 시험 답안 제출
    @Override
    public int insertStudentAnswer(StudentExamDTO params) {
        int result = 0;

        result = testmapper.insertStudentAnswer(params);

        return result;
    }

    // 제출한 시험지 조회
    @Override
    public List<MarkingTestDTO> viewStudentAnswer(HashMap<String, String> ids) {
        List<MarkingTestDTO> list = Collections.emptyList();

        list = testmapper.viewStudentAnswer(ids);

        return list;
    }

    // 채점 처리
    @Override
    public int updateCON(MarkingTestDTO params) {
        int result = 0;

        result = testmapper.updateCON(params);

        return result;
    }

}
