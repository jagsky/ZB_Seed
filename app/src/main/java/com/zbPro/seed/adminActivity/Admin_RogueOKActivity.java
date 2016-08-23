package com.zbPro.seed.adminActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.activity.BaseActivity;
import com.zbPro.seed.activity.R;
import com.zbPro.seed.adapter.MyAdminRogueAdapter;
import com.zbPro.seed.adapter.MyAdminSeedAdapter;
import com.zbPro.seed.bean.RogueBean;
import com.zbPro.seed.net.IsNetOK;
import com.zbPro.seed.util.Constant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Admin_RogueOKActivity extends BaseActivity {
    MyAdminRogueAdapter myAdminRogueAdapter1;
    MyAdminRogueAdapter myAdminRogueAdapter2;
    ArrayList<RogueBean> rogueBeen1;
    ArrayList<RogueBean> rogueBeen2;
    @Bind(R.id.admin_rogue_list1)
    ListView adminRogueList1;
    @Bind(R.id.admin_rogue_list2)
    ListView adminRogueList2;
    // 获取技术员的名字以及基地号
    private String city1;
    private String city2;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String IsSkiplogin = bundle.getString("str");
            updateUI(IsSkiplogin);
            //IsSkiplogin(IsSkiplogin);

        }
    };

    private void updateUI(String isSkiplogin) {
        Gson gson = new Gson();
        Type type = new TypeToken<LinkedList<RogueBean>>() {
        }.getType();
        LinkedList<RogueBean> farmerLinkedList = gson.fromJson(isSkiplogin, type);
        System.out.println("发送过来的数据" + farmerLinkedList.toString());
        rogueBeen1 = new ArrayList<RogueBean>();
        rogueBeen2 = new ArrayList<RogueBean>();

        for (int i = 0; i < farmerLinkedList.size(); i++) {
            if (farmerLinkedList.get(i).getConmeOutMother() != null && farmerLinkedList.get(i).getConmeOutMother().length() > 0) {

                rogueBeen1.add(farmerLinkedList.get(i));
            } else {

                rogueBeen2.add(farmerLinkedList.get(i));
            }
        }
        //System.out.println(rogueBeen2.toString());
        myAdminRogueAdapter1 = new MyAdminRogueAdapter(this, rogueBeen1);
        myAdminRogueAdapter2 = new MyAdminRogueAdapter(this, rogueBeen2);
        adminRogueList1.setAdapter(myAdminRogueAdapter1);
        adminRogueList2.setAdapter(myAdminRogueAdapter2);
        adminRogueList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RogueBean rogueBean = rogueBeen1.get(position);
                ViewHolderRogue viewHolderRogue = new ViewHolderRogue();
                View inflate = LayoutInflater.from(Admin_RogueOKActivity.this).inflate(R.layout.admin_rogue_query, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_RogueOKActivity.this);
                viewHolderRogue.editText1 = (EditText) inflate.findViewById(R.id.admin_rogue1);
                viewHolderRogue.editText2 = (EditText) inflate.findViewById(R.id.admin_rogue2);
                viewHolderRogue.editText3 = (EditText) inflate.findViewById(R.id.admin_rogue3);
                viewHolderRogue.editText4 = (EditText) inflate.findViewById(R.id.admin_rogue4);
                viewHolderRogue.editText5 = (EditText) inflate.findViewById(R.id.admin_rogue5);
                viewHolderRogue.editText6 = (EditText) inflate.findViewById(R.id.admin_rogue6);
                viewHolderRogue.editText7 = (EditText) inflate.findViewById(R.id.admin_rogue7);
                viewHolderRogue.editText8 = (EditText) inflate.findViewById(R.id.admin_rogue8);
                viewHolderRogue.editText9 = (EditText) inflate.findViewById(R.id.admin_rogue9);
                viewHolderRogue.editText10 = (EditText) inflate.findViewById(R.id.admin_rogue10);
                viewHolderRogue.editText11 = (EditText) inflate.findViewById(R.id.admin_rogue11);
                viewHolderRogue.editText12 = (EditText) inflate.findViewById(R.id.admin_rogue12);
                viewHolderRogue.editText13 = (EditText) inflate.findViewById(R.id.admin_rogue13);
                viewHolderRogue.editText1.setText(rogueBean.getFramarName());
                viewHolderRogue.editText2.setText(rogueBean.getType());
                viewHolderRogue.editText3.setText(rogueBean.getdKNumber());
                viewHolderRogue.editText4.setText(rogueBean.getTime());
                viewHolderRogue.editText5.setText(rogueBean.getRowFather());
                viewHolderRogue.editText6.setText(rogueBean.getRowMothers());
                viewHolderRogue.editText7.setText(rogueBean.getLineWidth());
                viewHolderRogue.editText8.setText(rogueBean.getLineRatio());
                viewHolderRogue.editText9.setText(rogueBean.getComeOutFather());
                viewHolderRogue.editText10.setText(rogueBean.getConmeOutMother());
                viewHolderRogue.editText11.setText(rogueBean.getImpurties());
                viewHolderRogue.editText12.setText(rogueBean.getFertility());
                viewHolderRogue.editText13.setText(rogueBean.getBeiZhu());
                builder.setTitle("去杂信息");
                builder.setView(inflate);
                builder.show();

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__rogue_ok);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        city1 = intent.getStringExtra("city1");
        city2 = intent.getStringExtra("city2");

        if (IsNetOK.isNetworkAvailable(Admin_RogueOKActivity.this)) {
            seedhttp();
        } else {
            Toast.makeText(Admin_RogueOKActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
        }
    }

    private void seedhttp() {
        final OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);
        client.setWriteTimeout(30, TimeUnit.SECONDS);
        RequestBody requestBody = new FormEncodingBuilder()
                .add("city1", city1)
                .add("city2", city2)
                .build();

        final Request request = new Request.Builder()
                .url(Constant.PATH + Constant.ADMIN_ROGUE)
                .post(requestBody)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String str = response.body().string();
                        System.out.println(str);
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

    public class ViewHolderRogue {
        EditText editText1;
        EditText editText2;
        EditText editText3;
        EditText editText4;
        EditText editText5;
        EditText editText6;
        EditText editText7;
        EditText editText8;
        EditText editText9;
        EditText editText10;
        EditText editText11;
        EditText editText12;
        EditText editText13;
    }
}
