package com.zbPro.seed.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zbPro.seed.activity.FarmerDataActivity;
import com.zbPro.seed.activity.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/6/7.
 * ClassName ：com.zbPro.seed.fragment
 * 作用：
 */
public class Fragment3 extends Fragment {

    @Bind(R.id.te_et)
    EditText teEt;
    @Bind(R.id.te_btn)
    Button teBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragmentpage3, null);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.te_btn)
    public void onClick() {
        if (teEt.getText().toString().equals("123123")) {
            Intent intent = new Intent(getActivity(), FarmerDataActivity.class);
            startActivity(intent);
        }

    }
}