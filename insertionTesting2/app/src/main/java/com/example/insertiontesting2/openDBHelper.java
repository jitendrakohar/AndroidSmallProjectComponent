package com.example.insertiontesting2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class openDBHelper extends SQLiteOpenHelper {
    Context context;
    public openDBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE whatsapp(Data TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("drop table if exists whatsapp");
    }

    public boolean insertData(String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", data);
        long result=db.insert("whatsapp",null,contentValues);
        if(result==-1) {
            Toast.makeText(context, "insertion failed", Toast.LENGTH_SHORT).show();
        return  false;
        }
            else
        {
            Toast.makeText(context, "insertion succesful", Toast.LENGTH_SHORT).show();
        return true;
        }
    }
}
