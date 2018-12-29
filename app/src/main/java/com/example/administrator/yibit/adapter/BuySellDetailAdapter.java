package com.example.administrator.yibit.adapter;

import android.content.Context;
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

public class BuySellDetailAdapter extends RecyclerView.Adapter<BuySellDetailAdapter.MyHolder>{

    private Context context;
    private List<BuySellBean> list;
    public BuySellDetailAdapter(Context context, List<BuySellBean> list){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public BuySellDetailAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.adapter_buysell_detail,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuySellDetailAdapter.MyHolder myHolder, int i) {
            //TODO 设置数据
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.number)
        TextView number;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
