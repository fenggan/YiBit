package com.example.administrator.yibit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.yibit.R;
import com.example.administrator.yibit.bean.WalletBean;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.MyHolder> implements View.OnClickListener{

    private WalletClickListener listener;
    private Context context;
    private List<WalletBean> list;
    public WalletAdapter(Context context, List<WalletBean> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.adapter_wallet,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.itemView.setOnClickListener(this);
        myHolder.itemView.setTag(i);
        if(i==0){
            myHolder.privateKey.setText("请备份私钥");
        }
        //TODO 设置数据。
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        int position= (int) v.getTag();
        listener.onWalletClickListener(list.get(position),position);
    }

    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.username)
        TextView userName;
        @BindView(R.id.private_key)
        TextView privateKey;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.type)
        TextView type;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface WalletClickListener{
        void onWalletClickListener(WalletBean bean,int position);
    }
    public void setWalletListener(WalletClickListener listener){
        this.listener=listener;
    }
}
