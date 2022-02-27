package com.tt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.domain.StudentDTO;
import com.tt.mapper.StudentMapper;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public boolean registerStudent(StudentDTO studentDTO) {
        int queryResult = 0;

        queryResult = studentMapper.insertStudent(studentDTO);

        return (queryResult == 1) ? true : false;
    }

    @Override
    public StudentDTO getStudentDetail(String studentId) {
        return studentMapper.selectStudentDetail(studentId);
    }

    @Override
    public boolean loginStudent(StudentDTO studentDTO) {
        int queryResult = 0;

        System.out.println("dddd");

        queryResult = studentMapper.selectStudent(studentDTO);

        return (queryResult == 1) ? true : false;
    }
}
