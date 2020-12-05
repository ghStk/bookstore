package com.ghstk.domain;

import java.util.List;

/**
 * 2020/11/26 15:03
 */
public class Page<T> {
    public static final int PAGE_SIZE = 4;
    private int pageNo;
    private int pageTotal;
    private int itemCount;
    private List<T> items;
    private String url;

    public Page() {
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            pageNo = 1;
        } else if (pageTotal != 0) { //pageTotal如果已经初始化完成
            pageNo = Math.min(pageNo, pageTotal);
        }
        this.pageNo = pageNo;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", itemCount=" + itemCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
