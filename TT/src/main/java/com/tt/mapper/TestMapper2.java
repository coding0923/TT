package com.tt.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tt.domain.QuestionDTO;
import com.tt.domain.TestPaperDTO;

@Mapper
public interface TestMapper2 {

    // 데이터 베이스에 등록되어있는 문제 수 조회
    public int countQuestion();

    // 시험에 등록된 문제수
    public int questionInTestPaper(String tid);

    // 문제 등록
    public int registerQuestion(QuestionDTO params);

    // 문제 리스트 전체 조회
    public List<QuestionDTO> viewAllQuestion();

    // 문제 상세 내용 조회
    public QuestionDTO detailQuestion(String qid);

    // 문제 삭제
    public int deleteQuestion(String qid);

    // 문제 수정
    public int updateQuestion(QuestionDTO params);

    // 커리큘럼 데이터 조회
    public List<Map<String, String>> teacherCurri(String teacherId);

    // 문제집 리스트 전체 selectbox 출력
    public List<TestPaperDTO> selectBoxTestPaper();

    // 시험지 생성
    public int insertTestPaper(TestPaperDTO params);

    // 시험지 리스트 전체 조회
    public List<TestPaperDTO> viewAllTestPaper();

    // 학생 시험 풀러 가기
    public List<QuestionDTO> solveTest(String tid);

    // 제출 여부 확인
    public int checkSubmitAnswer(HashMap<String, String> ids);

    // 문제집 등록
    // public int registerTestList(TestListDTO params);
    // 문제집 상세 내용 조회
    // public TestListDTO detailTestList(String tid);
    /*
     * // 문제집 상세 내용 조회 public List<TestListDTO> detailTestList(String tid);
     * 
     * 
     * 
     * // 학생 답안 등록 public int insertStudentAnswer(StudentTestDTO params);
     * 
     * // 채점 내용 조회 public List<MarkingTestDTO> viewStudentAnswer(HashMap<String,
     * String> ids);
     * 
     * // 채점 처리 public int updateCON(MarkingTestDTO params);
     * 
     * 
     * 
     * // 반 목록 조회 public List<Map<String, String>> teacherClass(String teacher);
     */

}
