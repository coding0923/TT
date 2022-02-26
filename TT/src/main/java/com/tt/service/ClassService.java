package com.tt.service;

import java.util.List;
import java.util.Map;

import com.tt.domain.ClassDTO;
import com.tt.domain.CurriculumDTO;
import com.tt.domain.SubjectDTO;
import com.tt.domain.TeacherDTO;

public interface ClassService {

    public List<ClassDTO> classList();

    public List<ClassDTO> myClassList(TeacherDTO dto);

    public int createClass(ClassDTO dto);

    public int insertTeachingHistory(ClassDTO dto);

    public ClassDTO classDetails(ClassDTO dto);

    public List<SubjectDTO> subjectlist();

    public List<SubjectDTO> subjectSearch(String subSearch);

    public int subjectInsert(SubjectDTO dto);

    public int insertCurri(CurriculumDTO clist);

    public int selectTeachinghistory(String id);

    public List<Map<String, String>> selectClassAcademyjoin(String id);

}
