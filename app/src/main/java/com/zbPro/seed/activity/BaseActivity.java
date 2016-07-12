package com.zbPro.seed.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.zbPro.seed.collector.ActivityCollector;

/*
* 类名：BaseActivity
* 作用：基础Activity
* 功能：所有Activity继承此界面，方便管理
* */
public class BaseActivity extends AppCompatActivity {

    Activity activity;
    public int intScreenBrightness;

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

   /* private void screenBrightness_check() {
        //先关闭系统的亮度自动调节
        try {
            if (android.provider.Settings.System.getInt(getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE) == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                android.provider.Settings.System.putInt(getContentResolver(),
                        android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE,
                        android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }
        } catch (Settings.SettingNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //获取当前亮度,获取失败则返回255
        intScreenBrightness = (int) (android.provider.Settings.System.getInt(getContentResolver(),
                android.provider.Settings.System.SCREEN_BRIGHTNESS,
                255));
        //设置当前activity的屏幕亮度
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        //0到1,调整亮度暗到全亮
        lp.screenBrightness = 255f;
        this.getWindow().setAttributes(lp);
        //文本、进度条显示
       *//* mSeekBar_light.setProgress(intScreenBrightness);
        mTextView_light.setText("" + intScreenBrightness * 100 / 255);*//*

    }*/

}
