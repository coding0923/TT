package com.tt.service;

import java.util.List;

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
    public List<AttendeeDTO> AttendeeList() {
        return attendeeMapper.AttendeeList();
    }

    @Override
    public int AttendeeCall(String id) {
        return attendeeMapper.AttendeeCall(id);
    }

    @Override
    public int AttendeeDelete(String id) {
        return attendeeMapper.AttendeeDelete(id);
    }

    @Override
    public int AttendeeGiveup(String id) {
        return attendeeMapper.AttendeeGiveup(id);
    }

}
