package com.tt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tt.domain.StudentDTO;

@Mapper
public interface StudentMapper {

    public int insertStudent(StudentDTO studentDTO);

    public StudentDTO selectStudentDetail(String studentId);

    public int selectStudent(StudentDTO studentDTO);

    public int checkId(String studentId);

    public int checkEmail(String studentEmail);

    public String findId(StudentDTO studentDTO);

    public int findPw(StudentDTO studentDTO);

    public int setNewPw(StudentDTO studentDTO);

    public int updateStudent(StudentDTO studentDTO);

    public int deleteStudent(String studentId);

    public int updateProfile(StudentDTO studentDTO);

}
