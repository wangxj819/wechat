package com.kxjl.brain.police.common.utils;

import it.sauronsoftware.jave.*;

import java.io.File;
import java.io.InputStream;

/**
 * amr转成mp3格式
 * Created by Dengwei on 2017/11/3.
 */
public class AmrToMp3Utils {
    public static void changeToMp3(String sourcePath, String targetPath) {
        File source = new File(sourcePath);//要转换的文件
        File target = new File(targetPath);//要生成的目标文件
        AudioAttributes audio = new AudioAttributes();
        EncodingAttributes attrs = new EncodingAttributes();
        Encoder encoder = new Encoder();
        audio.setCodec("libmp3lame");//载入要生成的文件格式相应的编码器
        audio.setChannels(new Integer(1));
        audio.setSamplingRate(new Integer(8000));//设置采样率
        attrs.setFormat("mp3");//设置生成格式
        attrs.setAudioAttributes(audio);

        try {
            encoder.encode(source, target, attrs);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InputFormatException e) {
            e.printStackTrace();
        } catch (EncoderException e) {
//            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
        url =  String.format(url,"vNUZawCTG4tzPmCj88TH4fVsCFapp7khj77B4anOZbmXhGx1KH2vSiT5akBgVgWn8qXJfvsUpR1kWG9FnJ5o5ljXcnfhGYL3-bxnMfIXQ9N2v7YgR_aE9fkJE8PcJ3s9VFAeACAEOJ","hk91_ChWtRT-qBFJg-22Yy362B-JIWgk3VdKPJ0R8bXrsUU40F8YCsL-VRbSJrQA" );

        InputStream in =  HttpWalker.visit(url).download().getResponseStream();
        FileUtils.saveFile("F:\\hk91_ChWtRT-qBFJg-22Yy362B-JIWgk3VdKPJ0R8bXrsUU40F8YCsL-VRbSJrQA.amr",in);

//        String old ="F:\\hk91_ChWtRT-qBFJg-22Yy362B-JIWgk3VdKPJ0R8bXrsUU40F8YCsL-VRbSJrQA.amr";
//        String neww = "F:\\1111.mp3";
//        changeToMp3(old,neww);
    }
}
