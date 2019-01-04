package com.example.administrator.yibit.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yibit.R;
import com.example.administrator.yibit.adapter.BuySellAdapter;
import com.example.administrator.yibit.bean.BusGetBuySellListBean;
import com.example.administrator.yibit.widget.AmountView;
import com.example.administrator.yibit.widget.NoScrollViewpager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TransactionFragment extends Fragment {
    @BindView(R.id.choose)
    TextView choose;
    @BindView(R.id.viewpager)
    NoScrollViewpager viewPager;
    @BindView(R.id.sell)
    TextView sell;
    @BindView(R.id.buy)
    TextView buy;

    private Unbinder unbinder;
    private List<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=getLayoutInflater().inflate(R.layout.fragment_transaction,container,false);
        unbinder=ButterKnife.bind(this,view);
        EventBus.getDefault().register(this);
        initData();
        return view;
    }
    private void initData(){
        fragments=new ArrayList<>();
        fragments.add(BuySellFragment.newInstance(0));
        fragments.add(BuySellFragment.newInstance(1));
        init();
    }
    private void init(){
        choose.setText("ETC/JRC");
        viewPager.setAdapter(new BuySellAdapter(getChildFragmentManager(),fragments));
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(BusGetBuySellListBean bean){
        choose.setText(bean.getOne()+"/"+bean.getTwo());
    }


    @OnClick({R.id.choose,R.id.sell,R.id.buy,R.id.kline})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.choose:
//                showPop();
                break;
            case R.id.buy:
                viewPager.setCurrentItem(0);
                buy.setBackgroundResource(R.drawable.shape_cornor_left_green);
                sell.setBackgroundResource(R.drawable.shape_cornor_right_gray);
                buy.setTextColor(Color.parseColor("#FFFFFF"));
                sell.setTextColor(Color.parseColor("#A5A5A5"));
                break;
            case R.id.sell:
                viewPager.setCurrentItem(1);
                buy.setBackgroundResource(R.drawable.shape_cornor_left_gray);
                sell.setBackgroundResource(R.drawable.shape_cornor_right_red);
                buy.setTextColor(Color.parseColor("#A5A5A5"));
                sell.setTextColor(Color.parseColor("#FFFFFF"));
                break;
            case R.id.kline:
                //k线图界面
                break;
        }
    }

    //弹出选择货币的窗口。
    private void showPop(){
        String items[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        ListPopupWindow listPopupWindow = new ListPopupWindow(getActivity());
        // ListView适配器
        listPopupWindow.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items));
        // 选择item的监听事件
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Toast.makeText(getActivity(), "选择:" + pos, Toast.LENGTH_SHORT).show();
                // listPopupWindow.dismiss();
            }
        });
        listPopupWindow.setWidth(500);
        listPopupWindow.setHeight(600);
        // ListPopupWindow的锚,弹出框的位置是相对当前View的位置
        listPopupWindow.setAnchorView(choose);
        // ListPopupWindow 距锚view的距离
//        listPopupWindow.setHorizontalOffset(50);
//        listPopupWindow.setVerticalOffset(100);
        listPopupWindow.setModal(false);
        listPopupWindow.show();
    }
}
