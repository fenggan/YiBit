package com.example.administrator.yibit.bean;

import java.util.List;

public class RechargeBean {
    /**
     * create_time : 2018-09-17T01:51:02.465Z
     * bts_account : zxc1213
     * state : true
     * id : 110
     * depositAddresss : [{"update_time":"2018-09-17T03:55:32.302Z","address":"0xe9aCA9ccD39600EbBF115E2e00E1AA9f6B0B2C97","address_type":"ETH","create_time":"2018-09-17T03:55:32.302Z","bts_deposit_address_id":110,"id":105}]
     */
    private String create_time;
    private String bts_account;
    private boolean state;
    private int id;
    private List<DepositAddresssEntity> depositAddresss;

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

    public void setDepositAddresss(List<DepositAddresssEntity> depositAddresss) {
        this.depositAddresss = depositAddresss;
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

    public List<DepositAddresssEntity> getDepositAddresss() {
        return depositAddresss;
    }

    public class DepositAddresssEntity {
        /**
         * update_time : 2018-09-17T03:55:32.302Z
         * address : 0xe9aCA9ccD39600EbBF115E2e00E1AA9f6B0B2C97
         * address_type : ETH
         * create_time : 2018-09-17T03:55:32.302Z
         * bts_deposit_address_id : 110
         * id : 105
         */
        private String update_time;
        private String address;
        private String address_type;
        private String create_time;
        private int bts_deposit_address_id;
        private int id;

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setAddress_type(String address_type) {
            this.address_type = address_type;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public void setBts_deposit_address_id(int bts_deposit_address_id) {
            this.bts_deposit_address_id = bts_deposit_address_id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public String getAddress() {
            return address;
        }

        public String getAddress_type() {
            return address_type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public int getBts_deposit_address_id() {
            return bts_deposit_address_id;
        }

        public int getId() {
            return id;
        }
    }
}
