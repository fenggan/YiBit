package com.example.administrator.yibit.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yibit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CashWithdrawalActivity extends AppCompatActivity implements TextWatcher {

    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.number)
    EditText number;
    @BindView(R.id.market_msg)
    EditText market;

    @BindView(R.id.min)
    TextView min;
    @BindView(R.id.poundage_one)
    TextView poundageOne;
    @BindView(R.id.poundage_two)
    TextView poundageTwo;

    @BindView(R.id.total_number)
    TextView totalNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_withdrawal);
        ButterKnife.bind(this);
        init();
    }

    private void init(){
        number.addTextChangedListener(this);
    }

    @OnClick({R.id.back, R.id.commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.commit:
                String tempAddress = address.getText().toString().trim();
                String tempNumber = number.getText().toString().trim();
                if (tempAddress == null || "".equals(tempAddress) || tempAddress.length() == 0) {
                    Toast.makeText(this, "请输入提币地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tempNumber == null || "".equals(tempNumber) || tempNumber.length() == 0) {
                    Toast.makeText(this, "请输入提币数量", Toast.LENGTH_SHORT).show();
                    return;
                }
                //TODO  提币

                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //TODO 计算最小提取数量和手续费。
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
