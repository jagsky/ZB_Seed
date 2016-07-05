package com.zbPro.seed.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.zbPro.seed.bean.CastrationBean;
import com.zbPro.seed.bean.RogueBean;
import com.zbPro.seed.collector.LogBase;
import com.zbPro.seed.dao.CastrationDao;
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
* 类名：CastrationActivity
* 作用：去雄界面
* 实现功能：
* */
public class CastrationActivity extends BaseActivity {
    String dk;
    SharedPreferences preferences;
    HashMap<String, Object> positionRaw = null;
    //获取CastrationDao表操作工具
    CastrationDao castrationDao;
    //收取用户填写信息的数据
    CastrationBean castrationBean;
    String framarName;
    String dKNumber;
    String type;
    String startTime;
    String motherExtractTime;
    String inspectTime;
    String motherNoCastration;
    String motherExtract;
    String motherLoose;
    String fatherLoose;
    String content;
    //单独查询地块号会返回一个ArrayList
    ArrayList lineArrayList;
    //收集搜索栏选择的地块号
    String queryDKNumber;
    //收集 姓名 地块号 种类信息
    ArrayList<HashMap<String, Object>> castrationDaoNDTList;
    //创建一个map 收取搜索栏对应的下标的 地块号 姓名 种类
    HashMap<String, Object> NDKmap;
    ArrayAdapter arrayAdapter;
    //获取搜索栏控件
    AutoCompleteTextView castrationAutoCompleteTextView;

    //获取日期格式器对象
    DateFormat fmtDate = new SimpleDateFormat( "yyyy-MM-dd" );
    //获取一个日历对象
    Calendar dateAndTime = Calendar.getInstance( Locale.CHINA );
    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set( Calendar.YEAR, year );
            dateAndTime.set( Calendar.MONTH, monthOfYear );
            dateAndTime.set( Calendar.DAY_OF_MONTH, dayOfMonth );
            //将页面TextView的显示更新为最新时间
            castrationTxtDate1.setText( fmtDate.format( dateAndTime.getTime() ) );


        }
    };
    DatePickerDialog.OnDateSetListener d2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set( Calendar.YEAR, year );
            dateAndTime.set( Calendar.MONTH, monthOfYear );
            dateAndTime.set( Calendar.DAY_OF_MONTH, dayOfMonth );
            //将页面TextView的显示更新为最新时间
            castrationTxtDate2.setText( fmtDate.format( dateAndTime.getTime() ) );


        }
    };
    DatePickerDialog.OnDateSetListener d3 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set( Calendar.YEAR, year );
            dateAndTime.set( Calendar.MONTH, monthOfYear );
            dateAndTime.set( Calendar.DAY_OF_MONTH, dayOfMonth );
            //将页面TextView的显示更新为最新时间

            castrationDate3.setText( fmtDate.format( dateAndTime.getTime() ) );
        }
    };


    @Bind(R.id.castration_FarmerName_et)
    EditText castrationFarmerNameEt;
    @Bind(R.id.castration_Number_et)
    EditText castrationNumberEt;
    @Bind(R.id.castration_Type_et)
    EditText castrationTypeEt;
    @Bind(R.id.castration_txtDate1)
    EditText castrationTxtDate1;
    @Bind(R.id.castration_btnDate1)
    Button castrationBtnDate1;
    @Bind(R.id.castration_txtDate2)
    EditText castrationTxtDate2;
    @Bind(R.id.castration_Date2)
    Button castrationDate2;
    @Bind(R.id.castration_Date3)
    EditText castrationDate3;
    @Bind(R.id.castration_btnDate3)
    Button castrationBtnDate3;
    @Bind(R.id.castration_rowFather)
    EditText castrationRowFather;
    @Bind(R.id.castration_rowMothers)
    EditText castrationRowMothers;
    @Bind(R.id.castration_lineWidth)
    EditText castrationLineWidth;
    @Bind(R.id.castration_lineRatio)
    EditText castrationLineRatio;
    @Bind(R.id.castration_comeOutFather)
    EditText castrationComeOutFather;
    @Bind(R.id.castration_BC_btn)
    Button castrationBCBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_castration );
        ButterKnife.bind( this );
        initView();
        castrationTxtDate1.setText( fmtDate.format( dateAndTime.getTime() ) );
        castrationTxtDate2.setText( fmtDate.format( dateAndTime.getTime() ) );
        castrationDate3.setText( fmtDate.format( dateAndTime.getTime() ) );

    }

    private void initView() {
        //获取搜索栏空间
        castrationAutoCompleteTextView = (AutoCompleteTextView) findViewById( R.id.castration_AutoCompleteTextView );
        //获取castration表的操作权限
        castrationDao = new CastrationDao( getApplicationContext() );
        //查询地块这一列的信息
        lineArrayList = castrationDao.displayCastrationBeanDKnumber();
        LogBase.i( "去雄界面" + lineArrayList.toString() );
        //设置一个搜索栏的适配器
        arrayAdapter = new ArrayAdapter( CastrationActivity.this,
                android.R.layout.simple_expandable_list_item_1, lineArrayList );
        castrationAutoCompleteTextView.setAdapter( arrayAdapter );
        //设置搜索栏的item点击事件
        castrationAutoCompleteTextView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE )).hideSoftInputFromWindow( CastrationActivity.this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS );
                //获取搜索栏点击item的数据
                queryDKNumber = parent.getItemAtPosition( position ).toString();
                //查询数据
                ArrayList<HashMap<String, Object>> castrationSelectAllData =
                        castrationDao.displayCastrationBeanAllData();
                //获取搜索栏position下数据库对应的position这一行信息
                positionRaw = castrationSelectAllData.get( position );
                //重数据库中提取相应的信息
                framarName = (String) positionRaw.get( "framarName" );
                castrationFarmerNameEt.setText( framarName );
                castrationNumberEt.setText( queryDKNumber );
                type = (String) positionRaw.get( "type" );
                castrationTypeEt.setText( type );
                startTime = (String) positionRaw.get( "startTime" );
                if (startTime != null) {
                    castrationTxtDate1.setText( startTime );
                } else {
                    castrationTxtDate1.setText( fmtDate.format( dateAndTime.getTime() ) );
                }
                motherExtractTime = (String) positionRaw.get( "motherExtractTime" );
                if (motherExtractTime != null) {
                    castrationTxtDate2.setText( motherExtractTime );
                } else {
                    castrationTxtDate2.setText( fmtDate.format( dateAndTime.getTime() ) );
                }
                inspectTime = (String) positionRaw.get( "inspectTime" );
                if (inspectTime != null) {
                    castrationDate3.setText( inspectTime );
                } else {
                    castrationDate3.setText( fmtDate.format( dateAndTime.getTime() ) );
                }
                motherNoCastration = (String) positionRaw.get( "motherNoCastration" );
                if (motherNoCastration != null) {
                    castrationRowFather.setText( motherNoCastration );
                } else {
                    castrationRowFather.setText( "" );
                }
                motherExtract = (String) positionRaw.get( "motherExtract" );
                if (motherExtract != null) {
                    castrationRowMothers.setText( motherExtract );
                } else {
                    castrationRowMothers.setText( "" );
                }
                motherLoose = (String) positionRaw.get( "motherLoose" );
                if (motherLoose != null) {
                    castrationLineWidth.setText( motherLoose );
                } else {
                    castrationLineWidth.setText( "" );
                }
                fatherLoose = (String) positionRaw.get( "fatherLoose" );
                if (fatherLoose != null) {
                    castrationLineRatio.setText( fatherLoose );
                } else {
                    castrationLineRatio.setText( "" );
                }
                content = (String) positionRaw.get( "content" );
                if (content != null) {
                    castrationComeOutFather.setText( content );
                } else {
                    castrationComeOutFather.setText( "" );
                }
                Toast.makeText( CastrationActivity.this, "你选择了   " + framarName, Toast.LENGTH_SHORT ).show();
            }
        } );

    }

    Button castration_send_btn;

    @OnClick({R.id.castration_btnDate1, R.id.castration_Date2, R.id.castration_btnDate3,
            R.id.castration_BC_btn, R.id.castration_send_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.castration_btnDate1:
                DatePickerDialog dateDlg1 = new DatePickerDialog( CastrationActivity.this,
                        d1,
                        dateAndTime.get( Calendar.YEAR ),
                        dateAndTime.get( Calendar.MONTH ),
                        dateAndTime.get( Calendar.DAY_OF_MONTH ) );
                dateDlg1.show();

                break;
            case R.id.castration_Date2:
                DatePickerDialog dateDlg2 = new DatePickerDialog( CastrationActivity.this,
                        d2,
                        dateAndTime.get( Calendar.YEAR ),
                        dateAndTime.get( Calendar.MONTH ),
                        dateAndTime.get( Calendar.DAY_OF_MONTH ) );
                dateDlg2.show();

                break;
            case R.id.castration_btnDate3:
                DatePickerDialog dateDlg3 = new DatePickerDialog( CastrationActivity.this,
                        d3,
                        dateAndTime.get( Calendar.YEAR ),
                        dateAndTime.get( Calendar.MONTH ),
                        dateAndTime.get( Calendar.DAY_OF_MONTH ) );
                dateDlg3.show();

                break;
            case R.id.castration_BC_btn:
                framarName = castrationFarmerNameEt.getText().toString();
                if (!framarName.equals( "" )) {
                    saveDataDialog();
                } else {
                    Toast.makeText( CastrationActivity.this, "请获取农户基本信息", Toast.LENGTH_SHORT ).show();
                }

                break;
            case R.id.castration_send_btn:
                framarName = castrationFarmerNameEt.getText().toString();
                System.out.println( framarName + "+++++" );
                if (!framarName.equals( "" )) {
                    seedHttpDialog();
                } else {
                    Toast.makeText( CastrationActivity.this, "请获取农户基本信息", Toast.LENGTH_SHORT ).show();
                }

                break;
        }
    }

    String sendData;

    //发送去雄界面数据到服务端
    private void castrationSendhttpPost() {
        //在发送数据前保存数据到本地数据库
        saveCastrationBeanData();
        //首先要保存数据
        saveCastrationBeanData();
        Gson gson = new Gson();
        sendData = gson.toJson( castrationBean );
        new Thread( new Runnable() {
            @Override
            public void run() {
                HttpPost.SendhttpPostJson( sendData, Constant.PATH + Constant.CASTRATION );
            }
        } ).start();


    }

    //获取所有的空间对应的数据
    public void getAllViewData() {
        castrationBean = new CastrationBean();
        preferences = getSharedPreferences( "login", MODE_PRIVATE );
        castrationBean.setUserId( preferences.getString( "register_userName", "da" ) );
        castrationBean.setFramarName( castrationFarmerNameEt.getText().toString() );
        castrationBean.setdKNumber( castrationNumberEt.getText().toString() );
        dk = castrationNumberEt.getText().toString();
        castrationBean.setType( castrationTypeEt.getText().toString() );
        castrationBean.setStartTime( castrationTxtDate1.getText().toString() );
        castrationBean.setMotherExtractTime( castrationTxtDate2.getText().toString() );
        castrationBean.setInspectTime( castrationDate3.getText().toString() );
        castrationBean.setMotherNoCastration( castrationRowFather.getText().toString() );
        castrationBean.setMotherExtract( castrationRowMothers.getText().toString() );
        castrationBean.setMotherLoose( castrationLineWidth.getText().toString() );
        castrationBean.setFatherLoose( castrationLineRatio.getText().toString() );
        castrationBean.setContent( castrationComeOutFather.getText().toString() );
    }

    private void saveCastrationBeanData() {
        try {


            //首先查询地块这一列的信息


            if (lineArrayList.contains( dk ) && !dk.equals( "" )) {
                castrationDao.refreshCastrationBeanRow( dk );
                CastrationBean rogueUp = castrationDao.addCastrationBean( castrationBean );
                LogBase.i( "更新成功" + rogueUp.toString() );
                Toast.makeText( CastrationActivity.this, framarName + "   的数据更新成功", Toast.LENGTH_SHORT ).show();
                finish();
                // System.out.println(seedUp.toString());
            } else if (!lineArrayList.contains( dk ) && !dk.equals( "" )) {
                CastrationBean rogueUp = castrationDao.addCastrationBean( castrationBean );
                LogBase.i( "创建成功" );
                Toast.makeText( CastrationActivity.this, "创建数据成功", Toast.LENGTH_SHORT ).show();
                finish();
            } else {
                Toast.makeText( CastrationActivity.this, "请先获取农户基本信息", Toast.LENGTH_SHORT ).show();
            }

            // System.out.println(seed1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送数据到服务端
    protected void seedHttpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder( CastrationActivity.this );
        builder.setMessage( "是否发送？" );
        builder.setTitle( "提示" );
        builder.setPositiveButton( "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获取数据
                getAllViewData();
                //发送json到服务端
                castrationSendhttpPost();
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

    //保存数据到客服端数据库
    protected void saveDataDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder( CastrationActivity.this );
        builder.setMessage( "是否保存数据？" );
        builder.setTitle( "提示" );
        builder.setPositiveButton( "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getAllViewData();
                //在保存的时候获取所有View的数据
                saveCastrationBeanData();
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
}
