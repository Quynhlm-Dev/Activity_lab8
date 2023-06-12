package com.quynhlm.dev.activity_lab8;

public class Sinh_Vien_model {
    private String co_so;
    private String name;
    private String diaChi;

    public Sinh_Vien_model(String co_so, String name, String diaChi) {
        this.co_so = co_so;
        this.name = name;
        this.diaChi = diaChi;
    }

    public String getCo_so() {
        return co_so;
    }

    public void setCo_so(String co_so) {
        this.co_so = co_so;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
