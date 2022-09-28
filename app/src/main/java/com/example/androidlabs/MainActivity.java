package com.example.androidlabs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String MY_PREF = "MyPref";
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();

        EditText editText = (EditText) findViewById(R.id.editTextName);
        SharedPreferences sharedPref = getApplicationContext()
                .getSharedPreferences(MY_PREF, MODE_PRIVATE);
        String name = sharedPref.getString("Email", "");
        editText.setText(name);

        Button button = (Button) findViewById(R.id.buttonNext);
        button.setOnClickListener((click) -> {
            int LAUNCH_NAME_ACTIVITY = 1;

            Intent nextPage  = new Intent(this, NameActivity.class);
            nextPage.putExtra("name",editText.getText().toString());

            startActivityForResult(nextPage , LAUNCH_NAME_ACTIVITY);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        EditText editText = (EditText) findViewById(R.id.editTextName);

        SharedPreferences sharedPref = getApplicationContext()
                .getSharedPreferences(MY_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", editText.getText().toString());
        editor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            finish();
        }
    }
}