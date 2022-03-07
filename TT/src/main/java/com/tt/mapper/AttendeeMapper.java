package com.tt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tt.domain.AttendeeDTO;
import com.tt.domain.CounsellingDTO;

@Mapper
public interface AttendeeMapper {
    // 수강신청
    public int Attendeeput(AttendeeDTO dto);

    // 수강신청한 학생 수락
    public int AttendeeCall(AttendeeDTO dto);

    // 수강신청한 학생 거절
    public int AttendeeDelete(AttendeeDTO dto);

    // 수강생 중 중도포기학생으로 상태변환
    public int AttendeeGiveup(AttendeeDTO dto);

    // 수강신청한학생 리스트 표시
    public List<Map<String, String>> AttendeeList(Map<String, String> map);

    // 수강신청한 학생등 상세보기
    public Map<String, String> AttendeeDetail(AttendeeDTO dto);

    // 상담이력 전체리스트 불러오기
    public List<CounsellingDTO> CounsellingList(String studendtid);

    // 상담이력 인서트
    public int CounsellingInsert(CounsellingDTO dto);

    // 상담이력들 중 선택한 상담이력 출력
    public CounsellingDTO CounsellingDetail(int counsellingId);

}
