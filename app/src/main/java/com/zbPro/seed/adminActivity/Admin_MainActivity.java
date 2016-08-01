package com.zbPro.seed.adminActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.zbPro.seed.activity.R;
import com.zbPro.seed.admin_fragment.Admin_Fragment1;
import com.zbPro.seed.admin_fragment.Admin_Fragment2;
import com.zbPro.seed.admin_fragment.Admin_Fragment3;
import com.zbPro.seed.admin_fragment.Admin_Fragment4;

public class Admin_MainActivity extends FragmentActivity {
    //存放Dock栏文字
    private String[] mTabText = {"首页", "日常", "调查", "个人"};
    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.tab_home_btn, R.drawable.tab_message_btn,
            R.drawable.tab_square_btn, R.drawable.tab_selfinfo_btn};

    //定义一个Class数组，收录所有的fragment
    private Class[] fragmentArray = {Admin_Fragment1.class, Admin_Fragment2.class,
            Admin_Fragment3.class, Admin_Fragment4.class};
    //引用一个布局填充器
    private LayoutInflater layoutInflater;
    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        init();
    }

    //初始化控件
    private void init() {
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        layoutInflater = LayoutInflater.from(this);
        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(mTabText[i]).setIndicator(getItemView(i));
            tabHost.addTab(tabSpec, fragmentArray[i], null);
            tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
        }
    }

    //获取子布局
    private View getItemView(int index) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.main_table_view, null);
        ImageView iv = (ImageView) inflate.findViewById(R.id.imageview);
        iv.setImageResource(mImageViewArray[index]);
        TextView tv = (TextView) inflate.findViewById(R.id.textview);
        tv.setText(mTabText[index]);

        return inflate;
    }
}
