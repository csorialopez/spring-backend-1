package com.sales.market.service.mail;

public interface EmailService {
    void sendSimpleMessage(String to,
                           String text);
    void sendMessage(String to, String subject, String text);
}
