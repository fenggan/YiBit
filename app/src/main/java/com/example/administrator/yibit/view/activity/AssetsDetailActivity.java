package com.example.administrator.yibit.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yibit.R;
import com.example.administrator.yibit.adapter.TransactionRecordAdapter;
import com.example.administrator.yibit.bean.BusSkipBean;
import com.example.administrator.yibit.bean.TransactionRecordBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AssetsDetailActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.lock)
    TextView lock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_detail);
        ButterKnife.bind(this);
        init();
    }

    //TODO 请求：初始化recycler等数据。
    private void init() {
        TransactionRecordAdapter adapter = new TransactionRecordAdapter(this, new ArrayList<TransactionRecordBean>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.back, R.id.transaction, R.id.transfer, R.id.recharge, R.id.cash})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
                //交易
            case R.id.transaction:
                showAlert(new ArrayList<String>());
                break;
                //转账
            case R.id.transfer:
                startActivity(new Intent(this,TransferActivity.class));
                break;
                //充值
            case R.id.recharge:
                startActivity(new Intent(this,RechargeActivity.class));
                break;
                //提现
            case R.id.cash:
                startActivity(new Intent(this,CashWithdrawalActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void showAlert(List<String> data) {
        data.add("取消");
        final int count=data.size()-1;
        AlertDialog dialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setAdapter(
                new ArrayAdapter<String>(AssetsDetailActivity.this,
                        R.layout.dialog_list, R.id.type, data),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==count){
                            dialog.dismiss();
                        }else{
                            //TODO 选择相关的类型。请求数据并更新页面
                            EventBus.getDefault().post(new BusSkipBean(2));
                            finish();
                        }
                    }
                });
        dialog=builder.create();
        dialog.show();
    }
}
