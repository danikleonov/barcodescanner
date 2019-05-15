package com.github.danikleonov.barcodescanner.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.github.danikleonov.barcodescanner.Model.ListItem;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "mytable";
    public static final String DATABASE_NAME = "qrdb.db";

    public static final String COL_1 = "id";
    public static final String COL_2 = "code";
    public static final String COL_3 = "type";

    public DBHelper (@Nullable Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "code TEXT, type TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String code, String type){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, code);
        contentValues.put(COL_3, type);

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;

    }

    public ArrayList<ListItem> getAllInformation(){
        ArrayList<ListItem> arrayList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from "+TABLE_NAME,null);

        if(cursor != null){
            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                String code = cursor.getString(1);
                String type = cursor.getString(2);

                ListItem listItem = new ListItem(id,code,type);

                arrayList.add(listItem);
            }
        }

        cursor.close();
        return arrayList;
    }

    public void deleteRow(int value){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+ "=WHERE "+COL_1+"='"+value+"'");
    }

    public void dropTable(){
        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(database);


    }

}