package com.nt.pojo;

import java.io.Serializable;

/**
 * @author 99362
 */
public class TbCategory implements Serializable {

    private Integer id;
    private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
