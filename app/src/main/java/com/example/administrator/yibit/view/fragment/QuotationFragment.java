package com.example.administrator.yibit.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.yibit.Constact;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.adapter.QuotationAdapter;
import com.example.administrator.yibit.http.RetrofitUtils;
import com.example.administrator.yibit.widget.NoScrollViewpager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Retrofit;

public class QuotationFragment extends Fragment {
    private Unbinder unbinder;
    private List<Fragment> fragments;
    private String[] titles;

    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    NoScrollViewpager viewpager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=getLayoutInflater().inflate(R.layout.fragment_quotation,container,false);
        unbinder=ButterKnife.bind(this,view);
        init();
        initView();
        return view;
    }

    private void init(){
        titles=getResources().getStringArray(R.array.titles);
        fragments=new ArrayList<>();
        fragments.add(QuotationListFragment.newInstance(0));
        fragments.add(QuotationListFragment.newInstance(1));
        fragments.add(QuotationListFragment.newInstance(2));
        fragments.add(QuotationListFragment.newInstance(3));
    }
    private void initView(){
        QuotationAdapter adapter=new QuotationAdapter(getFragmentManager(),titles,fragments);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        viewpager.setCurrentItem(0);
        viewpager.setOffscreenPageLimit(4);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}