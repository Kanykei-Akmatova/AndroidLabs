package com.example.androidlabs;

import android.os.Bundle;

public class DadJokeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle dataToPass = getIntent().getExtras();

        DadJokeFragment dadJokeFragment = new DadJokeFragment();
        dadJokeFragment.setArguments(dataToPass);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, dadJokeFragment)
                .commit();
    }
}