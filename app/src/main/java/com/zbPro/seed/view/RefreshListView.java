package com.zbPro.seed.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/20.
 */
public class RefreshListView extends ListView implements AbsListView.OnScrollListener {
    //正常状态
    private final int NONE = 0;
    //下拉状态
    private final int PULL = 1;
    //提示释放状态
    private final int RELESE = 2;
    //正在刷新
    private final int REFLASHING = 3;
    //比值
    private final int RATIO = 3;

    //顶部刷新视图
    private View headerView;
    //顶部布局文件的高度
    private int headerViewHeiht;
    //当前第一个可见Item位置
    private int firstVisibleItem;
    //是否刷新结束
    private boolean isEnd;
    //是否可以刷新
    private boolean isRefreable;
    //标记当前ListView是否在第一个
    private boolean isRemark;
    private float startY;
    private float endY;
    private int state;
    private TextView tip;
    private ImageView img;
    private AnimationDrawable drawableAnim;

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }
    //初始化
    private void init(Context context, AttributeSet attrs) {
        //headerView = LayoutInflater.from(context).inflate(R.)
    }




    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
