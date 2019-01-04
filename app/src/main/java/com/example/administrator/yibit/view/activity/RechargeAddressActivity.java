package com.example.administrator.yibit.view.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.yibit.Constact;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.bean.CreateQRAdressBean;
import com.example.administrator.yibit.bean.RechargeBean;
import com.example.administrator.yibit.http.RetrofitUtils;
import com.example.administrator.yibit.util.ClipUtils;
import com.example.administrator.yibit.util.QRCodeUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RechargeAddressActivity extends AppCompatActivity {

    @BindView(R.id.qr)
    ImageView qr;
    @BindView(R.id.username)
    TextView userName;
    private Map<String, String> map;
    private RetrofitUtils retrofitUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_address);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initData();
    }

    private void initData() {
        //TODO  拼接filter的用户名zxc1213
        final String filter = "{\"where\":{\"bts_account\":\"zxc1213\"},\"include\":{\"relation\":\"depositAddresss\",\"scope\":{\"where\":{\"address_type\":\"ETH\"}}}}";
        String json = " {\"bts_account\":\"asd1232\",\"asset_type\": \"ETH\"}";
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofitUtils = new RetrofitUtils(this, Constact.grapheneBaseUrl2);
        retrofitUtils.getAPIService()
                     .createQRAdress(requestBody)
                     .flatMap(new Function<CreateQRAdressBean, ObservableSource<RechargeBean>>() {
                         @Override
                         public ObservableSource<RechargeBean> apply(CreateQRAdressBean createQRAdressBean) throws Exception {
                            if("success".equals(createQRAdressBean.getFlag())){
                                map = new HashMap<>();
                                map.put("filter", filter);
                                return retrofitUtils.getAPIService().getQRAdress(map);
                            }
                            return null;
                         }
                     })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RechargeBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(RechargeBean rechargeBean) {
                            userName.setText(rechargeBean.getDepositAddresss().get(0).getAddress());
                            Bitmap bitmap = QRCodeUtils.createQR(rechargeBean.getDepositAddresss().get(0).getAddress(), 200, 200, null);
                            qr.setBackground(new BitmapDrawable(bitmap));
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("RxAndroid", e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }

    @OnClick({R.id.back, R.id.copy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.copy:
                ClipUtils.clipCopy(this, userName.getText().toString().trim());
                Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(String msg) {
        if ("finish".equals(msg)) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
