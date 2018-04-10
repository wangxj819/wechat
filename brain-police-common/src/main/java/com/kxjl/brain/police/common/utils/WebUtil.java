package com.kxjl.brain.police.common.utils;

import com.kxjl.brain.police.common.Constants;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * web项目工具类
 * 

 */
public class WebUtil {
	
	/**
	 * 保存上传文件到webapp目录下
	 * 
	 * @param file 上传的文件对象
	 * @param path 路径
	 * @param fileName 文件名称
	 */ 
	public static void saveUploadFile(MultipartFile file, String path, String fileName){
		String realPath = Constants.REAL_FILE_ROOT+ File.separator+path;
		File file1 = new File(realPath);
		//判断文件夹是否存在,如果不存在则创建文件夹
		if (!file1.exists()) {
			file1.mkdir();
		}
		try {
			file.transferTo(new File(realPath,fileName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除上传的文件(webapp目录下)
	 * 
	 * @param path 路径
	 * @param fileName 文件名称
	 */ 
	public static void delUploadFile(String path, String fileName){
		String realPath = Constants.REAL_FILE_ROOT+ File.separator+path+File.separator+fileName;
		File file=new File(realPath);
		if(file.exists()){
			file.delete();
		}
	}
}
