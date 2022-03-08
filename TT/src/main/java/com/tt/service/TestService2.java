package com.tt.service;

import java.util.List;

import com.tt.domain.QuestionDTO;
import com.tt.domain.TestListDTO;

public interface TestService2 {

    public int countQuestion();

    public int registerQuestion(QuestionDTO params);

    public int registerTestList(TestListDTO params);

    public List<QuestionDTO> viewAllQuestion();

    public List<TestListDTO> viewAllTestList();

    public QuestionDTO detailQuestion(String qid);

    public List<TestListDTO> selectBoxTestList();

}
