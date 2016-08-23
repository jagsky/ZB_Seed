package com.zbPro.seed.adminActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
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
import com.zbPro.seed.adapter.MyAdminCastrationAdapter;
import com.zbPro.seed.bean.CastrationBean;
import com.zbPro.seed.net.IsNetOK;
import com.zbPro.seed.util.Constant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Admin_CastrationOKActivity extends BaseActivity {
    // 获取技术员的名字以及基地号
    ArrayList<CastrationBean> castrationBeen1;
    ArrayList<CastrationBean> castrationBeen2;
    MyAdminCastrationAdapter myAdminCastrationAdapter1;
    MyAdminCastrationAdapter myAdminCastrationAdapter2;
    @Bind(R.id.admin_cas_list1)
    ListView adminCasList1;
    @Bind(R.id.admin_cas_list2)
    ListView adminCasList2;

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
        Type type = new TypeToken<LinkedList<CastrationBean>>() {
        }.getType();
        LinkedList<CastrationBean> farmerLinkedList = gson.fromJson(isSkiplogin, type);
        System.out.println(farmerLinkedList.toString());
       /* if (farmerLinkedList == null && farmerLinkedList.size() == 0) {
            Toast.makeText(Admin_CastrationOKActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
        }*/
        System.out.println("发送过来的数据" + farmerLinkedList.toString());

        castrationBeen1 = new ArrayList<CastrationBean>();
        castrationBeen2 = new ArrayList<CastrationBean>();
        for (int i = 0; i < farmerLinkedList.size(); i++) {
            if (farmerLinkedList.get(i).getFatherLoose() != null && farmerLinkedList.get(i).getFatherLoose().length() > 0) {

                castrationBeen1.add(farmerLinkedList.get(i));
            } else {

                castrationBeen2.add(farmerLinkedList.get(i));
            }

            System.out.println("完成的数据" + castrationBeen1.toString());
            System.out.println("未完成的数据" + castrationBeen2.toString());
            myAdminCastrationAdapter1 = new MyAdminCastrationAdapter(Admin_CastrationOKActivity.this, castrationBeen1);
            myAdminCastrationAdapter2 = new MyAdminCastrationAdapter(Admin_CastrationOKActivity.this, castrationBeen2);

            adminCasList1.setAdapter(myAdminCastrationAdapter1);
            adminCasList2.setAdapter(myAdminCastrationAdapter2);
            adminCasList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ViewHolderCastration viewHolderCastration = new ViewHolderCastration();
                    CastrationBean castrationBean1 = castrationBeen1.get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Admin_CastrationOKActivity.this);
                    View inflate = LayoutInflater.from(Admin_CastrationOKActivity.this).inflate(R.layout.admin_castration_query, null);
                    viewHolderCastration.editText1 = (EditText) inflate.findViewById(R.id.admin_castration1);
                    viewHolderCastration.editText2 = (EditText) inflate.findViewById(R.id.admin_castration2);
                    viewHolderCastration.editText3 = (EditText) inflate.findViewById(R.id.admin_castration3);
                    viewHolderCastration.editText4 = (EditText) inflate.findViewById(R.id.admin_castration4);
                    viewHolderCastration.editText5 = (EditText) inflate.findViewById(R.id.admin_castration5);
                    viewHolderCastration.editText6 = (EditText) inflate.findViewById(R.id.admin_castration6);
                    viewHolderCastration.editText7 = (EditText) inflate.findViewById(R.id.admin_castration7);
                    viewHolderCastration.editText8 = (EditText) inflate.findViewById(R.id.admin_castration8);
                    viewHolderCastration.editText9 = (EditText) inflate.findViewById(R.id.admin_castration9);
                    viewHolderCastration.editText10 = (EditText) inflate.findViewById(R.id.admin_castration10);
                    viewHolderCastration.editText11 = (EditText) inflate.findViewById(R.id.admin_castration11);
                    viewHolderCastration.editText1.setText(castrationBean1.getFramarName());
                    viewHolderCastration.editText2.setText(castrationBean1.getdKNumber());
                    viewHolderCastration.editText3.setText(castrationBean1.getType());
                    viewHolderCastration.editText4.setText(castrationBean1.getStartTime());
                    viewHolderCastration.editText5.setText(castrationBean1.getMotherExtractTime());
                    viewHolderCastration.editText6.setText(castrationBean1.getInspectTime());
                    viewHolderCastration.editText7.setText(castrationBean1.getMotherNoCastration());
                    viewHolderCastration.editText8.setText(castrationBean1.getMotherExtract());
                    viewHolderCastration.editText9.setText(castrationBean1.getMotherLoose());
                    viewHolderCastration.editText10.setText(castrationBean1.getFatherLoose());
                    viewHolderCastration.editText11.setText(castrationBean1.getContent());
                    builder.setTitle("去雄详情：");
                    builder.setView(inflate);
                    builder.show();


                }
            });

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__castration_ok);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        city1 = intent.getStringExtra("city1");
        city2 = intent.getStringExtra("city2");
       if (IsNetOK.isNetworkAvailable(Admin_CastrationOKActivity.this)){
           seedhttp();
       }else {
           Toast.makeText(Admin_CastrationOKActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
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
                .url(Constant.PATH + Constant.ADMIN_CASTRATION)
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

    class ViewHolderCastration {
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

    }
}
