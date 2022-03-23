package com.tt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.domain.MarkingTestDTO;
import com.tt.domain.QuestionDTO;
import com.tt.domain.StudentExamDTO;
import com.tt.domain.TeacherDTO;
import com.tt.domain.TestPaperDTO;
import com.tt.service.TestService;

@Controller
public class TestController {

    @Autowired
    private TestService testservice;

    /* 메인 페이지 이동 */
    @GetMapping(value = "test/testMain")
    public String toMainPage(Model model) {

        List<TestPaperDTO> testpaper = testservice.selectBoxTestPaper();
        model.addAttribute("list", testpaper);

        return "test/testMain";
    }

    /* 문제 리스트 페이지 이동 */
    @GetMapping(value = "test/viewAllQuestion")
    public void viewAllQuestion(Model model) {
        List<QuestionDTO> list = testservice.viewAllQuestion();

        model.addAttribute("list", list);
    }

    /* 문제집 리스트 페이지 이동 */

    @GetMapping(value = "test/viewAllTestPaper")
    public void viewAllTestPaper(Model model) {
        List<TestPaperDTO> list = testservice.viewAllTestPaper();

        if (list.size() != 0) {
            // viewAllTestPaper에서 list로 받아온 데이터 중 TestPaperId만 String으로 받아서 tid로 변환
            String tid = list.get(0).getTestPaperId().toString();

            // 변환한 tid로 questionInTestPaper 실행해서 문제 개수 구해옴(qty)
            int qty = testservice.questionInTestPaper(tid);
            model.addAttribute("qty", qty);
        }
        model.addAttribute("list", list);
    }

    /* 시험 생성 페이지(팝업) */
    @GetMapping(value = "test/insertTestPaper")
    public void toInsertExam(Model model, HttpServletRequest request) {
        List<QuestionDTO> list = testservice.viewAllQuestion();

        model.addAttribute("list", list);
        // 선생님 커리큘럼 데이터 생성
        HttpSession session = request.getSession();
        TeacherDTO dto = (TeacherDTO) session.getAttribute("loginUser");
        String teacherId = dto.getTeacherId();

        List<Map<String, String>> map = testservice.teacherCurri(teacherId);
        model.addAttribute("user", teacherId);
        model.addAttribute("map", map);
    }

    /* 문제 생성 페이지(팝업) */
    @GetMapping(value = "test/insertQuestion2")
    public void toInsertQuestionPage2(Model model, HttpServletRequest request) {
        // count = 데이터베이스에 생성되어있는 문제 수
        int count = testservice.countQuestion();

        // teacherId = 로그인된 선생님의 아이디 값 세션에서 가져옴
        HttpSession session = request.getSession();
        TeacherDTO dto = (TeacherDTO) session.getAttribute("loginUser");
        String teacherId = dto.getTeacherId();

        QuestionDTO question = new QuestionDTO();

        model.addAttribute("count", count);
        model.addAttribute("question", question);
        model.addAttribute("teacherId", teacherId);
    }

    /* 문제 생성 */
    @PostMapping(value = "/questionRegister")
    @ResponseBody
    public int questionRegister(@RequestBody List<QuestionDTO> test) {

        int result = 0;

        for (QuestionDTO dto : test) {
            result = testservice.registerQuestion(dto);
        }
        return result;
    }

    /* 문제상세 조회 */
    @GetMapping(value = "test/detailQuestion")
    public String detailQuestion(@RequestParam(value = "qid", required = false) String qid, Model model,
            HttpServletRequest request) {
        QuestionDTO question = testservice.detailQuestion(qid);
        HttpSession session = request.getSession();
        TeacherDTO dto = (TeacherDTO) session.getAttribute("loginUser");
        String teacherId = dto.getTeacherId();

        model.addAttribute("user", teacherId);
        model.addAttribute("question", question);

        return "test/detailQuestion";
    }

    /* 문제 삭제 */
    @PostMapping(value = "/deleteQuestion")
    public String deleteQuestion(@RequestParam(value = "qid", required = false) String qid) {
        int deleteResult = testservice.deleteQuestion(qid);

        if (deleteResult == 0) {
            System.out.println("문제 삭제 실패");
        }
        System.out.println("문제 삭제 성공");

        return "redirect:/test/viewAllQuestion";
    }

    /* 문제 수정 */
    @PostMapping(value = "/updateQuestion")
    public String updateQuestion(QuestionDTO dto) {
        System.out.println(dto);
        int updateResult = testservice.updateQuestion(dto);

        if (updateResult == 0) {
            System.out.println("문제 수정 실패");
        }
        System.out.println("문제 수정 성공");
        return "redirect:/test/viewAllQuestion";
    }

    /* 학생 이미 제출한 테스트 페이지 이동 */
    @GetMapping(value = "test/notice")
    public String noticePage() {

        return "test/notice";
    }

    /* 시험지 생성 */
    @PostMapping(value = "/registerTestPaper")
    @ResponseBody
    public int registerTestPaper(@RequestBody List<TestPaperDTO> list) {
        System.out.println(list);
        int result = 0;
        for (TestPaperDTO dto : list) {
            result = testservice.insertTestPaper(dto);
        }
        return result;
    }

    /* 문제집 문제풀러가기 */
    @PostMapping(value = "test/solveTest")
    public String viewTest(Model model, String testPaperId, String studentId, HashMap<String, String> ids) {

        ids.put("testPaperId", testPaperId);
        ids.put("studentId", studentId);
        System.out.println(ids);
        int chk = testservice.checkSubmitAnswer(ids);
        if (chk == 0) {
            StudentExamDTO student = new StudentExamDTO();
            List<QuestionDTO> list = testservice.solveTest(testPaperId);
            System.out.println(list);
            model.addAttribute("questionList", list);
            model.addAttribute("student", student);

            return "test/solveTest";
        } else {

            return "test/notice";
        }
    }

    /* 학생 답안 등록 */
    @PostMapping(value = "/studentAnswer")
    @ResponseBody
    public int answerRegister(@RequestBody List<StudentExamDTO> test) {
        int result = 0;
        for (StudentExamDTO dto : test) {
            result = testservice.insertStudentAnswer(dto);
        }
        return result;
    }

    /* 채점창 이동 */
    @PostMapping(value = "test/markTest")
    public String toMarkAnswer(Model model, String testPaperId, String studentId, HashMap<String, String> ids,
            HttpServletRequest request) {
        ids.put("tid", testPaperId);
        ids.put("sid", studentId);

        HttpSession session = request.getSession();
        TeacherDTO dto = (TeacherDTO) session.getAttribute("loginUser");
        String teacher = dto.getTeacherId();

        List<MarkingTestDTO> list = testservice.viewStudentAnswer(ids);
        model.addAttribute("teacher", teacher);
        model.addAttribute("answerList", list);

        return "test/markTest";
    }

    /* 채점 전송 */
    @PostMapping(value = "/checkCON")
    @ResponseBody
    public int correctOrNot(@RequestBody List<MarkingTestDTO> list) {
        int result = 0;
        for (MarkingTestDTO dto : list) {
            result = testservice.updateCON(dto);
        }
        return result;
    }

}
