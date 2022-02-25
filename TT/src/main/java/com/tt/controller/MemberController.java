package com.tt.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tt.domain.StudentDTO;
import com.tt.domain.TeacherDTO;
import com.tt.service.StudentService;
import com.tt.service.TeacherService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @GetMapping("teacherSignup")
    public String openTeacherSignup(Model model) {
        model.addAttribute("teacher", new TeacherDTO());

        return "teacherSignup";
    }

    @GetMapping("/studentSignup")
    public String openStudentSignup(Model model) {
        model.addAttribute("student", new StudentDTO());
        return "studentSignup";
    }

    @PostMapping("/teacher/register")
    public String teacherRegister(TeacherDTO teacherDTO) {

        boolean isRegistered = teacherService.registerTeacher(teacherDTO);
        if (isRegistered == false) {
            log.info("등록실패");
        }
        log.info("등록성공");
        return "redirect:/login";
    }

    @PostMapping("/student/register")
    public String studentRegister(StudentDTO studentDTO) {

        boolean isRegistered = studentService.registerStudent(studentDTO);
        if (isRegistered == false) {
            log.info("등록실패");
        }
        log.info("등록성공");
        return "redirect:/login";
    }

    @GetMapping("/mypage")
    public String mypage() {

        return "mypage";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("teacher", new TeacherDTO());
        model.addAttribute("student", new StudentDTO());

        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "login";
    }

    @PostMapping("/teacher")
    public String teacherLogin(TeacherDTO teacherDTO, HttpServletRequest request) {

        log.info(teacherDTO.getTeacherId());
        log.info(teacherDTO.getTeacherPassword());
        boolean isLoggedin = teacherService.loginTeacher(teacherDTO);
        if (isLoggedin == false) {
            log.info("로그인실패");
        }

        log.info("로그인성공");

        teacherDTO = teacherService.getTeacherDetail(teacherDTO.getTeacherId());

        HttpSession session = request.getSession();
        // LocalDate today = LocalDate.now();
        Date now = new Date();

        session.setAttribute("loginUser", teacherDTO);

//        session.setAttribute("teacherId", teacherDTO.getTeacherId());
//        session.setAttribute("loginUser", teacherDTO.getTeacherName());
        session.setAttribute("role", "teacher");
//        session.setAttribute("today", today.toString());
        session.setAttribute("now", now.getTime());
//
//        log.info((String) session.getAttribute("teacherId"));
//        log.info("" + session.getAttribute("role"));
//        log.info("" + session.getAttribute("today"));
        log.info("" + session.getAttribute("now"));
//        log.info(teacherDTO.getTeacherName());

        return "mypage";
    }

    @PostMapping("/student")
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

        return "mypage";
    }

}
