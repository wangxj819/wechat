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
 * 1.0	     Administrator    2017/6/314:24	  Create	
 */
package com.kxjl.brain.police.common.utils;

import com.google.common.collect.Lists;
import org.apache.commons.collections.map.HashedMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 输入流转成临时文件提取书签
 * @Author DengWei
 * @Date 2017/6/3 15:57
 */
public class BookMarkStart {

    /**
     * 提取word文件的书签
     * @param in 输入流
     * @param fileType 文件类型
     * @return
     */
    public static List<String> modifyDocumentAndSave(InputStream in,String fileType){
        File file = null;
        List<String> list = null;
        ZipFile docxFile = null;
        try{
           list = Lists.newArrayList();
           file = File.createTempFile("tmp", "."+fileType);
           FileOutputStream fs = new FileOutputStream(file);
           byte [] buff = new byte[1024];
           int len = 0;
           while ((len = in.read(buff))>0){
               fs.write(buff,0,len);
           }
           fs.flush();
           fs.close();
           in.close();

           // prints absolute path
           System.out.println("Temp File path: "+file.getAbsolutePath());
           docxFile = new ZipFile(file);
           ZipEntry documentXML = docxFile.getEntry("word/document.xml");
           InputStream documentXMLIS = docxFile.getInputStream(documentXML);
           DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           Document doc = dbf.newDocumentBuilder().parse(documentXMLIS);


           /**
            * 书签列表
            */
           NodeList this_book_list = doc.getElementsByTagName("w:bookmarkStart");
           if (this_book_list.getLength() != 0) {
               for (int j = 0; j < this_book_list.getLength(); j++) {
                   // 获取每个书签
                   Element oldBookStart = (Element) this_book_list.item(j);
                   // 书签名
                   //String bookMarkName = oldBookStart.getAttribute("w:name");
                   //System.out.println(bookMarkName);
                   list.add(oldBookStart.getAttribute("w:name"));

               }
           }
           docxFile.close();
           file.delete();
       }catch (Exception e){
            try {
                docxFile.close();
                file.delete();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
           e.printStackTrace();
       }
        return list;
    }


    public static void main(String [] args){
       try {
            File file=new File("D:\\Users\\Administrator\\Desktop\\预案\\docx");
            File[] tempList = file.listFiles();
            List lists =  Lists.newArrayList();
            for (int i=0;i<tempList.length;i++){
                Map<String,Object> obj = new HashedMap();
                InputStream in = new FileInputStream(tempList[i]);
                String fileName = getFileNameNoEx(tempList[i].getName());
                System.out.println(fileName);
                obj.put("fileName",fileName);
                obj.put("mark",modifyDocumentAndSave(in,"docx"));
                lists.add(obj);
            }
           System.out.println(JsonUtil.obj2json(lists));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
     }

    /**
     * 获取文件名称
     * @param filename
     * @return
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

}
