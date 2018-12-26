package com.example.administrator.yibit.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import com.google.zxing.WriterException;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.encode.CodeCreator;

/**
 * 调用此类前，请先申请相关的权限。camera,storage.
 */
public class QRCodeUtils {

    /**
     * 调用此方法请务必重写onActivityResult方法，在该方法中获取扫描结果。resultCode=-1。
     * 如果调用该方法的是fragment请传入fragment,如果是activity，请传入acitivity。否则收不到相应的回调。
     * 回调的内容通过“codedContent”获取。
     * @param activity
     * @param fragment
     * @param requestCode   该值应该大于0，小于65535
     */
     public static void scan(Activity activity, Fragment fragment,int requestCode){
         if(activity!=null){
             activity.startActivityForResult(new Intent(activity, CaptureActivity.class), requestCode);
         }else if(fragment!=null){
             fragment.startActivityForResult(new Intent(fragment.getActivity(), CaptureActivity.class), requestCode);
         }
     }

    /**
     *
     * @param content   生成二维码的内容
     * @param width     二维码的宽度
     * @param height    二维码的高度
     * @param logo      二维码的logo，没有可以为NULL
     * @return
     */
     public static Bitmap createQR(String content,int width,int height,Bitmap logo){
         Bitmap bitmap=null;
         try {
             if(logo!=null){
                 bitmap= CodeCreator.createQRCode(content,width,height,logo);
             }else{
                 bitmap= CodeCreator.createQRCode(content,width,height,null);
             }
         } catch (WriterException e) {
             e.printStackTrace();
         }
         return bitmap;
     }
}
