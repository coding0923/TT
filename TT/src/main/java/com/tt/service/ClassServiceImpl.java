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

	@Override
	public int selectTeachinghistory(String id) {
		int result = classMapper.selectTeachinghistory(id);
		return result;
	}

	@Override
	public List<Map<String, String>> selectClassAcademyjoin(String id) {
		List<Map<String, String>> resultlist = classMapper.selectClassAcademyjoin(id);
		return resultlist;
	}

	@Override
	public TeacherDTO classDetailsTeacher(ClassDTO dto) {
		TeacherDTO teachername = classMapper.classDetailsTeacher(dto);
		return teachername;
	}

}
