package com.tt.service;

import java.util.List;
import java.util.Map;

import com.tt.domain.AttendeeDTO;
import com.tt.domain.CounsellingDTO;

public interface AttendeeService {
    public int Attendeeput(AttendeeDTO dto);

    public int AttendeeCall(AttendeeDTO dto);

    public int AttendeeDelete(AttendeeDTO dto);

    public int AttendeeGiveup(AttendeeDTO dto);

    public List<Map<String, String>> AttendeeList(Map<String, String> map);

    public Map<String, String> AttendeeDetail(AttendeeDTO dto);

    public List<CounsellingDTO> CounsellingList(String studendtid);

    public int CounsellingInsert(CounsellingDTO dto);

    public CounsellingDTO CounsellingDetail(int counsellingId);
}
