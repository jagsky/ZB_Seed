package com.zbPro.seed.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zbPro.seed.activity.CastrationActivity;
import com.zbPro.seed.activity.MakersActivity;
import com.zbPro.seed.activity.PredictedActivity;
import com.zbPro.seed.activity.R;
import com.zbPro.seed.activity.RogueActivity;
import com.zbPro.seed.activity.SeedActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
   * 时间：2016/5/25
   * 名称：记录
   * 作用：    记录数据界面
   * 实现功能：实现农户的种植记录页面，并进行跳转
   * */
public class Fragment2 extends Fragment {
    LinearLayout layout;
    //注解所有控件
    @Bind(R.id.household_image)
    ImageView householdImage;
    @Bind(R.id.household_textview)
    TextView householdTextview;
    @Bind(R.id.household_layout)
    LinearLayout householdLayout;
    @Bind(R.id.seed_image)
    ImageView seedImage;
    @Bind(R.id.seed_textview)
    TextView seedTextview;
    @Bind(R.id.seed_layout)
    LinearLayout seedLayout;
    @Bind(R.id.rogue_image)
    ImageView rogueImage;
    @Bind(R.id.rogue_textview)
    TextView rogueTextview;
    @Bind(R.id.rogue_layout)
    LinearLayout rogueLayout;
    @Bind(R.id.castration_image)
    ImageView castrationImage;
    @Bind(R.id.castration_textview)
    TextView castrationTextview;
    @Bind(R.id.castration_layout)
    LinearLayout castrationLayout;
    @Bind(R.id.predicted_image)
    ImageView predictedImage;
    @Bind(R.id.predicted_textview)
    TextView predictedTextview;
    @Bind(R.id.predicted_layout)
    LinearLayout predictedLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentpage2, null);
        ButterKnife.bind(this, view);
        return view;


    }


    //在退出Fragment时解除控件的绑定
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //控件对应的点击事件
    @OnClick({R.id.household_layout, R.id.seed_layout, R.id.rogue_layout, R.id.castration_layout, R.id.predicted_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            //调转到播种界面
            case R.id.seed_layout:
                Intent seedIntent = new Intent(getActivity(), SeedActivity.class);
                startActivity(seedIntent);
                break;
            //跳转到去杂界面
            case R.id.rogue_layout:
                Intent rogueIntent = new Intent(getActivity(), RogueActivity.class);
                startActivity(rogueIntent);
                break;
            //跳转到去雄界面
            case R.id.castration_layout:
                Intent castrationIntent = new Intent(getActivity(), CastrationActivity.class);
                startActivity(castrationIntent);
                break;
            //跳转到测产界面
            case R.id.predicted_layout:
                Intent predicted = new Intent(getActivity(), PredictedActivity.class);
                startActivity(predicted);
                break;
            case R.id.household_layout:
                Intent intent = new Intent(getActivity(), MakersActivity.class);
                startActivity(intent);

        }
    }
}
