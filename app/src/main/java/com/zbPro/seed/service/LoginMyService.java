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
import com.zbPro.seed.bean.CastrationBean;
import com.zbPro.seed.bean.City;
import com.zbPro.seed.bean.FarmerBean;
import com.zbPro.seed.bean.GainBean;
import com.zbPro.seed.bean.RogueBean;
import com.zbPro.seed.bean.Seed;
import com.zbPro.seed.dao.CastrationDao;
import com.zbPro.seed.dao.CityDao;
import com.zbPro.seed.dao.FarmaerDao;
import com.zbPro.seed.dao.GainDao;
import com.zbPro.seed.dao.RogueDao;
import com.zbPro.seed.dao.SeedDao;
import com.zbPro.seed.util.Constant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//技术员登入后，返回农户基本信息
public class LoginMyService extends Service {
    Seed seed;
    SeedDao seedDao;
    CastrationBean castrationBean;
    CastrationDao castrationDao;
    GainBean gainBean;
    GainDao gainDao;
    RogueBean rogueBean;
    RogueDao rogueDao;
    FarmerBean farmerBean;
    FarmaerDao farmaerDao;
    CityDao cityDao;
    City city;

    public LoginMyService() {
        seedDao = new SeedDao(this);
        castrationDao = new CastrationDao(this);
        gainDao = new GainDao(this);
        rogueDao = new RogueDao(this);
        farmaerDao = new FarmaerDao(this);
        cityDao = new CityDao(this);


    }

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
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);
        client.setWriteTimeout(30, TimeUnit.SECONDS);
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

    //将返回的Json数据添加到本地对应的数据库中
    private void jsonTOArray(String jsonstr) {
        Gson gson = new Gson();
        Type type = new TypeToken<LinkedList<FarmerBean>>() {
        }.getType();


        List<FarmerBean> farmerBeanList = gson.fromJson(jsonstr, type);
        // System.out.println("这个是我新打印的" + farmerBeanList.toString());
        try {
            for (int i = 0; i < farmerBeanList.size(); i++) {
                farmaerDao.addFarmerBean(farmerBeanList.get(i));
                seed = new Seed(farmerBeanList.get(i).getFarmerName(), farmerBeanList.get(i).getdKnumber(), farmerBeanList.get(i).getType());
                seedDao.addSeed(seed);
                gainBean = new GainBean(farmerBeanList.get(i).getFarmerName(), farmerBeanList.get(i).getdKnumber(), farmerBeanList.get(i).getType());
                gainDao.addGainBean(gainBean);
                castrationBean = new CastrationBean(farmerBeanList.get(i).getFarmerName(), farmerBeanList.get(i).getdKnumber(), farmerBeanList.get(i).getType());
                castrationDao.addCastrationBean(castrationBean);
                rogueBean = new RogueBean(farmerBeanList.get(i).getFarmerName(), farmerBeanList.get(i).getdKnumber(), farmerBeanList.get(i).getType());
                rogueDao.addRogueBean(rogueBean);
                city = new City(farmerBeanList.get(i).getFarmerName() + "[" + farmerBeanList.get(i).getdKnumber() + "]", farmerBeanList.get(i).getFarmer_letter());
                cityDao.addCity(city);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean stopService(Intent name) {
        System.out.println("停止服务");
        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("关闭服务");
    }
}
