package com.zbPro.seed.fragment;

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
import android.widget.Toast;

import com.zbPro.seed.activity.FeedbackActivity;
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
    @Bind(R.id.fragment4_UserIdtv)
    TextView fragment4UserIdtv;
    @Bind(R.id.fragment4_SofeWareUp)
    Button fragment4Feedback;
    private SharedPreferences preferences;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentpage4, null);
        ButterKnife.bind(this, view);
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
        preferences = getActivity().getSharedPreferences("login", getActivity().MODE_PRIVATE);
        String register_userName = preferences.getString("register_userName", "欢迎使用");
        fragment4UserIdtv.setText(register_userName);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.fragment4_Feedback, R.id.fragment4_SofeWareUp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment4_Feedback:
                Intent intent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.fragment4_SofeWareUp:
                Toast.makeText(getActivity(), "已是最新版本", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
