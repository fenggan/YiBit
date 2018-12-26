package com.example.administrator.yibit.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.yibit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordDetailActivity extends AppCompatActivity {

    @BindView(R.id.transaction_time)
    TextView transactionTime;
    @BindView(R.id.block)
    TextView block;
    @BindView(R.id.transaction_number)
    TextView transactionNumber;
    @BindView(R.id.remark)
    TextView remark;
    @BindView(R.id.service_charge)
    TextView serviceCharge;
    @BindView(R.id.receive_user)
    TextView receiveUser;
    @BindView(R.id.send_user)
    TextView sendUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);
        ButterKnife.bind(this);
        initData();
    }
    public void initData(){
        //TODO 请求数据，显示。
    }

    @OnClick({R.id.back})
    public void onClick(View view){
        switch(view.getId()) {
            case R.id.back:
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
