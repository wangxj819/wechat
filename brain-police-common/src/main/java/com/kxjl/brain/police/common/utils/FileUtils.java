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
 * 1.0	     Administrator    2017/5/118:47	  Create	
 */
package com.kxjl.brain.police.common.utils;

import com.kxjl.brain.police.common.Constants;

import java.io.*;
/**
 * @Author DengWei
 * @Date 2017/5/11 9:06
 */
public class FileUtils {

    /**
     * 保存文件
     *
     * @param filePath
     * @param is
     * @author
     */
    public static boolean saveFile(String filePath, InputStream is) {
        String realPath = Constants.REAL_FILE_ROOT+ File.separator;
        OutputStream os = null;
        boolean operator = true;
        try {
            File file = createFile(realPath+ File.separator+filePath);
            os = new FileOutputStream(file);
            int byteCount;
            byte[] buf = new byte[1024];
            while ((byteCount = is.read(buf)) != -1) {
                os.write(buf, 0, byteCount);
            }
        } catch (Exception e) {
            operator = false;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return operator;
    }


    /**
     * 创建文件夹
     *
     * @param filePath
     * @return
     * @throws IOException
     * @author
     */
    private static File createFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            File pFile = file.getParentFile();
            if (!pFile.exists()) {
                pFile.mkdirs();
            }

            file.createNewFile();
        }
        return file;
    }


    /**
     * 删除文件
     *
     * @param filePath
     * @author ybzhu
     */
    public static void deleteFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.delete();
    }

}
