package com.tt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tt.domain.MarkingTestDTO;
import com.tt.domain.QuestionDTO;
import com.tt.domain.StudentTestDTO;
import com.tt.domain.TestPaperDTO;
import com.tt.domain.TestQuestionDTO;

public interface TestService {

    public int countQuestion();

    public List<TestPaperDTO> detailTestList(String tid);

    public int registerQuestion(TestQuestionDTO params);

    public int registerTestList(TestPaperDTO params);

    public TestQuestionDTO detailQuestion(String qid);

    public int deleteQuestion(String qid);

    public List<QuestionDTO> solveTest(String tid);

    public int insertStudentAnswer(StudentTestDTO params);

    public List<MarkingTestDTO> viewStudentAnswer(HashMap<String, String> ids);

    public int updateCON(MarkingTestDTO params);

    public int checkSubmitAnswer(HashMap<String, String> ids);

    public List<Map<String, String>> teacherClass(String teacher);
}
