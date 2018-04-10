package com.kxjl.brain.police.common.utils;

import java.util.Random;

/**
 * Created by xjwang5 on 2017/5/15.
 */
public class RandomNum
{
    /**
     * 随机生成六位数验证码
     * @return
     */
    public static String getRandNum() {
        /*String[] beforeShuffle = new String[] { "0","1","2", "3", "4", "5", "6", "7",
                                                "8", "9",  };
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
        return result;*/
            Random rad=new Random();

            String result  = rad.nextInt(1000000) +"";

            if(result.length()!=6){
                return getRandNum();
            }
            return result;
    }

}
