package com.example.administrator.yibit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.bean.TransactionBean;
import com.example.administrator.yibit.bean.TransactionRecordBean;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyHolder> implements View.OnClickListener{

    private TransactionClickListener listener;
    private Context context;
    private List<TransactionBean> list;
    public TransactionAdapter(Context context, List<TransactionBean> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.adapter_transaction,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        //TODO 设置数据。
    }

    @Override
    public void onClick(View v) {
        int position= (int) v.getTag();
        listener.onTransactionClickListener(list.get(position),position);
    }
    @Override
    public int getItemCount() {
        return 0;
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
            ButterKnife.bind(this,itemView);
        }
    }
    public interface TransactionClickListener{
        void onTransactionClickListener(TransactionBean bean, int position);
    }
    public void setWalletListener(TransactionClickListener listener){
        this.listener=listener;
    }
}
