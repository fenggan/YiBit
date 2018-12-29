package com.example.administrator.yibit.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.widget.AmountView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BuySellFragment extends Fragment {

    @BindView(R.id.recycler_one)
    RecyclerView recyclerOne;
    @BindView(R.id.recycler_two)
    RecyclerView recyclerTwo;
    @BindView(R.id.recycler_three)
    RecyclerView recyclerThree;
    @BindView(R.id.amount_one)
    AmountView amountOne;
    @BindView(R.id.amount_two)
    AmountView amountTwo;
    @BindView(R.id.tv_approximate)
    TextView approximate;
    @BindView(R.id.one_fourth)
    TextView oneFourth;
    @BindView(R.id.two_fourth)
    TextView twoFourth;
    @BindView(R.id.three_fourth)
    TextView threeFourth;
    @BindView(R.id.whole)
    TextView whole;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.poundage)
    TextView poundage;
    @BindView(R.id.commit)
    Button commit;

    private Unbinder unbinder;
    private int flag;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_buy_sell, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        //TODO 我的挂单没做。
        return view;
    }

    public static BuySellFragment newInstance(int index) {
        BuySellFragment f = new BuySellFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    private void init() {
        flag=getArguments().getInt("index");
        if(flag==0){
            commit.setText("买入");
            commit.setBackgroundResource(R.drawable.shape_cornor_green);
        }else if(flag==1){
            commit.setText("卖出");
            commit.setBackgroundResource(R.drawable.shape_cornor_red);
        }

        amountTwo.setHint("数量(BMAN)");
        //TODO 初始化买入卖出的最大数值
        amountOne.setMaxNumber(100);
        amountTwo.setMaxNumber(100);
    }
    @OnClick({R.id.commit})
    public void onClick(View view){
        switch(view.getId()){
            case R.id.commit:
                if(flag==0){

                }else if(flag==1){

                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
