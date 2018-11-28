package com.futech.smartbirth;

public class User {

    private String nik, username, address, phone;

    public User(String nik, String username, String phone) {
        this.nik = nik;
        this.username = username;
        this.address = address;
        this.phone = phone;
    }

    public String getNik() {
        return nik;
    }


    public String getPhone() {
        return phone;
    }
}