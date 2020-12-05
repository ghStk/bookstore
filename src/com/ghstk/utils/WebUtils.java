package com.ghstk.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 2020/11/25 0:56
 */
public class WebUtils {

    public static <T> T populate(T bean, Map<String, String[]> parameterMap) {
        try {
            BeanUtils.populate(bean, parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseToInt(String str,int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
//            e.printStackTrace(); //调试时暂时去掉
        }
        return defaultValue;
    }
}
