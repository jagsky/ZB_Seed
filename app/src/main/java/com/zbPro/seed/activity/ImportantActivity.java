package com.zbPro.seed.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zbPro.seed.bean.ImportantBean;
import com.zbPro.seed.net.HttpPost;
import com.zbPro.seed.util.Constant;
import com.zbPro.seed.util.ImportantData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImportantActivity extends BaseActivity {

    List<String> list = new ArrayList<String>();
    @Bind(R.id.title_editText)
    EditText titleEditText;
    @Bind(R.id.data_ed)
    EditText dataEd;
    @Bind(R.id.data_btn)
    Button dataBtn;
    @Bind(R.id.spinner_province)
    Spinner spinnerProvince;
    @Bind(R.id.spinner_city)
    Spinner spinnerCity;
    @Bind(R.id.spinner_county)
    Spinner spinnerCounty;
    @Bind(R.id.spinner_town)
    Spinner spinnerTown;
    @Bind(R.id.spinner_committee)
    Spinner spinnerCommittee;
    @Bind(R.id.spinner_contentType)
    Spinner spinnerContentType;
    @Bind(R.id.content_editText)
    EditText contentEditText;
    @Bind(R.id.send_btn)
    Button sendBtn;

    Context context;

    ImportantData importantData;
    //获取日期格式器对象
    SimpleDateFormat format = new SimpleDateFormat("yyyy-HH-dd");
    //获取一个日历对象
    Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            //修改日历控件的年，月，日
            //这dateAndTime里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dataEd.setText(format.format(dateAndTime.getTime()));
        }
    };
    //获取Spinner返回的集合对象
    List<String> provinceData;
    List<String> cityData;
    List<String> countyData;
    List<String> townData;
    List<String> streetData;
    List<String> contentType;

    //获取Spinner返回的集合对象对应的Item发送到服务器
    String provinceItem;
    String cityItem;
    String countyItem;
    String townItem;
    String streetItem;
    String contentTypeItem;

    String item;

    ImportantBean importantBean;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important);
        ButterKnife.bind(this);
        context = ImportantActivity.this;
        //在创建Activity时，就设置到控件的时间
        dataEd.setText(format.format(dateAndTime.getTime()));
        getAraeData();
        initView();
        //绑定适配器和值
        setSpinner();

    }

    //各省 市 县适配器
    ArrayAdapter provinceAdapter;
    ArrayAdapter cityAdapter;
    ArrayAdapter countyAdapter;
    ArrayAdapter districtAdapter;
    ArrayAdapter streetAdapter;
    ArrayAdapter contentTypeAdapter;

    //绑定适配器和值
    private void setSpinner() {
        //设置省份列表
        provinceAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, provinceData);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(provinceAdapter);
        //设置默认属性
        spinnerProvince.setSelection(1, true);
        System.out.println(spinnerProvince.getItemAtPosition(1));
        //获取默认的数值
        provinceItem = (String) spinnerProvince.getItemAtPosition(1);
        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provinceItem = (String) parent.getItemAtPosition(position);
                System.out.println(provinceItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //设置 市级 列表
        cityAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cityData);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(cityAdapter);
        //设置默认属性
        spinnerProvince.setSelection(1, true);
        //获取默认的数值
        cityItem = (String) spinnerCity.getItemAtPosition(1);
        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityItem = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //设置 县级 列表
        countyAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, countyData);
        countyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCounty.setAdapter(countyAdapter);
        //设置默认属性
        spinnerProvince.setSelection(1, true);
        //获取默认的数值
        countyItem = (String) spinnerCounty.getItemAtPosition(1);
        spinnerCounty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countyItem = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //设置 镇级 列表
        districtAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, townData);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTown.setAdapter(districtAdapter);
        //设置默认属性
        spinnerProvince.setSelection(1, true);
        //获取默认的数值
        townItem = (String) spinnerTown.getItemAtPosition(1);
        spinnerTown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                townItem = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //设置 村级 列表
        streetAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, streetData);
        streetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCommittee.setAdapter(streetAdapter);
        //设置默认属性
        spinnerCommittee.setSelection(1, true);
        //获取默认的数值
        streetItem = (String) spinnerCommittee.getItemAtPosition(1);
        spinnerCommittee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                streetItem = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //设置消息类型
        contentTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, contentType);
        contentTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerContentType.setAdapter(contentTypeAdapter);
        spinnerContentType.setSelection(1, true);
        contentTypeItem = (String) spinnerContentType.getItemAtPosition(1);
        spinnerContentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                contentTypeItem = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    //初始化View，设置适配器以及
    private void initView() {


    }

    //获取全国的基础数据
    private void getAraeData() {
        importantData = new ImportantData();
        provinceData = importantData.getProvinceData();
        cityData = importantData.getCityData();
        countyData = importantData.getCountyData();
        townData = importantData.getTownData();
        streetData = importantData.getStreetData();
        contentType = importantData.getContentType();
        System.out.println(contentType.toString());
    }


  /*  //获取省份选中的Item
    private void getProvinceDataItem() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, provinceData);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(arrayAdapter);
        //设置默认属性
        spinnerProvince.setSelection(1, true);
        System.out.println(spinnerProvince.getItemAtPosition(1));
        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provinceItem = (String) parent.getItemAtPosition(position);
                System.out.println(provinceItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }*/

    @OnClick({R.id.data_btn, R.id.send_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.data_btn:
                DatePickerDialog dialog = new DatePickerDialog(this, datePickerDialog,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dialog.show();
                break;
            case R.id.send_btn:
                //发送Json到服务器

                sendHttpPostForJson();

                Toast.makeText(ImportantActivity.this, "上传成功", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    //获取界面中所有的数据放到ImportantBean中
    private void getActivityAllData() {
        importantBean = new ImportantBean();
        importantBean.setTitle(titleEditText.getText().toString());
        importantBean.setDate(dataEd.getText().toString());
        importantBean.setProvince(provinceItem);
        importantBean.setCity(cityItem);
        importantBean.setCounty(countyItem);
        importantBean.setTown(townItem);
        importantBean.setVillage(streetItem);
        importantBean.setContenttype(contentTypeItem);
        importantBean.setContent(contentEditText.getText().toString());
        System.out.println(importantBean.toString());
    }

    String jsonStr;

    //发送Json到服务器
    private void sendHttpPostForJson() {
        getActivityAllData();
        Gson gson = new Gson();
        jsonStr = gson.toJson(importantBean);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpPost.SendhttpPostJson(jsonStr, Constant.PATH + Constant.IMPORTANT);

            }
        }).start();

    }

}
