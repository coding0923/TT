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
import com.tt.domain.StudentTestDTO;
import com.tt.domain.TeacherDTO;
import com.tt.domain.TestPaperDTO;
import com.tt.domain.TestQuestionDTO;
import com.tt.service.TestService;

@Controller
public class TestController {

    @Autowired
    private TestService testservice;

    /* 문제 생성 및 수정 페이지 이동 */
    @GetMapping(value = "test/insertQuestion")
    public String openInsertQuestionPage(@RequestParam(value = "qid", required = false) String qid, Model model,
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        TeacherDTO dto = (TeacherDTO) session.getAttribute("loginUser");
        String teacher = dto.getTeacherId();

        List<Map<String, String>> maplist = testservice.teacherClass(teacher);
        model.addAttribute("map", maplist);

        // qid 넘어온게 없는 경우 신규 문제 생성
        if (qid == null) {

            // count = 데이터베이스에 생성되어있는 문제 수
            int count = testservice.countQuestion();

            TestQuestionDTO question = new TestQuestionDTO();

            model.addAttribute("count", count);
            model.addAttribute("question", question);
        } else {

            // qid가 넘어온 경우 수정 처리
            TestQuestionDTO question = testservice.detailQuestion(qid);
            model.addAttribute("question", question);
        }

        return "test/insertQuestion";
    }

    /* 문제집 생성 페이지 이동 */
    @GetMapping(value = "test/insertTestList")
    public String toInsertTestListPage(Model model, String teacher, HttpServletRequest request) {
        HttpSession session = request.getSession();
        TeacherDTO dto = (TeacherDTO) session.getAttribute("loginUser");
        teacher = dto.getTeacherId();

        List<Map<String, String>> maplist = testservice.teacherClass(teacher);
        TestPaperDTO testlist = new TestPaperDTO();

        model.addAttribute("testlist", testlist);
        model.addAttribute("map", maplist);

        return "test/insertTestList";
    }

    /* 문제집 조회 */
    @PostMapping(value = "test/detailTestList")
    public String selectAllQuestion(Model model, String testListId) {

        List<TestPaperDTO> list = testservice.detailTestList(testListId);

        model.addAttribute("detailTestList", list);

        return "test/detailTestList";
    }

    /* 문제집 문제풀러가기 */
    @PostMapping(value = "test/solveTest")
    public String viewTest(Model model, String testListName, String studentId, HashMap<String, String> ids) {

        ids.put("testListName", testListName);
        ids.put("studentId", studentId);
        int chk = testservice.checkSubmitAnswer(ids);
        if (chk == 0) {
            StudentTestDTO student = new StudentTestDTO();
            List<QuestionDTO> list = testservice.solveTest(testListName);
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
    public int answerRegister(@RequestBody List<StudentTestDTO> test) {
        int result = 0;
        for (StudentTestDTO dto : test) {
            result = testservice.insertStudentAnswer(dto);
        }
        return result;
    }

    /* 채점창 이동 */
    @PostMapping(value = "test/markTest")
    public String toMarkAnswer(Model model, String testListId, String studentId, HashMap<String, String> ids) {
        ids.put("tid", testListId);
        ids.put("sid", studentId);
        List<MarkingTestDTO> list = testservice.viewStudentAnswer(ids);
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
