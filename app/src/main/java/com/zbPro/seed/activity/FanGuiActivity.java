package com.zbPro.seed.activity;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.collector.LogBase;
import com.zbPro.seed.util.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FanGuiActivity extends BaseActivity {

    @Bind(R.id.fangui1)
    EditText fangui1;
    @Bind(R.id.fangui2)
    EditText fangui2;
    @Bind(R.id.fangui_btn)
    Button fanguiBtn;

    String str1;
    String str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_gui);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.fangui_btn)
    public void onClick() {

        if (TextUtils.isEmpty(fangui1.getText())) {
            Toast.makeText(FanGuiActivity.this, "请输入数据", Toast.LENGTH_SHORT).show();
        } else {

            str1 = fangui1.getText().toString();
            if (!TextUtils.isEmpty(fangui2.getText())) {
                str2 = fangui2.getText().toString();
                sendhttpPost();
                Toast.makeText(FanGuiActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(FanGuiActivity.this, "请输入数据", Toast.LENGTH_SHORT).show();
            }

        }


    }

    private void sendhttpPost() {
        final OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setWriteTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
        RequestBody formBody = new FormEncodingBuilder()
                .add("str1", str1)
                .add("str2", str2)
                .build();

        final Request request = new Request.Builder()
                .url(Constant.PATH + Constant.FANGUI)
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
