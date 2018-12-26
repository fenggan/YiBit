package com.example.administrator.yibit.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.adapter.WalletAdapter;
import com.example.administrator.yibit.bean.WalletBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalletManagerActivity extends AppCompatActivity implements WalletAdapter.WalletClickListener{

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_manager);
        ButterKnife.bind(this);
        initData();
    }

    public void initData(){
        //TODO 请求钱包数据，显示到recyclerView.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        WalletAdapter adapter=new WalletAdapter(this,new ArrayList<WalletBean>());
        adapter.setWalletListener(this);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.import_wallet,R.id.create_wallet,R.id.back})
    public void onClick(View view){
        switch(view.getId()) {
            case R.id.create_wallet:
                startActivity(new Intent(this,CreateUserActivity.class));
                break;
            case R.id.import_wallet:
                startActivity(new Intent(this,ImportWalletActivitiy.class));
                break;
            case R.id.back:
                //暂时进入管理钱包
//                finish();
                startActivity(new Intent(this,WalletActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onWalletClickListener(WalletBean bean, int position) {
        //item点击事件跳转页面。
        startActivity(new Intent(this,WalletActivity.class));
    }
}
