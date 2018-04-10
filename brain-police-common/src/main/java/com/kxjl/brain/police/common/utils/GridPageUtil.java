package com.kxjl.brain.police.common.utils;

import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页工具类
 * @Author DengWei
 * @Date 2017/5/16 14:09
 */
public class GridPageUtil {
	
	/**
	 * 创建分页信息(mybatis分页插件专用)
	 * 
	 * @param queryResult 分页查询结果
	 * 
	 * @return 分页结果map
	 */
	@SuppressWarnings("unchecked")
	public static Map createPageInfo(Object queryResult){
		Page page = (Page)queryResult;
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("pageIndex", page == null ? 0 : page.getPageNum());//当前页
		pageMap.put("totalPage", page == null ? 0 : page.getPages());//总页数
		pageMap.put("totalRows", page == null ? 0 : page.getTotal());//总记录数
		pageMap.put("rows", page == null ? "" : page.getResult());//记录集合
		return pageMap;
	}
	
}
