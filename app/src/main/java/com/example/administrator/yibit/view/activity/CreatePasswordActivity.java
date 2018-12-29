package com.example.administrator.yibit.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.yibit.R;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cy.agorise.graphenej.BIP39;

public class CreatePasswordActivity extends AppCompatActivity {
    @BindView(R.id.password_one)
    EditText passwordOne;
    @BindView(R.id.password_two)
    EditText passwordTwo;
    @BindView(R.id.tip)
    TextView tip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @OnClick({R.id.next,R.id.back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.next:
                String one=passwordOne.getText().toString().trim();
                String two=passwordTwo.getText().toString().trim();
                if("".equals(one) || one==null || one.length()==0){
                    Toast.makeText(this, "请输入密码。", Toast.LENGTH_SHORT).show();
                    return;
                }
                if("".equals(two) || two==null || two.length()==0){
                    Toast.makeText(this, "请输入密码。", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!one.matches("^(?![^a-zA-Z]+$)(?!\\\\D+$).{8,16}$")){
                    Toast.makeText(this, "密码只能是大小写字母加数字。", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(one.equals(two)){
                    tip.setVisibility(View.GONE);
                    BIP39 bip39=new BIP39("0",one);
//                    bip39.getPrivateKey().getPrivKey().toString();

                    startActivity(new Intent(this,CreateSuccessActivity.class));
                }else{
                    tip.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.back:
                finish();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
