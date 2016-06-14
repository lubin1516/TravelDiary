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

	
	//���õ����ķ�ʽ��ȡDBHelper����
	private static DBHelper dbhelper;
	
	//���ݿ���
	private static final String DB_NAME = "travel.db";
	
	//���ݿ�汾
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
		try {//����
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
            TableUtils.dropTable(connectionsource, Cover.class, true);//ɾ��
            TableUtils.dropTable(connectionsource, Place.class, true);//ɾ��
            onCreate(database, connectionsource);//����
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
}
