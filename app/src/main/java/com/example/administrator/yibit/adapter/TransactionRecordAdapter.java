package com.example.administrator.yibit.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yibit.R;
import com.example.administrator.yibit.bean.SlideBean;
import com.example.administrator.yibit.bean.TransactionRecordBean;
import com.example.administrator.yibit.bean.TransferAccountRecordBean;
import com.example.administrator.yibit.util.StringUtils;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionRecordAdapter extends RecyclerView.Adapter<TransactionRecordAdapter.MyHolder> implements View.OnClickListener {

    private TransactionRecordClickListener listener;
    private Context context;
    private List<TransferAccountRecordBean.DataEntity> list;

    public TransactionRecordAdapter(Context context, List<TransferAccountRecordBean.DataEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_transaction_record, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        double num=list.get(i).getAsset().getAsset_num();
        if(num>0){
            myHolder.money.setText("+"+num);
            myHolder.money.setTextColor(Color.parseColor("#5ADBB1"));
        }else {
            myHolder.money.setText("-"+num);
            myHolder.money.setTextColor(Color.parseColor("#E3675C"));
        }
        myHolder.time.setText((list.get(i).getTime()).replace("T"," "));
        myHolder.number.setText(list.get(i).getTransfer_id());


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
        listener.onTransactionRecordClickListener(list.get(position), position);
    }

    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.money)
        TextView money;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface TransactionRecordClickListener {
        void onTransactionRecordClickListener(TransferAccountRecordBean.DataEntity bean, int position);
    }

    public void setTransactionRecordListener(TransactionRecordClickListener listener) {
        this.listener = listener;
    }
}
