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
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.adapter.AssetsCurrencyAdapter;
import com.example.administrator.yibit.bean.AssetsCurrencyBean;
import com.example.administrator.yibit.adapter.SlideAdapter;
import com.example.administrator.yibit.bean.SlideBean;
import com.example.administrator.yibit.util.ClipUtils;
import com.example.administrator.yibit.util.PhoneState;
import com.example.administrator.yibit.util.QRCodeUtils;
import com.example.administrator.yibit.view.activity.AssetsDetailActivity;
import com.example.administrator.yibit.view.activity.CreateUserActivity;
import com.example.administrator.yibit.view.activity.ImportWalletActivitiy;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AssetsFragment extends Fragment implements SlideAdapter.SlideClickListener,AssetsCurrencyAdapter.AssetsCurrencyClickListener {

    private Unbinder unbinder;

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
        return view;
    }

    private void init() {
        SlideAdapter slideAdapter=new SlideAdapter(getActivity(),new ArrayList<SlideBean>());
        slideAdapter.setSlideListener(this);
        slideRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        slideRecycler.setAdapter(slideAdapter);

        AssetsCurrencyAdapter assetsCurrencyAdapter = new AssetsCurrencyAdapter(getActivity(), new ArrayList<AssetsCurrencyBean>());
        assetsCurrencyAdapter.setAssetsCurrencytListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(assetsCurrencyAdapter);
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
//                showPopWindow("1234");
                Intent intent=new Intent(getActivity(),AssetsDetailActivity.class);
                startActivity(intent);
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
        //TODO 通知资产页面刷新相关的数据。
    }

    @Override
    public void onAssetsCurrencytClickListener(AssetsCurrencyBean bean, int position) {
        //TODO 传递数据到资产详情页面
        Intent intent=new Intent(getActivity(),AssetsDetailActivity.class);
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

        Bitmap bitmap=QRCodeUtils.createQR(qrCode,1000,1000,null);
        qr.setBackground(new BitmapDrawable(bitmap));
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipUtils.clipCopy(getActivity(),userName.getText().toString().trim());
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

        window.setContentView(view);
        window.setOutsideTouchable(true);
        window.setFocusable(true);
        window.setWidth(PhoneState.getScreenWidth(getActivity()));
        window.setHeight(PhoneState.getScreenHeight(getActivity()));
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PhoneState.setBackgroundAlpha(getActivity(),1);
            }
        });
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_cornor_white));
        window.showAtLocation(root,Gravity.BOTTOM,0,0);
        PhoneState.setBackgroundAlpha(getActivity(),0.5f);
    }

}
