package com.kxjl.brain.police.common.utils;

import java.util.Map;
import java.util.TreeMap;

/**
 * 签名工具类
 *
 * Created by WeiDeng on 2017/8/17.
 */
public class SignUtils {


    /**
     *
     * 按ACII码值排序加密字段
     *
     * @param map
     * @return
     */
    public static String signStr(Map<String, String> map){
        String str = "" ;
        for (Map.Entry<String, String> o : map.entrySet()){
            str += o.getValue();
        }
        System.out.println(str);
        return str;
    }

    public static void main(String[] args) {
        Map map = new TreeMap();
        map.put("app_id","111");
        map.put("sign","222");
        map.put("timestamp","333");
        System.out.println(MD5Utils.getMD5Value("app_id=1102app_secrect=bCDWjTMYRzEIxVYitimestamp=1503283977").toLowerCase());
    }


}
