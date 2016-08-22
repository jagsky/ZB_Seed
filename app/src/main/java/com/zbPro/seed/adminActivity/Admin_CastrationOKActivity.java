package com.zbPro.seed.adminActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.activity.BaseActivity;
import com.zbPro.seed.activity.R;
import com.zbPro.seed.adapter.MyAdminCastrationAdapter;
import com.zbPro.seed.bean.CastrationBean;
import com.zbPro.seed.util.Constant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Admin_CastrationOKActivity extends BaseActivity {
    // 获取技术员的名字以及基地号
    ArrayList<CastrationBean> castrationBeen1;
    ArrayList<CastrationBean> castrationBeen2;
    MyAdminCastrationAdapter myAdminCastrationAdapter1;
    MyAdminCastrationAdapter myAdminCastrationAdapter2;
    @Bind(R.id.admin_cas_list1)
    ListView adminCasList1;
    @Bind(R.id.admin_cas_list2)
    ListView adminCasList2;

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
        Type type = new TypeToken<LinkedList<CastrationBean>>() {
        }.getType();
        LinkedList<CastrationBean> farmerLinkedList = gson.fromJson(isSkiplogin, type);
        System.out.println(farmerLinkedList.toString());
       /* if (farmerLinkedList == null && farmerLinkedList.size() == 0) {
            Toast.makeText(Admin_CastrationOKActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
        }*/
        System.out.println("发送过来的数据" + farmerLinkedList.toString());

        castrationBeen1 = new ArrayList<CastrationBean>();
        castrationBeen2 = new ArrayList<CastrationBean>();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__castration_ok);
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
                .url(Constant.PATH + Constant.ADMIN_CASTRATION)
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
