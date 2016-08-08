package com.zbPro.seed.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.zbPro.seed.bean.CastrationBean;
import com.zbPro.seed.bean.City;
import com.zbPro.seed.bean.FarmerBean;
import com.zbPro.seed.bean.GainBean;
import com.zbPro.seed.bean.ImportantBean;
import com.zbPro.seed.bean.RogueBean;
import com.zbPro.seed.bean.Seed;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/14.
 * ClassName ：DatabaseHelper
 * 作用：数据库工具类
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    Dao<FarmerBean, Integer> userRuntimeDao;
    private static final String TAG = "DatabaseHelper";
    // 数据库名称
    private static final String DATABASE_NAME = "ZBOrmlite.db";
    // 数据库version
    private static final int DATABASE_VERSION = 1;
    //通过Dao初始化User dao<类名，主键类型>
    private Map<String, RuntimeExceptionDao> daoMap = new HashMap<String, RuntimeExceptionDao>();
    Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
// 可以用配置文件来生成 数据表，有点繁琐，不喜欢用
// super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /**
     * @param context
     * @param databaseName
     * @param factory
     * @param databaseVersion
     */
    public DatabaseHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
//建立User表
            TableUtils.createTableIfNotExists(connectionSource, FarmerBean.class);
            TableUtils.createTableIfNotExists(connectionSource, Seed.class);
            TableUtils.createTableIfNotExists(connectionSource, CastrationBean.class);
            TableUtils.createTableIfNotExists(connectionSource, RogueBean.class);
            TableUtils.createTableIfNotExists(connectionSource, GainBean.class);
            TableUtils.createTableIfNotExists(connectionSource, City.class);

        } catch (SQLException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {

      /*  try {
            TableUtils.dropTable(connectionSource, FarmerBean.class, true);
            TableUtils.dropTable(connectionSource, CastrationBean.class, true);
            TableUtils.dropTable(connectionSource, Seed.class, true);
            TableUtils.dropTable(connectionSource, RogueBean.class, true);
            TableUtils.dropTable(connectionSource, GainBean.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(sqLiteDatabase, connectionSource);*/

    }

    private static DatabaseHelper helper;

    public synchronized static DatabaseHelper getHelper(Context context) {
        if (helper == null) {
            helper = new DatabaseHelper(context);
        }
        return helper;
    }


    /* public RuntimeExceptionDao getAllDao(Class clazz) throws SQLException {
         String className = clazz.getSimpleName();

         if (daoMap.containsKey(className)) {
             userRuntimeDao = daoMap.get(className);
         }
         if (userRuntimeDao == null) {
             userRuntimeDao = super.getRuntimeExceptionDao(clazz);
             daoMap.put(className, userRuntimeDao);
         }
         LogBase.i(className);
         return userRuntimeDao;
     }*/
    //获取Farmer的操作权限
    public Dao<FarmerBean, Integer> getFarmerDataDao() {
        if (userRuntimeDao == null) {
            try {
                userRuntimeDao = getDao(FarmerBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userRuntimeDao;
    }

    Dao<Seed, Integer> seeds;

    public Dao<Seed, Integer> getSeedsDao() {
        if (seeds == null) {
            try {
                seeds = getDao(Seed.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return seeds;
    }

    Dao<CastrationBean, Integer> castrationBeanDao;

    public Dao<CastrationBean, Integer> getCastrationBeanDao() {
        if (castrationBeanDao == null) {
            try {
                castrationBeanDao = getDao(CastrationBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return castrationBeanDao;
    }

    Dao<GainBean, Integer> gainBeanDao;

    public Dao<GainBean, Integer> getGainBeanDao() {
        if (gainBeanDao == null) {
            try {
                gainBeanDao = getDao(GainBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return gainBeanDao;
    }


    Dao<RogueBean, Integer> rogueBeanDao;

    public Dao<RogueBean, Integer> getRogueBeanDao() {
        if (rogueBeanDao == null) {
            try {
                rogueBeanDao = getDao(RogueBean.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rogueBeanDao;
    }
    Dao<City, Integer> cities;
    public Dao<City, Integer> getCityDao() {
        if (cities == null) {
            try {
                cities = getDao(City.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cities;
    }


    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();

        for (String key : daoMap.keySet()) {
            RuntimeExceptionDao dao = daoMap.get(key);
            dao = null;
        }
    }

}

