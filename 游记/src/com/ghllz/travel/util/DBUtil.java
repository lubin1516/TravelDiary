package com.ghllz.travel.util;

import java.sql.SQLException;
import java.util.List;

import com.ghllz.travel.bean.Cover;
import com.j256.ormlite.dao.Dao;

import android.content.Context;

public class DBUtil {

	DBHelper dbhelper;
    private Dao<Cover, String> dao;

    public DBUtil(Context context){
        dbhelper = DBHelper.getInstance(context);
        try {
            dao = dbhelper.getDao(Cover.class);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void add(Cover bean){
        try {
            dao.create(bean);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void remove(Cover bean){
        try {
            dao.delete(bean);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Cover> query(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
//          throw new RuntimeException("查询时产生异常");
            return null;//throw和return随便选一个
        }
    }
}
