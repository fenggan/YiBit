package com.example.administrator.yibit.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.yibit.R;
import com.example.administrator.yibit.bean.BuySellBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyListAdapter extends RecyclerView.Adapter<BuyListAdapter.MyHolder> {

    private Context context;
    private List<BuySellBean.AsksEntity> list;

    public BuyListAdapter(Context context, List<BuySellBean.AsksEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BuyListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_buysell_detail, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyListAdapter.MyHolder myHolder, int i) {
        myHolder.price.setText(list.get(i).getPrice());
        myHolder.number.setText(list.get(i).getQuote());
        myHolder.price.setTextColor(Color.parseColor("#5ADBB1"));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.number)
        TextView number;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
