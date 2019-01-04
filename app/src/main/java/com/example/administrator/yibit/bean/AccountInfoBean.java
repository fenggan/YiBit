package com.example.administrator.yibit.bean;

import java.util.List;

public class AccountInfoBean {
    /**
     * asset : [{"amount":164394.2,"asset_id":"JRC"},{"amount":11346.22,"asset_id":"BMAN"},{"amount":100000,"asset_id":"KXC"},{"amount":62,"asset_id":"MRL"}]
     * username : zxc1213
     */
    private List<AssetEntity> asset;
    private String username;

    public void setAsset(List<AssetEntity> asset) {
        this.asset = asset;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<AssetEntity> getAsset() {
        return asset;
    }

    public String getUsername() {
        return username;
    }

    public class AssetEntity {
        /**
         * amount : 164394.2
         * asset_id : JRC
         */
        private double amount;
        private String asset_id;

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setAsset_id(String asset_id) {
            this.asset_id = asset_id;
        }

        public double getAmount() {
            return amount;
        }

        public String getAsset_id() {
            return asset_id;
        }
    }
}
