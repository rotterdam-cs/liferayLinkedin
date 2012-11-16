package com.aimprosoft.common.service.mail.impl;

import com.aimprosoft.common.exception.MailException;
import com.aimprosoft.common.service.mail.MailService;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.mail.MailMessage;

import javax.mail.internet.InternetAddress;

public class LiferayMailService implements MailService {

    public void sendEmail(String from, String to, String subject, String content) throws MailException {
        sendEmail(from, new String[]{to}, subject, content);
    }

    public void sendEmail(String from, String[] toAddresses, String subject, String content) throws MailException {
        try {
            InternetAddress[] to = new InternetAddress[toAddresses.length];

            for (int i = 0; i < toAddresses.length; i++){
                to[i] = new InternetAddress(toAddresses[i]);
            }

            //send email
            //compose message
            MailMessage mailMessage = new MailMessage();
            mailMessage.setFrom(new InternetAddress(from));
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setBody(content);
            mailMessage.setHTMLFormat(true);

            //send
            MailServiceUtil.sendEmail(mailMessage);
        } catch (Throwable e) {
            throw new MailException(e);
        }
    }
}
