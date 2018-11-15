//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wgh.model;

import java.util.Date;

public class BidListForm {
    private int id = 0;
    private Date bidTime = null;
    private String state = "";
    private String bidder = "";
    private float bid = 0.0F;

    public BidListForm() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBidTime() {
        return this.bidTime;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBidder() {
        return this.bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public float getBid() {
        return this.bid;
    }

    public void setBid(float bid) {
        this.bid = bid;
    }
}
