package com.kxjl.brain.police.common.utils;

import net.sf.cglib.beans.BeanMap;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xjwang5 on 2017/11/16.
 */
public class BeanMapReflect
{
    /**
     * 将一个 JavaBean 对象转化为一个  Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException
    {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    /**
     * 将一个 Map 对象转化为一个 JavaBean
     * @param type 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InstantiationException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    @SuppressWarnings("rawtypes")
    public static Object convertMap(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);

                Object[] args = new Object[1];
                args[0] = value;

                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }

    public static <T> T mapToBean(Map<String, Object> map,T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    public static Object convertBean2Bean(Object from, Object to) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(to.getClass());
            PropertyDescriptor[] ps = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor p : ps) {
                Method getMethod = p.getReadMethod();
                Method setMethod = p.getWriteMethod();
                if (getMethod != null && setMethod != null) {
                    try {
                        Object result = getMethod.invoke(from);
                        setMethod.invoke(to, result);
                    } catch (Exception e) {
                        // 如果from没有此属性的get方法，跳过
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return to;
    }

        /**
         * 不支持to继承(to是其他bean的子类)
         */
    public static Object coverBean2Bean(Object from, Object to) {
        Class fClass = from.getClass();
        Class tClass = to.getClass();
        // 拿to所有属性（如果有继承，父类属性拿不到）
        Field[] cFields = tClass.getDeclaredFields();
        try {
            for (Field field : cFields) {
                String cKey = field.getName();
                // 确定第一个字母大写
                cKey = cKey.substring(0, 1).toUpperCase() + cKey.substring(1);

                Method fMethod;
                Object fValue;
                try {
                    fMethod = fClass.getMethod("get" + cKey);// public方法
                    fValue = fMethod.invoke(from);// 取getfKey的值
                } catch (Exception e) {
                    // 如果from没有此属性的get方法，跳过
                    continue;
                }

                try {
                    // 用fMethod.getReturnType()，而不用fValue.getClass()
                    // 为了保证get方法时，参数类型是基本类型而传入对象时会找不到方法
                    Method cMethod = tClass.getMethod("set" + cKey, fMethod.getReturnType());
                    cMethod.invoke(to, fValue);
                } catch (Exception e) {
                    // 如果to没有此属性set方法，跳过
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return to;
    }


    public static void copyBean(Object src,Object dest){
        ConvertUtils.register(new Converter() {   //这是注册了一个转化类型  从字符串类型转化为Date类型  为抽象类 具体实现根据
            @Override                               //需要转化的bean的属性决定
            public Object convert(Class type, Object value) {

                if(value==null){
                    return null;
                }
                String str = (String) value;
                if(str.trim().equals("")){
                    return null;
                }
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return date.parse(str);   //返回date
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }, Date.class);
        try {
            BeanUtils.copyProperties(dest, src);     //这个导入的包 和上面那个类似   其中前面的参数为目标bean  后面的为源bean
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
