package com.tt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.domain.TeacherDTO;
import com.tt.mapper.TeacherMapper;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public boolean registerTeacher(TeacherDTO teacherDTO) {
        int queryResult = 0;

        System.out.println("--");

        queryResult = teacherMapper.insertTeacher(teacherDTO);

        return (queryResult == 1) ? true : false;
    }

    @Override
    public TeacherDTO getTeacherDetail(String teacherId) {
        return teacherMapper.selectTeacherDetail(teacherId);
    }

    @Override
    public boolean loginTeacher(TeacherDTO teacherDTO) {
        int queryResult = 0;

        queryResult = teacherMapper.selectTeacher(teacherDTO);

        return (queryResult == 1) ? true : false;
    }
}
