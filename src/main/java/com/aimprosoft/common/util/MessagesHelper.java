package com.aimprosoft.common.util;


import com.aimprosoft.common.spring.ObjectFactory;
import com.aimprosoft.common.spring.ResourceBundleMessageSource;
import com.liferay.portal.util.PortalUtil;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import javax.portlet.PortletRequest;
import java.io.IOException;
import java.util.*;

public class MessagesHelper {

    private static final Logger _logger = Logger.getLogger(MessagesHelper.class);

    private static final ResourceBundleMessageSource resourceBundleMessageSource;
    private static final ObjectMapper objectMapper;

    static {
        resourceBundleMessageSource = ObjectFactory.getBean(ResourceBundleMessageSource.class);
        objectMapper = ObjectFactory.getBean(ObjectMapper.class);
    }

    //get all messages from MessageResourceBundle which property name starts with String namespace
    public static String getMessagesLikeJSON(PortletRequest request, String namespace) {                  //get messages for js

        Map<String, String> messageMap = MessagesHelper.getMessagesByNamespace(request, namespace);

        return serializeData(messageMap);
    }

    public static Map<String, String> getMessagesByNamespace(PortletRequest request, String namespace) {

        Locale locale;

        Map<String, String> messages = new HashMap<String, String>();

        try {
            locale = request.getLocale();
        } catch (Exception e) {
            _logger.error(e);
            return messages;
        }

        ResourceBundle resourceBundle = resourceBundleMessageSource.getResourceBundleByLocale("content/messages", locale);

        Enumeration<String> keys = resourceBundle.getKeys();

        while (keys.hasMoreElements()) {

            String key = keys.nextElement();

            if (key.contains(namespace)) {
                messages.put(key, resourceBundle.getString(key));
            }

        }

        return messages;
    }

    private static String serializeData(Object obj) {                                     //serialize data as json
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            _logger.error(e);
            return "{}";
        }
    }

    public static String getApplicationMessage(String key, PortletRequest request) {

        Locale locale = null;

        try {
            locale = PortalUtil.getUser(request).getLocale();
        } catch (Exception e) {
            locale = Locale.ITALIAN;
        }

        ResourceBundle resourceBundle = resourceBundleMessageSource.getResourceBundleByLocale("content/messages", locale);

        return resourceBundle.getString(key);
    }
}

