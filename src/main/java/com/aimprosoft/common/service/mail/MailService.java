package com.aimprosoft.common.service.mail;

import com.aimprosoft.common.exception.MailException;

public interface MailService {

    void sendEmail(String from, String to, String subject, String content) throws MailException;

    void sendEmail(String from, String[] toAddresses, String subject, String content) throws MailException;
}
