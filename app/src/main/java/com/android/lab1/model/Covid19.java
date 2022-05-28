package com.android.lab1.model;

import java.io.Serializable;

public class Covid19 implements Serializable {
    private int id;
    private String name, cauTruc, ngayXuatHien, vacxin;
    private int soLuong;

    public Covid19(int id, String name, String cauTruc, String ngayXuatHien, String vacxin, int soLuong) {
        this.id = id;
        this.name = name;
        this.cauTruc = cauTruc;
        this.ngayXuatHien = ngayXuatHien;
        this.vacxin = vacxin;
        this.soLuong = soLuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCauTruc() {
        return cauTruc;
    }

    public void setCauTruc(String cauTruc) {
        this.cauTruc = cauTruc;
    }

    public String getNgayXuatHien() {
        return ngayXuatHien;
    }

    public void setNgayXuatHien(String ngayXuatHien) {
        this.ngayXuatHien = ngayXuatHien;
    }

    public String getVacxin() {
        return vacxin;
    }

    public void setVacxin(String vacxin) {
        this.vacxin = vacxin;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
