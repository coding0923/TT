package com.tt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tt.domain.TeacherDTO;

@Mapper
public interface TeacherMapper {

    public int insertTeacher(TeacherDTO teacherDTO);

    public TeacherDTO selectTeacherDetail(String teacherId);

    public int selectTeacher(TeacherDTO teacherDTO);

}
