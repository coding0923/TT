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
        System.out.println("바꼈나요" + teacherDTO);
        queryResult = teacherMapper.insertTeacher(teacherDTO);

        return (queryResult == 1) ? true : false;
    }

    @Transactional
    public String encryptPassword(String rawPassword) {

        System.out.println("원래이거" + rawPassword);
        String encryptedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("바뀐다" + encryptedPassword);
        return encryptedPassword;
    }

    @Override
    public TeacherDTO getTeacherDetail(String teacherId) {
        return teacherMapper.selectTeacherDetail(teacherId);
    }

//    @Override
//    public boolean loginTeacher(TeacherDTO teacherDTO) {
//        int queryResult = 0;
//        System.out.println("뭐야" + teacherDTO);
//        queryResult = teacherMapper.selectTeacher(teacherDTO);
//        System.out.println("조회결과" + queryResult);
//        return (queryResult == 1) ? true : false;
//    }

    @Override
    public boolean loginTeacher(TeacherDTO teacherDTO) {
        String id = teacherDTO.getTeacherId();
        String inputPw = teacherDTO.getTeacherPassword();
        System.out.println("아이디" + id);
        System.out.println("비번" + inputPw);
        TeacherDTO teacher = teacherMapper.selectTeacherDetail(id);

        String dbPw = teacher.getTeacherPassword();
        System.out.println("비번" + dbPw);

        if (passwordEncoder.matches(inputPw, dbPw)) {
            return true;
        } else {
            return false;
        }

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
}
