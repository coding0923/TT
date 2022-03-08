package com.tt.controller;

import java.util.List;

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

import com.tt.domain.QuestionDTO;
import com.tt.domain.TeacherDTO;
import com.tt.domain.TestListDTO;
import com.tt.service.TestService2;

@Controller
public class TestController2 {

    @Autowired
    private TestService2 testservice2;

    /* 메인 페이지 이동 */
    @GetMapping(value = "test/testMain")
    public String toMainPage(Model model) {

        List<TestListDTO> testlist = testservice2.selectBoxTestList();
        model.addAttribute("list", testlist);

        return "test/testMain";
    }

    /* 문제집 리스트 페이지 이동 */
    @GetMapping(value = "test/viewAllTestList")
    public void viewAllTestList(Model model) {
        List<TestListDTO> list = testservice2.viewAllTestList();

        model.addAttribute("list", list);
    }

    /* 문제 리스트 페이지 이동 */
    @GetMapping(value = "test/viewAllQuestion")
    public void viewAllQuestion(Model model) {
        List<QuestionDTO> list = testservice2.viewAllQuestion();

        model.addAttribute("list", list);
    }

    /* 문제 생성 페이지(팝업) */
    @GetMapping(value = "test/insertQuestion2")
    public void toInsertQuestionPage2(Model model, HttpServletRequest request) {
        // count = 데이터베이스에 생성되어있는 문제 수
        int count = testservice2.countQuestion();

        // teacherId = 로그인된 선생님의 아이디 값 세션에서 가져옴
        HttpSession session = request.getSession();
        TeacherDTO dto = (TeacherDTO) session.getAttribute("loginUser");
        String teacherId = dto.getTeacherId();

        QuestionDTO question = new QuestionDTO();

        model.addAttribute("count", count);
        model.addAttribute("question", question);
        model.addAttribute("teacherId", teacherId);
    }

    /* 문제집 생성 페이지(팝업) */
    @GetMapping(value = "test/insertTestList2")
    public void toInsertTestListPage2(Model model, HttpServletRequest request) {
        // teacherId = 로그인된 선생님의 아이디 값 세션에서 가져옴
        HttpSession session = request.getSession();
        TeacherDTO dto = (TeacherDTO) session.getAttribute("loginUser");
        String teacherId = dto.getTeacherId();

        TestListDTO testlist = new TestListDTO();

        model.addAttribute("testlist", testlist);
        model.addAttribute("teacherId", teacherId);
    }

    /* 문제 생성 및 수정 */
    @PostMapping(value = "/questionRegister")
    @ResponseBody
    public int questionRegister(@RequestBody List<QuestionDTO> test) {

        int result = 0;

        for (QuestionDTO dto : test) {
            result = testservice2.registerQuestion(dto);
        }
        return result;
    }

    /* 문제집 생성 */
    @PostMapping("/testListRegister")
    public String testListRegister(final TestListDTO params) {

        int result = testservice2.registerTestList(params);

        if (result == 0) {
            System.out.println("문제집 등록 실패");
        }
        System.out.println("문제집 등록 성공");

        return "redirect:/test/insertTestList2";
    }

    /* 문제상세 조회 */
    @GetMapping(value = "test/detailQuestion")
    public String detailQuestion(@RequestParam(value = "qid", required = false) String qid, Model model) {
        QuestionDTO question = testservice2.detailQuestion(qid);

        model.addAttribute("question", question);

        return "test/detailQuestion";
    }

}
