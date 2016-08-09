package com.zbPro.seed.activity;


    /*
    * 时间：2016/5/23
    * 名称：主界面
    * 作用：主界面的整体框架，table栏
    * 实现功能：自定义TabHost
    * */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.zbPro.seed.bean.CastrationBean;
import com.zbPro.seed.bean.FarmerBean;
import com.zbPro.seed.bean.GainBean;
import com.zbPro.seed.bean.RogueBean;
import com.zbPro.seed.bean.Seed;
import com.zbPro.seed.dao.CastrationDao;
import com.zbPro.seed.dao.FarmaerDao;
import com.zbPro.seed.dao.GainDao;
import com.zbPro.seed.dao.RogueDao;
import com.zbPro.seed.dao.SeedDao;
import com.zbPro.seed.fragment.Fragment1;
import com.zbPro.seed.fragment.Fragment2;
import com.zbPro.seed.fragment.Fragment3;
import com.zbPro.seed.fragment.Fragment4;
import com.zbPro.seed.service.LoginMyService;

import java.sql.SQLException;

import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;
    //定义一个布局填充器
    private LayoutInflater layoutInflater;
    //定义一个Class数组，收录所有的fragment
    private Class[] fragmentArray = {Fragment1.class, Fragment2.class, Fragment3.class, Fragment4.class};
    //定义一个String数组，收录Tab选项卡的文字
    private String[] mtableName = {"首页", "记录", "数据", "个人"};
    //定义一个Int数组，收录所有的按钮选择器对应的R文件下对应的ID
    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.tab_home_btn, R.drawable.tab_message_btn,
            R.drawable.tab_square_btn, R.drawable.tab_selfinfo_btn};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //启动服务获取返回的数据
        netService();
        //初始化View控件
        initView();
        //获取Intent传递过来的数据
        // getIntentData();
        // addDatabase();


    }

    private void netService() {
        Intent startIntent = getIntent();
        String s = startIntent.getStringExtra("register");
    }

/*
    private void addDatabase() {
        Seed seed;
        SeedDao seedDao;
        CastrationBean castrationBean;
        CastrationDao castrationDao;
        GainBean gainBean;
        GainDao gainDao;
        RogueBean rogueBean;
        RogueDao rogueDao;
        FarmerBean farmerBean;
        FarmaerDao farmaerDao;
        farmaerDao = new FarmaerDao(getApplicationContext());
        for (int i = 0; i < 20; i++) {
            farmerBean = new FarmerBean("张三" + i, "16JZYXX" + i + "xxx", "玉米", "甘肃",
                    "大运镇", "430421", "sdas", "dasda", "asda",
                    "asdas", "dasda", "dasda");
            try {
                farmaerDao.updataFarmerBean(farmerBean);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // farmaerDao.updataFarmerBean(farmerBean);
        }

        castrationDao = new CastrationDao(getApplicationContext());
        for (int i = 0; i < 20; i++) {
            castrationBean = new CastrationBean("张三" + i, "16JZYXX" + i + "xxx", "玉米");
            try {
                castrationDao.addCastrationBean(castrationBean);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        seedDao = new SeedDao(getApplicationContext());
        for (int i = 0; i < 20; i++) {
            seed = new Seed("张三" + i, "16JZYXX" + i + "xxx", "玉米");
            try {
                seedDao.addSeed(seed);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        gainDao = new GainDao(getApplicationContext());
        for (int i = 0; i < 20; i++) {
            gainBean = new GainBean("张三" + i, "16JZYXX" + i + "xxx", "玉米");
            try {
                gainDao.addGainBean(gainBean);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        rogueDao = new RogueDao(getApplicationContext());
        for (int i = 0; i < 20; i++) {
            rogueBean = new RogueBean("张三" + i, "16JZYXX" + i + "xxx", "玉米");
            try {
                rogueDao.addRogueBean(rogueBean);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }*/

/*
    //
    private void getIntentData() {
        //获取用户传递过来的值
        Intent intent = getIntent();
        String s = intent.getStringExtra("userName");
        int ss = intent.getIntExtra("isReqeust", 0);
        if (ss == 1) {
            //启动一个服务接收返回过来的数据
            Intent intent1 = new Intent(MainActivity.this, .class);
            intent1.putExtra("",);
            startService(intent1);
        }
    }*/


    //初始化组件

    private void initView() {
        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);
        //得到fragment的个数
        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mtableName[i])
                    .setIndicator(getTabItemView(i));

            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);

        }
    }

    /*
    * 名称：获取tab的文字与图片
    * 方法名：getTabItemView
    * 参数: index  tabhost对应的下标
    * 功能：给Tab按钮设置图标和文字
    *
    * */
    private View getTabItemView(int index) {
        //获取一个布局填充器，填充View
        View view = layoutInflater.inflate(R.layout.main_table_view, null);
        //获取应该加在什么样式的布局，将布局加载到Main界面中，类似于ListView的Adapter填充布局一样。
        //tabhost本身不是一个布局，更像是一个容器。
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mtableName[index]);

        return view;
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("关闭服务");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intentService = new Intent(this, LoginMyService.class);
        stopService(intentService);
        System.out.println("开启服务");
    }
}
