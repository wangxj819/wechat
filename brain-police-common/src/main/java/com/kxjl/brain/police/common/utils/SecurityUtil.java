/* 
 *
 * Copyright (C) 1999-2014 IFLYTEK Inc.All Rights Reserved. 
 * 
 * FileName：brain-police
 * 
 * Description：
 * 
 * History：
 * Version   Author      Date            Operation 
 * 1.0	     Administrator    2017/6/169:51	  Create	
 */
package com.kxjl.brain.police.common.utils;

import java.util.UUID;

public class SecurityUtil {


    /**
     * 生成32位编码
     * @return string
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid.toUpperCase();
    }




    public static void main(String [] args ){
        System.out.println(getUUID());
    }

}
