package com.ordersys.comon;

/**
 * Created by admin on 2019/1/8.
 */
public class PagingModel {
    private Integer pageindex;
    private Integer pagesize;
    private Integer totalrow;
    private Integer totalpage;
    private Object datalist;

    public Integer getPageindex() {
        return pageindex;
    }

    public void setPageindex(Integer pageindex) {
        this.pageindex = pageindex;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Integer getTotalrow() {
        return totalrow;
    }

    public void setTotalrow(Integer totalrow) {
        this.totalrow = totalrow;
    }

    public Integer getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(Integer totalpage) {
        this.totalpage = totalpage;
    }

    public Object getDatalist() {
        return datalist;
    }

    public void setDatalist(Object datalist) {
        this.datalist = datalist;
    }
}
