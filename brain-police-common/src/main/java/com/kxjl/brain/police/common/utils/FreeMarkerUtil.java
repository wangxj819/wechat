package com.kxjl.brain.police.common.utils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * FreeMarker工具
 * 
 * @author yhsu
 * @create 2015年12月22日下午6:51:18
 * @version 1.0
 * 
 */
public class FreeMarkerUtil {
	/**
	 * 日志记录文件
	 */
	private static Logger log = LoggerFactory.getLogger(FreeMarkerUtil.class);

	/**
	 * 模板字符集编码
	 */
	private static final String TEMPLATE_ENCODING = "UTF-8";

	/**
	 * 模板位置
	 */
	private static final String TEMPLATE_PATH;

	/**
	 * FreeMark配置
	 */
	private static Configuration config;

	static {

		TEMPLATE_PATH = System.getProperty("brain.api.root")+"/WEB-INF/ftl/";
		/**
		 * 创建Configuration对象
		 */
		config = new Configuration();
		/**
		 * 指定模板路径
		 */
		File file = new File(TEMPLATE_PATH);
		/**
		 * 设置要解析的模板所在的目录，并加载模板文件
		 */
		try {
			config.setDirectoryForTemplateLoading(file);
		} catch (IOException e) {
			log.error("FreeMarker初始化报错：" + TEMPLATE_PATH + "目录未找到", e);
			e.printStackTrace();
		}
		/**
		 * 设置包装器，并将对象包装为数据模型
		 */
		config.setObjectWrapper(new DefaultObjectWrapper());
	}

	/**
	 *
	 * @param templateName
	 *            模板文件名称
	 * @param templateEncoding
	 *            模板文件的编码方式
	 * @param root
	 *            数据模型根对象
	 */
	public static String analysisTemplate(String templateName,
			String templateEncoding, Map<?, ?> root) {
		StringWriter writer = null;
		try {
			/**
			 * 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			 */
			Template template = config.getTemplate(templateName,
					templateEncoding);

			/**
			 * 合并数据模型与模板
			 */
			writer = new StringWriter();

			template.process(root, writer);
			return writer.toString();
		} catch (IOException e) {
			log.error("读取模板位置错误：" + e.getStackTrace());
		} catch (TemplateException e) {
			log.error("适配模板错误：" + e.getStackTrace());
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * @description 渲染相关的FreeMark模板数据
	 * @author yhsu
	 * @create 2015年12月22日下午6:54:53
	 * @version 1.0
	 * @param templateName
	 * @param root
	 * @return
	 */
	public static String analysisTemplate(String templateName, Map<?, ?> root) {
		return analysisTemplate(templateName, TEMPLATE_ENCODING, root);
	}

}
