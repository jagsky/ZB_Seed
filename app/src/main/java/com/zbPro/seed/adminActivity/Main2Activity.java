package com.zbPro.seed.adminActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.zbPro.seed.activity.BaseActivity;
import com.zbPro.seed.activity.R;
import com.zbPro.seed.bean.City;
import com.zbPro.seed.net.HttpPost;
import com.zbPro.seed.util.Constant;
import com.zbPro.seed.view.RefreshableView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main2Activity extends BaseActivity {


    ListView listView;
    RefreshableView refreshableView;
    ArrayAdapter<String> adapter;
    String[] items = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                //在这里添加新的数据
                ArrayList arrayList = new ArrayList();
                arrayList.add("a");
                System.out.println(arrayList.toString());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        }, 0);
    }

    private void getHttpPostRequest() {
        final Gson gson = new Gson();
        final City city = new City();
        city.setCityName("长沙");
        city.setFirstLetter("A");
        final String jsonStr = gson.toJson(city);
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpPost.SendhttpPostJson(jsonStr, Constant.PATH + Constant.IMPORTANT);
            }
        }).start();

    }
}
