package com.tt.service;

import com.tt.domain.StudentDTO;

public interface StudentService {

    public boolean registerStudent(StudentDTO studentDTO);

    public StudentDTO getStudentDetail(String studentId);

    public boolean loginStudent(StudentDTO studentDTO);

    public boolean checkStudentId(String studentId);

    public boolean checkStudentEmail(String studentEmail);

    public String findStudentId(StudentDTO studentDTO);

    public boolean findStudentPw(StudentDTO studentDTO);

    public boolean setNewStudentPw(StudentDTO studentDTO);

    public boolean updateStudent(StudentDTO studentDTO);

    public boolean deleteStudent(String studentId);
}
