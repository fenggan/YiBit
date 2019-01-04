package com.example.administrator.yibit.bean;

import java.util.List;

public class OrderListBean {

    /**
     * data : [{"seller":"1.2.24323","w_price":"0.01","deferred_fee":100000000,"sell_price":{"quote":{"amount":1000000000,"asset_id":"1.3.1"},"base":{"amount":1000000000,"asset_id":"1.3.0"}},"for_sale":1000000000,"expiration":"2023-12-20T04:00:22","id":"1.7.30200"}]
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
         * seller : 1.2.24323
         * w_price : 0.01
         * deferred_fee : 100000000
         * sell_price : {"quote":{"amount":1000000000,"asset_id":"1.3.1"},"base":{"amount":1000000000,"asset_id":"1.3.0"}}
         * for_sale : 1000000000
         * expiration : 2023-12-20T04:00:22
         * id : 1.7.30200
         */
        private String seller;
        private String w_price;
        private int deferred_fee;
        private Sell_priceEntity sell_price;
        private int for_sale;
        private String expiration;
        private String id;

        public void setSeller(String seller) {
            this.seller = seller;
        }

        public void setW_price(String w_price) {
            this.w_price = w_price;
        }

        public void setDeferred_fee(int deferred_fee) {
            this.deferred_fee = deferred_fee;
        }

        public void setSell_price(Sell_priceEntity sell_price) {
            this.sell_price = sell_price;
        }

        public void setFor_sale(int for_sale) {
            this.for_sale = for_sale;
        }

        public void setExpiration(String expiration) {
            this.expiration = expiration;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSeller() {
            return seller;
        }

        public String getW_price() {
            return w_price;
        }

        public int getDeferred_fee() {
            return deferred_fee;
        }

        public Sell_priceEntity getSell_price() {
            return sell_price;
        }

        public int getFor_sale() {
            return for_sale;
        }

        public String getExpiration() {
            return expiration;
        }

        public String getId() {
            return id;
        }

        public class Sell_priceEntity {
            /**
             * quote : {"amount":1000000000,"asset_id":"1.3.1"}
             * base : {"amount":1000000000,"asset_id":"1.3.0"}
             */
            private QuoteEntity quote;
            private BaseEntity base;

            public void setQuote(QuoteEntity quote) {
                this.quote = quote;
            }

            public void setBase(BaseEntity base) {
                this.base = base;
            }

            public QuoteEntity getQuote() {
                return quote;
            }

            public BaseEntity getBase() {
                return base;
            }

            public class QuoteEntity {
                /**
                 * amount : 1000000000
                 * asset_id : 1.3.1
                 */
                private int amount;
                private String asset_id;

                public void setAmount(int amount) {
                    this.amount = amount;
                }

                public void setAsset_id(String asset_id) {
                    this.asset_id = asset_id;
                }

                public int getAmount() {
                    return amount;
                }

                public String getAsset_id() {
                    return asset_id;
                }
            }

            public class BaseEntity {
                /**
                 * amount : 1000000000
                 * asset_id : 1.3.0
                 */
                private int amount;
                private String asset_id;

                public void setAmount(int amount) {
                    this.amount = amount;
                }

                public void setAsset_id(String asset_id) {
                    this.asset_id = asset_id;
                }

                public int getAmount() {
                    return amount;
                }

                public String getAsset_id() {
                    return asset_id;
                }
            }
        }
    }
}
