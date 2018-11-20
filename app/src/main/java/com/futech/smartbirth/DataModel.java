package com.futech.smartbirth;

public class DataModel {


    private String tanggal;
    private String berat;

    public DataModel(){

    }

    public DataModel(String tanggal, String berat){
        this.tanggal = tanggal;
        this.berat = berat;
    }


    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }
}
