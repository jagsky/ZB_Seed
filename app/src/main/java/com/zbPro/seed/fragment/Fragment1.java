package com.zbPro.seed.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.activity.R;
import com.zbPro.seed.util.Constant;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/6/7.
 * ClassName ：com.zbPro.seed.fragment
 * 作用：
 */
public class Fragment1 extends Fragment {
    SharedPreferences preferences;
    String timeContent;
    String bobyContent;
    @Bind(R.id.fragment1_et)
    TextView fragment1Et;
    @Bind(R.id.content_et)
    EditText contentEt;
    @Bind(R.id.fragment1_send_btn)
    Button fragment1SendBtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentpage1, null);
        TimeThrad timeThread = new TimeThrad();
        timeThread.start();
        ButterKnife.bind(this, view);
        return view;
    }


    private boolean isRun = true;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    long timeMillis = System.currentTimeMillis();
                    CharSequence format = DateFormat.format("yyyy年MM月dd日 HH时mm分 \n EEEE ", timeMillis);
                    fragment1Et.setText(format);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.fragment1_send_btn)
    public void onClick() {
        //获取界面上的数据
        timeContent = fragment1Et.getText().toString();
        bobyContent = contentEt.getText().toString();
        System.out.println(timeContent);
        if (bobyContent.length() == 0 && bobyContent.equals("")) {
            Toast.makeText(getActivity(), "请输入信息", Toast.LENGTH_SHORT).show();
        } else {
           getDialog();
        }


    }

    //获取界面上的数据并判断内容不能为空
    private void getActivityAllData() {
        Toast.makeText(getActivity(), "发送成功", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //发送到服务器
                sendHttpPost();
            }
        }).start();

    }

    //发送到服务器
    private void sendHttpPost() {
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String register_userName = preferences.getString("register_userName", "sss");
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("register_userName", register_userName)
                .add("timeContent", timeContent)
                .add("bobyContent", bobyContent)
                .build();
        Request request = new Request.Builder()
                .url(Constant.PATH + Constant.EVERYDAY)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String resultSet = response.body().toString();
                System.out.println(resultSet);
            } else {

                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class TimeThrad extends Thread {

        @Override
        public void run() {
            super.run();

            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    }

    //创建对话框
    private void getDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("是否发送");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivityAllData();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}

