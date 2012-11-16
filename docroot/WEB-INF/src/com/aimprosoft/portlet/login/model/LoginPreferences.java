package com.aimprosoft.portlet.login.model;

public class LoginPreferences {

    private boolean linkedInIsEnabled;
    private String linkedInAppId;
    private String linkedInAppSecret;
    private String linkedInRedirectURL;
                                         
    private boolean twitterIsEnabled;
    private String twitterAppId;
    private String twitterAppSecret;
    private String twitterRedirectURL;

    private String emailFromName;
    private String emailFromAddress;

    public boolean getLinkedInIsEnabled() {
        return linkedInIsEnabled;
    }

    public void setLinkedInIsEnabled(boolean linkedInIsEnabled) {
        this.linkedInIsEnabled = linkedInIsEnabled;
    }

    public String getLinkedInAppId() {
        return linkedInAppId;
    }

    public void setLinkedInAppId(String linkedInAppId) {
        this.linkedInAppId = linkedInAppId;
    }

    public String getLinkedInAppSecret() {
        return linkedInAppSecret;
    }

    public void setLinkedInAppSecret(String linkedInAppSecret) {
        this.linkedInAppSecret = linkedInAppSecret;
    }

    public String getLinkedInRedirectURL() {
        return linkedInRedirectURL;
    }

    public void setLinkedInRedirectURL(String linkedInRedirectURL) {
        this.linkedInRedirectURL = linkedInRedirectURL;
    }

    public boolean getTwitterIsEnabled() {
        return twitterIsEnabled;
    }

    public void setTwitterIsEnabled(boolean twitterIsEnabled) {
        this.twitterIsEnabled = twitterIsEnabled;
    }

    public String getTwitterAppId() {
        return twitterAppId;
    }

    public void setTwitterAppId(String twitterAppId) {
        this.twitterAppId = twitterAppId;
    }

    public String getTwitterAppSecret() {
        return twitterAppSecret;
    }

    public void setTwitterAppSecret(String twitterAppSecret) {
        this.twitterAppSecret = twitterAppSecret;
    }

    public String getTwitterRedirectURL() {
        return twitterRedirectURL;
    }

    public void setTwitterRedirectURL(String twitterRedirectURL) {
        this.twitterRedirectURL = twitterRedirectURL;
    }

    public String getEmailFromName() {
        return emailFromName;
    }

    public void setEmailFromName(String emailFromName) {
        this.emailFromName = emailFromName;
    }

    public String getEmailFromAddress() {
        return emailFromAddress;
    }

    public void setEmailFromAddress(String emailFromAddress) {
        this.emailFromAddress = emailFromAddress;
    }
}
