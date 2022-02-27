package com.tt.service;

import java.util.List;

import com.tt.domain.AttendeeDTO;

public interface AttendeeService {
    public int Attendeeput(AttendeeDTO dto);

    public List<AttendeeDTO> AttendeeList();

    public int AttendeeCall(String id);

    public int AttendeeDelete(String id);

    public int AttendeeGiveup(String id);
}
