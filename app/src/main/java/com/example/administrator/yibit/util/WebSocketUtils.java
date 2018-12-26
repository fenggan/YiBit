package com.example.administrator.yibit.util;

import com.example.administrator.yibit.Constact;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocketListener;

public class WebSocketUtils {
    public static void request(WebSocketListener listener) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(Constact.SocketUrl).build();
        client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }
}
