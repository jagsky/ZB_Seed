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
import com.zbPro.seed.activity.RogueActivity;
import com.zbPro.seed.adminActivity.Admin_CastrationActivity;
import com.zbPro.seed.adminActivity.Admin_GainActitivy;
import com.zbPro.seed.adminActivity.Admin_RogueActivity;
import com.zbPro.seed.adminActivity.Admin_SeedActivity;
import com.zbPro.seed.adminActivity.Admin_TodayActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Admin_Fragment2 extends Fragment {

    @Bind(R.id.admin_fragment2_today)
    LinearLayout adminFragment2Today;
    @Bind(R.id.admin_fragment2_seed)
    LinearLayout adminFragment2Seed;
    @Bind(R.id.admin_fragment2_rogue)
    LinearLayout adminFragment2Rogue;
    @Bind(R.id.admin_fragment2_cats)
    LinearLayout adminFragment2Cats;
    @Bind(R.id.admin_fragment2_gain)
    LinearLayout adminFragment2Gain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_admin__fragment2, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.admin_fragment2_today, R.id.admin_fragment2_seed, R.id.admin_fragment2_rogue, R.id.admin_fragment2_cats, R.id.admin_fragment2_gain})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.admin_fragment2_today:
                Intent todayIntent = new Intent(getActivity(), Admin_TodayActivity.class);
                startActivity(todayIntent);
                break;
            case R.id.admin_fragment2_seed:
                Intent seedIntent = new Intent(getActivity(), Admin_SeedActivity.class);
                startActivity(seedIntent);
                break;
            case R.id.admin_fragment2_rogue:
                Intent rogueIntent = new Intent(getActivity(), Admin_RogueActivity.class);
                startActivity(rogueIntent);
                break;
            case R.id.admin_fragment2_cats:
                Intent catsIntent = new Intent(getActivity(), Admin_CastrationActivity.class);
                startActivity(catsIntent);
                break;
            case R.id.admin_fragment2_gain:
                Intent gainIntent = new Intent(getActivity(), Admin_GainActitivy.class);
                startActivity(gainIntent);
                break;
        }
    }
}
