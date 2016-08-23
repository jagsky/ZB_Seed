package com.zbPro.seed.adminActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.zbPro.seed.activity.BaseActivity;
import com.zbPro.seed.activity.R;
import com.zbPro.seed.bean.ImportantBean;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Admin_ImportantActivity extends BaseActivity {

    @Bind(R.id.admin_important1)
    EditText adminImportant1;
    @Bind(R.id.admin_important2)
    EditText adminImportant2;
    @Bind(R.id.admin_important3)
    EditText adminImportant3;
    @Bind(R.id.admin_important4)
    EditText adminImportant4;
    @Bind(R.id.admin_important5)
    EditText adminImportant5;
    @Bind(R.id.admin_important6)
    EditText adminImportant6;
    @Bind(R.id.admin_important7)
    EditText adminImportant7;
    @Bind(R.id.admin_important8)
    EditText adminImportant8;
    @Bind(R.id.admin_important9)
    EditText adminImportant9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__important);
        ButterKnife.bind(this);
        ImportantBean importantBean = (ImportantBean) getIntent().getSerializableExtra("importantBean123");
        System.out.println(importantBean.toString());
        adminImportant1.setText(importantBean.getTitle());
        adminImportant2.setText(importantBean.getDate());
        adminImportant3.setText(importantBean.getProvince());
        adminImportant4.setText(importantBean.getCity());
        adminImportant5.setText(importantBean.getCounty());
        adminImportant6.setText(importantBean.getTown());
        adminImportant7.setText(importantBean.getVillage());
        adminImportant8.setText(importantBean.getContenttype());
        adminImportant9.setText(importantBean.getContent());
    }
}
