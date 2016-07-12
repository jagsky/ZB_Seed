package com.zbPro.seed.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
* 类名：WelComeActivity
* 作用：欢迎界面
* 功能：欢迎界面，自动跳转到登入界面
* */

public class WelComeActivity extends BaseActivity {
    //提取preferences中的Boolem类型ISOK 判断时候直接跳转
    private SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Bind(R.id.welcome_iv)
    ImageView welcomeIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView( R.layout.activity_wel_come );
        ButterKnife.bind( this );
        //初始化数据
        init();
    }

    private void init() {
        //创建一个渐变动画
        AlphaAnimation animation = new AlphaAnimation( 0.1f, 1.0f );
        animation.setDuration( 2000 );
        welcomeIv.setAnimation( animation );
        animation.setAnimationListener( new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            //这是我最新
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                preferences = getSharedPreferences( "login", MODE_PRIVATE );
                boolean IsOK = preferences.getBoolean( "IsOK", false );
                String s = preferences.getString( "register_userName", "0" );
                if (IsOK == false) {
                    //如果之前没有保存过账号密码，则先跳转到登入界面
                    Intent intent = new Intent( WelComeActivity.this, LoginActivity.class );
                    startActivity( intent );
                    finish();
                } else {
                    Intent intent = new Intent( WelComeActivity.this, MainActivity.class );
                    startActivity( intent );
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        } );

    }
}
