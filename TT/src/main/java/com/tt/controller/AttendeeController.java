package com.tt.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.domain.AttendeeDTO;
import com.tt.domain.CounsellingDTO;
import com.tt.domain.TeacherDTO;
import com.tt.service.AttendeeService;
import com.tt.service.ClassService;

@Controller
public class AttendeeController {
    @Autowired
    AttendeeService attendeeservice;
    @Autowired
    ClassService classService;

    @GetMapping("/attendee/attendeelist")
    public String GetAttendeeList(Model model, HttpSession session) {
        Map<String, String> academyclass = (Map) session.getAttribute("academyclasss");
        List<Map<String, String>> AttendeeList = attendeeservice.AttendeeList(academyclass);
        System.out.println("리스트" + AttendeeList);
        model.addAttribute("AttendeeList", AttendeeList);
        return "attendee/attendeelist";
    }

    @PostMapping("/attendee/attendeeput")
    @ResponseBody
    public int PostAttendeeput(@RequestParam(value = "userIds[]", required = false) AttendeeDTO attendeedto,
            HttpSession session) {
        System.out.println("AttendeeputPost진입");
        int result = 0;
        attendeeservice.Attendeeput(attendeedto);

        return result;
    }

    @PostMapping("/attendee/attendeecall")
    @ResponseBody
    public int PostAttendeeCall(@RequestParam(value = "userIds[]", required = false) List<String> checkArray,
            HttpSession session) {
        System.out.println("AttendeeCallPost진입");
        int result = 0;
        Map<String, String> academyclass = (Map) session.getAttribute("academyclasss");
        TeacherDTO teacherdto = (TeacherDTO) session.getAttribute("loginUser");
        for (String id : checkArray) {
            AttendeeDTO attendeedto = new AttendeeDTO();
            attendeedto.setUpdateUser(teacherdto.getTeacherId());
            attendeedto.setParticipateStatus("수강중");
            attendeedto.setAcademyId(academyclass.get("academyId"));
            attendeedto.setClassId(academyclass.get("classId"));
            attendeedto.setStudentId(id);
            result = attendeeservice.AttendeeCall(attendeedto);
            System.out.println("수락시 값" + attendeedto);
        }
        return result;
    }

    @PostMapping("/attendee/attendeegiveup")
    @ResponseBody
    public int PostAttendeeGiveUP(@RequestParam(value = "userIds[]", required = false) List<String> checkArray,
            HttpSession session) {
        System.out.println("AttendeeGiveUPPost진입");
        int result = 0;
        Map<String, String> academyclass = (Map) session.getAttribute("academyclasss");
        TeacherDTO teacherdto = (TeacherDTO) session.getAttribute("loginUser");
        for (String id : checkArray) {
            AttendeeDTO attendeedto = new AttendeeDTO();
            attendeedto.setUpdateUser(teacherdto.getTeacherId());
            attendeedto.setParticipateStatus("중도포기");
            attendeedto.setAcademyId(academyclass.get("academyId"));
            attendeedto.setClassId(academyclass.get("classId"));
            attendeedto.setStudentId(id);
            result = attendeeservice.AttendeeGiveup(attendeedto);
        }
        return result;
    }

    @PostMapping("/attendee/attendeedelete")
    @ResponseBody
    public int PostAttendeeDelete(@RequestParam(value = "userIds[]", required = false) List<String> checkArray,
            HttpSession session) {
        System.out.println("AttendeeDeletePost진입");
        int result = 0;
        Map<String, String> academyclass = (Map) session.getAttribute("academyclasss");
        for (String id : checkArray) {
            AttendeeDTO attendeedto = new AttendeeDTO();
            attendeedto.setAcademyId(academyclass.get("academyId"));
            attendeedto.setClassId(academyclass.get("classId"));
            attendeedto.setStudentId(id);
            result = attendeeservice.AttendeeDelete(attendeedto);
        }
        return result;
    }

    @GetMapping("/attendee/attendeedetaillist")
    public String GetAttendeeDetailList(@RequestParam(value = "studentId", required = false) String studentId,
            Model model, HttpSession session) {
        Map<String, String> academyclass = (Map) session.getAttribute("academyclasss");
        AttendeeDTO attendeedto = new AttendeeDTO();
        attendeedto.setStudentId(studentId);
        attendeedto.setAcademyId(academyclass.get("academyId"));
        attendeedto.setClassId(academyclass.get("classId"));
        Map<String, String> attendeeDetail = attendeeservice.AttendeeDetail(attendeedto);
        List<CounsellingDTO> counsellingIdList = attendeeservice.CounsellingList(studentId);
        System.out.println("리스트" + attendeeDetail);
        System.out.println("CounsellingDTO" + counsellingIdList);
        model.addAttribute("attendeeDetail", attendeeDetail);
        model.addAttribute("counsellingIdList", counsellingIdList);
        return "attendee/attendeedetaillist";
    }

    @PostMapping("/attendee/attendeedetaillist")
    public String PostCounsellingInsert(CounsellingDTO dto) {
        int result = attendeeservice.CounsellingInsert(dto);
        if (result == 1) {
            return "redirect:/attendee/attendeelist";
        } else {
            return "redirect:/attendee/attendeedetaillist";
        }
    }

    @GetMapping("/attendee/attendeeteacherexist")
    public String GetAttendeeteacherexist(HttpSession session) {
        Map<String, String> mapacademyclasss = (Map<String, String>) session.getAttribute("academyclasss");
        System.out.println(mapacademyclasss);
        if (mapacademyclasss != null) {
            return "redirect:/attendee/attendeelist";
        } else {
            return "redirect:/classs/classteacherexist";
        }
    }

    /*
     * @GetMapping("/attendee/attendeestudentexist") public String
     * GetAttendeestudentexist(HttpSession session) { StudentDTO studentdto =
     * (StudentDTO) session.getAttribute("loginUser"); int result =
     * classService.selectAttendee(studentdto.getStudentId()); if (result != 0) {
     * return "redirect:/attendee/attendeedetaillist"; } else { return
     * "redirect:/classs/classlist"; } }
     */
}
