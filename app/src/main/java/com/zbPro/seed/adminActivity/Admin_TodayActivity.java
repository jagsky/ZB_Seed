package com.zbPro.seed.adminActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.zbPro.seed.bean.TodayBean;
import com.zbPro.seed.util.Constant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*作用：手机日常信息
* 时间：2016.8.4
* */
public class Admin_TodayActivity extends BaseActivity {
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle todayData = msg.getData();
            String dataToday = (String) todayData.get("todayData");
            getUI(dataToday);
        }
    };
    String time;
    @Bind(R.id.admin_today_listview)
    ListView adminTodayListview;
    @Bind(R.id.admin_today_dateet)
    EditText adminTodayDateet;
    @Bind(R.id.admin_today_datebtn)
    Button adminTodayDatebtn;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar = Calendar.getInstance(Locale.CHINA);

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            adminTodayDateet.setText(format.format(calendar.getTime()));
            time = adminTodayDateet.getText().toString();
            System.out.println(time);

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__today);
        ButterKnife.bind(this);
        adminTodayDateet.setText(format.format(calendar.getTime()));
        time = adminTodayDateet.getText().toString();
        System.out.println(time);


    }

    @OnClick(R.id.admin_today_datebtn)
    public void onClick() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                d,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    //发送数据获取每日工作汇报
    private void sendHttpPost(String s) {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);

        RequestBody requestBody = new FormEncodingBuilder()
                .add("time", time)
                .build();
        final Request request = new Request.Builder()
                .url(Constant.PATH + Constant.ADMIN_TODAY)
                .post(requestBody)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String data = response.body().toString();
                        Message message = handler.obtainMessage();
                        Bundle bundle = new Bundle();
                        bundle.putString("todayData", data);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getUI(String dataToday) {
        Gson gson = new Gson();
        Type type = new TypeToken<LinkedList<TodayBean>>() {
        }.getType();
        List<TodayBean> todayBeen = gson.fromJson(dataToday, type);


    }

}
