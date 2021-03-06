package com.tt.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.domain.AttendanceDTO;
import com.tt.service.AttendanceService;
import com.tt.service.ClassService;

@Controller
public class AttendanceController {
    @Autowired
    AttendanceService attendenceservice;
    @Autowired
    ClassService classService;

    @GetMapping(value = "/attendance/attendancelist")
    public String AttendenceListGet(Model model, AttendanceDTO attendancedto, HttpSession session) {
        long nowdate = (long) session.getAttribute("now");
        Date now = new Date(nowdate);

        if (attendancedto.getAttendanceDate() != null) {
            System.out.println("dto진입");
            System.out.println(attendancedto);
            List<Map<String, String>> attendancemaplist = attendenceservice.AttendanceSelectList(attendancedto);
            System.out.println(attendancemaplist);
            model.addAttribute("attendanceDate", attendancedto.getAttendanceDate());
            model.addAttribute("attendancemaplist", attendancemaplist);
            return "attendance/attendancelist";
        } else {
            Map<String, String> academyclass = (Map<String, String>) session.getAttribute("academyclasss");
            System.out.println("dto널진입");
            attendancedto.setAttendanceDate(now);// 검색할날짜;
            attendancedto.setClassId(academyclass.get("classId"));
            attendancedto.setAcademyId(academyclass.get("academyId"));
            System.out.println(attendancedto);
            List<Map<String, String>> attendancemaplist = attendenceservice.AttendanceSelectList(attendancedto);// 출석부
            System.out.println(attendancemaplist);
            model.addAttribute("attendanceDate", attendancedto.getAttendanceDate());
            /*
             * model.addAttribute("attendanceDate",
             * attendancemaplist.get(0).get("ATTENDANCE_DATE"));
             */ model.addAttribute("attendancemaplist", attendancemaplist);
            return "attendance/attendancelist";
        }

    }

    @PostMapping(value = "/attendance/attendanceput")
    @ResponseBody
    public int AttendenceListPost(@RequestBody List<AttendanceDTO> attendencelist) {
        int result = 0;

        for (AttendanceDTO attnlist : attendencelist) {
            result = attendenceservice.AttendanceInsert(attnlist);
            System.out.println(attnlist);
        }

        return result;
    }

    @GetMapping("/attendance/attendanceteacherexist")
    public String GetAttendanceteacherexist(HttpSession session) {
        Map<String, String> mapacademyclasss = (Map<String, String>) session.getAttribute("academyclasss");
        System.out.println(mapacademyclasss);
        if (mapacademyclasss != null) {
            return "redirect:/attendance/attendancelist";
        } else {
            return "redirect:/classs/classteacherexist";
        }
    }

    @GetMapping("/attendance/attendancestudentexist")
    public String GetAttendancestudentexist(HttpSession session) {
        Map<String, String> mapacademyclasss = (Map<String, String>) session.getAttribute("academyclasss");
        System.out.println(mapacademyclasss);
        if (mapacademyclasss != null) {
            return "redirect:/attendance/attendancelist";
        } else {
            return "redirect:/classs/classteacherexist";
        }
    }

}
