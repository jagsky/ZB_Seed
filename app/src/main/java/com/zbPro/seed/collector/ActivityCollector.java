package com.zbPro.seed.collector;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;


/*
* 类名：ActivityCollector
* 作用：Activity活动管理器
* 功能：所有Activity的创建与销毁
* */
public class ActivityCollector {
    public static List<Activity> activityList = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);

    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
        activity = null;
    }

    public static void finshActivity() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }

        }
    }


}
