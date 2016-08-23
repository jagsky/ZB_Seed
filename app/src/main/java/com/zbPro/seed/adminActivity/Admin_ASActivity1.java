package com.zbPro.seed.adminActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zbPro.seed.activity.BaseActivity;
import com.zbPro.seed.activity.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*
*作用：年度调查界面
*时间：2016.08.05
 * */

public class Admin_ASActivity1 extends BaseActivity {

    @Bind(R.id.admin_as_ed1)
    EditText adminAsEd1;
    @Bind(R.id.admin_as_ed2)
    EditText adminAsEd2;
    @Bind(R.id.admin_as_ed3)
    EditText adminAsEd3;
    @Bind(R.id.admin_as_ed4)
    EditText adminAsEd4;
    @Bind(R.id.admin_as_ed5)
    EditText adminAsEd5;
    @Bind(R.id.admin_as_ed6)
    EditText adminAsEd6;
    @Bind(R.id.admin_as_ed7)
    EditText adminAsEd7;
    @Bind(R.id.admin_as1_btn)
    Button adminAs1Btn;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__as);
        ButterKnife.bind(this);
        preferences = getPreferences();


    }

    @OnClick(R.id.admin_as1_btn)
    public void onClick() {
        Intent intent = new Intent(this, Admin_ASActivity2.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }
}
