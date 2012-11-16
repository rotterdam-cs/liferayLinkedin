package com.aimprosoft.common.spring;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleMessageSource extends org.springframework.context.support.ResourceBundleMessageSource {

    public ResourceBundle getResourceBundleByLocale(String baseName, Locale locale){
        return getResourceBundle(baseName, locale);
    }

}
