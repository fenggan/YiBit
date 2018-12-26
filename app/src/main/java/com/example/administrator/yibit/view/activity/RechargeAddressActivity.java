package com.example.administrator.yibit.view.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.util.ClipUtils;
import com.example.administrator.yibit.util.QRCodeUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RechargeAddressActivity extends AppCompatActivity {

    @BindView(R.id.qr)
    ImageView qr;
    @BindView(R.id.username)
    TextView userName;
    @BindView(R.id.key)
    TextView key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_address);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
    }

    //TODO 初始化二维码，userName和key
    private void init() {
        Bitmap bitmap=QRCodeUtils.createQR("二维码内容",200,200,null);
        qr.setBackground(new BitmapDrawable(bitmap));
    }

    @OnClick({R.id.back, R.id.copy_one, R.id.copy_two})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.copy_one:
                ClipUtils.clipCopy(this,userName.getText().toString().trim());
                Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.copy_two:
                ClipUtils.clipCopy(this,key.getText().toString().trim());
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
