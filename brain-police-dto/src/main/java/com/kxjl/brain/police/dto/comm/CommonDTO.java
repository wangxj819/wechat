package com.kxjl.brain.police.dto.comm;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * 通用模型对象，封装常用属性
 * @Author DengWei
 * @Date 2017/5/16 10:43
 */
public class CommonDTO {
	
	/*********查询属性*************/
	private Integer pageIndex;//分页：页码
	private Integer pageSize;//分页：每页记录数
	private String sortOrder;//排序方式:asc升序,desc降序
	private String sortName;//排序字段

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
