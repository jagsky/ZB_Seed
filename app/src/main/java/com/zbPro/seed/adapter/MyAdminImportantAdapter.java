package com.zbPro.seed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zbPro.seed.activity.R;
import com.zbPro.seed.bean.ImportantBean;
import com.zbPro.seed.bean.ImportantTitleBean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/23.
 */
public class MyAdminImportantAdapter extends BaseAdapter {
    List<ImportantBean> importantBeen;
    Context context;

    public MyAdminImportantAdapter(List<ImportantBean> importantBeen, Context context) {
        this.importantBeen = importantBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return importantBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return importantBeen.get(position);
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
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.admin_listview_item_titletext1);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.admin_listview_item_titletext2);
            viewHolder.textView3 = (TextView) convertView.findViewById(R.id.admin_listview_item_titletext3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView1.setText(importantBeen.get(position).getTitle());
        viewHolder.textView2.setText(importantBeen.get(position).getUserName());
        viewHolder.textView3.setText(importantBeen.get(position).getDate());
        return convertView;
    }

    class ViewHolder {
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;

    }
}
