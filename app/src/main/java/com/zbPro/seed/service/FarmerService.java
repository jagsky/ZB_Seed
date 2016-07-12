package com.zbPro.seed.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.net.HttpPost;
import com.zbPro.seed.util.Constant;

import java.io.IOException;

/**
 * Created by Administrator on 2016/7/1.
 */
public class FarmerService extends Service {
    private String isRegisterOK;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            isRegisterOK = bundle.getString("str");
            IsRegisterOK(isRegisterOK);

        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String register_idCard = intent.getStringExtra("register_idCard");
        isRegisterOKGoHttpGet(register_idCard);
        return START_NOT_STICKY;
    }

    private void isRegisterOKGoHttpGet(String idCard) {
        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("idCard", idCard)
                .build();

        final Request request = new Request.Builder()
                .url(Constant.PATH + Constant.FARMER)
                .post(formBody)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {

                        String str = response.body().string();
                        Log.i("WY", "打印POST响应的数据：" + str);
                        Message message = handler.obtainMessage();
                        Bundle bundle = new Bundle();
                        bundle.putString("str", str);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void IsRegisterOK(String isRegisterOK) {
    }
}
