package com.tt.controller;

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

import com.tt.domain.ClassDTO;
import com.tt.domain.CurriculumDTO;
import com.tt.domain.SubjectDTO;
import com.tt.domain.TeacherDTO;
import com.tt.service.ClassService;

@Controller
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/classs/classlist")
    public void list(Model model, TeacherDTO dto, HttpSession session) {
        dto = (TeacherDTO) session.getAttribute("loginUser"); // 세션 선생님 id 받아오기
        List<ClassDTO> list = classService.classList();
        List<ClassDTO> mylist = classService.myClassList(dto);
        model.addAttribute("list", list);
        model.addAttribute("mylist", mylist);

    }

    @GetMapping("/classs/classchoice")
    public List<Map<String, String>> Getclasschoice(Model model, HttpSession session) {

        TeacherDTO loginUser = (TeacherDTO) session.getAttribute("loginUser");
        String userId = loginUser.getTeacherId();
        System.out.println("세션 티쳐아이디" + userId);

        List<Map<String, String>> maplist = classService.selectClassAcademyjoin(userId);
        System.out.println(maplist);
        model.addAttribute("maplist", maplist);
        return maplist;
    }

    @GetMapping("/classs/createclass")
    public void viewcreateclass() {

    }

    @PostMapping("/classs/createclass")
    public String createClass(ClassDTO dto, Model model, RedirectAttributes rttr) {
        int result = classService.createClass(dto);
        if (result == 1) {
            classService.insertTeachingHistory(dto);
            rttr.addFlashAttribute("result", result);
            return "redirect:/classs/classlist";
        } else {
            model.addAttribute("result", result);
            return "/classs/createclass";
        }
    }

    @PostMapping(value = "/classs/createcurri")
    @ResponseBody
    public int insertCurri(@RequestBody List<CurriculumDTO> jsonClist) {
        System.out.println("진입?");
        int result = 0;
        for (CurriculumDTO Clist : jsonClist) {
            result = classService.insertCurri(Clist);
        }
        return result;
    }

    @GetMapping("/classs/classdetails")
    public void classdetails(ClassDTO dto, Model model) {
        ClassDTO classdetails = classService.classDetails(dto);
        model.addAttribute("detail", classdetails);
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

    @GetMapping(value = "/classs/subjectinsert")
    public void subInsertview(SubjectDTO sub) {

    }

    @PostMapping(value = "/classs/subjectinsert")
    public void subInsert(SubjectDTO sub, Model model) {
        int result = classService.subjectInsert(sub);

        model.addAttribute("result", result);

    }

}
