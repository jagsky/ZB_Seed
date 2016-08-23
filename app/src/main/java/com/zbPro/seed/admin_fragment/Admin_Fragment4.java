package com.zbPro.seed.admin_fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zbPro.seed.activity.FanGuiActivity;
import com.zbPro.seed.activity.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Admin_Fragment4 extends Fragment {

    @Bind(R.id.admin_fragment4_UserIdtv)
    TextView adminFragment4UserIdtv;
    @Bind(R.id.admin_fragment4_Feedback)
    Button adminFragment4Feedback;
    @Bind(R.id.admin_fragment4_SofeWareUp)
    Button adminFragment4SofeWareUp;
    @Bind(R.id.admin_fragment4_help)
    Button adminFragment4Help;
    @Bind(R.id.admin_fragment4_btn)
    Button adminFragment4Btn;
    SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin__fragment4, null);
        ButterKnife.bind(this, view);
        preferences = getActivity().getSharedPreferences("login", getActivity().MODE_PRIVATE);
        String register_userName = preferences.getString("register_userName", "欢迎使用");
        System.out.println("管理员"+register_userName);
        adminFragment4UserIdtv.setText(register_userName);
        return view;
    }


    @OnClick({R.id.admin_fragment4_Feedback, R.id.admin_fragment4_SofeWareUp, R.id.admin_fragment4_help, R.id.admin_fragment4_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.admin_fragment4_Feedback:
                Intent intent = new Intent(getActivity(), FanGuiActivity.class);
                startActivity(intent);
                break;
            case R.id.admin_fragment4_SofeWareUp:
                Toast.makeText(getActivity(), "最新软件", Toast.LENGTH_SHORT).show();
                break;
            case R.id.admin_fragment4_help:
                Toast.makeText(getActivity(), "请联系公司", Toast.LENGTH_SHORT).show();
                break;
            case R.id.admin_fragment4_btn:
                Toast.makeText(getActivity(), "请联系公司", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

}
