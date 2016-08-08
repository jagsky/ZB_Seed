package com.zbPro.seed.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.bean.City;
import com.zbPro.seed.dao.CityDao;
import com.zbPro.seed.util.Constant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//管理员登入后启动的后台，用于返回技术员基本信息。
public class Admin_LoginService extends Service {
    String userName;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String jsonstr = bundle.getString("str");
            System.out.println("子线程获取" + jsonstr);
            jsonTOArray(jsonstr);

        }
    };


    public Admin_LoginService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        userName = intent.getStringExtra("userName");
        isRegisterOKGoHttpGet();
        return START_NOT_STICKY;
    }

    public void isRegisterOKGoHttpGet() {
        final OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);
        client.setWriteTimeout(30, TimeUnit.SECONDS);
        RequestBody formBody = new FormEncodingBuilder()
                .add("userName", userName)
                .build();

        final Request request = new Request.Builder()
                .url(Constant.PATH + Constant.TECHNICIAN)
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
        Type type = new TypeToken<LinkedList<City>>() {
        }.getType();
        CityDao cityDao = new CityDao(Admin_LoginService.this);

        List<City> cities = gson.fromJson(jsonstr, type);
        System.out.println("这个是我新打印的" + cities.toString());
        try {
            for (int i = 0; i < cities.size(); i++) {

                cityDao.addCity(cities.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
