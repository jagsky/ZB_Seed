package com.zbPro.seed.adminActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zbPro.seed.activity.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Admin_ASActivity4 extends AppCompatActivity {

    @Bind(R.id.as4_1)
    EditText as41;
    @Bind(R.id.as4_2)
    EditText as42;
    @Bind(R.id.as4_3)
    EditText as43;
    @Bind(R.id.as4_4)
    EditText as44;
    @Bind(R.id.as4_5)
    EditText as45;
    @Bind(R.id.as4_6)
    Button as46;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__as4);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.as4_6)
    public void onClick() {
        alertdialog();
    }

    private void alertdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("发送");
        builder.setMessage("是否发送");
        builder.setCancelable(false);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Admin_ASActivity4.this, "发送成功", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}
