package com.example.administrator.yibit.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.yibit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePasswordActivity extends AppCompatActivity implements TextWatcher {

    @BindView(R.id.old)
    EditText old;
    @BindView(R.id.new_one)
    EditText newOne;
    @BindView(R.id.new_two)
    EditText newTwo;
    @BindView(R.id.complete)
    Button complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        ButterKnife.bind(this);
        initListener();
    }

    public void initListener() {
        old.addTextChangedListener(this);
    }


    @OnClick({R.id.back, R.id.complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.complete:
                String tempOne = newOne.getText().toString().trim();
                String tempTwo = newTwo.getText().toString().trim();
                if (tempOne.length() == 0) {
                    Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tempTwo.length() == 0) {
                    Toast.makeText(this, "请确认新密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!tempTwo.equals(tempOne)) {
                    Toast.makeText(this, "新密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                //TODO 请求：修改密码.成功finish.失败提示。
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
        String tempOld = old.getText().toString().trim();
        if (tempOld.length() > 0) {
            complete.setBackgroundResource(R.drawable.shape_gradient_blue);
            complete.setClickable(true);
        } else {
            complete.setClickable(false);
            complete.setBackgroundColor(Color.parseColor("#D6D6D6"));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
