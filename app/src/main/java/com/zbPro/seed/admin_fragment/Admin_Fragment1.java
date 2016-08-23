package com.zbPro.seed.admin_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zbPro.seed.activity.R;
import com.zbPro.seed.adapter.MyAdminImportantAdapter;
import com.zbPro.seed.adminActivity.Admin_ImportantActivity;
import com.zbPro.seed.bean.ImportantBean;
import com.zbPro.seed.bean.ImportantTitleBean;
import com.zbPro.seed.net.HttpPost;
import com.zbPro.seed.util.Constant;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Admin_Fragment1 extends Fragment {
    MyAdminImportantAdapter myAdminImportantAdapter;
    @Bind(R.id.admin_fragment1_listView)
    ListView adminFragment1ListView;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String json = data.getString("json");
            updateUI(json);
            System.out.println("json" + json);

        }
    };

    private void updateUI(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<LinkedList<ImportantBean>>() {
        }.getType();
        //List<ImportantTitleBean> importantTitleBeen = new ArrayList<ImportantTitleBean>();
        final List<ImportantBean> importantBeen = gson.fromJson(json, type);
        myAdminImportantAdapter = new MyAdminImportantAdapter(importantBeen, getActivity());
        adminFragment1ListView.setAdapter(myAdminImportantAdapter);
        adminFragment1ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImportantBean importantBean = importantBeen.get(position);
                System.out.println("电机的数据"+importantBean.toString());
                Intent intent = new Intent(getActivity(),Admin_ImportantActivity.class);
                intent.putExtra("importantBean123",importantBean);
                startActivity(intent);

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin__fragment1, null);
        ButterKnife.bind(this, view);
        //获取重大信息数据

        init();
        return view;
    }

    private void init() {
        Gson gson = new Gson();
        ImportantTitleBean importantTitleBean = new ImportantTitleBean("ddd", "dd", "s", "sss");
        final String s = gson.toJson(importantTitleBean);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = HttpPost.SendhttpPostJson(s, Constant.PATH + Constant.ADMINIMPORTANT);
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("json", json);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }).start();


    }

    //初始化控件
    /*private void init() {
     *//*   List<HashMap<String, Object>> list = getAllData();*//*
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), list, R.layout.admin_listview_item, new String[]{"image", "title", "content"},
                new int[]{R.id.admin_listview_item_titleimage, R.id.admin_listview_item_titletext1, R.id.admin_listview_item_titletext2});
        adminFragment1ListView.setAdapter(adapter);
    }*/

   /* //获取填充到ListView中的数据
    private List<HashMap<String, Object>> getAllData() {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < 20; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("title", "标题" + i);
            map.put("content", "内容" + i);
            map.put("image", R.mipmap.head_image_60);
            list.add(map);
        }
        return list;
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
