package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        TextView textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewWelcome.setText("Welcome " + name + "!");

        Button buttonDoNotCall = findViewById(R.id.buttonDoNotCall);
        buttonDoNotCall.setOnClickListener((click) -> {
            setResult(0);
            finish();
        });

        Button buttonThankYou = findViewById(R.id.buttonThankYou);
        buttonThankYou.setOnClickListener((click) -> {
            setResult(1);
            finish();
        });
    }
}