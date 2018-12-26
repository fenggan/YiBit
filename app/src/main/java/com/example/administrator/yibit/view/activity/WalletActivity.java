package com.example.administrator.yibit.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yibit.R;
import com.example.administrator.yibit.util.PhoneState;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalletActivity extends AppCompatActivity {

    @BindView(R.id.username)
    TextView userName;
    @BindView(R.id.username_two)
    TextView userNameTwo;
    @BindView(R.id.root)
    RelativeLayout root;
    private EditText password;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        ButterKnife.bind(this);
        initData();
    }

    public void initData() {
        //初始化从上个页面跳转携带的数据
    }

    @OnClick({R.id.back, R.id.password, R.id.backups, R.id.remove_wallet})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.password:
                startActivity(new Intent(this, UpdatePasswordActivity.class));
                break;
            case R.id.remove_wallet:
                showPopWindow();
                //TODO  请求：删除钱包
                break;
            case R.id.backups:
                startActivity(new Intent(this, BackupsHideActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void showPopWindow() {
        PopupWindow window = new PopupWindow(this);
        View view = getLayoutInflater().inflate(R.layout.pop_delete_wallet, null);
        password = view.findViewById(R.id.password);
        delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = password.getText().toString().trim();
                if (temp == null && "".equals(temp) && temp.length() == 0) {
                    Toast.makeText(WalletActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    //TODO 请求：判断密码是否正确。false:tip.

                    startActivity(new Intent(WalletActivity.this, BackupsShowActivity.class));
                }
            }
        });
        window.setContentView(view);
        window.setOutsideTouchable(true);
        window.setFocusable(true);
        window.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        window.setHeight(700);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PhoneState.setBackgroundAlpha(WalletActivity.this, 1);
            }
        });
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        window.showAsDropDown(root);
        PhoneState.setBackgroundAlpha(WalletActivity.this, 0.5f);
    }
}
