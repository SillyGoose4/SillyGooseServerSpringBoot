package com.sillygoose.service.Model;

import java.util.Date;

public class CollectTime {

    public CollectTime(){

    }

    public CollectTime(int userId){
        this.userId = userId;
    }

    public void InitTime(Date currentDate){
        this.devilLasttime = currentDate;
        this.rainLasttime = currentDate;
        this.snowLasttime = currentDate;
        this.starLasttime = currentDate;
        this.windLasttime = currentDate;
        this.sunLasttime = currentDate;
    }

    private Integer userId;

    private Date sunLasttime;

    private Date snowLasttime;

    private Date rainLasttime;

    private Date devilLasttime;

    private Date windLasttime;

    private Date starLasttime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getSunLasttime() {
        return sunLasttime;
    }

    public void setSunLasttime(Date sunLasttime) {
        this.sunLasttime = sunLasttime;
    }

    public Date getSnowLasttime() {
        return snowLasttime;
    }

    public void setSnowLasttime(Date snowLasttime) {
        this.snowLasttime = snowLasttime;
    }

    public Date getRainLasttime() {
        return rainLasttime;
    }

    public void setRainLasttime(Date rainLasttime) {
        this.rainLasttime = rainLasttime;
    }

    public Date getDevilLasttime() {
        return devilLasttime;
    }

    public void setDevilLasttime(Date devilLasttime) {
        this.devilLasttime = devilLasttime;
    }

    public Date getWindLasttime() {
        return windLasttime;
    }

    public void setWindLasttime(Date windLasttime) {
        this.windLasttime = windLasttime;
    }

    public Date getStarLasttime() {
        return starLasttime;
    }

    public void setStarLasttime(Date starLasttime) {
        this.starLasttime = starLasttime;
    }
}