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

public class Admin_ASActivity3 extends BaseActivity {

    @Bind(R.id.as3_1)
    EditText as31;
    @Bind(R.id.as3_2)
    EditText as32;
    @Bind(R.id.as3_3)
    EditText as33;
    @Bind(R.id.as3_4)
    EditText as34;
    @Bind(R.id.as3_5)
    EditText as35;
    @Bind(R.id.as3_6)
    EditText as36;
    @Bind(R.id.as3_7)
    EditText as37;
    @Bind(R.id.as3_8)
    EditText as38;
    @Bind(R.id.as3_9)
    EditText as39;
    @Bind(R.id.as3_10)
    EditText as310;
    @Bind(R.id.as3_11)
    EditText as311;
    @Bind(R.id.as3_12)
    EditText as312;
    @Bind(R.id.as3_13)
    Button as313;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__as3);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.as3_13)
    public void onClick() {
        Intent intent = new Intent(this, Admin_ASActivity4.class);
        startActivity(intent);
    }
}
