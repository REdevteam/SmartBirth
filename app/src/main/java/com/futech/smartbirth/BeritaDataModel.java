package com.futech.smartbirth;

public class BeritaDataModel {

    private String judulBerita;
    private String tanggalBerita;
    private String deskripsiBerita;

    public BeritaDataModel(){

    }

    public BeritaDataModel(String judulBerita, String deskripsiBerita, String tanggalBerita){
        this.judulBerita = judulBerita;
        this.deskripsiBerita = deskripsiBerita;
        this.tanggalBerita = tanggalBerita;
    }

    public String getJudulBerita() {
        return judulBerita;
    }

    public void setJudulBerita(String judulBerita) {
        this.judulBerita = judulBerita;
    }

    public String getTanggalBerita() {
        return tanggalBerita;
    }

    public void setTanggalBerita(String tanggalBerita) {
        this.tanggalBerita = tanggalBerita;
    }

    public String getDeskripsiBerita() {
        return deskripsiBerita;
    }

    public void setDeskripsiBerita(String deskripsiBerita) {
        this.deskripsiBerita = deskripsiBerita;
    }
}
