package com.zbPro.seed.adminActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zbPro.seed.activity.BaseActivity;
import com.zbPro.seed.activity.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Admin_ASActivity2 extends BaseActivity {

    @Bind(R.id.as2_1)
    EditText as21;
    @Bind(R.id.as2_2)
    EditText as22;
    @Bind(R.id.as2_3)
    EditText as23;
    @Bind(R.id.as2_4)
    EditText as24;
    @Bind(R.id.as2_5)
    EditText as25;
    @Bind(R.id.as2_6)
    EditText as26;
    @Bind(R.id.as2_7)
    EditText as27;
    @Bind(R.id.as2_8)
    EditText as28;
    @Bind(R.id.as2_9)
    EditText as29;
    @Bind(R.id.as2_10)
    EditText as210;
    @Bind(R.id.as2_11)
    Button as212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__as2);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.as2_11)
    public void onClick() {
        Intent intent = new Intent(this, Admin_ASActivity3.class);
        startActivity(intent);

    }
}
