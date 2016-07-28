package com.zbPro.seed.adminActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zbPro.seed.activity.BaseActivity;
import com.zbPro.seed.activity.R;
import com.zbPro.seed.view.RefreshableView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main2Activity extends BaseActivity {


    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.refreshable_view)
    RefreshableView refreshableView;
    ArrayAdapter<String> adapter;
    String[] items = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        }, 0);
    }
}
