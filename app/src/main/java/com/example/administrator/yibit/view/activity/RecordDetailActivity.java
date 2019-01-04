package com.example.administrator.yibit.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.yibit.R;
import com.example.administrator.yibit.bean.TransferAccountRecordBean;

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
    @BindView(R.id.money)
    TextView money;

    private String from;
    private String to;
    private String time;
    private String id;
    private String name;
    private double serviceMoney;
    private int blockNum;
    private double number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        from = getIntent().getStringExtra("from");
        to = getIntent().getStringExtra("to");
        time = getIntent().getStringExtra("time");
        number = getIntent().getDoubleExtra("number", 0);
        blockNum = getIntent().getIntExtra("block", 0);
        id=getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");
        serviceMoney=getIntent().getDoubleExtra("money",0);
    }

    private void initView() {
        sendUser.setText(from);
        receiveUser.setText(to);
        transactionTime.setText(time);
        serviceCharge.setText(serviceMoney+"");
        transactionNumber.setText(id);
        block.setText(blockNum+"");
        if(number>0){
            money.setText("+"+number+"  "+name);
            money.setTextColor(Color.parseColor("#5ADBB1"));
        }else{
            money.setText("-"+number+"  "+name);
            money.setTextColor(Color.parseColor("#E3675C"));
        }
    }

    @OnClick({R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
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
