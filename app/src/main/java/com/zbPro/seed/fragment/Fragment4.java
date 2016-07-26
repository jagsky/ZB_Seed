package com.zbPro.seed.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zbPro.seed.activity.LoginActivity;
import com.zbPro.seed.activity.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 创建时间：2016/5/23
* ClassName：fragment4
* 作用：个人中心
* 实现功能：用户退出以及相关操作
* */
public class Fragment4 extends Fragment {
    private SharedPreferences preferences;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentpage4, null);
        //读取login事务中的数据
        gainUserName();
        return view;

    }

    /*
         * 方法名称：gainUserName
         * 功能：获取用户
         * 参数：无
         * */
    private void gainUserName() {
        preferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String register_userName = preferences.getString("register_userName", "欢迎使用");

    }


}
