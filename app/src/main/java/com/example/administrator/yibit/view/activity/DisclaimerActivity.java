package com.example.administrator.yibit.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.yibit.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DisclaimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.back})
    public void onClick(View view){
        switch(view.getId()){
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
