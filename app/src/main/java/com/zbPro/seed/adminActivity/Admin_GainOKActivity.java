package com.zbPro.seed.adminActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.activity.R;
import com.zbPro.seed.adapter.MyAdminGainAdapter;
import com.zbPro.seed.bean.GainBean;
import com.zbPro.seed.util.Constant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Admin_GainOKActivity extends AppCompatActivity {
    ArrayList<GainBean> gainBeen1;
    ArrayList<GainBean> gainBeen2;
    MyAdminGainAdapter myAdminGainAdapter1;
    MyAdminGainAdapter myAdminGainAdapter2;
    @Bind(R.id.admin_gain_list1)
    ListView adminGainList1;
    @Bind(R.id.admin_gain_list2)
    ListView adminGainList2;
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
        Type type = new TypeToken<LinkedList<GainBean>>() {
        }.getType();
        LinkedList<GainBean> farmerLinkedList = gson.fromJson(isSkiplogin, type);
        System.out.println("发送过来的数据" + farmerLinkedList.toString());
        gainBeen1 = new ArrayList<GainBean>();
        gainBeen2 = new ArrayList<GainBean>();
        for (int i = 0; i < farmerLinkedList.size(); i++) {
            if (farmerLinkedList.get(i).getGainOutput() != null && farmerLinkedList.get(i).getGainOutput().length() > 0) {

                gainBeen1.add(farmerLinkedList.get(i));
            } else {

                gainBeen2.add(farmerLinkedList.get(i));
            }
        }
        myAdminGainAdapter1 = new MyAdminGainAdapter(Admin_GainOKActivity.this, gainBeen1);
        myAdminGainAdapter2 = new MyAdminGainAdapter(Admin_GainOKActivity.this, gainBeen2);
        adminGainList1.setAdapter(myAdminGainAdapter1);
        adminGainList2.setAdapter(myAdminGainAdapter2);
        adminGainList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GainBean gainBean = gainBeen1.get(position);
                ViewHolderGain viewHolderGain = new ViewHolderGain();


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__gain_ok);
        ButterKnife.bind(this);
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
                .url(Constant.PATH + Constant.ADMIN_GAIN)
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

    class ViewHolderGain {
        EditText editText1;
        EditText editText2;
        EditText editText3;
        EditText editText4;
        EditText editText5;
        EditText editText6;
        EditText editText7;
        EditText editText8;
        EditText editText9;
        EditText editText10;
        EditText editText11;

    }
}
