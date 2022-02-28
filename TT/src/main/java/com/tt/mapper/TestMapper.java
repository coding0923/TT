package com.tt.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tt.domain.MarkingTestDTO;
import com.tt.domain.StudentTestDTO;
import com.tt.domain.TestListDTO;
import com.tt.domain.TestQuestionDTO;

@Mapper
public interface TestMapper {

    // 데이터 베이스에 등록되어있는 문제 수 조회
    public int countQuestion();

    // 문제집 상세 내용 조회
    public List<TestListDTO> detailTestList(String tid);

    // 문제 등록
    public int registerQuestion(TestQuestionDTO params);

    // 문제집 등록
    public int registerTestList(TestListDTO params);

    // 문제 상세 내용 조회
    public TestQuestionDTO detailQuestion(String qid);

    // 문제 삭제
    public int deleteQuestion(String qid);

    // 문제 수정
    public int updateQuestion(TestQuestionDTO params);

    // 학생 시험 풀러 가기
    public List<TestQuestionDTO> solveTest(String tid);

    // 학생 답안 등록
    public int insertStudentAnswer(StudentTestDTO params);

    // 채점 내용 조회
    public List<MarkingTestDTO> viewStudentAnswer(HashMap<String, String> ids);

    // 채점 처리
    public int updateCON(MarkingTestDTO params);

}
