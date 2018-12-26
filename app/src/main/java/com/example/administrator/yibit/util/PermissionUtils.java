package com.example.administrator.yibit.util;

import android.Manifest;
import android.content.Context;

import com.example.administrator.yibit.R;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

public class PermissionUtils {
    public final static int SENSER = 0;
    public final static int CAMERA = 1;
    public final static int STORAGE = 2;
    public final static int CALENDAR = 3;
    public final static int CONSTACT = 4;
    public final static int LOCATION = 5;
    public final static int MICRO_PHONE = 6;
    public final static int SMS = 7;
    public final static int PHONE = 8;


    /**
     * @param context
     * @param code     需要申请权限的数组
     * @param callback
     */
    public static void permission(Context context, int[] code, PermissionCallback callback) {
        List<PermissionItem> list = new ArrayList<>();
        for (int i = 0; i < code.length; i++) {
            switch (i) {
                case 0:
                    list.add(new PermissionItem(Manifest.permission.BODY_SENSORS, "SENSER", R.drawable.permission_ic_sensors));
                    break;
                case 1:
                    list.add(new PermissionItem(Manifest.permission.CAMERA, "CAMERA", R.drawable.permission_ic_camera));
                    break;
                case 2:
                    list.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "STORAGE", R.drawable.permission_ic_storage));
                    break;
                case 3:
                    list.add(new PermissionItem(Manifest.permission.READ_CALENDAR, "CALENDAR", R.drawable.permission_ic_calendar));
                    break;
                case 4:
                    list.add(new PermissionItem(Manifest.permission.READ_CONTACTS, "CONSTACT", R.drawable.permission_ic_contacts));
                    break;
                case 5:
                    list.add(new PermissionItem(Manifest.permission.ACCESS_COARSE_LOCATION, "LOCATION", R.drawable.permission_ic_location));
                    break;
                case 6:
                    list.add(new PermissionItem(Manifest.permission.RECORD_AUDIO, "MICRO_PHONE", R.drawable.permission_ic_micro_phone));
                    break;
                case 7:
                    list.add(new PermissionItem(Manifest.permission.READ_SMS, "SMS", R.drawable.permission_ic_sms));
                    break;
                case 8:
                    list.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "PHONE", R.drawable.permission_ic_phone));
                    break;
            }
        }
        HiPermission.create(context).permissions(list).checkMutiPermission(callback);
    }
}
