package com.zbPro.seed.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
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
public class SeedDao {
    Context context;
    Dao seeddao;
    RuntimeExceptionDao mSeedDao;
    ArrayList arrayList;
    private DatabaseHelper helper;

    public SeedDao(Context context) {
        this.context = context;
        //mUserDAO = DatabaseHelper.getHelper(context).getUserDataDao();
        seeddao = DatabaseHelper.getHelper(context).getSeedsDao();
    }

    public Seed addSeed(Seed seed) throws SQLException {
        return (Seed) seeddao.createIfNotExists(seed);
    }

  /*  public Seed updataseed(Seed user) throws SQLException {
//此方法只更新不创建也不删除,更新后不会删除原来的数据，但是如果无数据则不更新
        seeddao.update(user);
        //此方法更新如果没有数据就创建创建也不删除
        //  seeddao.createOrUpdate(user);

        return user;
    }*/

  /*  public void createOrUpdateSeedBean(String seed) throws SQLException {
//此方法只更新不创建也不删除
// mUserDAO.update(user);
        //此方法更新如果没有数据就创建创建也不删除
        seeddao.createOrUpdate(seed);
    }
*/


 /*   // * 查询所有的信息

    public List<HashMap<String, Object>> queryAllSeedBean() {
        List<HashMap<String, Object>> users = null;
        users = mSeedDao.queryForAll();

        return users;
    }*/

   /* //显示所有信息
    public void displaySeedBean() {
        List<Seed> users = queryAllSeedBean();
        arrayList = new ArrayList();
        for (Seed user1 : users) {
            arrayList.add(user1.getSeed_id());
        }
        System.out.println(arrayList.toString());
    }*/

  /*  //显示地块这一列的信息
    public ArrayList displayseedDKBean() {
        List<HashMap<String, Object>> users = queryAllSeedBean();
        arrayList = new ArrayList();
        for (HashMap<String, Object> user1 : users) {
            arrayList.add(user1.get(""));
        }
        System.out.println(arrayList.toString());
        //  System.out.println(users.toString());
        return arrayList;
    }*/


    //查询并显示  农户姓名 地块 种类信息
    public ArrayList<HashMap<String, Object>> displaySeedNDTData() {
        List<Seed> queryBuilder = null;
        try {
            queryBuilder = seeddao.queryBuilder().selectColumns
                    ("framarName").selectColumns("dKNumber").selectColumns("type").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap<String, Object>> arrayList1 = new ArrayList<HashMap<String, Object>>();
        for (Seed seed : queryBuilder) {

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("framarName", seed.getFramarName());
            map.put("DkNumber", seed.getdKNumber());
            map.put("type", seed.getType());
            arrayList1.add(map);

        }
        // System.out.println(arrayList1.toString());
        return arrayList1;
    }


    //查询所有信息
    public ArrayList<HashMap<String, Object>> displaySeedAllData() {
        List<Seed> queryBuilder = null;
        try {
            queryBuilder = seeddao.queryBuilder().selectColumns
                    ("framarName").selectColumns("dKNumber").selectColumns("type").selectColumns("seedDate").selectColumns("father1")
                    .selectColumns("father2").selectColumns("mother")
                    .selectColumns("fatherUse").selectColumns("motherUse").selectColumns("beizhu").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap<String, Object>> arrayList1 = new ArrayList<HashMap<String, Object>>();
        //  LogBase.i(arrayList1.get(1).toString()+"dasda");
        for (Seed seed : queryBuilder) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("framarName", seed.getFramarName());
            map.put("type", seed.getType());
            map.put("seedDate", seed.getSeedDate());
            map.put("father1", seed.getFather1());
            map.put("father2", seed.getFather2());
            map.put("mother", seed.getMother());
            map.put("fatherUse", seed.getFatherUse());
            map.put("motherUse", seed.getMotherUse());
            map.put("beizhu", seed.getBeizhu());


            arrayList1.add(map);

        }
        // System.out.println(arrayList1.toString());
        return arrayList1;
    }


 /*   *//**
     * 按照指定的id 与 username 删除一项
     *
     * @param //id
     * @param dKNumber
     * @return 删除成功返回true ，失败返回false
     *//*
    public int deleteSeedBean(String dKNumber) {
        try {
// 删除指定的信息，类似delete User where 'id' = id ;
            DeleteBuilder<Seed, Integer> deleteBuilder = mSeedDao.deleteBuilder();
            deleteBuilder.where().eq("dKNumber", dKNumber);

            return deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }*/

   /* */

    /**
     * 按照id查询user
     *
     * @param //id
     * @return
     *//*
    private Seed searchSeedBean(String username) {
        try {
// 查询的query 返回值是一个列表
// 类似 select * from User where 'username' = username;
            List<Seed> users = mSeedDao.queryBuilder().where().eq("username", username).query();
            if (users.size() > 0)
                return users.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    //查询地块号
    public ArrayList displaySeedDKnumber() {
        List<Seed> queryBuilder = null;
        try {
            queryBuilder = seeddao.queryBuilder().selectColumns("dKNumber").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        for (Seed seed1 : queryBuilder) {
            arrayList.add(seed1.getdKNumber());
        }

        return arrayList;
    }

    //删除一行信息
    public void refreshSeedBeanRow(String dKNumber) throws SQLException {
        String Sql = "delete from Seed where dKNumber = ?";
        seeddao.executeRaw(Sql, new String[]{dKNumber});
    }


}

