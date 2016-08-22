package com.zbPro.seed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zbPro.seed.activity.R;
import com.zbPro.seed.bean.CastrationBean;
import com.zbPro.seed.bean.GainBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class MyAdminGainAdapter extends BaseAdapter {
    Context context;
    List<GainBean> gainBeen;

    public MyAdminGainAdapter(Context context, List<GainBean> gainBeen) {
        this.context = context;
        this.gainBeen = gainBeen;
    }

    @Override
    public int getCount() {
        return gainBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return gainBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.admin_ok_listitem, null);
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.adminseed_text1);
            //  viewHolder.textView2 = (TextView) convertView.findViewById(R.id.adminseed_text2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView1.setText(gainBeen.get(position).getFramarName());
        System.out.println(gainBeen.get(position).getFramarName() + "这是我要的数据");


        return convertView;
    }

    private class ViewHolder {
        TextView textView1;
        //TextView textView2;
    }
}
