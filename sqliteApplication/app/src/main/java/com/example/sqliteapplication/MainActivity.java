package com.example.sqliteapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 EditText name,contact,dob;
 Button insert,update,delete,view;
 DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        contact=findViewById(R.id.Contact);
        dob=findViewById(R.id.dob);
        insert=findViewById(R.id.btnInsert);
        delete=findViewById(R.id.btnDelete);
        update=findViewById(R.id.btnUpdate);
        view=findViewById(R.id.btnView);
        view=findViewById(R.id.btnView);
        db=new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT=name.getText().toString();
                String contactTXT=contact.getText().toString();
                String dobTxt=contact.getText().toString();
                Boolean checkingInsertdata=db.insertUserData(nameTXT,contactTXT,dobTxt);
                if(checkingInsertdata)
                    Toast.makeText(MainActivity.this, "New Entery is inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Oops, Entry is not inserted", Toast.LENGTH_SHORT).show();

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT=name.getText().toString();
                String contactTXT=contact.getText().toString();
                String dobTxt=contact.getText().toString();
                Boolean checkingUpdatedata=db.updateUserData(nameTXT,contactTXT,dobTxt);
                if(checkingUpdatedata)
                    Toast.makeText(MainActivity.this, "Entery is updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Oops, Entry not updated", Toast.LENGTH_SHORT).show();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT=name.getText().toString();

                Boolean checkingDeletedata=db.deleteData(nameTXT);
                if(checkingDeletedata)
                    Toast.makeText(MainActivity.this, "Entery is deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Oops, Entry not deleted", Toast.LENGTH_SHORT).show();

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=db.getData();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name: "+res.getString(0)+"\n");
                    buffer.append("contact :"+res.getString(1)+"\n");
                    buffer.append("Date of Birth :"+res.getString(2)+"\n\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}