package com.ghllz.travel.util;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.ghllz.travel.bean.Cover;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DBHelper extends OrmLiteSqliteOpenHelper{

	
	//利用单例的方式获取DBHelper对象
	private static DBHelper dbhelper;

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
        super(context, "cover.db", null, 1);
    }


	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		try {
            TableUtils.createTableIfNotExists(arg1, Cover.class);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		try {
            TableUtils.dropTable(arg1, Cover.class, true);//删表
            onCreate(arg0, arg1);//建表
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
}
