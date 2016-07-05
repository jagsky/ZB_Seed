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
import com.zbPro.seed.bean.GainBean;
import com.zbPro.seed.collector.LogBase;
import com.zbPro.seed.dao.CastrationDao;
import com.zbPro.seed.dao.GainDao;
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
* 类名：PredictedActivity
* 作用：收获界面
* 实现功能：用户记录收获数据界面
* */
public class PredictedActivity extends BaseActivity {
    SharedPreferences preferences;
    HashMap<String, Object> positionRaw = null;
    //获取CastrationDao表操作工具
    GainDao gainDao;
    //收取用户填写信息的数据
    GainBean gainBean;
    String framarName;
    String dKNumber;
    String type;
    String motherNO;
    String singlePlant;
    String thousand;
    String fatherExciseStart;
    String fatherExciseStop;
    String gainTime;
    String gainOutput;
    String content;
    String dk;
    String beizhu;
    //单独查询地块号会返回一个ArrayList
    ArrayList lineArrayList;
    //收集搜索栏选择的地块号
    String queryDKNumber;
    //收集 姓名 地块号 种类信息
    ArrayList<HashMap<String, Object>> gainDaoNDTList;
    //创建一个map 收取搜索栏对应的下标的 地块号 姓名 种类
    HashMap<String, Object> NDKmap;
    ArrayAdapter arrayAdapter;
    //获取搜索栏控件
    AutoCompleteTextView ganinAutoCompleteTextView;
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
            gainTxtDate1.setText( fmtDate.format( dateAndTime.getTime() ) );


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
            gainTxtDate2.setText( fmtDate.format( dateAndTime.getTime() ) );


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
            gainTxtDate3.setText( fmtDate.format( dateAndTime.getTime() ) );


        }
    };
    @Bind(R.id.gain_FarmerName_et)
    EditText gainFarmerNameEt;
    @Bind(R.id.gain_Number_et)
    EditText gainNumberEt;
    @Bind(R.id.gain_Type_et)
    EditText gainTypeEt;
    @Bind(R.id.gain_rowFather)
    EditText gainRowFather;
    @Bind(R.id.gain_rowMothers)
    EditText gainRowMothers;
    @Bind(R.id.gain_lineWidth)
    EditText gainLineWidth;
    @Bind(R.id.gain_txtDate)
    EditText gainTxtDate1;
    @Bind(R.id.gain_btnDate)
    Button gainBtnDate;
    @Bind(R.id.gain_txtDate1)
    EditText gainTxtDate2;
    @Bind(R.id.gain_btnDate2)
    Button gainBtnDate2;
    @Bind(R.id.gain_txtDate3)
    EditText gainTxtDate3;
    @Bind(R.id.gain_btnDate3)
    Button gainBtnDate3;
    @Bind(R.id.gain_impurties)
    EditText gainImpurties;
    @Bind(R.id.gain_beiZhu)
    EditText gainBeiZhu;
    @Bind(R.id.gain_BC_btn)
    Button gainBCBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_predicted );
        ButterKnife.bind( this );
        initView();
        gainTxtDate1.setText( fmtDate.format( dateAndTime.getTime() ) );
        gainTxtDate2.setText( fmtDate.format( dateAndTime.getTime() ) );
        gainTxtDate3.setText( fmtDate.format( dateAndTime.getTime() ) );

    }

    private void initView() {
        //获取搜索栏控件
        ganinAutoCompleteTextView = (AutoCompleteTextView) findViewById( R.id.gain_AutoCompleteTextView );
        //获取收获表操作对象
        gainDao = new GainDao( getApplicationContext() );
        //查询地块这一列的信息
        lineArrayList = gainDao.displayGainBeanDKnumber();
        LogBase.i( lineArrayList.toString() + queryDKNumber );
        //设置一个搜索栏的适配器
        arrayAdapter = new ArrayAdapter( PredictedActivity.this,
                android.R.layout.simple_expandable_list_item_1, lineArrayList );
        ganinAutoCompleteTextView.setAdapter( arrayAdapter );
        ganinAutoCompleteTextView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE )).hideSoftInputFromWindow( PredictedActivity.this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS );
                //获取搜索栏点击item的数据
                queryDKNumber = parent.getItemAtPosition( position ).toString();
                //查询数据
                ArrayList<HashMap<String, Object>> ganinSelectAllData =
                        gainDao.displayGainBeanAllData();
                //获取搜索栏position下数据库对应的position这一行信息
                positionRaw = ganinSelectAllData.get( position );
                //重数据库中提取相应的信息
                framarName = (String) positionRaw.get( "framarName" );
                gainFarmerNameEt.setText( framarName );
                gainNumberEt.setText( queryDKNumber );
                type = (String) positionRaw.get( "type" );
                gainTypeEt.setText( type );
                motherNO = (String) positionRaw.get( "motherNO" );
                if (motherNO != null) {
                    gainRowFather.setText( motherNO );
                } else {
                    gainRowFather.setText( "" );
                }
                singlePlant = (String) positionRaw.get( "singlePlant" );
                if (singlePlant != null) {
                    gainRowMothers.setText( singlePlant );
                } else {
                    gainRowMothers.setText( "" );
                }
                thousand = (String) positionRaw.get( "thousand" );
                if (thousand != null) {
                    gainLineWidth.setText( thousand );
                } else {
                    gainLineWidth.setText( "" );
                }

                fatherExciseStart = (String) positionRaw.get( "fatherExciseStart" );
                if (fatherExciseStart != null) {
                    gainTxtDate1.setText( fatherExciseStart );
                } else {
                    gainTxtDate1.setText( fmtDate.format( dateAndTime.getTime() ) );
                }
                fatherExciseStop = (String) positionRaw.get( "fatherExciseStop" );
                if (fatherExciseStop != null) {
                    gainTxtDate2.setText( fatherExciseStop );
                } else {
                    gainTxtDate2.setText( fmtDate.format( dateAndTime.getTime() ) );
                }
                gainTime = (String) positionRaw.get( "gainTime" );
                if (gainTime != null) {
                    gainTxtDate3.setText( gainTime );
                } else {
                    gainTxtDate3.setText( fmtDate.format( dateAndTime.getTime() ) );
                }
                gainOutput = (String) positionRaw.get( "gainOutput" );
                if (gainOutput != null) {
                    gainImpurties.setText( gainOutput );
                } else {
                    gainImpurties.setText( "" );
                }
               /* beizhu = (String) positionRaw.get( "beizhu" );
                if (beizhu != null) {
                    gainBeiZhu.setText( beizhu );
                } else {
                    gainBeiZhu.setText( "" );
                }*/
                Toast.makeText( PredictedActivity.this, "你选择了   " + framarName, Toast.LENGTH_SHORT ).show();
            }
        } );

    }

    String str;
    Button gain_send_btn;

    @OnClick({R.id.gain_btnDate, R.id.gain_btnDate2, R.id.gain_btnDate3, R.id.gain_BC_btn, R.id.gain_send_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gain_btnDate:
                DatePickerDialog dateDlg1 = new DatePickerDialog( PredictedActivity.this,
                        d1,
                        dateAndTime.get( Calendar.YEAR ),
                        dateAndTime.get( Calendar.MONTH ),
                        dateAndTime.get( Calendar.DAY_OF_MONTH ) );
                dateDlg1.show();

                break;
            case R.id.gain_btnDate2:
                DatePickerDialog dateDlg2 = new DatePickerDialog( PredictedActivity.this,
                        d1,
                        dateAndTime.get( Calendar.YEAR ),
                        dateAndTime.get( Calendar.MONTH ),
                        dateAndTime.get( Calendar.DAY_OF_MONTH ) );
                dateDlg2.show();

                break;
            case R.id.gain_btnDate3:
                DatePickerDialog dateDlg3 = new DatePickerDialog( PredictedActivity.this,
                        d1,
                        dateAndTime.get( Calendar.YEAR ),
                        dateAndTime.get( Calendar.MONTH ),
                        dateAndTime.get( Calendar.DAY_OF_MONTH ) );
                dateDlg3.show();

                break;
            case R.id.gain_BC_btn:
                str = gainNumberEt.getText().toString();
                if (!str.equals( "" )) {
                    dialogbtn();
                } else {
                    Toast.makeText( PredictedActivity.this, "请获取农户基本信息", Toast.LENGTH_SHORT ).show();
                }
                break;
            case R.id.gain_send_btn:
                str = gainNumberEt.getText().toString();
                if (!str.equals( "" )) {
                    dialogSendHttpPost();
                } else {
                    Toast.makeText( PredictedActivity.this, "请获取农户基本信息", Toast.LENGTH_SHORT ).show();
                }
        }
    }

    //获取所有控件的数据
    public void getAllData() {
        gainBean = new GainBean();
        preferences = getSharedPreferences( "login", MODE_PRIVATE );
        gainBean.setUserId( preferences.getString( "register_userName", "da" ) );
        gainBean.setFramarName( gainFarmerNameEt.getText().toString() );
        gainBean.setdKNumber( gainNumberEt.getText().toString() );
        dk = gainNumberEt.getText().toString();
        gainBean.setType( gainTypeEt.getText().toString() );
        gainBean.setMotherNO( gainRowFather.getText().toString() );
        gainBean.setSinglePlant( gainRowMothers.getText().toString() );
        gainBean.setThousand( gainLineWidth.getText().toString() );
        gainBean.setFatherExciseStart( gainTxtDate1.getText().toString() );
        gainBean.setFatherExciseStop( gainTxtDate2.getText().toString() );
        gainBean.setGainTime( gainTxtDate3.getText().toString() );
        gainBean.setGainOutput( gainImpurties.getText().toString() );
       // gainBean.setBeizhu( gainBeiZhu.getText().toString() );
    }

    String json;

    private void gainSendHttpPost() {
        //在发送数据前先保存数据到本地数据库
        saveGainBean();
        //将数据设置成Json数据
        Gson gson = new Gson();
        json = gson.toJson( gainBean );
        new Thread( new Runnable() {
            @Override
            public void run() {
                HttpPost.SendhttpPostJson( json, Constant.PATH + Constant.GAIN );
            }
        } ).start();

        Toast.makeText( PredictedActivity.this, "发送成功", Toast.LENGTH_SHORT ).show();

    }

    private void saveGainBean() {
        try {


            //首先查询地块这一列的信息


            if (lineArrayList.contains( dk ) && !dk.equals( "" )) {
                gainDao.refreshGainBeanRow( dk );
                GainBean gainUp = gainDao.addGainBean( gainBean );
                // LogBase.i("更新成功" + rogueUp.toString());
                Toast.makeText( PredictedActivity.this, framarName + "  的数据更新成功", Toast.LENGTH_SHORT ).show();
                finish();
                // System.out.println(seedUp.toString());
            } else if (!lineArrayList.contains( dk ) && !dk.equals( "" )) {
                GainBean rogueUp = gainDao.addGainBean( gainBean );
                LogBase.i( "创建成功" );
                Toast.makeText( PredictedActivity.this, "创建数据成功", Toast.LENGTH_SHORT ).show();
                finish();
            } else {
                Toast.makeText( PredictedActivity.this, "请先获取农户基本信息", Toast.LENGTH_SHORT ).show();
            }

            // System.out.println(seed1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送数据
    protected void dialogSendHttpPost() {
        AlertDialog.Builder builder = new AlertDialog.Builder( PredictedActivity.this );
        builder.setMessage( "是否发送？" );
        builder.setTitle( "提示" );
        builder.setPositiveButton( "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getAllData();
                gainSendHttpPost();
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

    protected void dialogbtn() {
        AlertDialog.Builder builder = new AlertDialog.Builder( PredictedActivity.this );
        builder.setMessage( "是否保存数据？" );
        builder.setTitle( "提示" );
        builder.setPositiveButton( "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //在有数据的情况下，首先保存数据
                getAllData();
                //在保存的时候获取所有View的数据
                saveGainBean();
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
