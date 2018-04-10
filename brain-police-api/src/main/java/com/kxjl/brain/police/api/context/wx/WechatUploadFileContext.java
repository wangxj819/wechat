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
 * 1.0	     Administrator    2017/5/1015:23	  Create	
 */
package com.kxjl.brain.police.api.context.wx;

import com.google.common.collect.Lists;
import com.kxjl.arsenal.fileservice.KxjlOssClient;
import com.kxjl.arsenal.fileservice.OssResult;
import com.kxjl.arsenal.log.LogModule;
import com.kxjl.arsenal.rest.code.BaseRespondCode;
import com.kxjl.arsenal.rest.context.BaseContext;
import com.kxjl.arsenal.rest.result.BaseRespResult;
import com.kxjl.arsenal.restnew.event.ApiEvent;
import com.kxjl.brain.police.common.Constants;
import com.kxjl.brain.police.common.utils.JsonUtil;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文件上传处理Context
 * @Author DengWei
 * @Date 2017/5/11 9:08
 */
public class WechatUploadFileContext extends BaseContext<MultipartFormDataInput, MultipartFormDataInput> {

    private String OBJECT_PARAM = "object";

    private String fileNameSuffix = ".png";

    private List<InputPart> files = null;

    private static final String FILE_SUFFIX_PARAM = "suffix";

    protected WechatUploadFileContext(String businessId, MultipartFormDataInput input, String token,
            LogModule log) {
        super(businessId, input, token, log);
    }


    public static WechatUploadFileContext build(String businessId,  MultipartFormDataInput input, String token, LogModule log) {
        return new WechatUploadFileContext(businessId, input, token, log);
    }

    @Override protected void checkBefore() {
       return;
    }

    @Override protected void checkAfter() {

        Map<String, List<InputPart>> formData = body.getFormDataMap();
        if (formData == null) {
            setResult(BaseRespResult.build(BaseRespondCode.Param_RequestBody_Empty_Exp));
            return;
        }

       try {
            fileNameSuffix = formData.get(FILE_SUFFIX_PARAM).get(0).getBodyAsString();
        } catch (IOException e) {
            setResult(BaseRespResult.build(BaseRespondCode.Param_RequestBody_Empty_Exp));
            return;
        }
        files = formData.get(OBJECT_PARAM);
        if (files == null || files.size() == 0) {
            setResult(BaseRespResult.build(BaseRespondCode.Param_RequestBody_Empty_Exp));
            return;
        }

    }

   @Override protected MultipartFormDataInput parse() {
        return super.parse();
    }

    /**
     * 上传文件支持多个
     *
     * @return
     */
    @Override protected BaseRespResult run() {

        List list = Lists.newArrayList();
        KxjlOssClient kxjlOssClient = new KxjlOssClient(Constants.OSS_BASE_URL);
        OssResult ossResult = null;

        for ( InputPart inputPart :files) {
            String fileName=new Date().getTime()+fileNameSuffix;
            InputStream is = null;
            try {
                is = inputPart.getBody(InputStream.class, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(fileNameSuffix.indexOf("png") != -1 || fileNameSuffix.indexOf("jpg") != -1 || fileNameSuffix.indexOf("gif") != -1){
                ossResult = kxjlOssClient.upload(is,Constants.IMGPATH+fileName);
            }else{
                ossResult = kxjlOssClient.upload(is,Constants.VIDEOPATH+fileName);
            }
            if("0".equals(ossResult.getCode()+"")){
                if(fileName.indexOf("png") != -1 || fileName.indexOf("jpg") != -1 || fileName.indexOf("gif") != -1){
                    list.add(Constants.IMGPATH+fileName);
                }else{
                    list.add(Constants.VIDEOPATH+fileName);
                }
            }
            //不考虑上传失败的情况
            /*else{
                return BaseRespResult.build(BaseRespondCode.BaseExp,ossResult.getMessage());
            }*/
        }
        log.info(ApiEvent.CHECK_AFTER,JsonUtil.obj2json(list));
        return BaseRespResult.build(BaseRespondCode.OK,list);

    }



}
