package com.example.administrator.yibit.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.yibit.R;
import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferSuccessActivity extends AppCompatActivity {

    @BindView(R.id.number)
    TextView number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_success);
        ButterKnife.bind(this);
        init();
    }

    //TODO 初始化相关的信息。
    private void init(){

    }

    @OnClick({R.id.back,R.id.complete})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.complete:
                EventBus.getDefault().post("finish");
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
