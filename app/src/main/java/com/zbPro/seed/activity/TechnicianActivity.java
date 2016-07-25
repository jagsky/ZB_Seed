package com.zbPro.seed.activity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TechnicianActivity extends BaseActivity {

    @Bind(R.id.technician_seed_tv)
    TextView technicianSeedTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician);
        ButterKnife.bind(this);
    }
}
