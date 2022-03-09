package com.tt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tt.domain.TeacherDTO;

@Mapper
public interface TeacherMapper {

    public int insertTeacher(TeacherDTO teacherDTO);

    public TeacherDTO selectTeacherDetail(String teacherId);

    public int selectTeacher(TeacherDTO teacherDTO);

    public int checkId(String teacherId);

    public int checkEmail(String teacherEmail);

    public String findId(TeacherDTO teacherDTO);

    public int findPw(TeacherDTO teacherDTO);

    public int setNewPw(TeacherDTO teacherDTO);

    public int updateTeacher(TeacherDTO teacherDTO);

    public int deleteTeacher(String teacherId);

}
