package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int currentLayout = R.layout.activity_main_grid;
        setContentView(currentLayout);

        String toast_undo = getResources().getString(R.string.toast_undo);
        String the_checkbox_is_now = getResources().getString(R.string.toast_the_checkbox_is_now);
        String checkbox_on = getResources().getString(R.string.on);
        String checkbox_off = getResources().getString(R.string.off);

        Context context = getApplicationContext();

        // Comment the code below if you do not use activity_main_grid
        Button button = (Button) findViewById(R.id.buttonGrid1);
        EditText editText = (EditText) findViewById(R.id.editTextGrid1);
        TextView textView = (TextView) findViewById(R.id.textViewGrid1);
        CheckBox checkBox= (CheckBox) findViewById(R.id.checkBoxGrid1);

        // Uncomment the code below if you use activity_main_linear
//        Button button = (Button) findViewById(R.id.buttonLiner1);
//        EditText editText = (EditText) findViewById(R.id.editTextLinner1);
//        TextView textView = (TextView) findViewById(R.id.textViewLiner1);
//        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxLiner1);

        //  Uncomment the code below if you use activity_main_contraint
//        Button button = (Button) findViewById(R.id.buttonConst1);
//        EditText editText = (EditText) findViewById(R.id.editTextConst1);
//        TextView textView = (TextView) findViewById(R.id.textViewConst1);
//        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxConst1);

        button.setOnClickListener((click) -> {
            textView.setText(editText.getText());
            Toast.makeText(context, getResources().getString(R.string.toast_message), Toast.LENGTH_LONG)
                    .show();
        });

        checkBox.setOnCheckedChangeListener((CompoundButton cb, boolean isChecked) -> {
            String checkbox_status = isChecked ? checkbox_on : checkbox_off;
            String toast_message = the_checkbox_is_now + " " + checkbox_status;

            Snackbar.make(cb, toast_message, Snackbar.LENGTH_LONG)
                    .setAction( toast_undo, click -> cb.setChecked(!isChecked))
                    .show();
        });

    }
}