package com.tt.service;

public interface EmailService {
    String sendSimpleMessage(String to) throws Exception;

    String sendFindPwMessage(String to) throws Exception;
}
