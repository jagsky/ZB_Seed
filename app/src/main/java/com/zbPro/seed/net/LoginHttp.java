package com.zbPro.seed.net;

import android.os.Bundle;
import android.os.Message;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.collector.LogBase;
import com.zbPro.seed.util.Constant;

import java.io.IOException;

/**
 * Created by Administrator on 2016/6/7.
 * ClassName ：com.zbPro.seed.net
 * 作用：
 */
public class LoginHttp {



    public synchronized static void postRequest(String address) {
        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .build();

        final Request request = new Request.Builder()
                .url(Constant.PATH + Constant.LOGIN)
                .post(formBody)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        response.body().string();
                        LogBase.i("打印POST响应的数据：" + response.body().string());
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}
