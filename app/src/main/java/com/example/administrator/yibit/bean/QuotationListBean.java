package com.example.administrator.yibit.bean;

import java.util.List;

public class QuotationListBean {

    /**
     * data : [{"quote":"JRC","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00001999986437827","percent_change":"0.00000000000000000","highest_bid":"0.00001200022675094","base":"ETH","latest":"0.00001999999985252"},{"quote":"USDT","quote_volume":"448326.45280000002821907","base_volume":"3262.70732199999974910","lowest_ask":"0.01030865730965097","percent_change":"1.51046833779726608","highest_bid":"0.00740898204667864","base":"ETH","latest":"0.00740800000000000"},{"quote":"GENE","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00003925000000000","percent_change":"0.00000000000000000","highest_bid":"0.00002200000000000","base":"ETH","latest":"0.00003900000000000"},{"quote":"CMA","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00004298993190836","percent_change":"0.00000000000000000","highest_bid":"0.00000000000000000","base":"ETH","latest":"0.00004550000000000"},{"quote":"TOL","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00000000000000000","percent_change":"0.00000000000000000","highest_bid":"0.00000000000000000","base":"ETH","latest":"0.00000000000000000"},{"quote":"PAX","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00600000000000000","percent_change":"0.00000000000000000","highest_bid":"0.00500000000000000","base":"ETH","latest":"0.00600000000000000"},{"quote":"EVN","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00000000000000000","percent_change":"0.00000000000000000","highest_bid":"0.00000000000000000","base":"ETH","latest":"0.00000000000000000"},{"quote":"PIPS","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00000100000000000","percent_change":"0.00000000000000000","highest_bid":"0.00000000000000000","base":"ETH","latest":"0.00000100000000000"},{"quote":"TMB","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00001000000000000","percent_change":"0.00000000000000000","highest_bid":"0.00000100000000000","base":"ETH","latest":"0.00000100000000000"},{"quote":"KXC","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00000090000000000","percent_change":"0.00000000000000000","highest_bid":"0.00000000000000000","base":"ETH","latest":"0.00001000000000000"},{"quote":"BMAN","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00000009999866667","percent_change":"0.00000000000000000","highest_bid":"0.00000000674800000","base":"ETH","latest":"0.00000009999898505"},{"quote":"YUM","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00067000000000000","percent_change":"0.00000000000000000","highest_bid":"0.00000000000000000","base":"ETH","latest":"0.00000000000000000"},{"quote":"BTGN","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00001000000000000","percent_change":"0.00000000000000000","highest_bid":"0.00000000000000000","base":"ETH","latest":"0.00000000000000000"},{"quote":"SHEL","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00000000000000000","percent_change":"0.00000000000000000","highest_bid":"0.00000000000000000","base":"ETH","latest":"0.00000000000000000"},{"quote":"LEAX","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00299999013698495","percent_change":"0.00000000000000000","highest_bid":"0.00000000000000000","base":"ETH","latest":"0.00000000000000000"},{"quote":"HGT","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00000000000000000","percent_change":"0.00000000000000000","highest_bid":"0.00000000000000000","base":"ETH","latest":"0.00000000000000000"},{"quote":"MUXE","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00000000000000000","percent_change":"0.00000000000000000","highest_bid":"0.00000000000000000","base":"ETH","latest":"0.00000000000000000"},{"quote":"BGAME","quote_volume":"0.00000000000000000","base_volume":"0.00000000000000000","lowest_ask":"0.00020000000000000","percent_change":"0.00000000000000000","highest_bid":"0.00000000000000000","base":"ETH","latest":"0.00000000000000000"}]
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
         * quote : JRC
         * quote_volume : 0.00000000000000000
         * base_volume : 0.00000000000000000
         * lowest_ask : 0.00001999986437827
         * percent_change : 0.00000000000000000
         * highest_bid : 0.00001200022675094
         * base : ETH
         * latest : 0.00001999999985252
         */
        private String quote;
        private String quote_volume;
        private String base_volume;
        private String lowest_ask;
        private String percent_change;
        private String highest_bid;
        private String base;
        private String latest;

        public void setQuote(String quote) {
            this.quote = quote;
        }

        public void setQuote_volume(String quote_volume) {
            this.quote_volume = quote_volume;
        }

        public void setBase_volume(String base_volume) {
            this.base_volume = base_volume;
        }

        public void setLowest_ask(String lowest_ask) {
            this.lowest_ask = lowest_ask;
        }

        public void setPercent_change(String percent_change) {
            this.percent_change = percent_change;
        }

        public void setHighest_bid(String highest_bid) {
            this.highest_bid = highest_bid;
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

        public String getQuote_volume() {
            return quote_volume;
        }

        public String getBase_volume() {
            return base_volume;
        }

        public String getLowest_ask() {
            return lowest_ask;
        }

        public String getPercent_change() {
            return percent_change;
        }

        public String getHighest_bid() {
            return highest_bid;
        }

        public String getBase() {
            return base;
        }

        public String getLatest() {
            return latest;
        }
    }
}
