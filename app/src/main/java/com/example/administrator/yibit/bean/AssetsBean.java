package com.example.administrator.yibit.bean;

public class AssetsBean {

    /**
     * lockded_balances : {"locked_balance":1009.89,"lock_time":1546478289,"initial_lock_balance":"100000000000","interest":0,"lock_type":"userSet","lock_period":1728000,"locked_id":"1.16.1488"}
     * data : {"amount":163393.2,"asset_id":"1.3.0","asset":"JRC"}
     */
    private Lockded_balancesEntity lockded_balances;
    private DataEntity data;

    public void setLockded_balances(Lockded_balancesEntity lockded_balances) {
        this.lockded_balances = lockded_balances;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public Lockded_balancesEntity getLockded_balances() {
        return lockded_balances;
    }

    public DataEntity getData() {
        return data;
    }

    public class Lockded_balancesEntity {
        /**
         * locked_balance : 1009.89
         * lock_time : 1546478289
         * initial_lock_balance : 100000000000
         * interest : 0
         * lock_type : userSet
         * lock_period : 1728000
         * locked_id : 1.16.1488
         */
        private double locked_balance;
        private int lock_time;
        private String initial_lock_balance;
        private int interest;
        private String lock_type;
        private int lock_period;
        private String locked_id;

        public void setLocked_balance(double locked_balance) {
            this.locked_balance = locked_balance;
        }

        public void setLock_time(int lock_time) {
            this.lock_time = lock_time;
        }

        public void setInitial_lock_balance(String initial_lock_balance) {
            this.initial_lock_balance = initial_lock_balance;
        }

        public void setInterest(int interest) {
            this.interest = interest;
        }

        public void setLock_type(String lock_type) {
            this.lock_type = lock_type;
        }

        public void setLock_period(int lock_period) {
            this.lock_period = lock_period;
        }

        public void setLocked_id(String locked_id) {
            this.locked_id = locked_id;
        }

        public double getLocked_balance() {
            return locked_balance;
        }

        public int getLock_time() {
            return lock_time;
        }

        public String getInitial_lock_balance() {
            return initial_lock_balance;
        }

        public int getInterest() {
            return interest;
        }

        public String getLock_type() {
            return lock_type;
        }

        public int getLock_period() {
            return lock_period;
        }

        public String getLocked_id() {
            return locked_id;
        }
    }

    public class DataEntity {
        /**
         * amount : 163393.2
         * asset_id : 1.3.0
         * asset : JRC
         */
        private double amount;
        private String asset_id;
        private String asset;

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setAsset_id(String asset_id) {
            this.asset_id = asset_id;
        }

        public void setAsset(String asset) {
            this.asset = asset;
        }

        public double getAmount() {
            return amount;
        }

        public String getAsset_id() {
            return asset_id;
        }

        public String getAsset() {
            return asset;
        }
    }
}
