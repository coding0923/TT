package com.tt.service;

import com.tt.domain.TeacherDTO;

public interface TeacherService {

    public boolean registerTeacher(TeacherDTO teacherDTO);

    public TeacherDTO getTeacherDetail(String teacherId);

    public boolean loginTeacher(TeacherDTO teacherDTO);

    public boolean checkTeacherId(String teacherId);

    public boolean checkTeacherEmail(String teacherEmail);

    public String findTeacherId(TeacherDTO teacherDTO);

    public boolean findTeacherPw(TeacherDTO teacherDTO);

    public boolean setNewTeacherPw(TeacherDTO teacherDTO);

    public boolean updateTeacher(TeacherDTO teacherDTO);

    public boolean deleteTeacher(String teacherId);

    public boolean updateProfile(TeacherDTO teacherDTO);
}
