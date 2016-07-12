package com.zbPro.seed.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.service.FarmerService;
import com.zbPro.seed.util.Constant;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 创建时间：2016/5/23
* ClassName：RegisterActivity
* 作用：注册界面
* 实现功能：注册用户，并且把注册信息发送至服务端去匹配对应的数据。
* */
public class RegisterActivity extends BaseActivity {


    //获取EditText控件字符串
    String register_userName;
    String register_password1;
    String register_password2;
    String register_idCard;
    //定义一个变量，判断注册请求是否成功。
    String isRegisterOK;
    private SharedPreferences preferences;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            isRegisterOK = bundle.getString("str");
            IsRegisterOK(isRegisterOK);

        }
    };

    @Bind(R.id.register_userTv)
    TextView registerUserTv;
    @Bind(R.id.register_userEt)
    EditText registerUserEt;
    @Bind(R.id.register_passworld1Tv)
    TextView registerPassworld1Tv;
    @Bind(R.id.register_passworld1Et)
    EditText registerPassworld1Et;
    @Bind(R.id.register_password2Tv)
    TextView registerPassword2Tv;
    @Bind(R.id.register_passworld2Et)
    EditText registerPassworld2Et;
    @Bind(R.id.register_idCardTv)
    TextView registerIdCardTv;
    @Bind(R.id.register_idCardEt)
    EditText registerIdCardEt;
    @Bind(R.id.register_registerBtn)
    Button registerRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.register_registerBtn)
    public void onClick() {
        register_userName = registerUserEt.getText().toString();
        register_password1 = registerPassworld1Et.getText().toString();
        register_password2 = registerPassworld2Et.getText().toString();
        register_idCard = registerIdCardEt.getText().toString();
        //判断密码是否一致。
        //判断密码是否一致。
        if (register_password1.equals(register_password2)) {
            //如果密码一致，则发送注册的信息去匹配服务端对应的信息。
            isRegisterOKGoHttpGet(register_userName, register_password1, register_idCard);


        } else {
            Toast.makeText(RegisterActivity.this, "注册密码不一致", Toast.LENGTH_SHORT).show();
        }

    }


    /*
      * 方法名称：preferences
      * 功能：保存注册的号码，跳转到主界面
      * 参数：无
      * */
    private void preferences() {
        preferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("IsOK", true);
        editor.putString("register_userName", register_userName);
        editor.putString("register_password", register_password1);
        //提交数据
        editor.commit();

    }

    /*
    * 方法名称：注册是否成功
    * 参数：register_userName：注册的用户名
    *      register_password1 注册的密码
    *      register_idCard 注册的身份证号码
    *
    *
    * */
    private void isRegisterOKGoHttpGet(String name, String psd,
                                       String idCard) {
        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormEncodingBuilder()
                .add("userName", name)
                .add("password", psd)
                .add("idCard", idCard)
                .build();

        final Request request = new Request.Builder()
                .url(Constant.PATH + Constant.REGISTER)
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
                        Log.i("WY", "打印POST响应的数据：" + str);
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


    /*
* 方法名称：IsSkiplogin
* 功能：判断
* 参数：无
* */
    private void IsRegisterOK(String isRegisterOK) {

        //如果返回的信息为1，则通过注册，跳转到主页面。
        if (isRegisterOK.equals("1")) {
            //将数据添加到文件中
            preferences();
            Intent startSeveice = new Intent(RegisterActivity.this, FarmerService.class);
            startService(startSeveice);
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
           /* intent.putExtra("userName", register_userName);
            intent.putExtra("isReqeust", 1);*/
            intent.putExtra("register", "register");
            startActivity(intent);
            finish();
            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
        } else if (isRegisterOK.equals("0")) {
            Toast.makeText(RegisterActivity.this, "用户已存在", Toast.LENGTH_SHORT).show();
        } else if (isRegisterOK.equals("2")) {
            Toast.makeText(RegisterActivity.this, "身份证不匹配", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(RegisterActivity.this, "服务器错误", Toast.LENGTH_SHORT).show();

        }
    }
}

