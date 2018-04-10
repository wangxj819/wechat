package com.kxjl.brain.police.dto.wx;

/**
 * Created by xxqin on 2017/5/8.
 */
public class QueryWXCallPoliceDTO {
    private int pageIndex = 1;

    private int pageSize = 10;

    private Long businessId;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
}
