package com.example.administrator.yibit.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yibit.Constact;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.bean.CreateUserNameBean;
import com.example.administrator.yibit.http.RetrofitUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cy.agorise.graphenej.Address;
import cy.agorise.graphenej.BrainKey;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreateUserActivity extends AppCompatActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.choose)
    CheckBox choose;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.agree)
    TextView agree;
    @BindView(R.id.tip)
    TextView tip;
    private AlertDialog dialog;
    private RetrofitUtils retrofitUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
        initView();
        aaa();
    }

    private void aaa() {

    }

    private void init() {
        retrofitUtils = new RetrofitUtils(this, Constact.grapheneBaseUrl);
    }

    private void initView() {
        SpannableStringBuilder builder = new SpannableStringBuilder("同意《YiBit免责申明》");
        ForegroundColorSpan span = new ForegroundColorSpan(Color.parseColor("#436EEE"));
        builder.setSpan(span, 2, builder.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        agree.setText(builder);
    }

    @OnClick({R.id.next, R.id.back, R.id.agree})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                if (choose.isChecked()) {
                    String temp = username.getText().toString().trim();
                    if (temp.length() != 0) {
                        if (temp.matches("^[a-zA-Z][0-9a-zA-Z]{7,16}$")) {
                            showAlertDialog();
                            try {
                                CheckUserName2(getJson(temp));
//                                CheckUserName(getJson(temp));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(this, "账户名由8-16位大小写字母、数字组成，且由字母开头", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "请输入账号！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请在阅读免责申明后点击同意按钮。", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back:
                finish();
                break;
            case R.id.agree:
                startActivity(new Intent(this, DisclaimerActivity.class));
                break;
        }
    }

    private String getJson(String username) {
        BrainKey brainKey1 = new BrainKey(username, 0);
        BrainKey brainKey2 = new BrainKey(username, 1);
        String ownerAddress = brainKey1.getPublicAddress(Address.BITSHARES_PREFIX).toString();
        String activeAddress = brainKey2.getPublicAddress(Address.BITSHARES_PREFIX).toString();
//        brainKey1.getPrivateKey().decompress();
        CreateUserNameBean bean = new CreateUserNameBean();
        bean.setName(username);
        bean.setOwner_key(ownerAddress);
        bean.setActive_key(activeAddress);
        bean.setMemo_key(activeAddress);
        bean.setRefcode("");
        bean.setReferrer("");
        Gson gson = new Gson().newBuilder().serializeNulls().create();
        String json = gson.toJson(bean);
        Log.i("RxAndroid", json);
        return json;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(CreateUserActivity.this, "账户已经存在", Toast.LENGTH_SHORT).show();
        }
    };

    //检查用户名是否存在。临时方案
    public void CheckUserName2(String json) {
        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
        Request request = new Request.Builder()
                .url("https://finchain-faucet.com/api/v1/accounts")
                .post(RequestBody.create(mediaType, json))
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("RxAndroid", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dialog.dismiss();
                String data = response.body().string();
                Log.i("RxAndroid", "onResponse: " + data);
                if (data.contains("Account exists")) {
                    handler.sendEmptyMessage(0);
                } else {
                    //TODO  公钥是否需要存储？
                    startActivity(new Intent(CreateUserActivity.this, CreatePasswordActivity.class));
                }
            }
        });
    }

    //检查用户名是否存在。
    private void CheckUserName(String json) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofitUtils.getAPIService()
                .register(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String str) {
                        Log.i("RxAndroid", str);
                        dialog.dismiss();
                        startActivity(new Intent(CreateUserActivity.this, CreatePasswordActivity.class));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("RxAndroid", e.getLocalizedMessage() + ":onError");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_check_username);
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
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
