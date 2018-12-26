package com.example.administrator.yibit.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class ClipUtils {

    /**
     * @param context   上下文
     * @param content   复制的内容
     * @return
     */
    public static String clipCopy(Context context,String content){
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setPrimaryClip(ClipData.newPlainText(null, content));
        return  cmb.getPrimaryClip().getItemAt(0).getText().toString();
    }
}
