package com.zbPro.seed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zbPro.seed.activity.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27.
 */
public class MyAdapter_Admin extends BaseAdapter {
    Context context;
    List list;

    public MyAdapter_Admin(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.admin_listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.admin_listview_item_titleimage);
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.admin_listview_item_titletext1);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.admin_listview_item_titletext2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置

        return null;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
    }
}
