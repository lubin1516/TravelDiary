package com.ghllz.travel.util;

import java.sql.SQLException;
import java.util.List;

import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.bean.Place;
import com.j256.ormlite.dao.Dao;

import android.content.Context;

public class DBUtil {
	

	DBHelper dbhelper;
    private Dao<Cover, String> dao_cover;
    private Dao<Place, String> dao_place;

    public DBUtil(Context context){
        dbhelper = DBHelper.getInstance(context);
        try {
        	dao_cover = dbhelper.getDao(Cover.class);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void add(Cover bean){
        try {
        	dao_cover.create(bean);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void remove(Cover bean){
        try {
        	dao_cover.delete(bean);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Cover> query(){
        try {
            return dao_cover.queryForAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
//          throw new RuntimeException("查询时产生异常");
            return null;//throw和return随便选一个
        }
    }
    //参数为页数，1个页数等于10个cover,这个方法要是通过page参数得到相应的
    //cover集合,也就说这些cover集合里包含page这个属性
    public List<Cover> query(int page){
        return null;
    }
}
