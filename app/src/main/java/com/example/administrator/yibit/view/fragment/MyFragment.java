package com.example.administrator.yibit.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yibit.R;
import com.example.administrator.yibit.view.activity.TransactionRecordActivity;
import com.example.administrator.yibit.view.activity.WalletManagerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyFragment extends Fragment {
    private Unbinder unbinder;

    @BindView(R.id.my_head)
    ImageView my_head;
    @BindView(R.id.username)
    TextView userName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=getLayoutInflater().inflate(R.layout.fragment_my,container,false);
        unbinder=ButterKnife.bind(this,view);
        initData();
        return view;
    }
    public void initData(){
        //TODO 设置头像.用户名
    }

    @OnClick({R.id.transaction_record,R.id.wallet_manager})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.wallet_manager:
                startActivity(new Intent(getActivity(),WalletManagerActivity.class));
                break;
            case R.id.transaction_record:
                startActivity(new Intent(getActivity(),TransactionRecordActivity.class));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
