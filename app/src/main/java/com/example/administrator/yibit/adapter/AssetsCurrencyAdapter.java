package com.example.administrator.yibit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yibit.R;
import com.example.administrator.yibit.bean.AccountInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssetsCurrencyAdapter extends RecyclerView.Adapter<AssetsCurrencyAdapter.MyHolder> implements View.OnClickListener {

    private AssetsCurrencyClickListener listener;
    private Context context;
    private List<AccountInfoBean.AssetEntity> list;

    public AssetsCurrencyAdapter(Context context, List<AccountInfoBean.AssetEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_assets_currency, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.type.setText(list.get(i).getAsset_id());
        myHolder.moneyOne.setText(list.get(i).getAmount()+"");
        myHolder.moneyTwo.setText("---");

        myHolder.itemView.setOnClickListener(this);
        myHolder.itemView.setTag(i);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        listener.onAssetsCurrencytClickListener(list.get(position), position);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.money_two)
        TextView moneyTwo;
        @BindView(R.id.money_one)
        TextView moneyOne;
        @BindView(R.id.image)
        ImageView imageView;
        @BindView(R.id.type)
        TextView type;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface AssetsCurrencyClickListener {
        void onAssetsCurrencytClickListener(AccountInfoBean.AssetEntity bean, int position);
    }

    public void setAssetsCurrencytListener(AssetsCurrencyClickListener listener) {
        this.listener = listener;
    }
}
