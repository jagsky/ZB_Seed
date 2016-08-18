package com.zbPro.seed.adminActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zbPro.seed.activity.R;

public class Admin_GainOKActivity extends AppCompatActivity {
    // 获取技术员的名字以及基地号
    private String city1;
    private String city2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__gain_ok);
        Intent intent = getIntent();
        city1 = intent.getStringExtra("city1");
        city2 = intent.getStringExtra("city2");
        seedhttp();
    }

    private void seedhttp() {
    }
}
