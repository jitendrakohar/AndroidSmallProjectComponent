package com.example.insertiontesting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
   Context context;
    public DBHelper(Context context) {

        super(context, "Userdata.db", null, 1);
        this.context=context;
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table UserDetails (data TEXT,age NUMBER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j) {

        db.execSQL("drop Table if exists UserDetails");


    }

    public Boolean insertUserData(String data,int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", data);
        contentValues.put("age",age);
       long result = db.insert("Userdetails", null, contentValues);
        if (result == -1) {
            Log.e("Datainsertion", "insertUserData: Successful");
            Toast.makeText(context, "Insertion of data is failed", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            Log.e("Datainsertion", "insertUserData: Failed" );
            Toast.makeText(context, "Insertion of data is Failed", Toast.LENGTH_SHORT).show();
            return true;
        }
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
