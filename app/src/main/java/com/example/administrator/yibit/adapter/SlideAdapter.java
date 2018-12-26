package com.example.administrator.yibit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.administrator.yibit.R;
import com.example.administrator.yibit.bean.SlideBean;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.MyHolder> implements View.OnClickListener{

    private SlideClickListener listener;
    private Context context;
    private List<SlideBean> list;
    public SlideAdapter(Context context, List<SlideBean> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.adapter_slide,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.itemView.setOnClickListener(this);
        myHolder.itemView.setTag(i);
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View v) {
        int position= (int) v.getTag();
        listener.onSlideClickListener(list.get(position),position);
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.checkbox)
        CheckBox checkBox;
        @BindView(R.id.username)
        TextView userName;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface SlideClickListener{
        void onSlideClickListener(SlideBean bean, int position);
    }
    public void setSlideListener(SlideClickListener listener){
        this.listener=listener;
    }
}
