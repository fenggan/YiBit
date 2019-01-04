package com.example.administrator.yibit.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.yibit.Constact;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.adapter.BuyListAdapter;
import com.example.administrator.yibit.adapter.OrderListAdapter;
import com.example.administrator.yibit.bean.BusGetBuySellListBean;
import com.example.administrator.yibit.bean.OrderListBean;
import com.example.administrator.yibit.adapter.SellListAdapter;
import com.example.administrator.yibit.bean.BuySellBean;
import com.example.administrator.yibit.http.RetrofitUtils;
import com.example.administrator.yibit.widget.AmountView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    @BindView(R.id.no_state)
    TextView noState;

    private Unbinder unbinder;
    private int flag;
    private RetrofitUtils retrofitUtils;
    private Map<String, String> map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_buy_sell, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        init();
        initData();
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
        flag = getArguments().getInt("index");
        if (flag == 0) {
            commit.setText("买入");
            commit.setBackgroundResource(R.drawable.shape_cornor_green);
            approximate.setTextColor(Color.parseColor("#5ADBBC"));
        } else if (flag == 1) {
            commit.setText("卖出");
            commit.setBackgroundResource(R.drawable.shape_cornor_red);
            approximate.setTextColor(Color.parseColor("#E3675C"));
        }

        //初始化买入卖出的最大数值
        amountOne.setMaxNumber(100);
        amountTwo.setMaxNumber(100);
        amountTwo.setHint("数量(BMAN)");
    }

    private void initData() {
        requestBuySellList("", "");
        requestOrderList();
    }

    @OnClick({R.id.commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.commit:
                if (flag == 0) {

                } else if (flag == 1) {

                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventMainThread(BusGetBuySellListBean bean) {
        requestBuySellList(bean.getOne(), bean.getTwo());

    }

    public void requestOrderList() {
        retrofitUtils = new RetrofitUtils(getActivity(), Constact.BaseUrl);
        map = new HashMap<>();
        map.put("user", Constact.account2);
        retrofitUtils.getAPIService()
                .getOrderList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OrderListBean bean) {
                        recyclerThree.setVisibility(View.VISIBLE);
                        noState.setVisibility(View.GONE);
                        recyclerThree.setLayoutManager(new LinearLayoutManager(getActivity()));
                        OrderListAdapter adapter = new OrderListAdapter(getActivity(), bean.getData());
                        recyclerThree.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void requestBuySellList(String s1, String s2) {
        retrofitUtils = new RetrofitUtils(getActivity(), Constact.BaseUrl);
        map = new HashMap<>();
        if (!"".equals(s1) && !"".equals(s2)) {
            map.put("quote", s1);
            map.put("base", s2);
        }
        retrofitUtils.getAPIService()
                .getTransactionList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BuySellBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BuySellBean bean) {
                        recyclerOne.setLayoutManager(new LinearLayoutManager(getActivity()));
                        SellListAdapter sellAdapter = new SellListAdapter(getActivity(), bean.getBids());
                        recyclerOne.setAdapter(sellAdapter);
                        recyclerTwo.setLayoutManager(new LinearLayoutManager(getActivity()));
                        BuyListAdapter buyAdapter = new BuyListAdapter(getActivity(), bean.getAsks());
                        recyclerTwo.setAdapter(buyAdapter);
                        approximate.setText(bean.getLatest());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
