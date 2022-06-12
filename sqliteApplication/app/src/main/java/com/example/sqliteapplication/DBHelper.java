package com.example.sqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table UserDetails (name TEXT primary key,Contact TEXT,dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j) {

        db.execSQL("drop Table if exists UserDetails");


    }

    public Boolean insertUserData(String name, String contact, String dob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        long result = db.insert("Userdetails", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean updateUserData(String name, String contact, String dob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        Cursor cursor = db.rawQuery("Select * from UserDetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {

            long result = db.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1)
                return false;
            else
                return true;
        } else return false;
    }

    public Boolean deleteData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from UserDetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {

            long result = db.delete("Userdetails", "name=?", new String[]{name});
            if (result == -1)
                return false;
            else
                return true;
        } else return false;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from UserDetails",null);
        return cursor;
    }


}
