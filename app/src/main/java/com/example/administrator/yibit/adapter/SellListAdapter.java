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

public class SellListAdapter extends RecyclerView.Adapter<SellListAdapter.MyHolder> {

    private Context context;
    private List<BuySellBean.BidsEntity> list;

    public SellListAdapter(Context context, List<BuySellBean.BidsEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SellListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_buysell_detail, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SellListAdapter.MyHolder myHolder, int i) {
        myHolder.price.setText(list.get(i).getPrice());
        myHolder.number.setText(list.get(i).getQuote());
        myHolder.price.setTextColor(Color.parseColor("#E3675C"));
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
