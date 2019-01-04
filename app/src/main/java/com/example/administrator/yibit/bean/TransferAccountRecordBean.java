package com.example.administrator.yibit.bean;

import java.util.List;

public class TransferAccountRecordBean{

    /**
     * data : [{"block_num":10947765,"form":"ray6","fee":{"asset_name":"JRC","asset_num":1},"to":"zxc1213","time":"2018-12-29T02:36:39","transfer_id":"1.11.234454","asset":{"asset_name":"JRC","asset_num":1}},{"block_num":10122379,"form":"lymjat2","fee":{"asset_name":"JRC","asset_num":1},"to":"zxc1213","time":"2018-11-29T08:23:30","transfer_id":"1.11.208969","asset":{"asset_name":"JRC","asset_num":1}},{"block_num":9630295,"form":"zxc1213","fee":{"asset_name":"JRC","asset_num":1.11},"to":"gdex-wallet-jrc","time":"2018-11-12T06:14:48","transfer_id":"1.11.188178","asset":{"asset_name":"JRC","asset_num":1.11}},{"block_num":9425695,"form":"yibit4","fee":{"asset_name":"JRC","asset_num":1.09},"to":"zxc1213","time":"2018-11-05T02:48:21","transfer_id":"1.11.175815","asset":{"asset_name":"JRC","asset_num":1.09}},{"block_num":8403109,"form":"zxc1213","fee":{"asset_name":"JRC","asset_num":1},"to":"yibit-wallet","time":"2018-09-30T03:35:54","transfer_id":"1.11.147024","asset":{"asset_name":"JRC","asset_num":1}},{"block_num":6822425,"form":"finchain3","fee":{"asset_name":"JRC","asset_num":1.11},"to":"zxc1213","time":"2018-08-06T02:13:51","transfer_id":"1.11.53295","asset":{"asset_name":"JRC","asset_num":1.11}},{"block_num":6822408,"form":"finchain3","fee":{"asset_name":"JRC","asset_num":1.11},"to":"zxc1213","time":"2018-08-06T02:13:00","transfer_id":"1.11.53294","asset":{"asset_name":"JRC","asset_num":1.11}},{"block_num":6736979,"form":"finchain3","fee":{"asset_name":"JRC","asset_num":1.11},"to":"zxc1213","time":"2018-08-03T03:01:00","transfer_id":"1.11.51004","asset":{"asset_name":"JRC","asset_num":1.11}},{"block_num":6736115,"form":"finChain-faucet","fee":{"asset_name":"JRC","asset_num":1.11},"to":"zxc1213","time":"2018-08-03T02:17:48","transfer_id":"1.11.50994","asset":{"asset_name":"JRC","asset_num":1.11}}]
     */
    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public class DataEntity {
        /**
         * block_num : 10947765
         * form : ray6
         * fee : {"asset_name":"JRC","asset_num":1}
         * to : zxc1213
         * time : 2018-12-29T02:36:39
         * transfer_id : 1.11.234454
         * asset : {"asset_name":"JRC","asset_num":1}
         */
        private int block_num;
        private String form;
        private FeeEntity fee;
        private String to;
        private String time;
        private String transfer_id;
        private AssetEntity asset;

        public void setBlock_num(int block_num) {
            this.block_num = block_num;
        }

        public void setForm(String form) {
            this.form = form;
        }

        public void setFee(FeeEntity fee) {
            this.fee = fee;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setTransfer_id(String transfer_id) {
            this.transfer_id = transfer_id;
        }

        public void setAsset(AssetEntity asset) {
            this.asset = asset;
        }

        public int getBlock_num() {
            return block_num;
        }

        public String getForm() {
            return form;
        }

        public FeeEntity getFee() {
            return fee;
        }

        public String getTo() {
            return to;
        }

        public String getTime() {
            return time;
        }

        public String getTransfer_id() {
            return transfer_id;
        }

        public AssetEntity getAsset() {
            return asset;
        }

        public class FeeEntity {
            /**
             * asset_name : JRC
             * asset_num : 1
             */
            private String asset_name;
            private double asset_num;

            public void setAsset_name(String asset_name) {
                this.asset_name = asset_name;
            }

            public void setAsset_num(int asset_num) {
                this.asset_num = asset_num;
            }

            public String getAsset_name() {
                return asset_name;
            }

            public double getAsset_num() {
                return asset_num;
            }
        }

        public class AssetEntity {
            /**
             * asset_name : JRC
             * asset_num : 1
             */
            private String asset_name;
            private double asset_num;

            public void setAsset_name(String asset_name) {
                this.asset_name = asset_name;
            }

            public void setAsset_num(int asset_num) {
                this.asset_num = asset_num;
            }

            public String getAsset_name() {
                return asset_name;
            }

            public double getAsset_num() {
                return asset_num;
            }
        }
    }
}
