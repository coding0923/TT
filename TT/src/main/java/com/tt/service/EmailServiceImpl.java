package com.tt.service;

import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender emailSender;

    public static final String ePw = createKey();

    private MimeMessage createMessage(String to) throws Exception {
        System.out.println("보내는 대상 : " + to);
        System.out.println("인증 번호 : " + ePw);
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to);// 보내는 대상
        message.setSubject("TT(Teacher Time)회원가입 이메일 인증코드");// 제목

        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요 TT(Teacher Time)입니다. </h1>";
        msgg += "<br>";
        msgg += "<p>회원가입 창으로 돌아가 아래 코드를 입력해주세요<p>";
        msgg += "<br>";
        msgg += "<p>감사합니다!<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>회원가입 인증 코드</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += ePw + "</strong><div><br/> ";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");// 내용
        message.setFrom(new InternetAddress("teacher.time.test@gmail.com", "TT"));// 보내는 사람

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
            case 0:
                key.append((char) ((rnd.nextInt(26)) + 97));
                // a~z (ex. 1+97=98 => (char)98 = 'b')
                break;
            case 1:
                key.append((char) ((rnd.nextInt(26)) + 65));
                // A~Z
                break;
            case 2:
                key.append((rnd.nextInt(10)));
                // 0~9
                break;
            }
        }

        return key.toString();
    }

    @Override
    public String sendSimpleMessage(String to) throws Exception {
        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to);
        try {// 예외처리
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }

    @Override
    public String sendFindPwMessage(String to) throws Exception {
        // TODO Auto-generated method stub
        MimeMessage message = createFindPwMessage(to);
        try {// 예외처리
            emailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }

    private MimeMessage createFindPwMessage(String to) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to);// 보내는 대상
        message.setSubject("TT(Teacher Time)임시 비밀번호 발급");// 제목

        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요 TT(Teacher Time)입니다. </h1>";
        msgg += "<br>";
        msgg += "<p>비밀번호 찾기를 신청하시어 기존에 사용하시던 비밀번호는 초기화되었음을 알려드립니다.<p>";
        msgg += "<br>";
        msgg += "<p>아래 임시 비밀번호로 로그인 하신 후 비밀번호를 재설정해주시기 바랍니다.<p>";
        msgg += "<br>";
        msgg += "<p>감사합니다!<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>임시 비밀번호</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += ePw + "</strong><div><br/> ";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");// 내용
        message.setFrom(new InternetAddress("teacher.time.test@gmail.com", "TT"));// 보내는 사람

        return message;
    }

}
