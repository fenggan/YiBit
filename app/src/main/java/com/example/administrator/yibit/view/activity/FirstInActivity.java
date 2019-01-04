package com.example.administrator.yibit.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.administrator.yibit.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstInActivity extends AppCompatActivity{

    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.create)
    Button create;
    @BindView(R.id.join)
    Button join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_in);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }
    @OnClick({R.id.create,R.id.join})
    public void onClick(View view){
        switch(view.getId()){
            case R.id.create:
                startActivity(new Intent(this,CreateUserActivity.class));
                break;
            case R.id.join:
                startActivity(new Intent(this,ImportWalletActivitiy.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(String msg){
        if("finish".equals(msg)){
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
