package com.zbPro.seed.admin_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin__fragment4, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.admin_fragment4_Feedback, R.id.admin_fragment4_SofeWareUp, R.id.admin_fragment4_help, R.id.admin_fragment4_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.admin_fragment4_Feedback:
                break;
            case R.id.admin_fragment4_SofeWareUp:
                break;
            case R.id.admin_fragment4_help:
                break;
            case R.id.admin_fragment4_btn:
                break;
        }
    }
}
