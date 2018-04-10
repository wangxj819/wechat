/**
 * Copyright 2014 IFlyTek. All rights reserved.<br>
 */
package com.kxjl.brain.police.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * <code>CollectionUtils</code>集合的操作类.
 * </p>
 *
 * @author <a href="mailto:wqwu@iflytek.com">cheney</a>
 * @version 1.0
 * @since 1.0
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {

    /**
     * Set转成成List
     *
     * @param <T>
     * @param set
     * @return
     */
    public static <T> List<T> toList(Set<T> set) {
        if (set == null) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<T>();
        for (T t : set) {
            list.add(t);
        }
        return list;
    }

}
