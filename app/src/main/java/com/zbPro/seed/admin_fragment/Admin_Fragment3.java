package com.zbPro.seed.admin_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zbPro.seed.activity.R;
import com.zbPro.seed.adminActivity.Admin_ASActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Admin_Fragment3 extends Fragment {

    @Bind(R.id.admin_fragment3_as)
    LinearLayout adminFragment3As;
    @Bind(R.id.admin_fragment3_sd)
    LinearLayout adminFragment3Sd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin__fragment3, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.admin_fragment3_as, R.id.admin_fragment3_sd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.admin_fragment3_as:
                Intent asIntent = new Intent(getActivity(), Admin_ASActivity.class);
                startActivity(asIntent);

                break;
            case R.id.admin_fragment3_sd:
                Intent sdIntent = new Intent(getActivity(), Admin_ASActivity.class);
                startActivity(sdIntent);
                break;
        }
    }
}
