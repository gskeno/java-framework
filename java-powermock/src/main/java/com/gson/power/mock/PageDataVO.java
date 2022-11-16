package com.gson.power.mock;

import java.util.List;

public class PageDataVO<V> {
    private Integer total;

    private List<V> pageData;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<V> getPageData() {
        return pageData;
    }

    public void setPageData(List<V> pageData) {
        this.pageData = pageData;
    }

    public PageDataVO(Integer total, List<V> pageData) {
        this.total = total;
        this.pageData = pageData;
    }

    @Override
    public String toString() {
        return "PageDataVO{" +
                "total=" + total +
                ", pageData=" + pageData +
                '}';
    }
}
