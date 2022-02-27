package com.tt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tt.domain.AttendeeDTO;

@Mapper
public interface AttendeeMapper {
    public int Attendeeput(AttendeeDTO dto);

    public List<AttendeeDTO> AttendeeList();

    public int AttendeeCall(String id);

    public int AttendeeDelete(String id);

    public int AttendeeGiveup(String id);
}
