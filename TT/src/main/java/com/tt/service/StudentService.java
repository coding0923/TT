package com.tt.service;

import com.tt.domain.StudentDTO;

public interface StudentService {

    public boolean registerStudent(StudentDTO studentDTO);

    public StudentDTO getStudentDetail(String studentId);

    public boolean loginStudent(StudentDTO studentDTO);
}
