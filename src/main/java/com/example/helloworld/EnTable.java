package com.example.helloworld;

public class EnTable {
    int id=0;
    String dd;

    public String getDd() {
        return dd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }
    public EnTable(int id,String dd){
        this.dd=dd;
        this.id=id;
    }
}
