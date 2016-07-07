package com.zbPro.seed.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bigkoo.quicksidebar.QuickSideBarTipsView;
import com.bigkoo.quicksidebar.QuickSideBarView;
import com.bigkoo.quicksidebar.listener.OnQuickSideBarTouchListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.zbPro.seed.adapter.CityListAdapter;
import com.zbPro.seed.adapter.DividerDecoration;
import com.zbPro.seed.bean.City;
import com.zbPro.seed.bean.FarmerBean;
import com.zbPro.seed.dao.FarmaerDao;
import com.zbPro.seed.util.DataConstants;

import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/*
* 时间：2016/6/15
* 名称：农户信息界面
* 作用：显示农户的基本信息
* 实现功能：显示农户的基本信息
* */
public class MakersActivity extends BaseActivity implements OnQuickSideBarTouchListener {
    RecyclerView recyclerView;
    HashMap<String, Integer> letters = new HashMap<>();
    QuickSideBarView quickSideBarView;
    QuickSideBarTipsView quickSideBarTipsView;


    //获取listview控件
    ListView farmer_lv;
    //农户信息表操作
    FarmaerDao farmaerDao;
    //农户Bean
    FarmerBean farmerBean;
    //搜索空间
    AutoCompleteTextView farmer_AutoCompleteTextView;
    //单独查询地块号会返回一个ArrayList
    ArrayList lineArrayList;
    //收集搜索栏选择的地块号
    String queryDKNumber;
    //收集 姓名 地块号 种类信息
    ArrayList<HashMap<String, Object>> farmerNDTList;
    //搜索栏适配器
    ArrayAdapter adapter;
    //ListView适配器
    SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makers);
        //获取farmer数据库中的操作权限
        farmaerDao = new FarmaerDao(this);
        //获取地块号
        try {
            lineArrayList = farmaerDao.displayFarmerBean();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //获取 farmer表中 姓名 地块号 种类显示到Listvew中
        farmerNDTList = farmaerDao.displayFarmerNDT();
        initlistview();
        // initView();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        quickSideBarView = (QuickSideBarView) findViewById(R.id.quickSideBarView);
        quickSideBarTipsView = (QuickSideBarTipsView) findViewById(R.id.quickSideBarTipsView);

        //设置监听
        quickSideBarView.setOnQuickSideBarTouchListener(this);


        //设置列表数据和浮动header
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Add the sticky headers decoration
        CityListWithHeadersAdapter adapter = new CityListWithHeadersAdapter();

        //GSON解释出来
        Type listType = new TypeToken<LinkedList<City>>() {
        }.getType();
        Gson gson = new Gson();
        LinkedList<City> cities = gson.fromJson(DataConstants.cityDataList, listType);

        ArrayList<String> customLetters = new ArrayList<>();

        int position = 0;
        for (City city : cities) {
            String letter = city.getFirstLetter();
            //如果没有这个key则加入并把位置也加入
            if (!letters.containsKey(letter)) {
                letters.put(letter, position);
                customLetters.add(letter);
            }
            position++;
        }

        //不自定义则默认26个字母
        quickSideBarView.setLetters(customLetters);
        adapter.addAll(cities);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recyclerView.addItemDecoration(headersDecor);

        // Add decoration for dividers between list items
        recyclerView.addItemDecoration(new DividerDecoration(this));


    }

    private void initlistview() {
        //初始化控件
       /* //初始化Listview空间
        farmer_lv = (ListView) findViewById(R.id.farmer_lv);
        //设置一个ListView的适配器
        simpleAdapter = new SimpleAdapter(MakersActivity.this, farmerNDTList,
                R.layout.farmer_item,
                new String[]{"name", "DkNumber", "type"},
                new int[]{R.id.txtitem_1, R.id.txtitem_2, R.id.txtitem_3});
        farmer_lv.setAdapter(simpleAdapter);*/
    }

    /*private void initView() {
        //初始化搜索栏控件
        farmer_AutoCompleteTextView = (AutoCompleteTextView)
                findViewById(R.id.farmer_AutoCompleteTextView);
        //设置一个搜索栏的适配器
        adapter = new ArrayAdapter(MakersActivity.this,
                android.R.layout.simple_expandable_list_item_1, lineArrayList);
        farmer_AutoCompleteTextView.setAdapter(adapter);
        //获取选中的列表
        farmer_AutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(MakersActivity.this.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                queryDKNumber = parent.getItemAtPosition(position).toString();
                Toast.makeText(MakersActivity.this, "你选择了" + queryDKNumber, Toast.LENGTH_SHORT).show();
            }
        });


    <AutoCompleteTextView
        android:id="@+id/farmer_AutoCompleteTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginBottom="10dp"
        android:layout_marginLeft="5dp"
        android:completionThreshold="1"
        android:digits="@string/register_name_digits"
        android:dropDownHeight="wrap_content"
        android:hint="请输入农户的地块号"
        android:maxLength="15" />
    }*/


    @Override
    public void onLetterChanged(String letter, int position, int itemHeight) {
        quickSideBarTipsView.setText(letter, position, itemHeight);
        //有此key则获取位置并滚动到该位置
        if (letters.containsKey(letter)) {
            recyclerView.scrollToPosition(letters.get(letter));
        }
    }

    @Override
    public void onLetterTouching(boolean touching) {
        //可以自己加入动画效果渐显渐隐
        quickSideBarTipsView.setVisibility(touching ? View.VISIBLE : View.INVISIBLE);
    }

    private class CityListWithHeadersAdapter extends CityListAdapter<RecyclerView.ViewHolder>
            implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_item, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextView textView = (TextView) holder.itemView;
            textView.setText(getItem(position).getCityName());
        }

        @Override
        public long getHeaderId(int position) {
            return getItem(position).getFirstLetter().charAt(0);
        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_header, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
            TextView textView = (TextView) holder.itemView;
            textView.setText(String.valueOf(getItem(position).getFirstLetter()));
            holder.itemView.setBackgroundColor(getRandomColor());
        }

        private int getRandomColor() {
            SecureRandom rgen = new SecureRandom();
            return Color.HSVToColor(150, new float[]{
                    rgen.nextInt(359), 1, 1
            });
        }

    }
}
