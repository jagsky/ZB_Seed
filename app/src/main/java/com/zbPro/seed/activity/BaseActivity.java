package com.zbPro.seed.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zbPro.seed.collector.ActivityCollector;
import com.zbPro.seed.db.DatabaseHelper;

import java.util.List;

/*
* 类名：BaseActivity
* 作用：基础Activity
* 功能：所有Activity继承此界面，方便管理
* */
public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //在创建的时候知道是那个Activity
        ActivityCollector.addActivity(this);

    }

    //设置屏幕的方向为竖屏方向。
    @Override
    protected void onResume() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        Log.i("TAG", getClass().getSimpleName() + "创建");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        Log.i("TAG", getClass().getSimpleName() + "移除");

    }

}
