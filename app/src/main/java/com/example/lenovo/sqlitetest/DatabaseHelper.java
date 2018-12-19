package com.example.lenovo.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "Dishes";
    private static final String COL0 = "Fid";
    private static final String COL1 = "Title";
    private static final String COL2 = "Restaurant";
    private static final String COL3 = "Type";
    private static final String COL4 = "Price";
    private static final String COL5 = "Supplier";

    static final String DB_NAME = "FOOD.DB";

    // database version
    static final int DB_VERSION = 1;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super( context, DB_NAME, null, DB_VERSION );
    }

//    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
//        super( context, name, factory, version, errorHandler );
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ( " + COL0 + " INTEGER PRIMARY KEY AUTOINCREMENT , " + COL1 +" TEXT , "+ COL2 +" TEXT , " + COL3 +" TEXT , "+COL4+" INTEGER , "+COL5+" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String title,String restaurant,String Type,Integer Price,String Supplier) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, title);
        contentValues.put(COL2, restaurant);
        contentValues.put(COL3, Type);
        contentValues.put(COL4, Price);
        contentValues.put(COL5, Supplier);

        Log.d(TAG, contentValues.toString());

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


}
