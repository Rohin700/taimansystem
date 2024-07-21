package com.example.taimansystem.ExploreHub;

import java.util.Date;

public class ExploreHubbean {

   //measurements(Orderid varchar primary key,Mobile_no varchar,dress varchar,pic varchar(100),Deliv_date date,qty int(10),Price_per_unit int(10),bill int(10),measurements varchar(100),worker_assign varchar(30),doorder date);
    String Orderid;
    String Mobile_no;
    String dress;
    String Deliv_date;
    Integer qty;
    Integer bill;
    String worker_assign;
    String status;

    public ExploreHubbean(String orderid, String dress, String mobile_no, String deliv_date, Integer qty, Integer bill, String worker_assign, String status) {
        Orderid = orderid;
        this.dress = dress;
        Mobile_no = mobile_no;
        Deliv_date = deliv_date;
        this.qty = qty;
        this.bill = bill;
        this.worker_assign = worker_assign;
        this.status = status;
    }

    public String getOrderid() {
        return Orderid;
    }

    public void setOrderid(String orderid) {
        Orderid = orderid;
    }

    public String getMobile_no() {
        return Mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        Mobile_no = mobile_no;
    }

    public String getDress() {
        return dress;
    }

    public void setDress(String dress) {
        this.dress = dress;
    }

    public String getDeliv_date() {
        return Deliv_date;
    }

    public void setDeliv_date(String deliv_date) {
        Deliv_date = deliv_date;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getBill() {
        return bill;
    }

    public void setBill(Integer bill) {
        this.bill = bill;
    }

    public String getWorker_assign() {
        return worker_assign;
    }

    public void setWorker_assign(String worker_assign) {
        this.worker_assign = worker_assign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
