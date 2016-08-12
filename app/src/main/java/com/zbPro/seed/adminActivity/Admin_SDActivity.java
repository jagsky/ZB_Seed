package com.zbPro.seed.adminActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.zbPro.seed.activity.BaseActivity;
import com.zbPro.seed.activity.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

//试点调查界面
public class Admin_SDActivity extends BaseActivity {

    @Bind(R.id.admin_sd_spinner1)
    Spinner adminSdSpinner1;
    @Bind(R.id.admin_sd_spinner2)
    Spinner adminSdSpinner2;
    @Bind(R.id.admin_sd_spinner3)
    Spinner adminSdSpinner3;
    @Bind(R.id.admin_sd_spinner4)
    Spinner adminSdSpinner4;
    @Bind(R.id.admin_sd_btn1)
    Button adminSdBtn1;
    @Bind(R.id.admin_sd_btn2)
    Button adminSdBtn2;

    private List<String> data1;
    private List<String> data2;
    private List<String> data3;
    private List<String> data4;

    private String name1;
    private String name2;
    private String name3;
    private String name4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__sd);
        ButterKnife.bind(this);
        //给控件设置数据
        setViewData();
    }

    private void setViewData() {
        data1 = getData1();
        data2 = getData2();
        data3 = getData3();
        data4 = getData4();

        ArrayAdapter adapter1 = new ArrayAdapter(this, R.layout.spinner_item, data1);
        ArrayAdapter adapter2 = new ArrayAdapter(this, R.layout.spinner_item, data2);
        ArrayAdapter adapter3 = new ArrayAdapter(this, R.layout.spinner_item, data3);
        ArrayAdapter adapter4 = new ArrayAdapter(this, R.layout.spinner_item, data4);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adminSdSpinner1.setAdapter(adapter1);
        adminSdSpinner1.setSelection(0,true);

        adminSdSpinner2.setAdapter(adapter2);
        adminSdSpinner2.setSelection(0,true);

        adminSdSpinner3.setAdapter(adapter3);
        adminSdSpinner3.setSelection(0,true);

        adminSdSpinner4.setAdapter(adapter4);
        adminSdSpinner4.setSelection(0,true);

        //在打开activity时，获取默认的数据
        name1 = (String) adminSdSpinner1.getItemAtPosition(0);
        name2 = (String) adminSdSpinner2.getItemAtPosition(0);
        name3 = (String) adminSdSpinner3.getItemAtPosition(0);
        name4 = (String) adminSdSpinner4.getItemAtPosition(0);

        //spinner控件点击事件
        adminSdSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                name1 = (String) adminSdSpinner1.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adminSdSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                name2 = (String) adminSdSpinner2.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adminSdSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                name3 = (String) adminSdSpinner3.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        adminSdSpinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                name4 = (String) adminSdSpinner4.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private List<String> getData1() {
        List<String> list = new ArrayList<String>();
        list.add("2016");
        list.add("2017");
        list.add("2018");
        list.add("2019");
        list.add("2020");
        return list;
    }

    private List<String> getData2() {
        List<String> list = new ArrayList<String>();
        list.add("甘肃");
        list.add("北京");
        list.add("上海");
        list.add("湖南");
        list.add("湖北");
        return list;
    }

    private List<String> getData3() {
        List<String> list = new ArrayList<String>();
        list.add("顺义");
        list.add("长沙");
        list.add("东单");
        list.add("兰州");
        list.add("南京");
        return list;
    }

    private List<String> getData4() {
        List<String> list = new ArrayList<String>();
        list.add("HS50");
        list.add("HX30");
        list.add("HX33");
        list.add("HX32");
        list.add("HX38");
        return list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
