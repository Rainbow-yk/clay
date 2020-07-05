package com.nt.pojo;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable {

    private Long total;
    private List data;

    public PageResult() {
    }

    public PageResult(Long total, List data) {
        this.total = total;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
