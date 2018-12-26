package com.example.administrator.yibit.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.adapter.TransactionAdapter;
import com.example.administrator.yibit.bean.TransactionBean;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransactionRecordActivity extends AppCompatActivity implements TransactionAdapter.TransactionClickListener{
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_record);
        ButterKnife.bind(this);
        initView();
    }

    public void initView(){
        //TODO 获取数据让recyclerView显示,设置点击事件。
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TransactionAdapter adapter=new TransactionAdapter(this,new ArrayList<TransactionBean>());
        adapter.setWalletListener(this);
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.back})
    public void onClick(View view){
        switch(view.getId()) {
            case R.id.back:
                //暂时显示详情页面。
//                finish();
                startActivity(new Intent(this,RecordDetailActivity.class));
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onTransactionClickListener(TransactionBean bean, int position) {
        //item点击事件跳转页面。
    }
}
