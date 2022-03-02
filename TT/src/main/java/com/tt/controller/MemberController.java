package com.tt.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.domain.StudentDTO;
import com.tt.domain.TeacherDTO;
import com.tt.service.StudentService;
import com.tt.service.TeacherService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/teacherSignup")
    public void openTeacherSignup(Model model) {
        model.addAttribute("teacher", new TeacherDTO());

    }

    @GetMapping("/studentSignup")
    public void openStudentSignup(Model model) {
        model.addAttribute("student", new StudentDTO());
    }

    @PostMapping("/teacher/register")
    public String teacherRegister(TeacherDTO teacherDTO) {

        System.out.println(teacherDTO.getTeacherGender());
        if (null == teacherDTO.getTeacherGender()) {
            teacherDTO.setTeacherGender("남자");
            System.out.println(teacherDTO.getTeacherGender());
        }
        System.out.println(teacherDTO);

        boolean isRegistered = teacherService.registerTeacher(teacherDTO);
        if (isRegistered == false) {
            log.info("등록실패");
        }
        log.info("등록성공");
        return "redirect:/member/login";
    }

    @PostMapping("/student/register")
    public String studentRegister(StudentDTO studentDTO) {

        boolean isRegistered = studentService.registerStudent(studentDTO);
        if (isRegistered == false) {
            log.info("등록실패");
        }
        log.info("등록성공");
        return "redirect:/member/login";
    }

    @GetMapping("/mypage")
    public void mypage() {
    }

    @GetMapping("/login")
    public void login(Model model) {
        model.addAttribute("teacher", new TeacherDTO());
        model.addAttribute("student", new StudentDTO());
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/member/login";
    }

    @PostMapping("/teacher/login")
    public String teacherLogin(TeacherDTO teacherDTO, HttpServletRequest request) {

        boolean isLoggedin = teacherService.loginTeacher(teacherDTO);
        if (isLoggedin == false) {
        }

        teacherDTO = teacherService.getTeacherDetail(teacherDTO.getTeacherId());

        HttpSession session = request.getSession();
        Date now = new Date();
        session.setAttribute("loginUser", teacherDTO);

        session.setAttribute("role", "teacher");
        session.setAttribute("now", now.getTime());

        return "redirect:/classs/classteacherexist";
    }

    @PostMapping("/student/login")
    public String studentLogin(StudentDTO studentDTO, HttpServletRequest request) {

        boolean isLoggedin = studentService.loginStudent(studentDTO);
        if (isLoggedin == false) {
            log.info("로그인실패");
        }

        log.info("로그인성공");

        studentDTO = studentService.getStudentDetail(studentDTO.getStudentId());

        HttpSession session = request.getSession();
        Date now = new Date();

        session.setAttribute("loginUser", studentDTO);

        session.setAttribute("role", "student");
        session.setAttribute("now", now.getTime());

        log.info("" + session.getAttribute("now"));

        return "redirect:/classs/classstudendtexist";
    }

    @PostMapping("/idCheck")
    @ResponseBody
    public boolean idCheck(@RequestParam("teacherId") String teacherId) {

        boolean result = teacherService.checkId(teacherId);
        return result;
    }

}
