package com.zbPro.seed.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class FarmerLineActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_line);
        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityName");
        if (cityName != null) {

        } else {
            Toast.makeText(FarmerLineActivity.this, "信息异常", Toast.LENGTH_SHORT).show();

        }

    }
}
