package com.zbPro.seed.adminActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.zbPro.seed.activity.BaseActivity;
import com.zbPro.seed.activity.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Main2Activity extends BaseActivity {

    @Bind(R.id.admin_mian2_listView)
    ListView adminMian2ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
    }
}
