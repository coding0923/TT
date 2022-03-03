package com.tt.service;

import java.util.List;
import java.util.Map;

import com.tt.domain.AttendanceDTO;

public interface AttendanceService {
    public int TodayDataCheck();

    public List<Map<String, String>> AttendanceSelectList(AttendanceDTO dto);

    public int AttendanceInsert(AttendanceDTO dto);
}
