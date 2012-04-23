package net.jonathangardner;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Nation {
	public static String TAG = "Nation";
	public long population;

	public static void create(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE nations (" +
        "	population REAL NOT NULL DEFAULT 2" +
        ");");
	}
	
	public static void upgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading nations from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS nations");
        create(db);
    }
	
	public static Nation fetch(SQLiteDatabase db) {
        Cursor result = db.query(
        		false, 		// distinct
        		"nations", 	// table
        		new String[] { // columns
        				"population"
        		},
        		null, // selection
        		null, // selectionArgs
        		null, // groupBy
        		null, // having
        		null, // orderBy
        		"1"); // limit
        result.moveToFirst();
        try {
        	while (!result.isAfterLast()) {
        		return Nation.fromCursor(result);
        	}
        } finally {
        	result.close();
        }
        return _insert(db);
	
	}

	public static Nation fromCursor(Cursor cursor) {
		return new Nation(cursor.getLong(0));
		
	}

    private static Nation _insert(SQLiteDatabase db) {
    	// Delete any existing nation.
    	db.delete("nations", null, null);
    	
    	// Create a new one with population 2.
        ContentValues args = new ContentValues();
        args.put("population", 2);
        
    	db.insert("nations", null, args);
		return new Nation(2);
	}


	Nation(long population_) {
		population = population_;
	}
	
	public boolean update(SQLiteDatabase db) {
        ContentValues args = new ContentValues();
        args.put("population", population);

        return db.update("nations", args, null, null) > 0;		
	}
}
