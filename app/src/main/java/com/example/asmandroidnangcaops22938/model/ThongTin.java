package com.example.asmandroidnangcaops22938.model;

import java.io.Serializable;

public class ThongTin implements Serializable {
    private String date;
    private String address;

    public ThongTin(String date, String address) {
        this.date = date;
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
