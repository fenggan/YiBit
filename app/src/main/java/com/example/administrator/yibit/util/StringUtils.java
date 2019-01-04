package com.example.administrator.yibit.util;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;

public class StringUtils {
    public static SpannableStringBuilder synthesis(String s1,String s2){
        String temp=s1+" /"+s2;
        SpannableStringBuilder builder=new SpannableStringBuilder(temp);
        RelativeSizeSpan relativeSizeSpan=new RelativeSizeSpan(1.5f);
        builder.setSpan(relativeSizeSpan,0,s1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        ForegroundColorSpan foregroundColorSpan=new ForegroundColorSpan(Color.parseColor("#272729"));
        builder.setSpan(foregroundColorSpan,0,s1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return builder;
    }
    public static String conversion(String s1){
        String result=s1.substring(5,s1.length()-3).replaceFirst("T"," ");
        return result;
    }
}
