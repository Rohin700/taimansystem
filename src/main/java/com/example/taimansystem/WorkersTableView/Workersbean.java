package com.example.taimansystem.WorkersTableView;

public class Workersbean {

//workersname primary key,address,mobileno,specializations
    String workersname;
    String address;
    String mobileno;
    String specializations;


    public Workersbean(String workersname, String address, String mobileno, String specializations) {
        this.workersname = workersname;
        this.address = address;
        this.mobileno = mobileno;
        this.specializations = specializations;
    }

    public String getWorkersname() {
        return workersname;
    }

    public void setWorkersname(String workersname) {
        this.workersname = workersname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getSpecializations() {
        return specializations;
    }

    public void setSpecializations(String specializations) {
        this.specializations = specializations;
    }
}
