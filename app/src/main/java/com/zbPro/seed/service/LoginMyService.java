package com.zbPro.seed.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.bean.FarmerBean;
import com.zbPro.seed.dao.FarmaerDao;
import com.zbPro.seed.util.Constant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginMyService extends Service {
    public LoginMyService() {
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String jsonstr = bundle.getString("str");
            jsonTOArray(jsonstr);

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
        System.out.println("开启服务");


    }

    String userName = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        userName = intent.getStringExtra("userName");
        isRegisterOKGoHttpGet();
        return START_NOT_STICKY;
    }

    private void isRegisterOKGoHttpGet() {
        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("userName", userName)
                .build();

        final Request request = new Request.Builder()
                .url(Constant.PATH + Constant.LOGINFARMER)
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
                        Log.i("WY", "打印POST响应的数据：" + str + "服务服务");
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

    private void jsonTOArray(String jsonstr) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<FarmerBean>>() {
        }.getType();
        FarmerBean farmerBean = new FarmerBean();
        FarmaerDao farmaerDao = new FarmaerDao(LoginMyService.this);

        List<FarmerBean> farmerBeanList = gson.fromJson(jsonstr, type);
        try {
            for (int i = 0; i < farmerBeanList.size(); i++) {

                farmaerDao.addFarmerBean(farmerBeanList.get(i));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("关闭服务");
    }
}
