package com.zbPro.seed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.zbPro.seed.activity.R;
import com.zbPro.seed.bean.ExpandableListItem;
import com.zbPro.seed.bean.Seed;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Administrator on 2016/8/18.
 */
public class MyBaseExpandableListAdapter<T> extends BaseExpandableListAdapter {
    //创建需要的引用的对象
    private Context mContext;
    private ArrayList<String> groups;
    private LinkedList<LinkedList<Seed>> iData;

    //初始化对象属性
    public MyBaseExpandableListAdapter(Context mContext, ArrayList<String> groups, LinkedList<LinkedList<Seed>> iData) {
        this.mContext = mContext;
        this.groups = groups;
        this.iData = iData;
    }

    //获取父亲组的item个数
    @Override
    public int getGroupCount() {
        return groups.size();
    }

    //获取儿子组的item个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.size();
    }

    //获取父亲组Position对应的数据
    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    //获取父亲组Position对应的数据后，在对应的获取儿子组Position对应的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }

    //获取父亲组Position对应的id
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //获取儿子组Position对应的id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    //取得用于显示给定父亲组的视图. 这个方法仅返回父亲组的视图对象
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup viewHolderGroup = null;
        if (convertView == null) {
            //多么变态的写法，listview比她有趣多了
            convertView = LayoutInflater.from(mContext).inflate(R.layout.expandablelistview_fartheritem, parent, false);
            viewHolderGroup = new ViewHolderGroup();
            viewHolderGroup.tv_group_name = (TextView) convertView.findViewById(R.id.tv_group_name);
            //步骤和ListView BaseAdapter一样，没有什么太大的区别
            convertView.setTag(viewHolderGroup);
        } else {
            //如果 这个View不为空，就把这个控件传递给viewHolderGroup 让他去显示数据，节约资源。
            viewHolderGroup = (ViewHolderGroup) convertView.getTag();
        }

        viewHolderGroup.tv_group_name.setText(groups.get(groupPosition));
        return convertView;
    }

    //你懂得
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.expandablelistview_erziitem, parent, false);
            itemHolder = new ViewHolderItem();
            itemHolder.tv_name1 = (TextView) convertView.findViewById(R.id.tv_name1);
            itemHolder.tv_name2 = (TextView) convertView.findViewById(R.id.tv_name2);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        itemHolder.tv_name2.setText(iData.get(groupPosition).get(childPosition).getBeizhu());
        return convertView;
    }

    //设置子列表是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //与ListView一样，创建一个布局管理者，方便到时候直接传递给convertView，因为是两个视图联动，所以创建两个呗。
    private static class ViewHolderGroup {
        private TextView tv_group_name;
    }

    private static class ViewHolderItem {
        private TextView tv_name1;
        private TextView tv_name2;
    }

}


