package com.tt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.domain.AttendanceDTO;
import com.tt.mapper.AttendanceMapper;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceMapper attendancemapper;

    @Override
    public int TodayDataCheck() {
        return attendancemapper.TodayDataCheck();
    }

    @Override
    public List<Map<String, String>> AttendanceSelectList(AttendanceDTO dto) {
        return attendancemapper.AttendanceSelectList(dto);
    }

    @Override
    public int AttendanceInsert(AttendanceDTO dto) {
        return attendancemapper.AttendanceInsert(dto);
    }
}
