package com.tt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.tt.domain.AttendeeDTO;
import com.tt.domain.ClassDTO;
import com.tt.domain.CurriculumDTO;
import com.tt.domain.SubjectDTO;
import com.tt.domain.TeacherDTO;

@Mapper
public interface ClassMapper {

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

	public int selectAttendee(String id);

	public List<Map<String, String>> selectClassAcademyjoin(String id);

	public List<Map<String, String>> selectAttendeeClassjoin(String id);

	public TeacherDTO classDetailsTeacher(ClassDTO dto);

	public int application(String id);

	public int application2(String id);

	public int classUpdate(ClassDTO dto);

	public List<CurriculumDTO> curriList(ClassDTO dto);

	public int deleteCurri(String classId);

	public int countAttendee(AttendeeDTO dto);
}
