package com.example.app_nghe_nhac;

public class Bai_hat {
    String ten_bai_hat;
    int link_baihat;

    public Bai_hat(String ten_bai_hat, int link_baihat) {
        this.ten_bai_hat = ten_bai_hat;
        this.link_baihat = link_baihat;
    }

    public void setTen_bai_hat(String ten_bai_hat) {
        this.ten_bai_hat = ten_bai_hat;
    }

    public void setLink_baihat(int link_baihat) {
        this.link_baihat = link_baihat;
    }

    public String getTen_bai_hat() {
        return ten_bai_hat;
    }

    public int getLink_baihat() {
        return link_baihat;
    }
}
