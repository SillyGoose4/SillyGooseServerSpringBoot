package com.sillygoose.service.Model;

public class Pic {
    private Integer picId;

    private String picUrl;

    private String picBelong;

    private Character picLevel;

    private String picDescribe;

    public String getPicDescribe() {
        return picDescribe;
    }

    public void setPicDescribe(String picDescribe) {
        this.picDescribe = picDescribe;
    }

    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getPicBelong() {
        return picBelong;
    }

    public void setPicBelong(String picBelong) {
        this.picBelong = picBelong;
    }

    public Character getPicLevel() {
        return picLevel;
    }

    public void setPicLevel(Character picLevel) {
        this.picLevel = picLevel;
    }
}