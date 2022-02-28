package com.tt.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.domain.MarkingTestDTO;
import com.tt.domain.StudentTestDTO;
import com.tt.domain.TestListDTO;
import com.tt.domain.TestQuestionDTO;
import com.tt.mapper.TestMapper;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testmapper;

    // 문제 등록
    @Override
    public int registerQuestion(TestQuestionDTO params) {
        int result = 0;

        // params.getTestQuestionId가 null이면 등록
        if (params.getTestQuestionId() == null) {
            result = testmapper.registerQuestion(params);
        } else {
            // qid = null이 아니면 수정
            result = testmapper.updateQuestion(params);
        }

        return result;
    }

    // 문제 개수 체크
    @Override
    public int countQuestion() {
        int result = 0;

        result = testmapper.countQuestion();

        return result;
    }

    // 문제집 등록
    @Override
    public int registerTestList(TestListDTO params) {
        int result = 0;

        result = testmapper.registerTestList(params);

        return result;
    }

    // 문제집 조회
    @Override
    public List<TestListDTO> detailTestList(String tid) {

        List<TestListDTO> testlist = Collections.emptyList();

        testlist = testmapper.detailTestList(tid);

        return testlist;
    }

    // 문제 상세 조회
    @Override
    public TestQuestionDTO detailQuestion(String qid) {
        return testmapper.detailQuestion(qid);
    }

    // 문제 삭제
    @Override
    public int deleteQuestion(String qid) {
        int result = 0;

        TestQuestionDTO question = testmapper.detailQuestion(qid);

        if (question != null) {
            result = testmapper.deleteQuestion(qid);
        }

        return result;
    }

    // 문제 풀러 가기
    @Override
    public List<TestQuestionDTO> solveTest(String tid) {
        List<TestQuestionDTO> list = Collections.emptyList();

        list = testmapper.solveTest(tid);

        return list;
    }

    // 학생 답안 등록
    @Override
    public int insertStudentAnswer(StudentTestDTO params) {
        int result = 0;

        result = testmapper.insertStudentAnswer(params);

        return result;
    }

    // 채점 내용 조회
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
