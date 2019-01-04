package com.example.administrator.yibit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.yibit.R;
import com.example.administrator.yibit.bean.OrderListBean;
import com.example.administrator.yibit.util.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyHolder> {

    private Context context;
    private List<OrderListBean.DataEntity> list;

    public OrderListAdapter(Context context, List<OrderListBean.DataEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OrderListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_order_list, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.MyHolder myHolder, int i) {
        myHolder.currency.setText(list.get(i).getSell_price().getQuote().getAsset_id() + "/" + list.get(i).getSell_price().getBase().getAsset_id());
        myHolder.time.setText(StringUtils.conversion(list.get(i).getExpiration()));
        myHolder.price.setText(list.get(i).getW_price());
        myHolder.number.setText(list.get(i).getFor_sale()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.currency)
        TextView currency;
        @BindView(R.id.cancel)
        TextView cancel;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
