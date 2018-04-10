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
 * 1.0	     Administrator    2017/5/109:06	  Create	
 */
package com.kxjl.brain.police.common.utils;

import com.kxjl.arsenal.fileservice.KxjlOssClient;
import com.kxjl.arsenal.fileservice.OssResult;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;
/**
  * 将Word转成Html
  * Author:DengWei
  * DATE:2017/7/5 14:54
 **/
public class WordToHtmlUtil {
    private static String PATH="";
    private static String OSS_BASE_URL = "";
    public static String getHtmlByWord(InputStream input,String path,String oss_base_url) throws Throwable{
        PATH = path;
        OSS_BASE_URL = oss_base_url;
        KxjlOssClient kxjlOssClient = new KxjlOssClient(OSS_BASE_URL);
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
                DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .newDocument());
        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
            public String savePicture(byte[] content, PictureType pictureType,
                                      String suggestedName, float widthInches, float heightInches) {
                return suggestedName;
            }
        });
        wordToHtmlConverter.processDocument(wordDocument);
        List pics = wordDocument.getPicturesTable().getAllPictures();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                Picture pic = (Picture) pics.get(i);
                try {
                    pic.writeImageContent(new FileOutputStream(PATH
                            + pic.suggestFullFileName()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        Document htmlDocument = wordToHtmlConverter.getDocument();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");
        serializer.transform(domSource, streamResult);
        outStream.close();
        String content = new String(outStream.toByteArray());
        File ff=new File(PATH,DateUtil.getNowDate("yyyyMMddHHmmss")+".html");
        FileUtils.write(ff, content, "utf-8");
        String fileName=DateUtil.getNowDate("yyyyMMddHHmmss");
        OssResult ossResult = kxjlOssClient.upload(new FileInputStream(ff),"/110/wx/html/"+fileName+".html");
        if("0".equals(ossResult.getCode()+"")){
            ff.delete();
            return "/110/wx/html/"+fileName+".html";
        }else{
            return null;
        }


    }


    public static void main(String [] args){

        try {
            FileInputStream inputStream = new FileInputStream(new File("D:\\Users\\Administrator\\Desktop\\110文档存档\\预案\\docx\\合肥市公安局处置道路交通事故工作预案（试行）.doc"));
            String url =  getHtmlByWord(inputStream,"C:\\","http://172.16.1.64:8185/yungo-oss");
            System.out.print(url);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
