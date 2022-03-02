package com.tt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.domain.ClassDTO;
import com.tt.domain.CurriculumDTO;
import com.tt.domain.SubjectDTO;
import com.tt.domain.TeacherDTO;
import com.tt.mapper.ClassMapper;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public List<ClassDTO> classList() {
        List<ClassDTO> list = classMapper.classList();
        return list;
    }

    @Override
    public List<ClassDTO> myClassList(TeacherDTO dto) {
        List<ClassDTO> list = classMapper.myClassList(dto);
        return list;
    }

    @Override
    public int createClass(ClassDTO dto) {
        int result = classMapper.createClass(dto);
        return result;
    }

    @Override
    public int insertTeachingHistory(ClassDTO dto) {
        int result = classMapper.insertTeachingHistory(dto);
        return result;
    }

    @Override
    public ClassDTO classDetails(ClassDTO dto) {
        ClassDTO classdetails = classMapper.classDetails(dto);
        return classdetails;
    }

    @Override
    public List<SubjectDTO> subjectlist() {
        List<SubjectDTO> list = classMapper.subjectlist();
        return list;
    }

    @Override
    public List<SubjectDTO> subjectSearch(String subSearch) {
        List<SubjectDTO> sublist = classMapper.subjectSearch(subSearch);
        return sublist;
    }

    @Override
    public int subjectInsert(SubjectDTO dto) {
        int result = classMapper.subjectInsert(dto);
        return result;
    }

    @Override
    public int insertCurri(CurriculumDTO clist) {
        int result = classMapper.insertCurri(clist);
        return result;
    }

    @Override // 선생님이 로그인시 포함되어있는 반이 있는지 확인하는 함수
    public int selectTeachinghistory(String id) {
        int result = classMapper.selectTeachinghistory(id);
        return result;
    }

    @Override // 학생이 로그인시 수강중인 반이 있는지 확인하는 함수
    public int selectAttendee(String id) {
        int result = classMapper.selectAttendee(id);
        return result;
    }

    @Override // 선생님이 속한 반들을 가져오는 함수
    public List<Map<String, String>> selectClassAcademyjoin(String id) {
        List<Map<String, String>> resultlist = classMapper.selectClassAcademyjoin(id);
        return resultlist;
    }

    @Override // 학생이 속한 반들을 가져오는 함수
    public List<Map<String, String>> selectAttendeeClassjoin(String id) {
        List<Map<String, String>> resultlist = classMapper.selectAttendeeClassjoin(id);
        return resultlist;
    }

    @Override
    public TeacherDTO classDetailsTeacher(ClassDTO dto) {
        TeacherDTO teachername = classMapper.classDetailsTeacher(dto);
        return teachername;
    }

    @Override
    public int application(String id) {
        int result = classMapper.application(id);
        return result;
    }

    @Override
    public int application2(String id) {
        int result = classMapper.application2(id);
        return result;
    }

    @Override
    public int classUpdate(ClassDTO dto) {
        int result = classMapper.classUpdate(dto);
        return result;
    }

    @Override
    public List<CurriculumDTO> curriList(ClassDTO dto) {
        List<CurriculumDTO> list = classMapper.curriList(dto);
        return list;
    }

    @Override
    public int deleteCurri(String classId) {
        int result = classMapper.deleteCurri(classId);
        return result;
    }

}
