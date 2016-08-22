package com.zbPro.seed.adminActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.activity.BaseActivity;
import com.zbPro.seed.activity.R;
import com.zbPro.seed.adapter.MyAdminSeedAdapter;
import com.zbPro.seed.adapter.MyBaseExpandableListAdapter;
import com.zbPro.seed.bean.Seed;
import com.zbPro.seed.util.Constant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Admin_SeedOKActivity extends BaseActivity {
    MyAdminSeedAdapter myAdminSeedAdapter;
    @Bind(R.id.exlist_lol)
    ListView exlistLol;
    // 获取技术员的名字以及基地号
    private String city1;
    private String city2;
    MyBaseExpandableListAdapter myBaseExpandableListAdapter;
    ArrayList<String> arrayList = new ArrayList<String>();
    private LinkedList<LinkedList<Seed>> iData;

    //
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
    ArrayList<String> farmerList;

    //把获取Json解析，更新UI界面
    private void updateUI(String isSkiplogin) {
        farmerList = new ArrayList<String>();
        Gson gson = new Gson();
        Type type = new TypeToken<LinkedList<Seed>>() {
        }.getType();
        LinkedList<Seed> farmerLinkedList = gson.fromJson(isSkiplogin, type);
       /* for (int i = 0; i < farmerLinkedList.size(); i++) {
            String str = farmerLinkedList.get(i).getFramarName();
            farmerLinkedList.get(i);
            farmerList.add(str);
        }*/
        myAdminSeedAdapter = new MyAdminSeedAdapter(this,farmerLinkedList);
       // ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, farmerList);
        exlistLol.setAdapter(myAdminSeedAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__seed_ok);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        city1 = intent.getStringExtra("city1");
        city2 = intent.getStringExtra("city2");
        seedhttp();


    }

    private void seedhttp() {
        System.out.println(city1 + "这是要发送到数据");
        final OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);
        client.setWriteTimeout(30, TimeUnit.SECONDS);
        RequestBody requestBody = new FormEncodingBuilder()
                .add("city1", city1)
                .add("city2", city2)
                .build();

        final Request request = new Request.Builder()
                .url(Constant.PATH + Constant.ADMIN_SEED)
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