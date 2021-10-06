package com.boxuegu.crm.common.bean;

/**
 * 分页查询基础query
 *
 * @author lsx
 * @date 2021/10/6 13:44
 */
public class PageQuery {
    private Integer pageNum;
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum == null ? 0:pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 0:pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
