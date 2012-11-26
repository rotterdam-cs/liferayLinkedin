package com.aimprosoft.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * This class provides to get application beans
 */
@Service
public class ObjectFactory implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    /**
     * This method return spring application context
     *
     * @return spring application context
     */
    /*private static ApplicationContext getContext(){
        return ContextLoaderListener.getCurrentWebApplicationContext();
    }*/

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

            ObjectFactory.applicationContext = applicationContext;

        }


    /**
     * This method return bean from spring application context
     *
     * @param beanName - name of bean
     * @return bean
     */
    public static Object getBean(String beanName) {
//        return getContext().getBean(beanName);
        return applicationContext.getBean(beanName);
    }

    /**
     * This method return bean from spring application context
     *
     * @param className - class of bean
     * @return bean
     */
    public static <T> T getBean(Class<T> className) {
//        return getContext().getBean(className);
        return applicationContext.getBean(className);
    }

    /**
     * This method return bean from spring application context
     *
     * @param beanName - name of bean
     * @param className - class of bean
     * @return bean
     */
    public static <T> T getBean(String beanName, Class<T> className) {
//        return getContext().getBean(beanName, className);
        return applicationContext.getBean(beanName, className);
    }
}
