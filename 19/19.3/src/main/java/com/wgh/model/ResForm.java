//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wgh.model;

import java.util.Date;

public class ResForm {
    private int id = 0;
    private String name = "";
    private float startPrice = 0.0F;
    private int breadth = 0;
    private Date startTime = null;
    private Date endTime = null;
    private int hit = 0;
    private int isEnd = 0;
    private String picture = "";
    private float heightPrice = 0.0F;
    private int bidCount = 0;
    private String bidder = "";

    public ResForm() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getStartPrice() {
        return this.startPrice;
    }

    public void setStartPrice(float startPrice) {
        this.startPrice = startPrice;
    }

    public int getBreadth() {
        return this.breadth;
    }

    public void setBreadth(int breadth) {
        this.breadth = breadth;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getHit() {
        return this.hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getIsEnd() {
        return this.isEnd;
    }

    public void setIsEnd(int isEnd) {
        this.isEnd = isEnd;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setHeightPrice(float heightPrice) {
        this.heightPrice = heightPrice;
    }

    public float getHeightPrice() {
        return this.heightPrice;
    }

    public void setBidCount(int bidCount) {
        this.bidCount = bidCount;
    }

    public int getBidCount() {
        return this.bidCount;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public String getBidder() {
        return this.bidder;
    }
}
