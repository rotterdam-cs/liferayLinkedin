package com.aimprosoft.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("props")
public class ApplicationPropsBean {

    @Value("${login.portlet.registration.email.from}")
    private String registrationEmailFrom;

    @Value("${login.portlet.registration.email.subject}")
    private String registrationEmailSubject;


    @Value("${linkedin.default.connect.app.id}")
    private String linkedInDefaultAppId;

    @Value("${linkedin.default.connect.app.secret}")
    private String linkedInDefaultAppSecret;

    @Value("${linkedin.default.redirect.url}")
    private String linkedInDefaultRedirectUrl;

    @Value("${linkedin.default.connect.auth.enabled}")
    private String linkedInDefaultAuthEnabled;


    @Value("${twitter.default.connect.app.id}")
    private String twitterDefaultAppId;

    @Value("${twitter.default.connect.app.secret}")
    private String twitterDefaultAppSecret;

    @Value("${twitter.default.redirect.url}")
    private String twitterDefaultRedirectUrl;

    @Value("${twitter.default.connect.auth.enabled}")
    private String twitterDefaultAuthEnabled;

    @Value("${email.from.name.default}")
    private String emailFromNameDefault;

    @Value("${email.from.address.default}")
    private String emailFromAddressDefault;

    

    public String getRegistrationEmailFrom() {
        return registrationEmailFrom;
    }

    public void setRegistrationEmailFrom(String registrationEmailFrom) {
        this.registrationEmailFrom = registrationEmailFrom;
    }

    public String getRegistrationEmailSubject() {
        return registrationEmailSubject;
    }

    public void setRegistrationEmailSubject(String registrationEmailSubject) {
        this.registrationEmailSubject = registrationEmailSubject;
    }

    public String getLinkedInDefaultAppId() {
        return linkedInDefaultAppId;
    }

    public void setLinkedInDefaultAppId(String linkedInDefaultAppId) {
        this.linkedInDefaultAppId = linkedInDefaultAppId;
    }

    public String getLinkedInDefaultAppSecret() {
        return linkedInDefaultAppSecret;
    }

    public void setLinkedInDefaultAppSecret(String linkedInDefaultAppSecret) {
        this.linkedInDefaultAppSecret = linkedInDefaultAppSecret;
    }

    public String getLinkedInDefaultRedirectUrl() {
        return linkedInDefaultRedirectUrl;
    }

    public void setLinkedInDefaultRedirectUrl(String linkedInDefaultRedirectUrl) {
        this.linkedInDefaultRedirectUrl = linkedInDefaultRedirectUrl;
    }

    public String getLinkedInDefaultAuthEnabled() {
        return linkedInDefaultAuthEnabled;
    }

    public void setLinkedInDefaultAuthEnabled(String linkedInDefaultAuthEnabled) {
        this.linkedInDefaultAuthEnabled = linkedInDefaultAuthEnabled;
    }

    public String getTwitterDefaultAppId() {
        return twitterDefaultAppId;
    }

    public void setTwitterDefaultAppId(String twitterDefaultAppId) {
        this.twitterDefaultAppId = twitterDefaultAppId;
    }

    public String getTwitterDefaultAppSecret() {
        return twitterDefaultAppSecret;
    }

    public void setTwitterDefaultAppSecret(String twitterDefaultAppSecret) {
        this.twitterDefaultAppSecret = twitterDefaultAppSecret;
    }

    public String getTwitterDefaultRedirectUrl() {
        return twitterDefaultRedirectUrl;
    }

    public void setTwitterDefaultRedirectUrl(String twitterDefaultRedirectUrl) {
        this.twitterDefaultRedirectUrl = twitterDefaultRedirectUrl;
    }

    public String getTwitterDefaultAuthEnabled() {
        return twitterDefaultAuthEnabled;
    }

    public void setTwitterDefaultAuthEnabled(String twitterDefaultAuthEnabled) {
        this.twitterDefaultAuthEnabled = twitterDefaultAuthEnabled;
    }

    public String getEmailFromNameDefault() {
        return emailFromNameDefault;
    }

    public void setEmailFromNameDefault(String emailFromNameDefault) {
        this.emailFromNameDefault = emailFromNameDefault;
    }

    public String getEmailFromAddressDefault() {
        return emailFromAddressDefault;
    }

    public void setEmailFromAddressDefault(String emailFromAddressDefault) {
        this.emailFromAddressDefault = emailFromAddressDefault;
    }
}
