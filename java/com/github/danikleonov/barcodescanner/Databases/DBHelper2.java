package com.github.danikleonov.barcodescanner.Databases;

import android.app.LauncherActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.github.danikleonov.barcodescanner.Model.ListItem;
import com.github.danikleonov.barcodescanner.Model.ListItem2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DBHelper2 extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "mytable2";
    public static final String DATABASE_NAME = "qrdb2.db";

    public static final String COL_1 = "id";
    public static final String COL_2 = "code";
    public static final String COL_3 = "type";
    public static final String COL_4 = "clas";
    public static final String COL_5 = "descr";


    public DBHelper2 (@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "code TEXT, type TEXT, clas TEXT, descr TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String code, String type, String clas, String descr){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, code);
        contentValues.put(COL_3, type);
        contentValues.put(COL_4, clas);
        contentValues.put(COL_5, descr);

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;

    }

    public ArrayList<ListItem2> getAllInformation(){
        ArrayList<ListItem2> arrayList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from "+TABLE_NAME,null);

        if(cursor != null){
            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                String code = cursor.getString(1);
                String type = cursor.getString(2);
                String clas = cursor.getString(3);
                String descr = cursor.getString(4);

                ListItem2 listItem = new ListItem2(id,code,type,clas,descr);

                arrayList.add(listItem);
            }
        }

        cursor.close();
        return arrayList;
    }

   /* public String toFindOne(String giftedCode){
        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL("SELECT descr FROM mytable2 WHERE code = ? ", giftedCode);


    }*/

    public void dropTable(){
        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(database);
    }

    public String getDescr(String scannedCode, String scannedType) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;
        String descr = null;
        try {
            cursor = database.rawQuery("SELECT descr FROM mytable2 WHERE code=? AND type=?", new String[] {scannedCode + "",scannedType + ""});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                descr = cursor.getString(cursor.getColumnIndex("descr"));
            }
            return descr;
        }finally {
            cursor.close();
        }
    }


}


