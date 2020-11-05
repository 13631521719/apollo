package com.ctrip.framework.apollo.portal.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import java.util.Map;

/**
 * Spring Bean工具类
 *
 * @author chenlf001
 * @创建时间 2019/12/30 9:42
 * @TODO
 */
@Component
public class SpringContextUtil implements ApplicationContextAware, EmbeddedValueResolverAware {

    /**
     * Spring应用上下文
     */
    private static ApplicationContext applicationContext;

    /**
     * StringValueResolver
     */
    private static StringValueResolver stringValueResolver;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        SpringContextUtil.stringValueResolver = resolver;
    }

    /**
     * 获取bean
     *
     * @param name beanName
     * @return bean对象
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 获取bean
     *
     * @param clazz bean类
     * @return bean实例对象
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 获取bean
     *
     * @param clazz bean类
     * @return bean实例对象
     */
    public static <T> T getBean(String name,Class<T> clazz) {
        return applicationContext.getBean(name,clazz);
    }
    /**
     * 获取指定类型的bean实例集合
     *
     * @param clazz 实例类
     * @param <T>   类型
     * @return beanName - bean map
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

    /**
     * 动态获取配置文件中的值
     *
     * @param name 配置属性名
     * @return 配置属性值
     */
    public static String getPropertiesValue(String name) {
        try {
            name = String.format("${%s}", name);
            return stringValueResolver.resolveStringValue(name);
        } catch (Exception e) {
            return null;
        }
    }

}
