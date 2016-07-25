package com.zbPro.seed.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/6/7.
 * ClassName ：com.zbPro.seed.fragment
 * 作用：
 */
public class Fragment1 extends Fragment {
    String timeContent;
    String bobyContent;
    @Bind(R.id.fragment1_et)
    TextView fragment1Et;
    @Bind(R.id.content_et)
    EditText contentEt;
    @Bind(R.id.fragment1_send_btn)
    Button fragment1SendBtn;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentpage1, null);
        ButterKnife.bind(this, view);
        TimeThrad timeThread = new TimeThrad();
        timeThread.start();
        return view;
    }

    @OnClick({R.id.fragment1_send_btn})
    public void onClick(View view) throws IOException {
        switch (view.getId()) {
            case R.id.fragment1_send_btn:
                timeContent = fragment1Et.getText().toString();
                bobyContent = contentEt.getText().toString();
                if (bobyContent.length() == 0 && bobyContent.equals("")) {
                    Toast.makeText(getActivity(), "请输入信息", Toast.LENGTH_SHORT).show();
                } else {
                    sendHttpPost();
                }
                break;
        }
    }

    private void sendHttpPost() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormEncodingBuilder()
                .add("timeContent", timeContent)
                .add("bobyContent", bobyContent)
                .build();
        Request request = new Request.Builder()
                .url(Constant.PATH + Constant.EVERYDAY)
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            String resultSet = response.body().toString();
            System.out.println(resultSet);
        } else if (response.isRedirect()) {

            new Throwable("response" + response.body().toString());
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}

