package com.nt.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 99362
 */
public class TbUser implements Serializable {

    private Integer id;
    private String nickname;
    private String mobile;
    private String password;
    private String avatarUrl;
    private String sex;
    private String brief;
    private Date updateTime;
    private Date createTime;
    private boolean isDelete;

    public TbWorkInfo getWorkInfo() {
        return workInfo;
    }

    public void setWorkInfo(TbWorkInfo workInfo) {
        this.workInfo = workInfo;
    }

    private TbWorkInfo workInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

}
