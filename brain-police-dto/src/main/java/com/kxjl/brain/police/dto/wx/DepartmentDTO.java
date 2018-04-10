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
 * 1.0	     Administrator    2017/5/249:25	  Create	
 */
package com.kxjl.brain.police.dto.wx;

/**
 * 科室
 * @Author DengWei
 * @Date 2017/5/24 13:47
 */
public class DepartmentDTO {

    /**
     * 科室ID
     */
    private Long id;
    /**
     *部门ID
     */
    private Long pId;

    /**
     * 部门名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 部门or科室编号
     */
    private String dnumber;

    public String getDnumber() {
        return dnumber;
    }

    public void setDnumber(String dnumber) {
        this.dnumber = dnumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
