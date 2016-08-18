package com.zbPro.seed.adminActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.activity.BaseActivity;
import com.zbPro.seed.activity.R;
import com.zbPro.seed.util.Constant;

import java.io.IOException;

public class Admin_SeedOKActivity extends BaseActivity {
    // 获取技术员的名字以及基地号
    private String city1;
    private String city2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__seed_ok);
        Intent intent = getIntent();
        city1 = intent.getStringExtra("city1");
        city2 = intent.getStringExtra("city2");
        Toast.makeText(this, city1 + city2, Toast.LENGTH_SHORT).show();
        seedhttp();


    }

    private void seedhttp() {
        final OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("city1", city1)
                .add("city2", city1)
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
                    response =  client.newCall(request).execute();
                    if (response.isSuccessful()){


                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
