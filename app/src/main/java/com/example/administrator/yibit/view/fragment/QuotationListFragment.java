package com.example.administrator.yibit.view.fragment;

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

import com.example.administrator.yibit.Constact;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.adapter.QuotationListAdapter;
import com.example.administrator.yibit.bean.BusGetBuySellListBean;
import com.example.administrator.yibit.bean.BusSkipBean;
import com.example.administrator.yibit.bean.QuotationListBean;
import com.example.administrator.yibit.http.RetrofitUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QuotationListFragment extends Fragment implements QuotationListAdapter.QuotationDetailClickListener{
    private Unbinder unbinder;
    private RetrofitUtils retrofitUtils;
    private Map<String,String> map;
    private int assetNum;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_quotation_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        initData();
        return view;
    }

    private void init(){
        assetNum=getArguments().getInt("index");
    }

    private void initData(){
        map=new HashMap<>();
        map.put("asset_num",assetNum+"");
        retrofitUtils=new RetrofitUtils(getActivity(),Constact.BaseUrl);
        retrofitUtils.getAPIService()
                     .getquotationList(map)
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(new Observer<QuotationListBean>() {
                         @Override
                         public void onSubscribe(Disposable d) {

                         }

                         @Override
                         public void onNext(QuotationListBean quotationListBean) {
                             recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                             QuotationListAdapter adapter=new QuotationListAdapter(getActivity(),quotationListBean.getData());
                             adapter.setQuotationDetailListener(QuotationListFragment.this);
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

    public static QuotationListFragment newInstance(int index) {
        QuotationListFragment f = new QuotationListFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onQuotationDetailClickListener(QuotationListBean.DataEntity bean, int position) {
        EventBus.getDefault().post(new BusSkipBean(2));
        EventBus.getDefault().post(new BusGetBuySellListBean(bean.getQuote(),bean.getBase()));
    }
}
