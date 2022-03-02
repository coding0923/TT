package com.tt.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tt.domain.ClassDTO;
import com.tt.domain.CurriculumDTO;
import com.tt.domain.StudentDTO;
import com.tt.domain.SubjectDTO;
import com.tt.domain.TeacherDTO;
import com.tt.service.ClassService;

@Controller
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/classs/classlist")
    public void list(Model model, StudentDTO sdto, TeacherDTO tdto, HttpSession session) {
        String whologin = (String) session.getAttribute("role");
        if (whologin == "teacher") {
            tdto = (TeacherDTO) session.getAttribute("loginUser"); // 세션 선생님 id 받아오기
            List<ClassDTO> list = classService.classList();
            List<ClassDTO> mylist = classService.myClassList(tdto);
            model.addAttribute("list", list);
            model.addAttribute("mylist", mylist);
        } else {
            System.out.println("student클래스 리스트 진입");
            sdto = (StudentDTO) session.getAttribute("loginUser"); // 세션 선생님 id 받아오기
            List<ClassDTO> list = classService.classList();
            model.addAttribute("list", list);
        }

    }

    // 선생님이 소속된 반이 있는지 없는지에 따라서 화면 다르게 보여줄 매핑주소
    @GetMapping("/classs/classteacherexist")
    public String Getclassteacherexist(HttpSession session) {
        TeacherDTO teacherdto = (TeacherDTO) session.getAttribute("loginUser");
        int result = classService.selectTeachinghistory(teacherdto.getTeacherId());
        if (result != 0) {
            return "redirect:/classs/classchoice";
        } else {
            return "redirect:/classs/classlist";
        }
    }

    // 학생이 소속된 반이 있는지 없는지에 따라서 화면 다르게 보여줄 매핑주소
    @GetMapping("/classs/classstudendtexist")
    public String Getclassstudendtexist(HttpSession session) {
        StudentDTO studentdto = (StudentDTO) session.getAttribute("loginUser");
        int result = classService.selectAttendee(studentdto.getStudentId());// 학생꺼 만들어야함 일단 이거안됨
        if (result != 0) {
            return "redirect:/classs/classchoice";
        } else {
            return "redirect:/classs/classlist";
        }
    }

    @GetMapping("/classs/classchoice")
    public List<Map<String, String>> Getclasschoice(Model model, HttpSession session) {
        System.out.println("Getclasschoice진입");
        String whologin = (String) session.getAttribute("role");
        // 선생님 로그인시 넘겨줄정보
        if (whologin == "teacher") {
            TeacherDTO loginUser = (TeacherDTO) session.getAttribute("loginUser");
            String userId = loginUser.getTeacherId();

            List<Map<String, String>> maplist = classService.selectClassAcademyjoin(userId);
            model.addAttribute("maplist", maplist);
            return maplist;
        }
        // 학생 로그인시 넘겨줄정보
        else {
            System.out.println("학생진입");
            StudentDTO loginUser = (StudentDTO) session.getAttribute("loginUser");
            String userId = loginUser.getStudentId();

            List<Map<String, String>> maplist = classService.selectAttendeeClassjoin(userId);
            model.addAttribute("maplist", maplist);
            return maplist;
        }

    }

    @PostMapping("/classs/classchoice")
    @ResponseBody
    public int Getclasschoice(@RequestBody Map<String, String> map, HttpSession session) {
        int result = 1;
        session.setAttribute("academyclasss", map);
        Map<String, String> map2 = (Map<String, String>) session.getAttribute("academyclasss");
        System.out.println(map2);
        return result;
    }

    @GetMapping("/classs/createclass")
    public void viewcreateclass() {

    }

    @PostMapping("/classs/createclass")
    public String createClass(ClassDTO dto, Model model, RedirectAttributes rttr) {
        int result = classService.createClass(dto);
        if (result == 1) {
            classService.insertTeachingHistory(dto);
            rttr.addFlashAttribute("result", "succ");
            return "redirect:/classs/classlist";
        } else {
            rttr.addAttribute("result", "fail");
            return "redirect:/classs/createclass";
        }
    }

    @PostMapping(value = "/classs/createcurri")
    @ResponseBody
    public int insertCurri(@RequestBody Map<String, Object> jsonList)
            throws JsonMappingException, JsonProcessingException {

        String classid = (String) jsonList.get("classId");
        classService.deleteCurri(classid);

        String jsonlist = (String) jsonList.get("jsonlist");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<CurriculumDTO> jsonClist = Arrays.asList(mapper.readValue(jsonlist, CurriculumDTO[].class));
        int result = 0;
        for (CurriculumDTO Clist : jsonClist) {
            result = classService.insertCurri(Clist);
        }
        return result;
    }

    @GetMapping("/classs/classdetails")
    public void classdetails(ClassDTO dto, Model model) {
        ClassDTO classdetails = classService.classDetails(dto);
        TeacherDTO teachername = classService.classDetailsTeacher(dto);
        List<CurriculumDTO> currilist = classService.curriList(dto);
        model.addAttribute("detail", classdetails);
        model.addAttribute("teacher", teachername);
        model.addAttribute("currlist", currilist);
    }

    @GetMapping("/classs/subjectlist")
    public void subjectList(Model model) {
        List<SubjectDTO> sublist = classService.subjectlist();
        model.addAttribute("sublist", sublist);
    }

    @PostMapping(value = "/classs/subjectsearch")
    @ResponseBody
    public List<SubjectDTO> subSearch(@RequestParam String subSearch) {
        List<SubjectDTO> sublist = classService.subjectSearch(subSearch);

        return sublist;
    }

    @PostMapping(value = "/classs/application")
    @ResponseBody
    public int application(@RequestParam(value = "id") String id, @RequestParam(value = "status") int status) {
        System.out.println(status);
        if (status == 1) {

            int result = classService.application(id);
            return result;
        } else {
            int result = classService.application2(id);
            return result;
        }

    }

    @GetMapping(value = "/classs/subjectinsert")
    public void subInsertview(SubjectDTO sub) {

    }

    @PostMapping(value = "/classs/subjectinsert")
    public void subInsert(SubjectDTO sub, Model model) {
        int result = classService.subjectInsert(sub);

        model.addAttribute("result", result);

    }

    @GetMapping("/classs/classupdate")
    public void classupdate(ClassDTO dto, Model model) {
        ClassDTO list = classService.classDetails(dto);
        List<CurriculumDTO> currilist = classService.curriList(dto);
        model.addAttribute("list", list);
        model.addAttribute("currlist", currilist);
    }

    @PostMapping("/classs/classupdate")
    public String classdetailupdate(ClassDTO dto, Model model, RedirectAttributes rttr) {
        int result = classService.classUpdate(dto);

        if (result == 1) {
            rttr.addFlashAttribute("result", "updatesucc");
            return "redirect:/classs/classlist";
        } else {
            rttr.addFlashAttribute("updateresult", "fail");
            return "class/classupdate";
        }
    }

}
