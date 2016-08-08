package com.zbPro.seed.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.zbPro.seed.bean.City;
import com.zbPro.seed.bean.FarmerBean;
import com.zbPro.seed.db.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * //用于保存技术员名称数据
 * Created by Administrator on 2016/8/2.
 */
public class CityDao {
    Context context;
    Dao cityDao;
    LinkedList linkedList;

    public CityDao(Context context) {
        this.context = context;
        cityDao = DatabaseHelper.getHelper(context).getCityDao();
    }

    public City addCity(City city) throws SQLException {
        return (City) cityDao.createIfNotExists(city);
    }

    /**
     * 查询所有的信息
     */
    public List<City> queryAllCity() throws SQLException {
        List<City> users = cityDao.queryForAll();
        return users;
    }
}
