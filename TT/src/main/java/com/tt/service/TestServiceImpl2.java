package com.tt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.domain.QuestionDTO;
import com.tt.domain.TestListDTO;
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

    // 문제집 등록
    @Override
    public int registerTestList(TestListDTO params) {
        int result = 0;

        result = testmapper.registerTestList(params);

        return result;
    }

    @Override
    public List<QuestionDTO> viewAllQuestion() {
        List<QuestionDTO> list = testmapper.viewAllQuestion();

        return list;
    }

    @Override
    public List<TestListDTO> viewAllTestList() {

        List<TestListDTO> list = testmapper.viewAllTestList();

        return list;
    }

    @Override
    public QuestionDTO detailQuestion(String qid) {

        return testmapper.detailQuestion(qid);

    }

    @Override
    public List<TestListDTO> selectBoxTestList() {

        List<TestListDTO> testlist = testmapper.selectBoxTestList();

        return testlist;
    }

}
