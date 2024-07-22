package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDBHelper myDBHelper = new MyDBHelper(MainActivity.this);
        SQLiteDatabase db =  myDBHelper.getWritableDatabase();
//        SQLiteDatabase db = openOrCreateDatabase("dbdemo", MODE_PRIVATE, null);
//        try {
//            String sql = "CREATE TABLE users (id INTEGER PRIMARY KEY, first_name TEXT, last_name TEXT )";
//            db.execSQL(sql);
//        } catch (Exception e){
//        }

//        ContentValues data = new ContentValues();
//        data.put("id", 1);
//        data.put("first_name", "Nguyen Van");
//        data.put("last_name", "A");
//        db.insert("users", null, data);

        Cursor c = db.query("users", null, null, null, null, null, null);
        c.moveToNext();
        String data = "";
        while (c.isAfterLast() == false)
        {
            data = c.getString(0)+" - "+c.getString(1)+" - "+c.getString(2);
            c.moveToNext();
        }
        c.close();
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}