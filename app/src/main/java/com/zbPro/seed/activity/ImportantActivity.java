package com.zbPro.seed.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Spannable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

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
    @Bind(R.id.spinner_district)
    Spinner spinnerDistrict;
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
    List<String> districtData;
    List<String> streetData;
    List<String> contentType;

    //获取Spinner返回的集合对象对应的Item发送到服务器
    String provinceItem;
    String cityItem;
    String countyItem;
    String streetItem;
    String districtItem;
    String contentTypeItem;

    String item;

    //各省 市 县适配器
    ArrayAdapter provinceAdapter;
    ArrayAdapter cityAdapter;
    ArrayAdapter countyAdapter;
    ArrayAdapter districtAdapter;
    ArrayAdapter streetAdapter;
    ArrayAdapter contentTypeAdapter;


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

    //绑定适配器和值
    private void setSpinner() {

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
        districtData = importantData.getDistrictData();
        streetData = importantData.getStreetData();
        contentType = importantData.getContentType();
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
                break;
        }
    }

}
