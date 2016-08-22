package com.zbPro.seed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zbPro.seed.activity.R;
import com.zbPro.seed.bean.Seed;

import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class MyAdminSeedAdapter extends BaseAdapter {
    Context context;
    List<Seed> seeds;

    public MyAdminSeedAdapter(Context context, List<Seed> seeds) {
        this.context = context;
        this.seeds = seeds;
    }

    @Override
    public int getCount() {
        return seeds.size();
    }

    @Override
    public Object getItem(int position) {
        return seeds.get(position);
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
        if (seeds.get(position).getFatherUse() != null && seeds.get(position).getFatherUse().length() > 0) {
            viewHolder.textView1.setText(seeds.get(position).getFramarName());
            viewHolder.textView1.setBackgroundResource(R.color.aquamarine);
        } else {
            viewHolder.textView1.setText(seeds.get(position).getFramarName());
        }

        return convertView;
    }

    public class ViewHolder {
        TextView textView1;
        //TextView textView2;
    }
}
