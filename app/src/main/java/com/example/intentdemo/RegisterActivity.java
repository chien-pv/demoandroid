package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent intent = getIntent();
        String str = intent.getStringExtra("email");
        EditText email = findViewById(R.id.email);
        email.setText(str);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentnew = new Intent(RegisterActivity.this, HomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("email", "abc@gmial.com");
                bundle.putString("uname", "vana");
                intentnew.putExtras(bundle);
                startActivity(intentnew);
            }
        });
    }
}