package com.zbPro.seed.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.adminActivity.Admin_MainActivity;
import com.zbPro.seed.collector.LogBase;
import com.zbPro.seed.service.Admin_LoginService;
import com.zbPro.seed.service.LoginMyService;
import com.zbPro.seed.util.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/*
* 创建时间：2016/5/23
* 类名：LoginActivity
* 作用：登入界面
* 实现功能：用户登入，并且把登入数据发送到服务端匹配是否存在此用户
* */
public class LoginActivity extends BaseActivity {
    //定义一个用户名与密码
    String userName;
    String password;
    String selectType;
    @Bind(R.id.radioMale)
    RadioButton radioMale;
    @Bind(R.id.radioFemale)
    RadioButton radioFemale;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    //判断登入是否成功
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String IsSkiplogin = bundle.getString("str");

            IsSkiplogin(IsSkiplogin);

        }
    };
    @Bind(R.id.password_tv)
    TextView passwordTv;
    @Bind(R.id.password_et)
    EditText passwordEt;
    @Bind(R.id.user_tv)
    TextView userTv;
    @Bind(R.id.user_et)
    EditText userEt;
    @Bind(R.id.login_btn)
    Button loginBtn;
    RadioButton riaodButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        riaodButton = (RadioButton) findViewById(R.id.radioMale);
        selectType = riaodButton.getText().toString();
        //获取IsOK事务的SharedPreferences对象以及编辑对象
        preferences = getSharedPreferences("login", MODE_PRIVATE);
        editor = preferences.edit();
        radioMale.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                // radioButton = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                riaodButton = (RadioButton) findViewById(radioButtonId);
                System.out.println(riaodButton.getText().toString());
            }
        });
    }


    @OnClick({R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                //点击登入按钮时，获取用户的账号和密码。
                userName = userEt.getText().toString();
                password = passwordEt.getText().toString();
                selectType = riaodButton.getText().toString();
                System.out.println(selectType+"selectType");


                //首先判断是否有网络可用
                // 然后执行网络请求方法，传入用户名与密码 获得是否验证成功。
                boolean isNetwork = isNetworkConnected();
                //   System.out.println(isNetwork);
                if (isNetwork) {
                    if (userName.length() != 0 && !userName.equals("") && password.length() != 0) {
                        postRequest();
                    } else {
                        Toast.makeText(LoginActivity.this, "账号/密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                }


                break;

        }
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /*
    * 方法名称：postRequest
    * 功能：在用户点击登入按钮时候 发送Post请求携带用户名与密码去判断用户账号与密码
    * 参数：name 用户名  psd 登入密码
    * */
    public void postRequest() {
        final OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setWriteTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);
        RequestBody formBody = new FormEncodingBuilder()
                .add("userName", userName)
                .add("password", password)
                .add("selectType", selectType)
                .build();

        final Request request = new Request.Builder()
                .url(Constant.PATH + Constant.LOGIN)
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
                        LogBase.i("打印POST响应的数据：ACTIVITY" + str);
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


    //是否登入成功，如果成功则在此处
    private void IsSkiplogin(String isSkiplogin) {
        // System.out.println(IsSkiplogin);
        if (isSkiplogin.equals("1")) {
            editor.putBoolean("IsOK", true);
            editor.putString("register_userName", userName);
            editor.putString("register_password", password);
            editor.putString("selectType", selectType);
            editor.commit();
            if (selectType.equals("技术员")) {
                Intent intent = new Intent(LoginActivity.this, LoginMyService.class);
                intent.putExtra("userName", userName);
                startService(intent);
                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(loginIntent);
                finish();

            } else if (selectType.equals("管理员")) {
                Intent intent = new Intent(LoginActivity.this, Admin_LoginService.class);
                intent.putExtra("userName", userName);
                startService(intent);
                Intent adminIntent = new Intent(LoginActivity.this, Admin_MainActivity.class);
                startActivity(adminIntent);
                finish();
            }


            Toast.makeText(LoginActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
        } else if (isSkiplogin.equals("0")) {
            Toast.makeText(LoginActivity.this, "密码/账号错误", Toast.LENGTH_SHORT).show();
        } else if (isSkiplogin.equals("")) {
            Toast.makeText(LoginActivity.this, "服务器错误", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "连接失败", Toast.LENGTH_SHORT).show();
        }
    }

    //通过idcard查询其管理农户的基本信息


}
