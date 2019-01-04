package com.example.administrator.yibit.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.administrator.yibit.Constact;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.bean.AssetsBean;
import com.example.administrator.yibit.bean.BusGetBuySellListBean;
import com.example.administrator.yibit.bean.BusSkipBean;
import com.example.administrator.yibit.http.RetrofitUtils;
import com.example.administrator.yibit.util.DoubleUtils;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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

    private String currency;
    private String userName;
    private RetrofitUtils retrofitUtils;
    private Map<String,String> map;
    private String []currencys;
    private List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_detail);
        ButterKnife.bind(this);
        init();
        initData();
    }

    private void init() {
        list=new ArrayList<>();
        currencys=getResources().getStringArray(R.array.alert_titles);
        currency=getIntent().getStringExtra("currency");
        userName=getIntent().getStringExtra("userName");
        for(String temp:currencys){
            if(!temp.equals(currency)){
                list.add(temp);
            }
        }
        type.setText(currency);
    }

    private void initData(){
        map=new HashMap<>();
        map.put("user",userName);
        map.put("asset","1.3.0");
        retrofitUtils=new RetrofitUtils(this,Constact.BaseUrl);
        retrofitUtils.getAPIService()
                     .getAssets(map)
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(new Observer<AssetsBean>() {
                         @Override
                         public void onSubscribe(Disposable d) {

                         }

                         @Override
                         public void onNext(AssetsBean bean) {
                             double balanceNum=bean.getData().getAmount();
                             double lockNum=bean.getLockded_balances().getLocked_balance();
                             total.setText(DoubleUtils.add(lockNum,balanceNum)+"");
                             balance.setText(balanceNum+"");
                             lock.setText(lockNum+"");
                         }

                         @Override
                         public void onError(Throwable e) {
                             Log.i("Rxx",e.getMessage());
                         }

                         @Override
                         public void onComplete() {

                         }
                     });

    }
    @OnClick({R.id.back, R.id.transaction, R.id.transfer, R.id.recharge, R.id.cash})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
                //交易
            case R.id.transaction:
                showAlert();
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

    private void showAlert() {
        final int count=list.size()-1;
        AlertDialog dialog;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setAdapter(
                new ArrayAdapter(AssetsDetailActivity.this,
                        R.layout.dialog_list, R.id.type, list),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==count){
                            dialog.dismiss();
                        }else{
                            EventBus.getDefault().post(new BusSkipBean(2));
                            EventBus.getDefault().post(new BusGetBuySellListBean(currency,list.get(which)));
                            finish();
                        }
                    }
                });
        dialog=builder.create();
        dialog.show();
    }
}
