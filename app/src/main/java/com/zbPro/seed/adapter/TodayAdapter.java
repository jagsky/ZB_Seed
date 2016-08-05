package com.zbPro.seed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zbPro.seed.activity.R;
import com.zbPro.seed.bean.TodayBean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class TodayAdapter extends BaseAdapter {
    Context context;
    LinkedList<TodayBean> list;

    public TodayAdapter(Context context,  LinkedList<TodayBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.admin_today_listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.admin_today_name);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.admin_today_time);
            viewHolder.textView3 = (TextView) convertView.findViewById(R.id.admin_today_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView1.setText(list.get(position).getId());
        viewHolder.textView2.setText(list.get(position).getTime());
        viewHolder.textView3.setText(list.get(position).getContent());

        return convertView;
    }

    class ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
    }
}
