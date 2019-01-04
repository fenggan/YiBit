package com.example.administrator.yibit.bean;

import java.util.List;

public class BuySellBean {
    /**
     * quote : ETH
     * asks : [{"quote":"0.01490900000000000","price":"83331.75870376493548974","base":"1242.39319051000006766"},{"quote":"0.01000000000000000","price":"83333.33333299998776056","base":"833.33333332999995946"},{"quote":"0.16098499999999999","price":"84033.29502748703816906","base":"13528.10000000000036380"},{"quote":"0.09000000000000000","price":"111111.11111111110949423","base":"10000.00000000000000000"},{"quote":"0.08000000000000000","price":"125000.00000000000000000","base":"10000.00000000000000000"}]
     * bids : [{"quote":"0.14311499999999999","price":"50000.33905663277982967","base":"7155.79852408999977342"},{"quote":"0.07199999999999999","price":"50000.00000000000727596","base":"3600.00000000000000000"},{"quote":"0.03000000000000000","price":"45454.54572173678752733","base":"1363.63637165999989520"},{"quote":"0.00021600000000000","price":"41666.66666666666424135","base":"9.00000000000000000"},{"quote":"0.00024000000000000","price":"41666.66666666666424135","base":"10.00000000000000000"}]
     * base : JRC
     * latest : 50000.00036870958865620
     */
    private String quote;
    private List<AsksEntity> asks;
    private List<BidsEntity> bids;
    private String base;
    private String latest;

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setAsks(List<AsksEntity> asks) {
        this.asks = asks;
    }

    public void setBids(List<BidsEntity> bids) {
        this.bids = bids;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public String getQuote() {
        return quote;
    }

    public List<AsksEntity> getAsks() {
        return asks;
    }

    public List<BidsEntity> getBids() {
        return bids;
    }

    public String getBase() {
        return base;
    }

    public String getLatest() {
        return latest;
    }

    public class AsksEntity {
        /**
         * quote : 0.01490900000000000
         * price : 83331.75870376493548974
         * base : 1242.39319051000006766
         */
        private String quote;
        private String price;
        private String base;

        public void setQuote(String quote) {
            this.quote = quote;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getQuote() {
            return quote;
        }

        public String getPrice() {
            return price;
        }

        public String getBase() {
            return base;
        }
    }

    public class BidsEntity {
        /**
         * quote : 0.14311499999999999
         * price : 50000.33905663277982967
         * base : 7155.79852408999977342
         */
        private String quote;
        private String price;
        private String base;

        public void setQuote(String quote) {
            this.quote = quote;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getQuote() {
            return quote;
        }

        public String getPrice() {
            return price;
        }

        public String getBase() {
            return base;
        }
    }
}
