package com.tt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tt.domain.AttendanceDTO;

@Mapper
public interface AttendanceMapper {
    public int TodayDataCheck();

    public List<Map<String, String>> AttendanceSelectList(AttendanceDTO dto);

    public int AttendanceInsert(AttendanceDTO dto);
}
