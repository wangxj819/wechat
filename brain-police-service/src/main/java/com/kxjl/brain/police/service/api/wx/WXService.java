package com.kxjl.brain.police.service.api.wx;

import com.kxjl.arsenal.rest.result.SearchResult;
import com.kxjl.brain.police.dto.wx.QueryWXCallPoliceDTO;

/**
 * Created by xxqin on 2017/5/8.
 */
public interface WXService {

    SearchResult queryWXCallPolice(QueryWXCallPoliceDTO queryWXCallPoliceDTO);
   /* *//**
     * 获取Token
     *//*
    void startAccessToken();*/

    /**
     * 请求access token
     */
    String getAccessToken();

    /**
     * 请求js ticket
     */
    String getJSTicket();


    /**
     * 创建创菜单
     */
    void reloadWxMenu();
}
