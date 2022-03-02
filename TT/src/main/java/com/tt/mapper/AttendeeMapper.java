package com.tt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tt.domain.AttendeeDTO;

@Mapper
public interface AttendeeMapper {
    public int Attendeeput(AttendeeDTO dto);

    public int AttendeeCall(AttendeeDTO dto);

    public int AttendeeDelete(AttendeeDTO dto);

    public int AttendeeGiveup(AttendeeDTO dto);

    public List<Map<String, String>> AttendeeList(Map<String, String> map);
}
