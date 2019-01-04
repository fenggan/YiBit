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
import com.example.administrator.yibit.bean.TransferAccountRecordBean;
import com.example.administrator.yibit.util.StringUtils;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TransferAccountRecordAdapter extends RecyclerView.Adapter<TransferAccountRecordAdapter.MyHolder> implements View.OnClickListener {

    private TransactionClickListener listener;
    private Context context;
    private List<TransferAccountRecordBean.DataEntity> list;

    public TransferAccountRecordAdapter(Context context, List<TransferAccountRecordBean.DataEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_transaction, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.currencyName.setText(list.get(i).getForm());
        myHolder.currencyTime.setText((list.get(i).getTime()).replace("T"," "));
        double temp=list.get(i).getAsset().getAsset_num();
        if(temp>0){
            myHolder.currencyNumber.setText("+"+temp);
            myHolder.currencyNumber.setTextColor(Color.parseColor("#5ADBB1"));
        }else{
            myHolder.currencyNumber.setText("-"+temp);
            myHolder.currencyNumber.setTextColor(Color.parseColor("#E3675C"));
        }


        myHolder.itemView.setOnClickListener(this);
        myHolder.itemView.setTag(i);
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        listener.onTransactionClickListener(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.currency_name)
        TextView currencyName;
        @BindView(R.id.currency_time)
        TextView currencyTime;
        @BindView(R.id.currency_number)
        TextView currencyNumber;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface TransactionClickListener {
        void onTransactionClickListener(TransferAccountRecordBean.DataEntity bean, int position);
    }

    public void setWalletListener(TransactionClickListener listener) {
        this.listener = listener;
    }
}
