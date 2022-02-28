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
		TeacherDTO teachername = classService.classDetailsTeacher(dto);
		model.addAttribute("detail", classdetails);
		model.addAttribute("teacher", teachername);
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
	public void classupdate() {

	}

}
