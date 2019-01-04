package com.example.administrator.yibit.bean;

public class CreateQRAdressBean {

    /**
     * res : {"create_time":"2019-01-03T07:05:57.663Z","bts_account":"asd1232","state":true,"id":953}
     * flag : success
     */
    private ResEntity res;
    private String flag;

    public void setRes(ResEntity res) {
        this.res = res;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ResEntity getRes() {
        return res;
    }

    public String getFlag() {
        return flag;
    }

    public class ResEntity {
        /**
         * create_time : 2019-01-03T07:05:57.663Z
         * bts_account : asd1232
         * state : true
         * id : 953
         */
        private String create_time;
        private String bts_account;
        private boolean state;
        private int id;

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public void setBts_account(String bts_account) {
            this.bts_account = bts_account;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public String getBts_account() {
            return bts_account;
        }

        public boolean isState() {
            return state;
        }

        public int getId() {
            return id;
        }
    }
}
