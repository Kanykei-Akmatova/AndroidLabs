package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DadJoke extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_dad_joke);

        Bundle dataToPass = getIntent().getExtras();

        DadJokeFragment dadJokeFragment = new DadJokeFragment();
        dadJokeFragment.setArguments(dataToPass);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, dadJokeFragment)
                .commit();
    }
}