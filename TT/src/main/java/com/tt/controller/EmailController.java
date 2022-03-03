package com.tt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.service.EmailService;
import com.tt.service.EmailServiceImpl;

@Controller
public class EmailController {
    @Autowired
    EmailService service;

    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @PostMapping("/service/mail")
    @ResponseBody
    public void emailConfirm(String email) throws Exception {
        service.sendSimpleMessage(email);
    }

    @PostMapping("/service/verifyCode")
    @ResponseBody
    public boolean verifyCode(String code) {
        logger.info("Post verifyCode");

        int result = 0;
        if (EmailServiceImpl.ePw.equals(code)) {
            result = 1;
        }
        return (result == 0) ? true : false;
    }

    @GetMapping("/member/forgot")
    public void teacherSignup2() {

    }
}
