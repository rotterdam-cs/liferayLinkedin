package com.aimprosoft.common.util;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import java.util.Map;

public class JacksonJsonViewHelper {

    public static MappingJacksonJsonView getJsonView(String key, Object value) {

        final MappingJacksonJsonView mappingJacksonJsonView = new MappingJacksonJsonView();

        mappingJacksonJsonView.addStaticAttribute(key, value);

        return mappingJacksonJsonView;
    }

    public static MappingJacksonJsonView getJsonView(Map<String, Object> objects) {

        final MappingJacksonJsonView mappingJacksonJsonView = new MappingJacksonJsonView();

        for (String key : objects.keySet()) {
            mappingJacksonJsonView.addStaticAttribute(key, objects.get(key));
        }
        return mappingJacksonJsonView;
    }

}
