package com.tt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tt.domain.StudentDTO;
import com.tt.mapper.StudentMapper;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean registerStudent(StudentDTO studentDTO) {

        String encryptedPW = encryptPassword(studentDTO.getStudentPassword());
        studentDTO.setStudentPassword(encryptedPW);

        int queryResult = 0;

        queryResult = studentMapper.insertStudent(studentDTO);

        return (queryResult == 1) ? true : false;
    }

    @Transactional
    public String encryptPassword(String rawPassword) {

        String encryptedPassword = passwordEncoder.encode(rawPassword);
        return encryptedPassword;
    }

    @Override
    public StudentDTO getStudentDetail(String studentId) {
        return studentMapper.selectStudentDetail(studentId);
    }

    @Override
    public boolean loginStudent(StudentDTO studentDTO) {
        String id = studentDTO.getStudentId();
        String inputPw = studentDTO.getStudentPassword();
        StudentDTO student = studentMapper.selectStudentDetail(id);

        if (student != null) {
            String dbPw = student.getStudentPassword();

            if (passwordEncoder.matches(inputPw, dbPw)) {
                return true;
            } else {
                return false;
            }
        }
        return false;

    }

    @Override
    public boolean checkStudentId(String studentId) {
        int queryResult = 0;

        queryResult = studentMapper.checkId(studentId);

        return (queryResult == 0) ? true : false;
    }

    @Override
    public boolean checkStudentEmail(String studentEmail) {
        int queryResult = 0;

        queryResult = studentMapper.checkEmail(studentEmail);

        return (queryResult == 0) ? true : false;
    }

    @Override
    public String findStudentId(StudentDTO studentDTO) {
        String studentId = null;

        studentId = studentMapper.findId(studentDTO);

        return studentId;
    }

    @Override
    public boolean findStudentPw(StudentDTO studentDTO) {
        int queryResult = 0;

        queryResult = studentMapper.findPw(studentDTO);

        return (queryResult == 0) ? false : true;
    }

    @Override
    public boolean setNewStudentPw(StudentDTO studentDTO) {
        String encryptedPW = encryptPassword(studentDTO.getStudentPassword());
        studentDTO.setStudentPassword(encryptedPW);

        int queryResult = 0;

        queryResult = studentMapper.setNewPw(studentDTO);

        return (queryResult == 0) ? false : true;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {

        String encryptedPW = encryptPassword(studentDTO.getStudentPassword());
        studentDTO.setStudentPassword(encryptedPW);

        int queryResult = 0;
        queryResult = studentMapper.updateStudent(studentDTO);

        return (queryResult == 1) ? true : false;
    }

    @Override
    public boolean deleteStudent(String studentId) {

        int queryResult = 0;
        queryResult = studentMapper.deleteStudent(studentId);

        return (queryResult == 1) ? true : false;

    }

    @Override
    public boolean updateProfile(StudentDTO studentDTO) {

        int queryResult = 0;
        queryResult = studentMapper.updateProfile(studentDTO);

        return (queryResult == 1) ? true : false;
    }
}
