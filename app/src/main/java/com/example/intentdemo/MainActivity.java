package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

public class MainActivity extends AppCompatActivity {
    String lichsu="";
    private String filename = "MySampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;


    @Override
    protected void onPause() {
        super.onPause();
//        SharedPreferences sharedPref = getSharedPreferences("MyPrefls", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString("kq", lichsu);
//        editor.commit();
        myExternalFile = new File(getExternalFilesDir(filepath), filename);
//
        try {
            FileOutputStream outputStream = new FileOutputStream(myExternalFile);
//          FileOutputStream outputStream = openFileOutput("data.txt", Context.MODE_PRIVATE);
            outputStream.write(lichsu.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Err Save file", Toast.LENGTH_SHORT).show();
        }

}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        EditText soa = findViewById(R.id.nhapa);
        EditText sob = findViewById(R.id.nhapb);
        TextView kq = findViewById(R.id.kq);
        TextView ls = findViewById(R.id.lichsu);
        Button clearBtn = findViewById(R.id.clear);

//        SharedPreferences sharedPref = getSharedPreferences("MyPrefls", Context.MODE_PRIVATE);
//        String shls = sharedPref.getString("kq", "");
//        if (shls!=""){
//            ls.setText(shls);
//            shls = shls.trim();
//            shls = shls+ "\n";
//            lichsu  +=shls;
//        }
        myExternalFile = new File(getExternalFilesDir(filepath), filename);
        try {
//            FileInputStream fileInputStream = openFileInput("data.txt");
            FileInputStream fileInputStream = new FileInputStream(myExternalFile);

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            fileInputStream.close();
            ls.setText(stringBuilder.toString());
            Toast.makeText(this, "Content read from file successfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading from file.", Toast.LENGTH_SHORT).show();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(soa.getText().toString());
                int b = Integer.parseInt(sob.getText().toString());
                int sum = a + b;
                String strkq = a+" + "+b+" = "+sum + "\n";
                kq.setText(strkq);
                lichsu = lichsu.concat(strkq);
                ls.setText(lichsu);
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getSharedPreferences("MyPrefls", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove("ls");
                lichsu = "";
                editor.apply();
                ls.setText("");
            }
        });

    }
}