package com.ghllz.travel.util;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.ghllz.travel.bean.Cover;
import com.ghllz.travel.bean.Place;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DBHelper extends OrmLiteSqliteOpenHelper{

	
	//利用单例的方式获取DBHelper对象
	private static DBHelper dbhelper;
	
	//数据库名
	private static final String DB_NAME = "travel.db";
	
	//数据库版本
    private static final int DB_VERSION = 1;

	public static DBHelper getInstance(Context context){
        if(dbhelper==null){
            synchronized (DBHelper.class) {
                if(dbhelper==null){
                    dbhelper = new DBHelper(context);
                }
            }
        }
        return dbhelper;
    }
	
//	public DBHelper(Context context, String databaseName,
//			CursorFactory factory, int databaseVersion) {
//		super(context, "cover.db", null, 1);
//	}
	
	private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionsource) {
		try {//建表
            TableUtils.createTableIfNotExists(connectionsource, Cover.class);
            TableUtils.createTableIfNotExists(connectionsource, Place.class);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionsource,
			int oldVersion, int newVersion) {
		try {
            TableUtils.dropTable(connectionsource, Cover.class, true);//删表
            TableUtils.dropTable(connectionsource, Place.class, true);//删表
            onCreate(database, connectionsource);//建表
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
}
