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
 * 1.0	     Administrator    2017/5/1013:36	  Create	
 */
package com.kxjl.brain.police.api.context.wx;

import com.alibaba.fastjson.JSONObject;
import com.kxjl.arsenal.fileservice.KxjlOssClient;
import com.kxjl.arsenal.fileservice.OssResult;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.brain.police.common.Constants;
import com.kxjl.brain.police.common.utils.FileUtils;
import com.kxjl.brain.police.common.utils.HttpWalker;
import com.kxjl.brain.police.service.api.wx.WXService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 上传mediaId
 * @Author DengWei
 * @Date 2017/5/11 9:08
 */
public class UploadVoiceMediaIdContext extends BaseContext<Map, String> {

    private WXService wxService;

    protected UploadVoiceMediaIdContext(String businessId, String body, String token, LogModule log) {
        super(businessId,body, token, log);
    }

    public static UploadVoiceMediaIdContext build(String businessId, String body, String token, LogModule log) {
        return new UploadVoiceMediaIdContext(businessId, body, token, log);
    }


    public UploadVoiceMediaIdContext setService(WXService wxService){
        this.wxService = wxService;
        return this;
    }

    @Override protected void checkAfter() {

    }

    @Override protected Map parse() {
        System.out.println(body);
        JSONObject jsonObject = JSONObject.parseObject(body);
        return jsonObject;
    }


    @Override protected BaseRespResult run() {

        String  url =  String.format(Constants.WX_FILE_DOWNLOAD_URL,wxService.getAccessToken(), dto.get("mediaId").toString());
        InputStream in =  HttpWalker.visit(url).download().getResponseStream();
        String amrpath = Constants.WX_VOICE_AMR_PATH+ File.separator+dto.get("mediaId")+".amr";
        //String mp3path = Constants.REAL_FILE_ROOT+File.separator+Constants.WX_VOICE_MP3_PATH+ File.separator+dto.get("mediaId")+".mp3";
        List list = null;
        if(FileUtils.saveFile(amrpath,in)){
            amrpath = Constants.REAL_FILE_ROOT+File.separator+amrpath;
            //AmrToMp3Utils.changeToMp3(amrpath,mp3path);
            KxjlOssClient kxjlOssClient = new KxjlOssClient(Constants.OSS_BASE_URL);
            OssResult ossResult = null;
            list = new ArrayList();
            //ossResult = kxjlOssClient.upload(in,Constants.WX_VOICE_AMR_OSS_PATH+dto.get("mediaId") + ".amr");
            try {
                ossResult = kxjlOssClient.upload(new FileInputStream(new File(amrpath)),Constants.WX_VOICE_AMR_OSS_PATH+dto.get("mediaId") + ".amr");
                if("0".equals(ossResult.getCode()+"")){
                    System.out.println(Constants.WX_VOICE_AMR_OSS_PATH+dto.get("mediaId") + ".amr");
                    list.add(Constants.WX_VOICE_AMR_OSS_PATH+dto.get("mediaId") + ".amr");
                    //删除本地录音文件
                    FileUtils.deleteFile(amrpath);
                   // FileUtils.deleteFile(amrpath);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return BaseRespResult.build(BaseRespondCode.OK,list);
    }
}
