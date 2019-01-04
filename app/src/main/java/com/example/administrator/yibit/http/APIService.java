package com.example.administrator.yibit.http;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/13
 */

import com.example.administrator.yibit.bean.AssetsBean;
import com.example.administrator.yibit.bean.CreateQRAdressBean;
import com.example.administrator.yibit.bean.OrderListBean;
import com.example.administrator.yibit.bean.AccountInfoBean;
import com.example.administrator.yibit.bean.BuySellBean;
import com.example.administrator.yibit.bean.QuotationListBean;
import com.example.administrator.yibit.bean.RechargeBean;
import com.example.administrator.yibit.bean.TransferAccountRecordBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface APIService {

    /**
     * 判断用户名是否存在
     * @return String
     */
    @POST("api/v1/accounts")
    Observable<String> register(@Body RequestBody body);

    /**
     * 获取用户信息
     * @param map
     * @return String
     */
    @FormUrlEncoded
    @POST("get_accountinfo.php")
    Observable<AccountInfoBean> getAcounntInfo(@FieldMap Map<String, String> map);

    /**
     * 获取行情列表
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("quotes.php")
    Observable<QuotationListBean> getquotationList(@FieldMap Map<String, String> map);

    /**
     * 获取交易列表
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("exchange.php")
    Observable<BuySellBean> getTransactionList(@FieldMap Map<String, String> map);

    /**
     * 获取挂单列表
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("limit_order.php")
    Observable<OrderListBean> getOrderList(@FieldMap Map<String, String> map);

    /**
     * 获取资产信息（包含余额）
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("one_asset.php")
    Observable<AssetsBean> getAssets(@FieldMap Map<String, String> map);


    /**
     * 获取生成二维码的地址
     * @param map
     * @return
     */
    @GET("BtsAccountRegisterManages/findOne")
    Observable<RechargeBean> getQRAdress(@HeaderMap Map<String, String> map);


    /**
     * 生成二维码
     * @param body
     * @return
     */
    @POST("BtsAccountRegisterManages/registerDepositAccount")
    Observable<CreateQRAdressBean> createQRAdress(@Body RequestBody body);

    /**
     *  获取转账记录
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("transfer.php")
    Observable<TransferAccountRecordBean> getTransferAccountRecord(@FieldMap Map<String, String> map);
}
