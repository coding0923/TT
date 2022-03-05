package com.tt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.domain.AttendeeDTO;
import com.tt.mapper.AttendeeMapper;

@Service
public class AttendeeServiceImpl implements AttendeeService {
    @Autowired
    private AttendeeMapper attendeeMapper;

    @Override
    public int Attendeeput(AttendeeDTO dto) {

        return attendeeMapper.Attendeeput(dto);
    }

    @Override
    public List<Map<String, String>> AttendeeList(Map<String, String> map) {
        return attendeeMapper.AttendeeList(map);
    }

    @Override
    public int AttendeeCall(AttendeeDTO dto) {
        return attendeeMapper.AttendeeCall(dto);
    }

    @Override
    public int AttendeeDelete(AttendeeDTO dto) {
        return attendeeMapper.AttendeeDelete(dto);
    }

    @Override
    public int AttendeeGiveup(AttendeeDTO dto) {
        return attendeeMapper.AttendeeGiveup(dto);
    }

    @Override
    public Map<String, String> AttendeeDetail(AttendeeDTO dto) {
        return attendeeMapper.AttendeeDetail(dto);
    }
}
