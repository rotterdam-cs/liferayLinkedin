package com.aimprosoft.common.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

/**
 * This class provides to get application beans
 */
public class ObjectFactory {

    /**
     * This method return bean from spring application context
     *
     * @param beanName - name of bean
     * @return bean
     */
    public static Object getBean(String beanName) {
        return getContext().getBean(beanName);
    }

    /**
     * This method return bean from spring application context
     *
     * @param className - class of bean
     * @return bean
     */
    public static <T> T getBean(Class<T> className) {
        return getContext().getBean(className);
    }

    /**
     * This method return bean from spring application context
     *
     * @param beanName - name of bean
     * @param className - class of bean
     * @return bean
     */
    public static <T> T getBean(String beanName, Class<T> className) {
        return getContext().getBean(beanName, className);
    }

    /**
     * This method return spring application context
     *
     * @return spring application context
     */
    private static ApplicationContext getContext(){
        return ContextLoaderListener.getCurrentWebApplicationContext();
    }
}
