package com.zbPro.seed.adminActivity;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.zbPro.seed.adapter.MyAdminSeedAdapter;
import com.zbPro.seed.adapter.MyBaseExpandableListAdapter;
import com.zbPro.seed.admin_fragment.Admin_FragmentOne;
import com.zbPro.seed.admin_fragment.Admin_Fragmenttwo;
import com.zbPro.seed.bean.Seed;
import com.zbPro.seed.net.IsNetOK;
import com.zbPro.seed.util.Constant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Admin_SeedOKActivity extends BaseActivity {
    MyAdminSeedAdapter myAdminSeedAdapter1;
    MyAdminSeedAdapter myAdminSeedAdapter2;
    @Bind(R.id.admin_seed_list2)
    ListView adminSeedList2;
    @Bind(R.id.admin_seed_list1)
    ListView adminSeedList1;
    // 获取技术员的名字以及基地号
    private String city1;
    private String city2;
    MyBaseExpandableListAdapter myBaseExpandableListAdapter;
    ArrayList<String> arrayList = new ArrayList<String>();
    private LinkedList<LinkedList<Seed>> iData;

    FragmentManager fragmentManager;
    Admin_FragmentOne admin_fragmentOne;
    Admin_Fragmenttwo admin_fragmenttwo;
    FragmentTransaction fragmentTransaction;

    //
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
    List<Seed> seedList1;
    List<Seed> seedList2;

    //把获取Json解析，更新UI界面
    private void updateUI(String isSkiplogin) {
        Gson gson = new Gson();
        Type type = new TypeToken<LinkedList<Seed>>() {
        }.getType();
        LinkedList<Seed> farmerLinkedList = gson.fromJson(isSkiplogin, type);
       /* for (int i = 0; i < farmerLinkedList.size(); i++) {
            String str = farmerLinkedList.get(i).getFramarName();
            farmerLinkedList.get(i);
            farmerList.add(str);
        }*/
        seedList1 = new ArrayList<Seed>();
        seedList2 = new ArrayList<Seed>();
        for (int i = 0; i < farmerLinkedList.size(); i++) {
            if (farmerLinkedList.get(i).getFatherUse() != null && farmerLinkedList.get(i).getFatherUse().length() > 0) {

                seedList2.add(farmerLinkedList.get(i));
            } else {

                seedList1.add(farmerLinkedList.get(i));
            }
        }

        myAdminSeedAdapter1 = new MyAdminSeedAdapter(this, seedList1);
        myAdminSeedAdapter2 = new MyAdminSeedAdapter(this, seedList2);
        adminSeedList1.setAdapter(myAdminSeedAdapter1);
        adminSeedList2.setAdapter(myAdminSeedAdapter2);
        adminSeedList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Seed seed = seedList2.get(position);
                ViewHolder viewHolder = new ViewHolder();
                View inflate = LayoutInflater.from(Admin_SeedOKActivity.this).inflate(R.layout.admin_seed_query, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_SeedOKActivity.this);
                builder.setTitle("播种信息：");
                viewHolder.editText1 = (EditText) inflate.findViewById(R.id.admin_seed1);
                viewHolder.editText2 = (EditText) inflate.findViewById(R.id.admin_seed2);
                viewHolder.editText3 = (EditText) inflate.findViewById(R.id.admin_seed3);
                viewHolder.editText4 = (EditText) inflate.findViewById(R.id.admin_seed4);
                viewHolder.editText5 = (EditText) inflate.findViewById(R.id.admin_seed5);
                viewHolder.editText6 = (EditText) inflate.findViewById(R.id.admin_seed6);
                viewHolder.editText7 = (EditText) inflate.findViewById(R.id.admin_seed7);
                viewHolder.editText8 = (EditText) inflate.findViewById(R.id.admin_seed8);
                viewHolder.editText9 = (EditText) inflate.findViewById(R.id.admin_seed9);
                viewHolder.editText10 = (EditText) inflate.findViewById(R.id.admin_seed10);
                viewHolder.editText1.setText(seed.getFramarName());
                viewHolder.editText2.setText(seed.getdKNumber());
                viewHolder.editText3.setText(seed.getType());
                viewHolder.editText4.setText(seed.getSeedDate());
                viewHolder.editText5.setText(seed.getFather1());
                viewHolder.editText6.setText(seed.getFather2());
                viewHolder.editText7.setText(seed.getMother());
                viewHolder.editText8.setText(seed.getFatherUse());
                viewHolder.editText9.setText(seed.getMotherUse());
                viewHolder.editText10.setText(seed.getBeizhu());
                builder.setView(inflate);
                builder.show();
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
        });

        // ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, farmerList);
        //exlistLol.setAdapter(myAdminSeedAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__seed_ok);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        city1 = intent.getStringExtra("city1");
        city2 = intent.getStringExtra("city2");


        if (IsNetOK.isNetworkAvailable(Admin_SeedOKActivity.this)) {
            seedhttp();
        } else {
            Toast.makeText(Admin_SeedOKActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
        }


    }


    private void seedhttp() {
        System.out.println(city1 + "这是要发送到数据");
        final OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);
        client.setWriteTimeout(30, TimeUnit.SECONDS);
        RequestBody requestBody = new FormEncodingBuilder()
                .add("city1", city1)
                .add("city2", city2)
                .build();

        final Request request = new Request.Builder()
                .url(Constant.PATH + Constant.ADMIN_SEED)
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

    public class ViewHolder {
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
    }

}
