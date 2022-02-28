package com.tt.service;

import java.util.HashMap;
import java.util.List;

import com.tt.domain.MarkingTestDTO;
import com.tt.domain.StudentTestDTO;
import com.tt.domain.TestListDTO;
import com.tt.domain.TestQuestionDTO;

public interface TestService {

    public int countQuestion();

    public List<TestListDTO> detailTestList(String tid);

    public int registerQuestion(TestQuestionDTO params);

    public int registerTestList(TestListDTO params);

    public TestQuestionDTO detailQuestion(String qid);

    public int deleteQuestion(String qid);

    public List<TestQuestionDTO> solveTest(String tid);

    public int insertStudentAnswer(StudentTestDTO params);

    public List<MarkingTestDTO> viewStudentAnswer(HashMap<String, String> ids);

    public int updateCON(MarkingTestDTO params);
}
