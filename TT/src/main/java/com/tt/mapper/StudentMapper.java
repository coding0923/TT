package com.tt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tt.domain.StudentDTO;

@Mapper
public interface StudentMapper {

    public int insertStudent(StudentDTO studentDTO);

    public StudentDTO selectStudentDetail(String studentId);

    public int selectStudent(StudentDTO studentDTO);

}
