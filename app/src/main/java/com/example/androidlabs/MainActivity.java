package com.example.androidlabs;

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

    private final String MY_PREF = "MyPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();

        // Comment the code below if you do not use activity_main_grid
        Button button = (Button) findViewById(R.id.buttonNext);
        EditText editText = (EditText) findViewById(R.id.editTextName);

        SharedPreferences sharedPref = getApplicationContext()
                .getSharedPreferences(MY_PREF, MODE_PRIVATE);
        String name = sharedPref.getString("Email", "");
        editText.setText(name);

        button.setOnClickListener((click) -> {
            int LAUNCH_NAME_ACTIVITY = 1;
            Intent nextPage  = new Intent(this, NameActivity.class);
            startActivityForResult(nextPage , LAUNCH_NAME_ACTIVITY);
//            Toast.makeText(context, getResources().getString(R.string.toast_message), Toast.LENGTH_LONG)
//                    .show();
        });

//        checkBox.setOnCheckedChangeListener((CompoundButton cb, boolean isChecked) -> {
//            String checkbox_status = isChecked ? checkbox_on : checkbox_off;
//            String toast_message = the_checkbox_is_now + " " + checkbox_status;
//
//            Snackbar.make(cb, toast_message, Snackbar.LENGTH_LONG)
//                    .setAction( toast_undo, click -> cb.setChecked(!isChecked))
//                    .show();
//        });
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
}