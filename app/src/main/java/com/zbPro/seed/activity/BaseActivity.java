package com.zbPro.seed.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.zbPro.seed.bean.City;
import com.zbPro.seed.collector.ActivityCollector;
import com.zbPro.seed.dao.CityDao;

/*
* 类名：BaseActivity
* 作用：基础Activity
* 功能：所有Activity继承此界面，方便管理
* */
public class BaseActivity extends Activity {
    Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //在创建的时候知道是那个Activity
        ActivityCollector.addActivity(this);
        initwindown();
        // screenBrightness_check();
    }

    private void initwindown() {

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
