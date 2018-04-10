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
 * 1.0	     Administrator    2017/5/1111:18	  Create	
 */
package com.kxjl.brain.police.service.api.wx.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.arsenal.rest.result.SearchResult;
import com.kxjl.brain.police.common.Constants;
import com.kxjl.brain.police.common.utils.DateUtil;
import com.kxjl.brain.police.common.utils.GridPageUtil;
import com.kxjl.brain.police.common.utils.JsonUtil;
import com.kxjl.brain.police.dto.wx.LoginDto;
import com.kxjl.brain.police.dto.wx.WechatAlarmDTO;
import com.kxjl.brain.police.repository.bean.WechatAlarmObject;
import com.kxjl.brain.police.repository.bean.report.WechatCountDim;
import com.kxjl.brain.police.repository.mapper.WechatMapper;
import com.kxjl.brain.police.service.api.wx.WechatService;
import com.kxjl.brain.police.service.api.wx.WxUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 微信Service
 * @Author DengWei
 * @Date 2017/5/11 11:20
 */
@Service
public class WechatServiceImpl implements WechatService {

    @Autowired
    WechatMapper wechatMapper;

    @Autowired
    WxUtilsService wxUtilsService;

    @Override public BaseRespResult queryByList(WechatAlarmObject model) {
        PageHelper.startPage(model.getPageIndex(), model.getPageSize());
        List<WechatAlarmDTO> list= wechatMapper.queryByList(model);
        return BaseRespResult.build(BaseRespondCode.OK, GridPageUtil.createPageInfo(list));
    }

    @Override public BaseRespResult queryCountByStatus() {
        Map map = new HashMap<>();
        map.put("unWork",wechatMapper.queryCountByStatus("00")+"");//未处理
        map.put("hasWork",wechatMapper.queryCountByStatus("01")+"");//已处理并且同步到DS
        map.put("saveWork",wechatMapper.queryCountByStatus("02")+"");//已处理未同步到DS
        return BaseRespResult.build(BaseRespondCode.OK,map);
    }

    @Override public BaseRespResult queryById(Long id) {
        return BaseRespResult.build(BaseRespondCode.OK,wechatMapper.queryById(id));
    }

    @Override public BaseRespResult insertWechat(WechatAlarmObject model) {
        wechatMapper.insertWechat(model);
        System.out.println(JsonUtil.obj2json(model));
        //HttpWalker.visit(Constants.DS_SUB_URL).putBody(JsonUtil.obj2json(model)).post).getResponseStr();
        String sex = "";
        if("男".equals(model.getSex())){
            sex+="先生";
        }else{
            sex+="女士";
        }
        //后期做存状态加判断
        String msg = String.format(Constants.WECHAT_MSG,model.getName()+sex,
                DateUtil.DateToString(model.getAlarmdate(),"yyyy-MM-dd HH:mm:ss"),model.getDescription());
        wxUtilsService.wxSendMsg(model.getOpenid(),msg);
        return SearchResult.build(BaseRespondCode.OK);
    }

    @Override public BaseRespResult updateWechat(WechatAlarmObject model) {
        wechatMapper.updateWechat(model);
        return  SearchResult.build(BaseRespondCode.OK);
    }

    @Override public BaseRespResult deleteWechat(Long id) {
        wechatMapper.deleteWechat(id);
        return SearchResult.build(BaseRespondCode.OK);
    }

    @Override
    public BaseRespResult login(LoginDto param)
    {
        Map map  = new HashMap();
        List<LoginDto> list = wechatMapper.login(param);
        if (list != null && list.size()>0){
            map.put("phone",list.get(0).getAccount());
            map.put("imAccount",list.get(0).getAccount());
            map.put("password",list.get(0).getPassword());
            return BaseRespResult.build(BaseRespondCode.OK,map);
        }else {
            map.put("phone","");
            map.put("imAccount","");
            map.put("password","");
            return BaseRespResult.build(BaseRespondCode.Param_Exp,map);
        }
    }

    @Override
    public BaseRespResult report(Map param)
    {
        String startTime = param.get("startTime").toString();
        String endTime = param.get("endTime").toString();
        String type = param.get("returnType").toString();

        Long startLong = stringToLong(startTime);
        Long endLong = stringToLong(endTime);
        Date startDate = new Date(startLong);
        Date endDate = new Date(endLong);

        Calendar calendar_start = Calendar.getInstance();
        calendar_start.setTime(startDate);
        Calendar calendar_end = Calendar.getInstance();
        calendar_end.setTime(endDate);

        List<WechatCountDim> dimList = wechatMapper.report(startTime,endTime,type);
        System.out.println(JSON.toJSONString(dimList));
        List<String> dimListClone = new ArrayList<>();

        for (WechatCountDim dim:dimList){
            dimListClone.add(dim.getTime());
        }

        //如果按小时算，补全为0的时间数据比如说02,03,07等都有,缺少01,04,05,06,相当于补全这几个数据
        if ("h".equals(type)){
            for (int i=0;i<24;i++){
                if (!dimListClone.contains(int2Str(i))){
                    WechatCountDim dimTemp = new WechatCountDim();
                    dimTemp.setTime(int2Str(i));
                    dimTemp.setConnectNum(0);
                    dimList.add(dimTemp);
                }
            }
        }
        //按天算，计算天数
        if ("d".equals(type)){
            //获取天数
            int day = (int) ((endLong-startLong)/(24*60*60*1000))+1;
            //如果相差一个月
            /**
             * 下面这段代码是这个意思:比如monthstart=11,days=11也就是说今天是当月的11号
             * 会根据当前月有多少天:比如30天
             * 11/11,11/12,11/13,...,11/30
             */
            if (calendar_end.get(Calendar.MONTH)- calendar_start.get(Calendar.MONTH)==1){
                String monthstart = int2Str(calendar_start.get(Calendar.MONTH)+1);//返回第几月
                int days = calendar_start.get(Calendar.DATE);//返回第几天
                int daymax = calendar_start.getActualMaximum(Calendar.DAY_OF_MONTH);//返回当前月多少天
                for (int i= days;i<=daymax;i++) {
                    String loopday = monthstart+"/"+int2Str(i);
                    if (!dimListClone.contains(loopday)){
                        WechatCountDim dimTemp = new WechatCountDim();
                        dimTemp.setTime(loopday);
                        dimTemp.setConnectNum(0);
                        dimList.add(dimTemp);
                    }
                }
                /**
                 * 补全前面空缺的天数
                 * 11/01,11/02,...,11/10
                 */
                int dayC = day-(daymax-days+1);
                String monthend = int2Str(calendar_end.get(Calendar.MONTH)+1);
                for (int i= 1;i<=dayC;i++) {
                    String loopday = monthend+"/"+int2Str(i);
                    if (!dimListClone.contains(loopday)){
                        WechatCountDim dimTemp = new WechatCountDim();
                        dimTemp.setTime(loopday);
                        dimTemp.setConnectNum(0);
                        dimList.add(dimTemp);
                    }
                }
            }else if (calendar_end.get(Calendar.MONTH)- calendar_start.get(Calendar.MONTH)==0){
                String month = int2Str(calendar_start.get(Calendar.MONTH)+1);
                for (int i= calendar_start.get(Calendar.DATE);i<=calendar_end.get(Calendar.DATE);i++) {
                    String loopday = month+"/"+int2Str(i);
                    if (!dimListClone.contains(loopday)){
                        WechatCountDim dimTemp = new WechatCountDim();
                        dimTemp.setTime(loopday);
                        dimTemp.setConnectNum(0);
                        dimList.add(dimTemp);
                    }
                }
            }else {
                /**
                 * 第一次循环,起始时间不变,结束时间为月末
                 * 中间循环,起始时间为月初,结束时间为月末
                 * 最后一次循环,起始时间为月初,结束时间不变
                 */
                int begin=0;
                int end=0;
                int dis=0;
                int disMonth = calendar_end.get(Calendar.MONTH) - calendar_start.get(Calendar.MONTH) + 1;
                for (int num=0; num<disMonth; num++){
                    if (num == 0){
                        //起始时间变,设置结束时间为月末
                        begin = calendar_start.get(Calendar.DATE);
                        end = calendar_start.getActualMaximum(Calendar.DAY_OF_MONTH);
                    }else if (num < disMonth-1){
                        //获取一个月的最后一天
                        begin = 1;
                        int nextMonth = calendar_start.get(Calendar.MONTH)+dis;
                        int year = calendar_start.get(Calendar.YEAR);
                        Calendar c = Calendar.getInstance();
                        c.set(year,nextMonth,1);
                        end = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    }else if(num == disMonth-1){
                        //设置起始时间为月初,结束时间不变
                        begin = 1;
                        end = calendar_end.get(Calendar.DATE);
                    }

                    String month = int2Str(calendar_start.get(Calendar.MONTH)+dis+1);
                    for (int i=begin;i<=end;i++) {
                        String loopday = month+"/"+int2Str(i);
                        if (!dimListClone.contains(loopday)){
                            WechatCountDim dimTemp = new WechatCountDim();
                            dimTemp.setTime(loopday);
                            dimTemp.setConnectNum(0);
                            dimList.add(dimTemp);
                        }
                    }
                    dis++;
                }
            }

        }
//        int dayMax = endDate.getDate()-startDate.getDate()+1;

        //按月算
        if ("m".equals(type)){
            for (int i = calendar_start.get(Calendar.MONTH)+1;i<=calendar_end.get(Calendar.MONTH)+1;i++){
                if (!dimListClone.contains(int2Str(i))){
                    WechatCountDim dimTemp = new WechatCountDim();
                    dimTemp.setTime(int2Str(i));
                    dimTemp.setConnectNum(0);
                    dimList.add(dimTemp);
                }
            }
        }

        //按年算
        if ("y".equals(type)){
            for (int i = calendar_start.get(Calendar.YEAR);i<= calendar_end.get(Calendar.YEAR);i++){
                if (!dimListClone.contains(int2Str(i))){
                    WechatCountDim dimTemp = new WechatCountDim();
                    dimTemp.setTime(int2Str(i));
                    dimTemp.setConnectNum(0);
                    dimList.add(dimTemp);
                }
            }
        }
        Collections.sort(dimList);//对集合进行排序
        return BaseRespResult.build(BaseRespondCode.OK,dimList);
    }

    @Override
    public BaseRespResult queryDetail(Map data)
    {
        Long id = Long.parseLong(data.get("id").toString());
        List<WechatAlarmObject> wechatList = wechatMapper.queryDetail(id);
        return BaseRespResult.build(BaseRespondCode.OK,wechatList);
    }

    private String int2Str(int number){
        String[] array = {"00","01","02","03","04","05","06","07","08","09"};
        if (number>=0 && number <10){
            return array[number];
        }

        return Integer.toString(number);
    }

    private long takeRemainder(long number){
        return number%60000==0?number/60000:number/60000+1;
    }

    private Long stringToLong(String s){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sDate = null;
        try
        {
            sDate = format.parse(s);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return sDate.getTime();
    }
}
