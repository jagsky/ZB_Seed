package com.zbPro.seed.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.zbPro.seed.bean.FarmerBean;
import com.zbPro.seed.db.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/14.
 * ClassName ：FarmaerDao
 * 作用：管理数据库农户这张表单
 */
public class FarmaerDao {
    Context context;
    Dao mUserDAO;
    ArrayList arrayList;

    public FarmaerDao(Context context) {
        this.context = context;
        //mUserDAO = DatabaseHelper.getHelper(context).getUserDataDao();
        mUserDAO = DatabaseHelper.getHelper(context).getFarmerDataDao();
    }

    public FarmerBean addFarmerBean(FarmerBean farmerBean) throws SQLException {
        return (FarmerBean) mUserDAO.createIfNotExists(farmerBean);
    }

    public void updataFarmerBean(FarmerBean user) throws SQLException {
//此方法只更新不创建也不删除,更新后不会删除原来的数据，但是如果无数据则不更新
        //mUserDAO.update(user);
        //此方法更新如果没有数据就创建创建也不删除
        mUserDAO.createOrUpdate(user);
    }

    /**
     * 查询所有的信息
     */
    public List<FarmerBean> queryAllFarmerBean() throws SQLException {
        List<FarmerBean> users = mUserDAO.queryForAll();
        return users;
    }

    //显示地块这一列的信息
    public ArrayList displayFarmerBean() throws SQLException {
        List<FarmerBean> users = queryAllFarmerBean();
        arrayList = new ArrayList();
        for (FarmerBean user1 : users) {
            arrayList.add(user1.getdKnumber());
        }
        //  System.out.println(arrayList.toString());
        //  System.out.println(users.toString());
        return arrayList;
    }

    //显示 农户姓名 地块 种类信息
    public ArrayList<HashMap<String, Object>> displayFarmerNDT() {
        List<FarmerBean> queryBuilder = null;
        try {
            queryBuilder = mUserDAO.queryBuilder().selectColumns
                    ("name").selectColumns("dKNumber").selectColumns("type").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap<String, Object>> arrayList1 = new ArrayList<HashMap<String, Object>>();
        for (FarmerBean farmerBean : queryBuilder) {

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", farmerBean.getFarmerName());
            map.put("DkNumber", farmerBean.getdKnumber());
            map.put("type", farmerBean.getType());
            arrayList1.add(map);

        }
        // System.out.println(arrayList1.toString());
        return arrayList1;
    }

    /**
     * 删除全部
     */
    public void deleteAllFarmerBean() throws SQLException {
        mUserDAO.delete(queryAllFarmerBean());
    }

    /**
     * 按照指定的id 与 username 删除一项
     *
     * @param //id
     * @param username
     * @return 删除成功返回true ，失败返回false
     */
    public int deleteFarmerBean(String username) {
        try {
// 删除指定的信息，类似delete User where 'id' = id ;
            DeleteBuilder<FarmerBean, Integer> deleteBuilder = mUserDAO.deleteBuilder();
            deleteBuilder.where().eq("username", username);

            return deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 按照id查询user
     *
     * @param //id
     * @return
     */
    public FarmerBean searchFarmerBean(String name) {
        try {
// 查询的query 返回值是一个列表
// 类似 select * from User where 'username' = username;
            List<FarmerBean> users = mUserDAO.queryBuilder().where().eq("name", name).query();
            if (users.size() > 0)
                return users.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    FarmerBean farmerBean = new FarmerBean();

    public FarmerBean queryFarmerLine(String dk) {
        try {
// 查询的query 返回值是一个列表
// 类似 select * from User where 'username' = username;
            List<FarmerBean> users = mUserDAO.queryBuilder().where().eq("dKNumber", dk).query();

            return users.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
