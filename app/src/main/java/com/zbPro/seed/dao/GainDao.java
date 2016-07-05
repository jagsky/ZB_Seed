package com.zbPro.seed.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.zbPro.seed.bean.CastrationBean;
import com.zbPro.seed.bean.FarmerBean;
import com.zbPro.seed.bean.GainBean;
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
public class GainDao {
    Context context;
    Dao gainDAO;
    ArrayList arrayList;

    public GainDao(Context context) {
        this.context = context;
        //mUserDAO = DatabaseHelper.getHelper(context).getUserDataDao();
        gainDAO = DatabaseHelper.getHelper( context ).getGainBeanDao();
    }

    //查询并显示  农户姓名 地块 种类信息
    public ArrayList<HashMap<String, Object>> displayGainBeanNDTData() {
        List<GainBean> queryBuilder = null;
        try {
            queryBuilder = gainDAO.queryBuilder().selectColumns
                    ( "framarName" ).selectColumns( "dKNumber" ).selectColumns( "type" ).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap<String, Object>> arrayList1 = new ArrayList<HashMap<String, Object>>();
        for (GainBean gainBean : queryBuilder) {

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put( "framarName", gainBean.getFramarName() );
            map.put( "DkNumber", gainBean.getdKNumber() );
            map.put( "type", gainBean.getType() );
            arrayList1.add( map );

        }
        return arrayList1;
    }

    public GainBean addGainBean(GainBean gainBean) throws SQLException {
        return (GainBean) gainDAO.createIfNotExists( gainBean );
    }

    //查询所有信息
    public ArrayList<HashMap<String, Object>> displayGainBeanAllData() {
        List<GainBean> queryBuilder = null;
        try {
            //selectColumns( "beizhu" ).
            queryBuilder = gainDAO.queryBuilder().selectColumns
                    ( "framarName" ).selectColumns( "dKNumber" ).selectColumns( "type" ).
                    selectColumns( "motherNO" ).selectColumns( "singlePlant" )
                    .selectColumns( "thousand" ).selectColumns( "fatherExciseStart" )
                    .selectColumns( "fatherExciseStop" ).selectColumns( "gainTime" ).
                            selectColumns( "gainOutput" ).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<HashMap<String, Object>> arrayList1 = new ArrayList<HashMap<String, Object>>();
        //  LogBase.i(arrayList1.get(1).toString()+"dasda");
        for (GainBean gainBean : queryBuilder) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put( "framarName", gainBean.getFramarName() );
            map.put( "type", gainBean.getType() );
            map.put( "motherNO", gainBean.getMotherNO() );
            map.put( "singlePlant", gainBean.getSinglePlant() );
            map.put( "thousand", gainBean.getThousand() );
            map.put( "fatherExciseStart", gainBean.getFatherExciseStart() );
            map.put( "fatherExciseStop", gainBean.getFatherExciseStop() );
            map.put( "gainTime", gainBean.getGainTime() );
            map.put( "gainOutput", gainBean.getGainOutput() );
           // map.put( "beizhu", gainBean.getBeizhu() );


            arrayList1.add( map );

        }
        // System.out.println(arrayList1.toString());
        return arrayList1;
    }


    //查询地块号
    public ArrayList displayGainBeanDKnumber() {
        List<GainBean> queryBuilder = null;
        try {
            queryBuilder = gainDAO.queryBuilder().selectColumns( "dKNumber" ).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        for (GainBean gainBean : queryBuilder) {
            arrayList.add( gainBean.getdKNumber() );
        }
        return arrayList;
    }

    //删除一行信息
    public void refreshGainBeanRow(String dKNumber) throws SQLException {
        String Sql = "delete from GainBean where dKNumber = ?";
        gainDAO.executeRaw( Sql, new String[]{dKNumber} );
    }


    public void updataGainBean(GainBean gainBean) throws SQLException {
//此方法只更新不创建也不删除
        gainDAO.update( gainBean );
        //此方法更新如果没有数据就创建创建也不删除
        // mUserDAO.createOrUpdate(gainBean);
    }

    public void createOrUpdateBean(GainBean gainBean) throws SQLException {
//此方法只更新不创建也不删除
// mUserDAO.update(user);
        //此方法更新如果没有数据就创建创建也不删除
        gainDAO.createOrUpdate( gainBean );
    }

    /**
     * 查询所有的信息
     */
    private List<GainBean> queryAllGainBean() throws SQLException {
        List<GainBean> users = gainDAO.queryForAll();
        return users;
    }

    //显示所有信息
    private void displayGainBean() throws SQLException {
        List<GainBean> users = queryAllGainBean();
        arrayList = new ArrayList();
        for (GainBean user1 : users) {
            arrayList.add( user1.getId() );
        }
        System.out.println( arrayList.toString() );
    }


    /**
     * 删除全部
     */
    private void deleteAllFarmerBean() throws SQLException {
        gainDAO.delete( queryAllGainBean() );
    }

    /**
     * 按照指定的id 与 username 删除一项
     *
     * @param //id
     * @param username
     * @return 删除成功返回true ，失败返回false
     */
    private int deleteFarmerBean(String username) {
        try {
// 删除指定的信息，类似delete User where 'id' = id ;
            DeleteBuilder<GainBean, Integer> deleteBuilder = gainDAO.deleteBuilder();
            deleteBuilder.where().eq( "username", username );

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
    private GainBean searchFarmerBean(String username) {
        try {
// 查询的query 返回值是一个列表
// 类似 select * from User where 'username' = username;
            List<GainBean> users = gainDAO.queryBuilder().where().eq( "username", username ).query();
            if (users.size() > 0)
                return users.get( 0 );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
