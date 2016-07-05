package com.zbPro.seed.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.zbPro.seed.bean.CastrationBean;
import com.zbPro.seed.bean.FarmerBean;
import com.zbPro.seed.bean.Seed;
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
public class CastrationDao {
    Context context;
    Dao castrationDao;
    ArrayList arrayList;

    public CastrationDao(Context context) {
        this.context = context;
        //mUserDAO = DatabaseHelper.getHelper(context).getUserDataDao();
        castrationDao = DatabaseHelper.getHelper(context).getCastrationBeanDao();
    }

    public CastrationBean addCastrationBean(CastrationBean castrationBean) throws SQLException {
        return (CastrationBean) castrationDao.createIfNotExists(castrationBean);
    }

    //查询并显示  农户姓名 地块 种类信息
    public ArrayList<HashMap<String, Object>> displayCastrationBeanNDTData() {
        List<CastrationBean> queryBuilder = null;
        try {
            queryBuilder = castrationDao.queryBuilder().selectColumns
                    ("framarName").selectColumns("dKNumber").selectColumns("type").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap<String, Object>> arrayList1 = new ArrayList<HashMap<String, Object>>();
        for (CastrationBean castration : queryBuilder) {

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("framarName", castration.getFramarName());
            map.put("DkNumber", castration.getdKNumber());
            map.put("type", castration.getType());
            arrayList1.add(map);

        }
        // System.out.println(arrayList1.toString());
        return arrayList1;
    }

    //查询所有信息
    public ArrayList<HashMap<String, Object>> displayCastrationBeanAllData() {
        List<CastrationBean> queryBuilder = null;
        try {
            queryBuilder = castrationDao.queryBuilder().selectColumns
                    ("framarName").selectColumns("dKNumber").selectColumns("type").selectColumns("startTime").selectColumns("motherExtractTime")
                    .selectColumns("inspectTime").selectColumns("motherNoCastration")
                    .selectColumns("motherExtract").selectColumns("motherLoose").
                            selectColumns("fatherLoose").selectColumns("content").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap<String, Object>> arrayList1 = new ArrayList<HashMap<String, Object>>();
        //  LogBase.i(arrayList1.get(1).toString()+"dasda");
        for (CastrationBean castration : queryBuilder) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("framarName", castration.getFramarName());
            map.put("type", castration.getType());
            map.put("startTime", castration.getStartTime());
            map.put("motherExtractTime", castration.getMotherExtractTime());
            map.put("inspectTime", castration.getInspectTime());
            map.put("motherNoCastration", castration.getMotherNoCastration());
            map.put("motherExtract", castration.getMotherExtract());
            map.put("motherLoose", castration.getMotherLoose());
            map.put("fatherLoose", castration.getFatherLoose());
            map.put("content", castration.getContent());


            arrayList1.add(map);

        }
        // System.out.println(arrayList1.toString());
        return arrayList1;
    }

    //查询地块号
    public ArrayList displayCastrationBeanDKnumber() {
        List<CastrationBean> queryBuilder = null;
        try {
            queryBuilder = castrationDao.queryBuilder().selectColumns("dKNumber").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        for (CastrationBean castrationBean : queryBuilder) {
            arrayList.add(castrationBean.getdKNumber());
        }
        return arrayList;
    }

    //删除一行信息
    public void refreshCastrationBeanRow(String dKNumber) throws SQLException {
        String Sql = "delete from CastrationBean where dKNumber = ?";
        castrationDao.executeRaw(Sql, new String[]{dKNumber});
    }



/*
    public void createOrUpdateCastrationBean(CastrationBean castrationBean) throws SQLException {
//此方法只更新不创建也不删除
// mUserDAO.update(user);
        //此方法更新如果没有数据就创建创建也不删除
        castrationDao.createOrUpdate(castrationBean);
    }

    public void updataCastrationBean(CastrationBean castrationBean) throws SQLException {
//此方法只更新不创建也不删除
        castrationDao.update(castrationBean);
        //此方法更新如果没有数据就创建创建也不删除
        //mUserDAO.createOrUpdate(castrationBean);
    }*/

    /**
     * 查询所有的信息
     */
  /*  private List<CastrationBean> queryAllCastrationBean() throws SQLException {
        List<CastrationBean> users = castrationDao.queryForAll();
        return users;
    }

    //显示所有信息
    private void displayCastrationBean() throws SQLException {
        List<CastrationBean> users = queryAllCastrationBean();
        arrayList = new ArrayList();
        for (CastrationBean castrationBean : users) {
            arrayList.add(castrationBean.getId());

        }
        System.out.println(arrayList.toString());
    }


    *//**
     * 删除全部
     *//*
    private void deleteAllCastrationBean() throws SQLException {
        castrationDao.delete(queryAllCastrationBean());
    }

    *//**
     * 按照指定的id 与 username 删除一项
     *
     * @param //id
     * @param username
     * @return 删除成功返回true ，失败返回false
     *//*
    private int deleteFarmerBean(String username) {
        try {
// 删除指定的信息，类似delete User where 'id' = id ;
            DeleteBuilder<FarmerBean, Integer> deleteBuilder = castrationDao.deleteBuilder();
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
    private CastrationBean searchFarmerBean(String username) {
        try {
// 查询的query 返回值是一个列表
// 类似 select * from User where 'username' = username;
            List<CastrationBean> users = castrationDao.queryBuilder().where().eq("username", username).query();
            if (users.size() > 0)
                return users.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/


}

