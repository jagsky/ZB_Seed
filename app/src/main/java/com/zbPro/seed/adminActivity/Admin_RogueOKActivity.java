package com.zbPro.seed.adminActivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.activity.R;
import com.zbPro.seed.bean.GainBean;
import com.zbPro.seed.bean.RogueBean;
import com.zbPro.seed.util.Constant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Admin_RogueOKActivity extends AppCompatActivity {
    // 获取技术员的名字以及基地号
    private String city1;
    private String city2;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String IsSkiplogin = bundle.getString("str");
            updateUI(IsSkiplogin);
            //IsSkiplogin(IsSkiplogin);

        }
    };

    private void updateUI(String isSkiplogin) {
        Gson gson = new Gson();
        Type type = new TypeToken<LinkedList<RogueBean>>() {
        }.getType();
        LinkedList<RogueBean> farmerLinkedList = gson.fromJson(isSkiplogin, type);
        System.out.println("发送过来的数据" + farmerLinkedList.toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__rogue_ok);
        Intent intent = getIntent();
        city1 = intent.getStringExtra("city1");
        city2 = intent.getStringExtra("city2");
        seedhttp();
    }

    private void seedhttp() {
        final OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);
        client.setWriteTimeout(30, TimeUnit.SECONDS);
        RequestBody requestBody = new FormEncodingBuilder()
                .add("city1", city1)
                .add("city2", city2)
                .build();

        final Request request = new Request.Builder()
                .url(Constant.PATH + Constant.ADMIN_ROGUE)
                .post(requestBody)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String str = response.body().string();
                        System.out.println(str);
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
}
