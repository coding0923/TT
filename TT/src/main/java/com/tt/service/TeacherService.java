package com.tt.service;

import com.tt.domain.TeacherDTO;

public interface TeacherService {

    public boolean registerTeacher(TeacherDTO teacherDTO);

    public TeacherDTO getTeacherDetail(String teacherId);

    public boolean loginTeacher(TeacherDTO teacherDTO);
}
