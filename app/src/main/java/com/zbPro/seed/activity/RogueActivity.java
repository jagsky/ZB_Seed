package com.zbPro.seed.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
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
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.internal.framed.FrameReader;
import com.zbPro.seed.bean.RogueBean;
import com.zbPro.seed.collector.LogBase;
import com.zbPro.seed.dao.RogueDao;
import com.zbPro.seed.net.HttpPost;
import com.zbPro.seed.util.Constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 创建时间：2016/6/12
* 类名：RogueActivity
* 作用：去杂界面
* 实现功能：用户记录去杂界面
* */
public class RogueActivity extends BaseActivity {
    SharedPreferences preferences;
    HashMap<String, Object> positionRaw = null;
    //获取RogueBean表操作工具
    RogueDao rogueDao;
    //收取用户填写信息的数据
    RogueBean rogueBean;
    String userId;
    String framarName;
    String dKNumber;
    String type;
    String time;
    String rowFather;
    String rowMothers;
    String lineWidth;
    String lineRatio;
    String comeOutFather;
    String conmeOutMother;
    String impurties;
    String fertility;
    String beiZhu;


    //农户信息表操作
    // FarmaerDao farmaerDao;
    //农户Bean
    //  FarmerBean farmerBean;
    //单独查询地块号会返回一个ArrayList
    ArrayList lineArrayList;
    //收集搜索栏选择的地块号
    String queryDKNumber;
    //收集 姓名 地块号 种类信息
    ArrayList<HashMap<String, Object>> farmerNDTList;
    //创建一个map 收取搜索栏对应的下标的 地块号 姓名 种类
    HashMap<String, Object> NDKmap;
    ArrayAdapter arrayAdapter;
    //获取搜索栏控件
    AutoCompleteTextView rogueAutoCompleteTextView;
    @Bind(R.id.rogue_FarmerName_et)
    EditText rogueFarmerNameEt;
    @Bind(R.id.rogue_Number_et)
    EditText rogueNumberEt;
    @Bind(R.id.rogue_Type_et)
    EditText rogueTypeEt;
    @Bind(R.id.rogue_txtDate)
    EditText rogueTxtDate;
    @Bind(R.id.btnDate)
    Button btnDate;
    @Bind(R.id.rogue_rowFather)
    EditText rogueRowFather;
    @Bind(R.id.rogue_rowMothers)
    EditText rogueRowMothers;
    @Bind(R.id.rogue_lineWidth)
    EditText rogueLineWidth;
    @Bind(R.id.rogue_lineRatio)
    EditText rogueLineRatio;
    @Bind(R.id.rogue_comeOutFather)
    EditText rogueComeOutFather;
    @Bind(R.id.rogue_conmeOutMother)
    EditText rogueConmeOutMother;
    @Bind(R.id.rogue_impurties)
    EditText rogueImpurties;
    @Bind(R.id.rogue_fertility)
    EditText rogueFertility;
    @Bind(R.id.rogue_beiZhu)
    EditText rogueBeiZhu;
    @Bind(R.id.rogue_BC_btn)
    Button rogueBCBtn;
    String dk;
    String s;
    //获取日期格式器对象
    DateFormat fmtDate = new SimpleDateFormat( "yyyy-MM-dd" );
    //获取一个日历对象
    Calendar dateAndTime = Calendar.getInstance( Locale.CHINA );
    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set( Calendar.YEAR, year );
            dateAndTime.set( Calendar.MONTH, monthOfYear );
            dateAndTime.set( Calendar.DAY_OF_MONTH, dayOfMonth );
            //将页面TextView的显示更新为最新时间
            upDateDate();


        }
    };

    private void upDateDate() {
        rogueTxtDate.setText( fmtDate.format( dateAndTime.getTime() ) );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_rogue );
        ButterKnife.bind( this );
        rogueTxtDate.setText( fmtDate.format( dateAndTime.getTime() ) );
        initView();

    }

    private void initView() {
        rogueAutoCompleteTextView = (AutoCompleteTextView) findViewById( R.id.rogue_AutoCompleteTextView );
        rogueDao = new RogueDao( getApplicationContext() );
        lineArrayList = rogueDao.displayRogueDKnumber();
        arrayAdapter = new ArrayAdapter( RogueActivity.this,
                android.R.layout.simple_expandable_list_item_1, lineArrayList );
        rogueAutoCompleteTextView.setAdapter( arrayAdapter );
        //搜索栏点击事件
        rogueAutoCompleteTextView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE )).hideSoftInputFromWindow( RogueActivity.this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS );
                //获取搜索栏点击下标的地块号
                queryDKNumber = parent.getItemAtPosition( position ).toString();
                ArrayList<HashMap<String, Object>> rogueSelectAllData = rogueDao.displayRogueAllData();
                positionRaw = rogueSelectAllData.get( position );
                framarName = (String) positionRaw.get( "framarName" );
                rogueFarmerNameEt.setText( framarName );
                rogueNumberEt.setText( queryDKNumber );
                type = (String) positionRaw.get( "type" );
                rogueTypeEt.setText( type );
                time = (String) positionRaw.get( "time" );
                if (time != null) {
                    rogueTxtDate.setText( time );
                } else {
                    rogueTxtDate.setText( fmtDate.format( dateAndTime.getTime() ) );
                }
                rowFather = (String) positionRaw.get( "rowFather" );
                if (rowFather != null) {
                    rogueRowFather.setText( rowFather );
                } else {
                    rogueRowFather.setText( "" );
                }
                rowMothers = (String) positionRaw.get( "rowMothers" );
                if (rowMothers != null) {
                    rogueRowMothers.setText( rowMothers );
                } else {
                    rogueRowMothers.setText( "" );
                }
                lineWidth = (String) positionRaw.get( "lineWidth" );
                if (lineWidth != null) {
                    rogueLineWidth.setText( lineWidth );
                } else {
                    rogueLineWidth.setText( "" );
                }
                lineRatio = (String) positionRaw.get( "lineRatio" );
                if (lineRatio != null) {
                    rogueLineRatio.setText( lineRatio );
                } else {
                    rogueLineRatio.setText( "" );
                }
                comeOutFather = (String) positionRaw.get( "comeOutFather" );
                if (comeOutFather != null) {
                    rogueComeOutFather.setText( comeOutFather );
                } else {
                    rogueComeOutFather.setText( "" );
                }
                conmeOutMother = (String) positionRaw.get( "conmeOutMother" );
                if (conmeOutMother != null) {
                    rogueConmeOutMother.setText( conmeOutMother );
                } else {
                    rogueConmeOutMother.setText( "" );
                }
                impurties = (String) positionRaw.get( "impurties" );
                if (impurties != null) {
                    rogueImpurties.setText( impurties );
                } else {
                    rogueImpurties.setText( "" );
                }
                fertility = (String) positionRaw.get( "fertility" );
                if (fertility != null) {
                    rogueFertility.setText( fertility );
                } else {
                    rogueFertility.setText( "" );
                }

                beiZhu = (String) positionRaw.get( "beiZhu" );
                if (beiZhu != null) {
                    rogueBeiZhu.setText( beiZhu );
                } else {
                    rogueBeiZhu.setText( "" );
                }

                Toast.makeText( RogueActivity.this, "你选择了   " + framarName, Toast.LENGTH_SHORT ).show();

            }
        } );


    }

    @OnClick({R.id.btnDate, R.id.rogue_BC_btn, R.id.rogue_send_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDate:
                DatePickerDialog dateDlg2 = new DatePickerDialog( RogueActivity.this,
                        d,
                        dateAndTime.get( Calendar.YEAR ),
                        dateAndTime.get( Calendar.MONTH ),
                        dateAndTime.get( Calendar.DAY_OF_MONTH ) );
                dateDlg2.show();

                break;

            case R.id.rogue_BC_btn:
                s = rogueFarmerNameEt.getText().toString();
                if (!s.equals( "" )) {
                    dialogSaveData();
                } else {
                    Toast.makeText( RogueActivity.this, "请获取农户基本信息", Toast.LENGTH_SHORT ).show();
                }
                //保存数据按钮
                //1.在保存的时候弹出对话框，判断是否保存
                //2.如果点击确定，则在对话框保存那一项中执行获取Seed界面控件对应的数据
                //3.然后执行保存
                //最好把时候提取了农户信息的数据判断是否可以保存放在获取数据是判断，而不是在最后。

                break;
            case R.id.rogue_send_btn:
                s = rogueNumberEt.getText().toString();
                //发送数据
                /*1.通过获取界面上地块好是否为空，判断是否有对应的信息
                  2.如果为空，则Toast,如果不为空，则弹出对话空
                  3.如果点击确认则首先说去数据，然后发送
                * */
                if (!s.equals( "" )) {
                    dialogSendHttpPost();
                } else {
                    Toast.makeText( RogueActivity.this, "请获取农户基本信息", Toast.LENGTH_SHORT ).show();
                }


        }
    }


    private void getAllData() {
        //获取控件保存的数据
        rogueBean = new RogueBean();
        preferences = getSharedPreferences( "login", MODE_PRIVATE );
        rogueBean.setUserId( preferences.getString( "register_userName", "da" ) );
        rogueBean.setFramarName( rogueFarmerNameEt.getText().toString() );
        rogueBean.setdKNumber( rogueNumberEt.getText().toString() );
        dk = rogueNumberEt.getText().toString();
        rogueBean.setType( rogueTypeEt.getText().toString() );
        rogueBean.setTime( rogueTxtDate.getText().toString() );
        rogueBean.setRowFather( rogueRowFather.getText().toString() );
        rogueBean.setRowMothers( rogueRowMothers.getText().toString() );
        rogueBean.setLineWidth( rogueLineWidth.getText().toString() );
        rogueBean.setLineRatio( rogueLineRatio.getText().toString() );
        rogueBean.setComeOutFather( rogueComeOutFather.getText().toString() );
        rogueBean.setConmeOutMother( rogueConmeOutMother.getText().toString() );
        rogueBean.setImpurties( rogueImpurties.getText().toString() );
        rogueBean.setFertility( rogueFertility.getText().toString() );
        rogueBean.setBeiZhu( rogueBeiZhu.getText().toString() );

    }

    //保存数据到客服端数据库
    private void saveRogueBeanData() {
        try {


            //首先查询地块这一列的信息


            if (lineArrayList.contains( dk ) && !dk.equals( "" )) {
                rogueDao.refreshRogueBeanRow( dk );
                RogueBean rogueUp = rogueDao.addRogueBean( rogueBean );
                LogBase.i( "更新成功" + rogueUp.toString() );
                Toast.makeText( RogueActivity.this, framarName + "   的数据更新成功", Toast.LENGTH_SHORT ).show();
                finish();
                // System.out.println(seedUp.toString());
            } else if (!lineArrayList.contains( dk ) && !dk.equals( "" )) {
                RogueBean rogueUp = rogueDao.addRogueBean( rogueBean );
                LogBase.i( "创建成功" );
                Toast.makeText( RogueActivity.this, "创建数据数据成功", Toast.LENGTH_SHORT ).show();
                finish();
            } else {
                Toast.makeText( RogueActivity.this, "请先获取农户基本信息", Toast.LENGTH_SHORT ).show();
            }

            // System.out.println(seed1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送数据的对话框
    protected void dialogSendHttpPost() {
        AlertDialog.Builder builder = new AlertDialog.Builder( RogueActivity.this );
        builder.setMessage( "是否发送？" );
        builder.setTitle( "提示" );
        builder.setPositiveButton( "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获取数据，然后发送数据
                getAllData();
                rogueSendhttpPostJson();
                dialog.dismiss();
            }
        } );
        builder.setNegativeButton( "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        } );
        builder.create().show();
    }

    //保存数据的对话框
    protected void dialogSaveData() {
        AlertDialog.Builder builder = new AlertDialog.Builder( RogueActivity.this );
        builder.setMessage( "是否保存数据？" );
        builder.setTitle( "提示" );
        builder.setPositiveButton( "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //在保存的时候获取所有View的数据
                getAllData();
                //同时保存数据
                saveRogueBeanData();

                dialog.dismiss();
            }
        } );
        builder.setNegativeButton( "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        } );
        builder.create().show();
    }


    Gson gson;
    String json;

    //发送Json数据到服务器
    private void rogueSendhttpPostJson() {
        //发送数居前先保存数据到本地数据库
        saveRogueBeanData();
        gson = new Gson();
        json = gson.toJson( rogueBean );
        LogBase.i( json );
        //1.将Bean对象转换成Json格式数据
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        String s = HttpPost.SendhttpPostJson( json, Constant.PATH + Constant.ROGUE );
                        //Toast.makeText( RogueActivity.this, "ssss", Toast.LENGTH_SHORT ).show();
                    }
                }
        ).start();

        Toast.makeText( RogueActivity.this, "发送成功", Toast.LENGTH_SHORT ).show();
    }
}
