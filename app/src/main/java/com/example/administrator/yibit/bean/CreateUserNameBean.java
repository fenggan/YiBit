package com.example.administrator.yibit.bean;

public class CreateUserNameBean {
    private String name;
    private String owner_key;
    private String active_key;
    private String memo_key;
    private String refcode;
    private String referrer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner_key() {
        return owner_key;
    }

    public void setOwner_key(String owner_key) {
        this.owner_key = owner_key;
    }

    public String getActive_key() {
        return active_key;
    }

    public void setActive_key(String active_key) {
        this.active_key = active_key;
    }

    public String getMemo_key() {
        return memo_key;
    }

    public void setMemo_key(String memo_key) {
        this.memo_key = memo_key;
    }

    public String getRefcode() {
        return refcode;
    }

    public void setRefcode(String refcode) {
        this.refcode = refcode;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }
}
