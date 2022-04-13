package com.tt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tt.domain.TeacherDTO;
import com.tt.mapper.TeacherMapper;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean registerTeacher(TeacherDTO teacherDTO) {

        String encryptedPW = encryptPassword(teacherDTO.getTeacherPassword());
        teacherDTO.setTeacherPassword(encryptedPW);

        int queryResult = 0;
        queryResult = teacherMapper.insertTeacher(teacherDTO);

        return (queryResult == 1) ? true : false;
    }

    @Transactional
    public String encryptPassword(String rawPassword) {

        String encryptedPassword = passwordEncoder.encode(rawPassword);
        return encryptedPassword;
    }

    @Override
    public TeacherDTO getTeacherDetail(String teacherId) {
        return teacherMapper.selectTeacherDetail(teacherId);
    }

    @Override
    public boolean loginTeacher(TeacherDTO teacherDTO) {
        String id = teacherDTO.getTeacherId();
        String inputPw = teacherDTO.getTeacherPassword();
        TeacherDTO teacher = teacherMapper.selectTeacherDetail(id);

        if (teacher != null) {
            String dbPw = teacher.getTeacherPassword();

            if (passwordEncoder.matches(inputPw, dbPw)) {
                return true;
            } else {
                return false;
            }
        }
        return false;

    }

    @Override
    public boolean checkTeacherId(String teacherId) {
        int queryResult = 0;

        queryResult = teacherMapper.checkId(teacherId);

        return (queryResult == 0) ? true : false;
    }

    @Override
    public boolean checkTeacherEmail(String teacherEmail) {
        int queryResult = 0;

        queryResult = teacherMapper.checkEmail(teacherEmail);

        return (queryResult == 0) ? true : false;
    }

    @Override
    public String findTeacherId(TeacherDTO teacherDTO) {
        String teacherId = null;

        teacherId = teacherMapper.findId(teacherDTO);

        return teacherId;
    }

    @Override
    public boolean findTeacherPw(TeacherDTO teacherDTO) {
        int queryResult = 0;

        queryResult = teacherMapper.findPw(teacherDTO);

        return (queryResult == 0) ? false : true;
    }

    @Override
    public boolean setNewTeacherPw(TeacherDTO teacherDTO) {
        String encryptedPW = encryptPassword(teacherDTO.getTeacherPassword());
        teacherDTO.setTeacherPassword(encryptedPW);

        int queryResult = 0;

        queryResult = teacherMapper.setNewPw(teacherDTO);

        return (queryResult == 0) ? false : true;
    }

    @Override
    public boolean updateTeacher(TeacherDTO teacherDTO) {

        String encryptedPW = encryptPassword(teacherDTO.getTeacherPassword());
        teacherDTO.setTeacherPassword(encryptedPW);

        int queryResult = 0;
        queryResult = teacherMapper.updateTeacher(teacherDTO);

        return (queryResult == 1) ? true : false;
    }

    @Override
    public boolean deleteTeacher(String teacherId) {

        int queryResult = 0;
        queryResult = teacherMapper.deleteTeacher(teacherId);

        return (queryResult == 1) ? true : false;

    }

    @Override
    public boolean updateProfile(TeacherDTO teacherDTO) {

        int queryResult = 0;
        queryResult = teacherMapper.updateProfile(teacherDTO);

        return (queryResult == 1) ? true : false;
    }
}
