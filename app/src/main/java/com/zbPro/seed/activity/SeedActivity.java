package com.zbPro.seed.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zbPro.seed.bean.Seed;
import com.zbPro.seed.collector.LogBase;
import com.zbPro.seed.dao.FarmaerDao;
import com.zbPro.seed.dao.SeedDao;
import com.zbPro.seed.net.HttpPost;
import com.zbPro.seed.util.Constant;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeedActivity extends BaseActivity {
    //用于存放查询到的某一行数据，进行存储。
    HashMap<String, Object> positionRaw = null;
    //收集各个空间最后操作留下来的数据，不错最好是SeedBean这个类进行存放数据。
    String userId;
    String framarName;
    String dKNumber;
    String type;
    String seedDate;
    String father1;
    String father2;
    String mother;
    String fatherUse;
    String motherUse;
    String beizhu;
    String s = null;
    Button seed_send_btn;
    //收取用户填写信息的数据
    Seed seed;
    //农户信息表操作
    //FarmaerDao farmaerDao;
    //农户Bean
    //  FarmerBean farmerBean;
    //单独查询地块号会返回一个ArrayList
    ArrayList lineArrayList;
    SeedDao seedDao;
    //收集搜索栏选择的地块号
    String queryDKNumber;
    //收集 姓名 地块号 种类信息
    ArrayList<HashMap<String, Object>> farmerNDTList;
    //创建一个map 收取搜索栏对应的下标的 地块号 姓名 种类
    HashMap<String, Object> NDKmap;
    //获取搜索栏控件
    AutoCompleteTextView seedAutoCompleteTextView;
    private final static String TAG = "TimeDate";
    //设置一个搜索栏控件的适配器
    ArrayAdapter arrayAdapter;
    //获取日期格式器对象
    DateFormat fmtDate = new SimpleDateFormat("yyyy-MM-dd");
    //获取一个日历对象
    Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //将页面TextView的显示更新为最新时间
            txtDate.setText(fmtDate.format(dateAndTime.getTime()));

        }
    };
    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //将页面TextView的显示更新为最新时间
            txtDatefu1.setText(fmtDate.format(dateAndTime.getTime()));

        }
    };
    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //将页面TextView的显示更新为最新时间
            txtDatefu2.setText(fmtDate.format(dateAndTime.getTime()));

        }
    };
    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d3 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //将页面TextView的显示更新为最新时间
            txtDatemo.setText(fmtDate.format(dateAndTime.getTime()));

        }
    };
    String IsSkiplogin;
    @Bind(R.id.seedFarmerName_et)
    EditText seedFarmerNameEt;
    @Bind(R.id.seedNumber_et)
    EditText seedNumberEt;
    @Bind(R.id.seedType_et)
    EditText seedTypeEt;
    @Bind(R.id.txtDate)
    EditText txtDate;
    @Bind(R.id.seed_btnDate)
    Button seedBtnDate;
    @Bind(R.id.txtDatefu1)
    EditText txtDatefu1;
    @Bind(R.id.btnDatefu1)
    Button btnDatefu1;
    @Bind(R.id.txtDatefu2)
    EditText txtDatefu2;
    @Bind(R.id.btnDatefu2)
    Button btnDatefu2;
    @Bind(R.id.txtDatemo)
    EditText txtDatemo;
    @Bind(R.id.btnDatemo)
    Button btnDatemo;
    @Bind(R.id.seedfatherUse)
    EditText seedfatherUse;
    @Bind(R.id.seedMontherUse)
    EditText seedMontherUse;
    @Bind(R.id.seedBeiZhu_id)
    EditText seedBeiZhuId;
    @Bind(R.id.seedBC_btn)
    Button seedBCBtn;
    String far;
    //通过子线程进行耗时操作，获取返回的数据进行相关的操作
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            IsSkiplogin = bundle.getString("str");

            IsSkiplogin();

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seed);
        ButterKnife.bind(this);
        //获取搜索栏控件
        seedDao = new SeedDao(this);
        seedAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.seed_AutoCompleteTextView);
        //获取Button控件
        seed_send_btn = (Button) findViewById(R.id.seed_send_btn);
        //获取Seed表操作权限
        seedDao = new SeedDao(getApplicationContext());
        //获取 farmer表中 姓名 地块号 种类显示到Listvew中
        lineArrayList = seedDao.displaySeedDKnumber();
        //在创建Activity时，设置今天的时间
        txtDate.setText(fmtDate.format(dateAndTime.getTime()));
        txtDatefu1.setText(fmtDate.format(dateAndTime.getTime()));
        txtDatefu2.setText(fmtDate.format(dateAndTime.getTime()));
        txtDatemo.setText(fmtDate.format(dateAndTime.getTime()));
        //  System.out.println(lineArrayList.toString());
        txtDate = (EditText) findViewById(R.id.txtDate);
        seedBtnDate = (Button) findViewById(R.id.seed_btnDate);
        seedBtnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dateDlg = new DatePickerDialog(SeedActivity.this,
                        d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dateDlg.show();


            }

        });
        //设置一个是配置，用于高级控件展示数据。
        arrayAdapter = new ArrayAdapter(SeedActivity.this,
                android.R.layout.simple_expandable_list_item_1, lineArrayList);
        //播种界面搜索栏设置适配器
        seedAutoCompleteTextView.setAdapter(arrayAdapter);
        //搜索栏点击事件
        seedAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(SeedActivity.this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                //获取搜索栏点击下标的地块号
                queryDKNumber = parent.getItemAtPosition(position).toString();
                //获取数据库seed表的数据，但是最好用SeedBean实体接收数据
                ArrayList<HashMap<String, Object>> seedSelectAllData = seedDao.displaySeedAllData();
                //通过position（下标），获取是哪一行的数据
                positionRaw = seedSelectAllData.get(position);
                //获取此行信息，将数据显示到界面对应的空间中。
                //如果数据为空，则判断为不显示
                seedFarmerNameEt.setText(positionRaw.get("framarName").toString());
                far = (String) positionRaw.get("framarName");
                String types = (String) positionRaw.get("type");
                seedNumberEt.setText(queryDKNumber);
                seedTypeEt.setText(positionRaw.get("type").toString());
                String s1 = null;
                s1 = (String) positionRaw.get("seedDate");
                if (s1 != null) {
                    txtDate.setText(s1);
                } else {
                    txtDate.setText(fmtDate.format(dateAndTime.getTime()));
                }
                String s2 = (String) positionRaw.get("father1");
                if (s2 != null) {
                    txtDatefu1.setText(s2);
                } else {
                    txtDatefu1.setText(fmtDate.format(dateAndTime.getTime()));
                }
                String s3 = (String) positionRaw.get("father2");
                if (s3 != null) {
                    txtDatefu2.setText(s3);
                } else {
                    txtDatefu2.setText(fmtDate.format(dateAndTime.getTime()));
                }
                String s4 = (String) positionRaw.get("mother");
                if (s4 != null) {
                    txtDatemo.setText(s4);
                } else {
                    txtDatemo.setText(fmtDate.format(dateAndTime.getTime()));
                }
                String s5 = (String) positionRaw.get("fatherUse");
                if (s5 != null) {
                    seedfatherUse.setText(s5);
                } else {
                    seedfatherUse.setText("");
                }
                String s6 = (String) positionRaw.get("motherUse");
                if (s6 != null) {
                    seedMontherUse.setText(s6);
                } else {
                    seedMontherUse.setText("");
                }

                String s7 = (String) positionRaw.get( "beizhu" );
                if (s7 != null) {
                    seedBeiZhuId.setText(s7);
                } else {
                    seedBeiZhuId.setText("");
                }
                Toast.makeText(SeedActivity.this, "你选择了   " + far, Toast.LENGTH_SHORT).show();


            }
        });


    }

    //Seed界面中所有的点击事件
    @OnClick({R.id.btnDatefu1, R.id.btnDatefu2, R.id.btnDatemo, R.id.seedBC_btn, R.id.seed_send_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDatefu1:
                DatePickerDialog dateDlg1 = new DatePickerDialog(SeedActivity.this,
                        d1,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dateDlg1.show();

                break;
            case R.id.btnDatefu2:
                DatePickerDialog dateDlg2 = new DatePickerDialog(SeedActivity.this,
                        d2,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dateDlg2.show();

                break;
            case R.id.btnDatemo:
                DatePickerDialog dateDlg = new DatePickerDialog(SeedActivity.this,
                        d3,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dateDlg.show();

                break;
            case R.id.seedBC_btn:
                s = seedNumberEt.getText().toString();
                if (!s.equals("")) {
                    dialogbtn();

                } else {
                    Toast.makeText(SeedActivity.this, "请获取农户基本信息", Toast.LENGTH_SHORT).show();
                }
                //保存数据按钮
                //1.在保存的时候弹出对话框，判断是否保存
                //2.如果点击确定，则在对话框保存那一项中执行获取Seed界面控件对应的数据
                //3.然后执行保存
                //最好把时候提取了农户信息的数据判断是否可以保存放在获取数据是判断，而不是在最后。

                break;
            case R.id.seed_send_btn:
                //发送数据
                /*1.通过获取界面上地块好是否为空，判断是否有对应的信息
                  2.如果为空，则Toast,如果不为空，则弹出对话空
                  3.如果点击确认则首先说去数据，然后发送
                * */
                s = seedNumberEt.getText().toString();
                if (!s.equals("")) {
                    dialog();

                } else {
                    Toast.makeText(SeedActivity.this, "请获取农户基本信息", Toast.LENGTH_SHORT).show();
                }


        }
    }

    Gson gson;
    String json;

    //发送json数据到服务器
    private void seedSendhttpPost() throws IOException {
        //发送数据前，先保存数据
        saveSeedData();
        //发送Json数据
        //1.将数据转换成Json类型
        gson = new Gson();
        json = gson.toJson(seed);
        LogBase.i(json);
        //1.将Bean对象转换成Json格式数据
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        String s = HttpPost.SendhttpPostJson(json, Constant.PATH + Constant.SEED);
                        //Toast.makeText( RogueActivity.this, "ssss", Toast.LENGTH_SHORT ).show();
                    }
                }
        ).start();
      /*  new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                RequestBody formBody = new FormEncodingBuilder()
                        .add("user", "user")
                        .add("name", "name")
                        .build();

                final Request request = new Request.Builder()
                        .url(Constant.PATH + Constant.SEED)
                        .post(formBody)
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response.isSuccessful()) {
                    try {
                        String resultSet = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/


    }

    SharedPreferences preferences;

    //在保存的时候获取所有View的数据，并将其保存到Seedbean实体类中
    private void getALlViewData() {
        preferences = getSharedPreferences("login", MODE_PRIVATE);
        userId = preferences.getString("register_userName", "da");
        framarName = seedFarmerNameEt.getText().toString();
        dKNumber = seedNumberEt.getText().toString();
        type = seedTypeEt.getText().toString();
        seedDate = txtDate.getText().toString();
        father1 = txtDatefu1.getText().toString();
        father2 = txtDatefu2.getText().toString();
        mother = txtDatemo.getText().toString();
        fatherUse = seedfatherUse.getText().toString();
        motherUse = seedMontherUse.getText().toString();
        beizhu = seedBeiZhuId.getText().toString();

        seed = new Seed(userId, framarName, dKNumber,
                type, seedDate, father1, father2, mother, fatherUse, motherUse, beizhu);
    }


    ArrayList dKnumberList;

    private void saveSeedData() {
        try {


            //首先查询地块这一列的信息

            dKnumberList = seedDao.displaySeedDKnumber();
            if (dKnumberList.contains(dKNumber) && !dKNumber.equals("")) {
                seedDao.refreshSeedBeanRow(dKNumber);
                Seed seedUp = seedDao.addSeed(seed);
                LogBase.i("更新成功");
                Toast.makeText(SeedActivity.this, far + "   的数据更新成功", Toast.LENGTH_SHORT).show();
                finish();
                // System.out.println(seedUp.toString());
            } else if (!dKnumberList.contains(dKNumber) && !dKNumber.equals("")) {
                Seed seedAdd = seedDao.addSeed(seed);
                LogBase.i("创建成功");
                Toast.makeText(SeedActivity.this, "创建数据数据成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(SeedActivity.this, "请先获取农户基本信息", Toast.LENGTH_SHORT).show();
            }

            // System.out.println(seed1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SeedActivity.this);
        builder.setMessage("是否发送？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getALlViewData();
                try {
                    seedSendhttpPost();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    public void dialogbtn() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SeedActivity.this);
        builder.setMessage("是否保存数据？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //在保存的时候获取所有View的数据
                getALlViewData();
                saveSeedData();
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

    private void IsSkiplogin() {

    }


}
