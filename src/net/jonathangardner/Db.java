package net.jonathangardner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Db {
	private static final String TAG = "Db";
	
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase db;

    private static final String DATABASE_NAME = "colinate.db";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        	Nation.create(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        	Nation.upgrade(db, oldVersion, newVersion);
        }
    }


    public Db(Context ctx) {
        this.mCtx = ctx;
    }


    public Db open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        db = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }




    public Nation fetchNation() {
    	return Nation.fetch(db);
    }

    public boolean update(Nation nation) {
    	return nation.update(db);
    }
}
