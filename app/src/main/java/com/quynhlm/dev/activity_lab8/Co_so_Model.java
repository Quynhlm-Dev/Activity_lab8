package com.quynhlm.dev.activity_lab8;

public class Co_so_Model {
    private String name;
    private int imglogo;

    public Co_so_Model(String name, int imglogo) {
        this.name = name;
        this.imglogo = imglogo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImglogo() {
        return imglogo;
    }

    public void setImglogo(int imglogo) {
        this.imglogo = imglogo;
    }
}
