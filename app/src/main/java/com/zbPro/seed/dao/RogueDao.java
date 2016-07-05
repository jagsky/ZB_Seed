package com.zbPro.seed.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.zbPro.seed.bean.FarmerBean;
import com.zbPro.seed.bean.RogueBean;
import com.zbPro.seed.bean.Seed;
import com.zbPro.seed.collector.LogBase;
import com.zbPro.seed.db.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 * ClassName ：com.zbPro.seed.dao
 * 作用：
 */
public class RogueDao {
    Context context;
    Dao rogueDao;
    ArrayList arrayList;

    public RogueDao(Context context) {
        this.context = context;
        rogueDao = DatabaseHelper.getHelper(context).getRogueBeanDao();
    }

    public RogueBean addRogueBean(RogueBean rogueBean) throws SQLException {
        return (RogueBean) rogueDao.createIfNotExists(rogueBean);
    }

   /* public void updataRogueBean(RogueBean rogueBean) throws SQLException {
        //此方法只更新不创建也不删除
        mUserDAO.update(rogueBean);
        //此方法更新如果没有数据就创建创建也不删除
        //  mUserDAO.createOrUpdate(rogueBean);
    }

    public void createOrUpdateBean(RogueBean rogueBean) throws SQLException {
        //此方法只更新不创建也不删除
        // mUserDAO.update(user);
        //此方法更新如果没有数据就创建创建也不删除
        mUserDAO.createOrUpdate(rogueBean);
    }*/

    //查询并显示  农户姓名 地块 种类信息
    public ArrayList<HashMap<String, Object>> displayRogueNDTData() {
        List<RogueBean> queryBuilder = null;
        try {
            queryBuilder = rogueDao.queryBuilder().selectColumns
                    ("framarName").selectColumns("dKNumber").selectColumns("type").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap<String, Object>> arrayList1 = new ArrayList<HashMap<String, Object>>();
        for (RogueBean rogueBean : queryBuilder) {

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("framarName", rogueBean.getFramarName());
            map.put("DkNumber", rogueBean.getdKNumber());
            map.put("type", rogueBean.getType());
            arrayList1.add(map);

        }
        // System.out.println(arrayList1.toString());
        return arrayList1;
    }

    //查询所有信息
    public ArrayList<HashMap<String, Object>> displayRogueAllData() {
        List<RogueBean> queryBuilder = null;
        try {
            queryBuilder = rogueDao.queryBuilder().selectColumns
                    ("framarName").selectColumns("dKNumber").selectColumns("type").selectColumns("time").selectColumns("rowFather")
                    .selectColumns("rowMothers").selectColumns("lineWidth")
                    .selectColumns("lineRatio").selectColumns("comeOutFather").selectColumns("conmeOutMother")
                    .selectColumns("impurties").selectColumns("fertility").selectColumns("beiZhu").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap<String, Object>> arrayList1 = new ArrayList<HashMap<String, Object>>();
        //  LogBase.i(arrayList1.get(1).toString()+"dasda");
        for (RogueBean rogueBean : queryBuilder) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("framarName", rogueBean.getFramarName());
            map.put("type", rogueBean.getType());
            map.put("time", rogueBean.getTime());
            map.put("rowFather", rogueBean.getRowFather());
            map.put("rowMothers", rogueBean.getRowMothers());
            map.put("lineWidth", rogueBean.getLineWidth());
            map.put("lineRatio", rogueBean.getLineRatio());
            map.put("comeOutFather", rogueBean.getComeOutFather());
            map.put("conmeOutMother", rogueBean.getConmeOutMother());
            map.put("impurties", rogueBean.getImpurties());
            map.put("fertility", rogueBean.getFertility());
            map.put("beiZhu", rogueBean.getBeiZhu());


            arrayList1.add(map);

        }
        // System.out.println(arrayList1.toString());
        return arrayList1;
    }

    //查询地块号
    public ArrayList displayRogueDKnumber() {
        List<RogueBean> queryBuilder = null;
        try {
            queryBuilder = rogueDao.queryBuilder().selectColumns("dKNumber").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        for (RogueBean rogueBean : queryBuilder) {
            arrayList.add(rogueBean.getdKNumber());
        }
        LogBase.i(arrayList.toString());
        return arrayList;
    }

    //删除一行信息
    public void refreshRogueBeanRow(String dKNumber) throws SQLException {
        String Sql = "delete from RogueBean where dKNumber = ?";
        rogueDao.executeRaw(Sql, new String[]{dKNumber});
    }


  /*  *//**
     * 查询所有的信息
     *//*
    private List<RogueBean> queryAllRogueBean() throws SQLException {
        List<RogueBean> users = rogueDao.queryForAll();
        return users;
    }

    //显示所有信息
    private void displayRogueBean() throws SQLException {
        List<RogueBean> users = queryAllRogueBean();
        arrayList = new ArrayList();
        for (RogueBean user1 : users) {
            arrayList.add(user1.getId());
        }
        System.out.println(arrayList.toString());
    }


    *//**
     * 删除全部
     *//*
    private void deleteAllRogueBean() throws SQLException {
        rogueDao.delete(queryAllRogueBean());
    }

    *//**
     * 按照指定的id 与 username 删除一项
     *
     * @param //id
     * @param username
     * @return 删除成功返回true ，失败返回false
     *//*
    private int deleteRogueBean(String username) {
        try {
// 删除指定的信息，类似delete User where 'id' = id ;
            DeleteBuilder<RogueBean, Integer> deleteBuilder = rogueDao.deleteBuilder();
            deleteBuilder.where().eq("username", username);

            return deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    *//**
     * 按照id查询user
     *
     * @param //id
     * @return
     *//*
    private RogueBean searchRogueBean(String username) {
        try {
// 查询的query 返回值是一个列表
// 类似 select * from User where 'username' = username;
            List<RogueBean> users = rogueDao.queryBuilder().where().eq("username", username).query();
            if (users.size() > 0)
                return users.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/


}

