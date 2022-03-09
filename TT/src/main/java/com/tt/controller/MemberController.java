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
import com.tt.service.EmailService;
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
    @Autowired
    private EmailService emailService;

    /* teacher */
    @GetMapping("/teacherRegister")
    public void teacherRegister() {
    }

    @GetMapping("/teacherForgotId")
    public void teacherForgotId() {
    }

    @GetMapping("/teacherForgotPw")
    public void teacherForgotPw() {
    }

    @GetMapping("/studentSignup")
    public void openStudentSignup(Model model) {
        model.addAttribute("student", new StudentDTO());
    }

    @PostMapping("/teacher/register")
    public String teacherRegister(TeacherDTO teacherDTO) {

        if (null == teacherDTO.getTeacherGender()) {
            teacherDTO.setTeacherGender("남자");
        }

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

    @PostMapping("/teacher/loginSuccess")
    public String LoginSuccess(TeacherDTO teacherDTO, HttpServletRequest request) {

        teacherDTO = teacherService.getTeacherDetail(teacherDTO.getTeacherId());

        HttpSession session = request.getSession();
        Date now = new Date();
        session.setAttribute("loginUser", teacherDTO);

        session.setAttribute("role", "teacher");
        session.setAttribute("now", now.getTime());

        return "redirect:/classs/classteacherexist";
    }

    @PostMapping("/teacher/loginProc")
    @ResponseBody
    public String teacherLogin(String teacherId, String teacherPassword, HttpServletRequest request) {

        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setTeacherId(teacherId);
        teacherDTO.setTeacherPassword(teacherPassword);
        boolean isLoggedin = teacherService.loginTeacher(teacherDTO);

        if (isLoggedin == true) {
            return "success";
        }
        return "fail";
    }

    @PostMapping("/student/login")
    public String studentLogin(StudentDTO studentDTO, HttpServletRequest request) {

        boolean isLoggedin = studentService.loginStudent(studentDTO);
        if (isLoggedin == false) {
            log.info("학생 로그인실패");
            return "redirect:/member/login";
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

    @PostMapping("/teacher/idCheck")
    @ResponseBody
    public boolean idCheckTeacher(@RequestParam("teacherId") String teacherId) {

        boolean result = teacherService.checkTeacherId(teacherId);
        return result;
    }

    @PostMapping("/teacher/emailCheck")
    @ResponseBody
    public boolean emailCheckTeacher(@RequestParam("teacherEmail") String teacherEmail) {

        boolean result = teacherService.checkTeacherEmail(teacherEmail);
        return result;
    }

    @PostMapping("/findTeacherId")
    @ResponseBody
    public String findTeacherId(@RequestParam("name") String teacherName, @RequestParam("email") String teacherEmail) {

        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setTeacherName(teacherName);
        teacherDTO.setTeacherEmail(teacherEmail);

        String teacherId = teacherService.findTeacherId(teacherDTO);

        return teacherId;
    }

    @PostMapping("/findTeacherPw")
    @ResponseBody
    public boolean findTeacherPw(@RequestParam("username") String teacherId, @RequestParam("name") String teacherName,
            @RequestParam("email") String teacherEmail) throws Exception {

        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setTeacherId(teacherId);
        teacherDTO.setTeacherName(teacherName);
        teacherDTO.setTeacherEmail(teacherEmail);

        boolean result = teacherService.findTeacherPw(teacherDTO);

        if (result == false) {
            return result;
        } else {
            String tempPw = emailService.sendFindPwMessage(teacherEmail);
            teacherDTO.setTeacherPassword(tempPw);

            boolean outcome = teacherService.setNewTeacherPw(teacherDTO);
            if (outcome) {
                return true;
            }
            return result;
        }
    }

    /* student */
    @GetMapping("/studentRegister")
    public void studentRegister() {
    }

    @GetMapping("/studentForgotId")
    public void studentForgotId() {
    }

    @GetMapping("/studentForgotPw")
    public void studentForgotPw() {
    }

    @PostMapping("/student/loginSuccess")
    public String LoginSuccess(StudentDTO studentDTO, HttpServletRequest request) {

        studentDTO = studentService.getStudentDetail(studentDTO.getStudentId());

        HttpSession session = request.getSession();
        Date now = new Date();
        session.setAttribute("loginUser", studentDTO);

        session.setAttribute("role", "student");
        session.setAttribute("now", now.getTime());

        return "redirect:/classs/classstudendtexist";
    }

    @PostMapping("/student/loginProc")
    @ResponseBody
    public String studentLogin(String studentId, String studentPassword, HttpServletRequest request) {

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(studentId);
        studentDTO.setStudentPassword(studentPassword);
        boolean isLoggedin = studentService.loginStudent(studentDTO);

        if (isLoggedin == true) {
            return "success";
        }
        return "fail";
    }

    @PostMapping("/student/idCheck")
    @ResponseBody
    public boolean idCheckStudent(@RequestParam("studentId") String studentId) {

        boolean result = studentService.checkStudentId(studentId);
        return result;
    }

    @PostMapping("/student/emailCheck")
    @ResponseBody
    public boolean emailCheckStudent(@RequestParam("studentEmail") String studentEmail) {

        boolean result = studentService.checkStudentEmail(studentEmail);
        return result;
    }

    @PostMapping("/findStudentId")
    @ResponseBody
    public String findStudentId(@RequestParam("name") String studentName, @RequestParam("email") String studentEmail) {

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentName(studentName);
        studentDTO.setStudentEmail(studentEmail);

        String studentId = studentService.findStudentId(studentDTO);

        return studentId;
    }

    @PostMapping("/findStudentPw")
    @ResponseBody
    public boolean findStudentPw(@RequestParam("username") String studentId, @RequestParam("name") String studentName,
            @RequestParam("email") String studentEmail) throws Exception {

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(studentId);
        studentDTO.setStudentName(studentName);
        studentDTO.setStudentEmail(studentEmail);

        boolean result = studentService.findStudentPw(studentDTO);

        if (result == false) {
            return result;
        } else {
            String tempPw = emailService.sendFindPwMessage(studentEmail);
            studentDTO.setStudentPassword(tempPw);

            boolean outcome = studentService.setNewStudentPw(studentDTO);
            if (outcome) {
                return true;
            }
            return result;
        }
    }
}
