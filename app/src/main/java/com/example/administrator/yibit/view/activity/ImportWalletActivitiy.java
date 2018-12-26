package com.example.administrator.yibit.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.yibit.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImportWalletActivitiy extends AppCompatActivity {

    @BindView(R.id.username)
    EditText userName;
    @BindView(R.id.password_one)
    EditText passwordOne;
    @BindView(R.id.password_two)
    EditText passwordTwo;
    @BindView(R.id.private_key)
    EditText privateKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_wallet);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.confirm, R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.confirm:
                String key = privateKey.getText().toString().trim();
                String one = passwordOne.getText().toString().trim();
                String two = passwordTwo.getText().toString().trim();
                String name = userName.getText().toString().trim();
                if (key.length() == 0 | "".equals(key) | key == null) {
                    Toast.makeText(this, "请输入密钥", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (name.length() == 0 | "".equals(name) | name == null) {
                    Toast.makeText(this, "请输入钱包账号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (one.length() == 0 | "".equals(one) | one == null) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (two.length() == 0 | "".equals(two) | two == null) {
                    Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!two.equals(one)) {
                    Toast.makeText(this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    return;
                }
                //TODO 请求：导入钱包

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
