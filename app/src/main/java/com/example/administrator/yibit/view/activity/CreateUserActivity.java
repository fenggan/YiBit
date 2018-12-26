package com.example.administrator.yibit.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
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
import com.example.administrator.yibit.util.AESUtil;
import com.example.administrator.yibit.util.Base58;
import com.example.administrator.yibit.util.ECCUtil;
import com.google.gson.Gson;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
        jdkDSA();
    }

    private void init() {
        retrofitUtils = new RetrofitUtils(this, Constact.baseUrl);
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
                        if (temp.matches("^(?![^a-zA-Z]+$)(?!\\\\D+$).{8,16}$")) {
                            showAlertDialog();
                            try {
                                KeyPair pairOne = ECCUtil.getKeyPair();
                                String publicKeyOne = ECCUtil.getPublicKey(pairOne);
                                String privateKeyOne=ECCUtil.getPrivateKey(pairOne);
                                KeyPair pairTwo = ECCUtil.getKeyPair();
                                String privateKeyTwo=ECCUtil.getPrivateKey(pairTwo);
                                String publicKeyTwo = ECCUtil.getPublicKey(pairTwo);
                                Log.i("RxAndroid", publicKeyOne + "-------");
                                Log.i("RxAndroid", publicKeyTwo + "-------");
                                Log.i("RxAndroid", privateKeyTwo + "-------");
                                Log.i("RxAndroid", privateKeyOne + "-------");
                                Log.i("RxAndroid", pairOne.getPrivate().toString() + "-------");
                                CreateUserNameBean bean = new CreateUserNameBean();
                                bean.setName(temp);
                                bean.setOwner_key(publicKeyOne);
                                bean.setActive_key(publicKeyOne);
                                bean.setMemo_key(publicKeyTwo);
                                bean.setRefcode("");
                                bean.setReferrer("");
                                Gson gson = new Gson().newBuilder().serializeNulls().create();
                                String json = gson.toJson(bean);
                                String aa="{\"active_key\":\"JRC8AFykCLXKmURs5WDdAR4byKQ5HQKuXEGNuwKE3cofmqXyy3YJ8\",\"memo_key\":\"JRC8AFykCLXKmURs5WDdAR4byKQ5HQKuXEGNuwKE3cofmqXyy3YJ8\",\"name\":\"jjjj111122\",\"owner_key\":\"JRC7diFG1z7JwvXZprNwAN53a92jvW67P2kCYDTXKGjL4qh9GRe1X\",\"refcode\":\"\",\"referrer\":\"\"}";
                                Log.i("RxAndroid", aa);
                                Log.i("RxAndroid", json);
                                test(aa);
//                                CheckUserName(aa);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(this, "账户名由大小写字母、数字组成。", Toast.LENGTH_SHORT).show();
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

    //DSA
    public static void jdkDSA() {
        String str = "hello";
        String strs= ",M���v�N9ٳ�\u001D��\u001E�����\u0019?D\u000BY�}\u001C<a";
        // 1. 初始化 秘钥
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(256);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            ECPublicKey ecPublicKey = (ECPublicKey) keyPair.getPublic();
            ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair.getPrivate();

            // 2.执行签名
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(ecPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("EC");

            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Log.i("RxAndroid_md5.~",AESUtil.byte2Base64((md5(new String(privateKey.getEncoded())).getBytes())));
            Log.i("RxAndroid_md5.~",new String(privateKey.getEncoded()));
            Log.i("RxAndroid_58.~",Base58.encode(strs.getBytes()));
            Log.i("RxAndroid_58.~",AESUtil.byte2Base64(strs.getBytes()));

            Signature signature = Signature.getInstance("SHA1withECDSA");
            signature.initSign(privateKey);

            signature.update(str.getBytes());
            byte[] sign = signature.sign();

            // 验证签名
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(ecPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("EC");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Log.i("RxAndroid_md5..",md5(Base58.encode(publicKey.getEncoded())));

            signature = Signature.getInstance("SHA1withECDSA");
            signature.initVerify(publicKey);
            signature.update(str.getBytes());

            boolean bool = signature.verify(sign);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void test(String json) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
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
                Log.d("RxAndroid", "onResponse: " + response.body().string());
            }
        });
    }

    //检查用户名是否存在。
    //TODO 请求：检查用户名是否存在
    private void CheckUserName(String json) {
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofitUtils.getAPIService()
                .register(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("RxAndroid", "onSubscribe");
                    }

                    @Override
                    public void onNext(String str) {
                        Log.i("RxAndroid", str);
//                        if (!aBoolean) {
//                            dialog.dismiss();
//                            startActivity(new Intent(CreateUserActivity.this, CreatePasswordActivity.class));
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("RxAndroid", e.getLocalizedMessage() + ":onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("RxAndroid", "onComplete");
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
