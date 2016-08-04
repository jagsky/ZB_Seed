package com.zbPro.seed.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import com.zbPro.seed.net.HttpPost;
import com.zbPro.seed.util.Constant;
import com.zbPro.seed.util.RecyclerItemClickListener;

import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;

public class FarmerDataActivity extends BaseActivity implements OnQuickSideBarTouchListener {
    public String recycleViewItemData;
    RecyclerView recyclerView;
    HashMap<String, Integer> letters = new HashMap<>();
    QuickSideBarView quickSideBarView;
    QuickSideBarTipsView quickSideBarTipsView;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String allData = bundle.getString("str");
            System.out.println(allData);
            getUI(allData);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_data);
        String address = getLocalIpAddress();
        System.out.println(address);
        if (address == null) {
            Toast.makeText(FarmerDataActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();
        } else {
            httpJson();
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        quickSideBarView = (QuickSideBarView) findViewById(R.id.quickSideBarView);
        quickSideBarTipsView = (QuickSideBarTipsView) findViewById(R.id.quickSideBarTipsView);
        //设置监听
        quickSideBarView.setOnQuickSideBarTouchListener(this);


    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("TAG", ex.toString());
        }
        return null;
    }


    protected void getUI(String allData) {
        //设置列表数据和浮动header
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Add the sticky headers decoration
        CityListWithHeadersAdapter adapter = new CityListWithHeadersAdapter();

        //GSON解释出来
        final Type type = new TypeToken<LinkedList<City>>() {
        }.getType();
        Gson gson = new Gson();

        LinkedList<City> cities = gson.fromJson(allData, type);

//        System.out.println("便利签" + cities.toString());
        ArrayList<String> customLetters = new ArrayList<String>();
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
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recyclerView.addItemDecoration(headersDecor);

        // Add decoration for dividers between list items
        recyclerView.addItemDecoration(new DividerDecoration(this));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));

    }

    private void httpJson() {
        String str = "{boolean\": true,\n" +
                "  \"null\": null,\n" +
                "  \"number\": 123,\n}";

        Gson gson = new Gson();
        final String json = gson.toJson(str);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = HttpPost.SendhttpPostJson(json, Constant.PATH + Constant.TECHNICIAN);
                Message message = Message.obtain(handler);
                Bundle bundle = new Bundle();
                bundle.putString("str", str);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        }).start();
    }








    @Override
    public void onLetterChanged(String letter, int position, float y) {
        quickSideBarTipsView.setText(letter, position, y);
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






    public class CityListWithHeadersAdapter extends CityListAdapter<RecyclerView.ViewHolder>
            implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
        public FarmerDataActivity.MyItemClickListener mItemClickListener;

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_item, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            TextView textView = (TextView) holder.itemView;
            textView.setText(getItem(position).getCityName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FarmerDataActivity.this, ((TextView) holder.itemView).getText().toString(), Toast.LENGTH_SHORT).show();
                    recycleViewItemData = ((TextView) holder.itemView).getText().toString();
                    System.out.println(recycleViewItemData);

                }
            });
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
        /**
         * 设置Item点击监听
         * @param listener
         */
        public void setOnItemClickListener(MyItemClickListener listener){
            this.mItemClickListener = listener;
        }

    }

    public interface MyItemClickListener {
        public void onItemClick(View view, int postion);
    }
}
