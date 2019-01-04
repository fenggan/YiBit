package com.example.administrator.yibit.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.administrator.yibit.Constact;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.adapter.TransferAccountRecordAdapter;
import com.example.administrator.yibit.bean.TransactionBean;
import com.example.administrator.yibit.bean.TransferAccountRecordBean;
import com.example.administrator.yibit.http.RetrofitUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TransactionRecordActivity extends AppCompatActivity implements TransferAccountRecordAdapter.TransactionClickListener {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private RetrofitUtils retrofitUtils;
    private Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_record);
        ButterKnife.bind(this);
        initData();
    }

    public void initData() {
        map = new HashMap<>();
        map.put("user", Constact.account2);
        retrofitUtils = new RetrofitUtils(this, Constact.BaseUrl);
        retrofitUtils.getAPIService()
                .getTransferAccountRecord(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TransferAccountRecordBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TransferAccountRecordBean bean) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(TransactionRecordActivity.this));
                        TransferAccountRecordAdapter adapter = new TransferAccountRecordAdapter(TransactionRecordActivity.this, bean.getData());
                        adapter.setWalletListener(TransactionRecordActivity.this);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick({R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
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

    @Override
    public void onTransactionClickListener(TransferAccountRecordBean.DataEntity bean, int position) {
        Intent intent = new Intent(this, RecordDetailActivity.class);
        intent.putExtra("from", bean.getForm());
        intent.putExtra("to", bean.getTo());
        intent.putExtra("time", bean.getTime());
        intent.putExtra("money", bean.getFee().getAsset_num());
        intent.putExtra("block", bean.getBlock_num());
        intent.putExtra("id", bean.getTransfer_id());
        intent.putExtra("number", bean.getAsset().getAsset_num());
        intent.putExtra("name", bean.getAsset().getAsset_name());
        startActivity(intent);
    }
}
