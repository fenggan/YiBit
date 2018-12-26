package com.example.administrator.yibit.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.yibit.MainActivity;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.util.ClipUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BackupsShowActivity extends AppCompatActivity {

    @BindView(R.id.private_key)
    EditText privateKey;
    @BindView(R.id.copy)
    Button copy;
    @BindView(R.id.back_home)
    Button backHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backups_show);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.copy,R.id.back_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.copy:
                String temp=privateKey.getText().toString().trim();
                ClipUtils.clipCopy(this,temp);
                copy.setText("已复制");
                copy.setBackgroundColor(Color.parseColor("#D6D6D6"));
                copy.setClickable(false);
                Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
                backHome.setVisibility(View.VISIBLE);
                break;
            case R.id.back_home:
                startActivity(new Intent(this,MainActivity.class));
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
