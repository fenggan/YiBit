package com.example.administrator.yibit.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yibit.Constact;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.adapter.AssetsCurrencyAdapter;
import com.example.administrator.yibit.bean.AccountInfoBean;
import com.example.administrator.yibit.adapter.SlideAdapter;
import com.example.administrator.yibit.bean.SlideBean;
import com.example.administrator.yibit.http.RetrofitUtils;
import com.example.administrator.yibit.util.ClipUtils;
import com.example.administrator.yibit.util.KeyboardUtils;
import com.example.administrator.yibit.util.PhoneStateUtils;
import com.example.administrator.yibit.util.QRCodeUtils;
import com.example.administrator.yibit.view.activity.AssetsDetailActivity;
import com.example.administrator.yibit.view.activity.CreateUserActivity;
import com.example.administrator.yibit.view.activity.ImportWalletActivitiy;
import java.util.ArrayList;
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

public class AssetsFragment extends Fragment implements SlideAdapter.SlideClickListener,AssetsCurrencyAdapter.AssetsCurrencyClickListener {

    private Unbinder unbinder;
    private RetrofitUtils retrofitUtils;
    private Map<String,String> map;

    @BindView(R.id.money_one)
    TextView moneyOne;
    @BindView(R.id.money_two)
    TextView moneyTwo;
    @BindView(R.id.username)
    TextView userName;
    @BindView(R.id.side_slide)
    ImageView sideSlide;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.recycler_slide)
    RecyclerView slideRecycler;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.root)
    LinearLayout root;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_assets, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        initData();
        return view;
    }

    private void init() {
        SlideAdapter slideAdapter=new SlideAdapter(getActivity(),new ArrayList<SlideBean>());
        slideAdapter.setSlideListener(this);
        slideRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        slideRecycler.setAdapter(slideAdapter);


    }
    private void initData(){
        map =new HashMap<>();
        map.put("user",Constact.account2);
        retrofitUtils=new RetrofitUtils(getActivity(),Constact.BaseUrl);
        retrofitUtils.getAPIService()
                .getAcounntInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AccountInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AccountInfoBean s) {
                        userName.setText(s.getUsername());
                        AssetsCurrencyAdapter assetsCurrencyAdapter = new AssetsCurrencyAdapter(getActivity(), s.getAsset());
                        assetsCurrencyAdapter.setAssetsCurrencytListener(AssetsFragment.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(assetsCurrencyAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick({R.id.create_wallet, R.id.import_wallet, R.id.side_slide,R.id.username})
    public void onCLick(View view) {
        switch (view.getId()) {
            case R.id.create_wallet:
                startActivity(new Intent(getActivity(), CreateUserActivity.class));
                break;
            case R.id.import_wallet:
                startActivity(new Intent(getActivity(), ImportWalletActivitiy.class));
                break;
            case R.id.side_slide:
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.username:
                showPopWindow(Constact.account2);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSlideClickListener(SlideBean bean, int position) {

    }

    @Override
    public void onAssetsCurrencytClickListener(AccountInfoBean.AssetEntity bean, int position) {
        Intent intent=new Intent(getActivity(),AssetsDetailActivity.class);
        intent.putExtra("currency",bean.getAsset_id());

        startActivity(intent);
    }

    private void showPopWindow(String qrCode) {
        final PopupWindow window = new PopupWindow(getActivity());
        View view = getLayoutInflater().inflate(R.layout.pop_qr, null);
        ImageView qr = view.findViewById(R.id.qr);
        ImageView close = view.findViewById(R.id.close);
        final TextView userName = view.findViewById(R.id.username);
        EditText number = view.findViewById(R.id.number);
        Button copy = view.findViewById(R.id.copy);

        userName.setText(qrCode);
        Bitmap bitmap=QRCodeUtils.createQR(qrCode,1000,1000,null);
        qr.setBackground(new BitmapDrawable(bitmap));
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipUtils.clipCopy(getActivity(),userName.getText().toString().trim());
                Toast.makeText(getActivity(), "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyboardUtils.hideKeyboard(v);
                window.dismiss();
            }
        });

        window.setContentView(view);
        window.setOutsideTouchable(true);
        window.setFocusable(true);
        window.setWidth(PhoneStateUtils.getScreenWidth(getActivity()));
        window.setHeight(PhoneStateUtils.getScreenHeight(getActivity()));
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PhoneStateUtils.setBackgroundAlpha(getActivity(),1);
            }
        });
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_cornor_white));
        window.showAtLocation(root,Gravity.BOTTOM,0,0);
        PhoneStateUtils.setBackgroundAlpha(getActivity(),0.5f);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
}
