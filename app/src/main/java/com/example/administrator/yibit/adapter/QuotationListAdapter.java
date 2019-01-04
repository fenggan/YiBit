package com.example.administrator.yibit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.bean.QuotationListBean;
import com.example.administrator.yibit.util.StringUtils;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuotationListAdapter extends RecyclerView.Adapter<QuotationListAdapter.MyHolder> implements View.OnClickListener {

    private QuotationDetailClickListener listener;
    private Context context;
    private List<QuotationListBean.DataEntity> list;

    public QuotationListAdapter(Context context, List<QuotationListBean.DataEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuotationListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_quotation_detail, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuotationListAdapter.MyHolder myHolder, int i) {
        myHolder.currency.setText(StringUtils.synthesis(list.get(i).getQuote(),list.get(i).getBase()));
        myHolder.volume.setText("成交量:"+list.get(i).getQuote_volume());
        myHolder.priceOne.setText(list.get(i).getLowest_ask());
        String tempPercentage=list.get(i).getPercent_change()+"%";
        if(tempPercentage.contains("-")){
            myHolder.percentage.setBackgroundResource(R.drawable.shape_cornor_red);
            myHolder.percentage.setText("-"+tempPercentage);
        }else{
            myHolder.percentage.setBackgroundResource(R.drawable.shape_cornor_green);
            myHolder.percentage.setText("+"+tempPercentage);
        }

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
        listener.onQuotationDetailClickListener(list.get(position), position);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.currency)
        TextView currency;
        @BindView(R.id.volume)
        TextView volume;
        @BindView(R.id.price_one)
        TextView priceOne;
        @BindView(R.id.percentage)
        Button percentage;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface QuotationDetailClickListener {
        void onQuotationDetailClickListener(QuotationListBean.DataEntity bean, int position);
    }

    public void setQuotationDetailListener(QuotationDetailClickListener listener) {
        this.listener = listener;
    }
}
