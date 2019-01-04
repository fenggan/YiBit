package com.example.administrator.yibit;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yibit.adapter.HomePagerAdapter;
import com.example.administrator.yibit.bean.BusSkipBean;
import com.example.administrator.yibit.util.StringUtils;
import com.example.administrator.yibit.view.fragment.AssetsFragment;
import com.example.administrator.yibit.view.fragment.MyFragment;
import com.example.administrator.yibit.view.fragment.QuotationFragment;
import com.example.administrator.yibit.view.fragment.TransactionFragment;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager viewPager;
    @BindView(R.id.assets_img)
    ImageView assetsImage;
    @BindView(R.id.transaction_img)
    ImageView transactionImage;
    @BindView(R.id.market_img)
    ImageView marketImage;
    @BindView(R.id.myself_img)
    ImageView myselfImage;
    @BindView(R.id.myself_tv)
    TextView myselfText;
    @BindView(R.id.transaction_tv)
    TextView transactionText;
    @BindView(R.id.market_tv)
    TextView marketText;
    @BindView(R.id.assets_tv)
    TextView assetsText;

    private List<Integer> grayIcons;
    private List<ImageView> imageviews;
    private List<TextView> textViews;
    private List<Integer> blueIcons;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initData();
        initView();
    }

    private void initView() {
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setCurrentItem(0);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
    }

    private void initData() {
        grayIcons = new ArrayList<>();
        blueIcons = new ArrayList<>();
        fragments = new ArrayList<>();
        imageviews = new ArrayList<>();
        textViews = new ArrayList<>();

        textViews.add(assetsText);
        textViews.add(marketText);
        textViews.add(transactionText);
        textViews.add(myselfText);

        imageviews.add(assetsImage);
        imageviews.add(marketImage);
        imageviews.add(transactionImage);
        imageviews.add(myselfImage);

        grayIcons.add(R.drawable.assets_gray);
        grayIcons.add(R.drawable.market_gray);
        grayIcons.add(R.drawable.transaction_gray);
        grayIcons.add(R.drawable.myself_gray);

        blueIcons.add(R.drawable.assets_green);
        blueIcons.add(R.drawable.market_green);
        blueIcons.add(R.drawable.transaction_green);
        blueIcons.add(R.drawable.myself_green);

        fragments.add(new AssetsFragment());
        fragments.add(new QuotationFragment());
        fragments.add(new TransactionFragment());
        fragments.add(new MyFragment());
    }

    @OnClick({R.id.myself, R.id.transaction, R.id.market, R.id.assets})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.assets:
                viewPager.setCurrentItem(0);
                setImageDrawable(0);
                break;
            case R.id.market:
                viewPager.setCurrentItem(1);
                setImageDrawable(1);
                break;
            case R.id.transaction:
                viewPager.setCurrentItem(2);
                setImageDrawable(2);
                break;
            case R.id.myself:
                viewPager.setCurrentItem(3);
                setImageDrawable(3);
                break;
        }
    }

    //点击导航。切换图片
    public void setImageDrawable(int number) {
        for (int i = 0; i < 4; i++) {
            if (number == i) {
                textViews.get(i).setTextColor(Color.parseColor("#73B3B5"));
                imageviews.get(i).setImageResource(blueIcons.get(i));
            } else {
                textViews.get(i).setTextColor(Color.parseColor("#A5A5A5"));
                imageviews.get(i).setImageResource(grayIcons.get(i));
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(BusSkipBean bean) {
        viewPager.setCurrentItem(bean.getSkipId());
        setImageDrawable(2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
