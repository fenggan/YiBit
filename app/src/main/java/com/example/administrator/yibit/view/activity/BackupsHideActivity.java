package com.example.administrator.yibit.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.yibit.R;
import com.example.administrator.yibit.util.PhoneState;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BackupsHideActivity extends AppCompatActivity {

    @BindView(R.id.private_key)
    EditText privateKey;
    @BindView(R.id.root)
    RelativeLayout root;

    private EditText password;
    private Button popNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backups_hide);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.next:
                showPopWindow();
                break;
        }
    }

    private void showPopWindow() {
        PopupWindow window = new PopupWindow(this);
        View view = getLayoutInflater().inflate(R.layout.pop_input_password, null);
        password = view.findViewById(R.id.password);
        popNext = view.findViewById(R.id.pop_next);
        popNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = password.getText().toString().trim();
                if (temp == null && "".equals(temp) && temp.length() == 0) {
                    Toast.makeText(BackupsHideActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else{
                    //TODO 请求：判断密码是否正确。false:tip.

                startActivity(new Intent(BackupsHideActivity.this,BackupsShowActivity.class));
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
                PhoneState.setBackgroundAlpha(BackupsHideActivity.this,1);
            }
        });
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        window.showAsDropDown(root);
        PhoneState.setBackgroundAlpha(BackupsHideActivity.this,0.5f);
    }
        @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
