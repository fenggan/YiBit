package com.example.administrator.yibit.bean;

public class PrivateKeyBean {

    private String privateKey;
    public PrivateKeyBean(String privateKey){
        this.privateKey=privateKey;
    }
    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
