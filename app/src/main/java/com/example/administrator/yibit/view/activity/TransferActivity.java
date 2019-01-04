package com.example.administrator.yibit.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.util.PhoneStateUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferActivity extends AppCompatActivity {

    @BindView(R.id.username_one)
    TextView myName;
    @BindView(R.id.username_two)
    TextView yourName;
    @BindView(R.id.number)
    EditText number;
    @BindView(R.id.usable)
    TextView usable;
    @BindView(R.id.market)
    EditText market;
    @BindView(R.id.poundage)
    TextView poundage;
    @BindView(R.id.root)
    ScrollView root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
    }
    private void init(){
        //TODO  初始化数据
    }
    @OnClick({R.id.back,R.id.next})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.next:
                showConfirmWindow();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //TODO  设置初始值.
    private void showConfirmWindow() {
        final PopupWindow window = new PopupWindow(this);
        View view = getLayoutInflater().inflate(R.layout.pop_confirm_transfer, null);
        ImageView close = view.findViewById(R.id.close);
        TextView myName = view.findViewById(R.id.my_name);
        TextView yourName = view.findViewById(R.id.your_name);
        TextView number = view.findViewById(R.id.number);
        TextView market = view.findViewById(R.id.market);
        TextView poundage = view.findViewById(R.id.poundage);
        Button send = view.findViewById(R.id.send);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPasswordWindow();
                window.dismiss();
            }
        });
        window.setContentView(view);
        window.setOutsideTouchable(true);
        window.setFocusable(true);
        window.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        window.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                PhoneStateUtils.setBackgroundAlpha(TransferActivity.this,1);
            }
        });
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_cornor_white));
        window.showAtLocation(root,Gravity.BOTTOM,0,0);
        PhoneStateUtils.setBackgroundAlpha(this,0.5f);
    }

    private void showPasswordWindow() {
        PopupWindow window = new PopupWindow(this);
        View view = getLayoutInflater().inflate(R.layout.pop_input_password, null);
        final EditText password = view.findViewById(R.id.password);
        Button popNext = view.findViewById(R.id.pop_next);
        popNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = password.getText().toString().trim();
                if (temp == null && "".equals(temp) && temp.length() == 0) {
                    Toast.makeText(TransferActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else{
                    //TODO 请求：判断密码是否正确。false:tip.

                    startActivity(new Intent(TransferActivity.this,TransferSuccessActivity.class));
                }
            }
        });
        window.setContentView(view);
        window.setFocusable(true);
        window.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        window.setHeight(700);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PhoneStateUtils.setBackgroundAlpha(TransferActivity.this,1);
            }
        });
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_cornor_white));
        window.showAsDropDown(root);
        PhoneStateUtils.setBackgroundAlpha(this,0.5f);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(String arg){
        if("finish".equals(arg)){
            finish();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
