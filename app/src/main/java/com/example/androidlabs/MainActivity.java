package com.example.androidlabs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ToDoItem> toDoItemList = new ArrayList<>();
    private ToDoItemListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.buttonAdd);
        Switch switchUrgent = findViewById(R.id.switchUrgent);

        button.setOnClickListener((click) -> {
            ToDoItem toDoItem = new ToDoItem(editText.getText().toString(), switchUrgent.isChecked());
            toDoItemList.add(toDoItem);
            editText.setText("");
            adapter.notifyDataSetChanged();
        });

        ListView listView = findViewById(R.id.myListView);
        listView.setAdapter(adapter = new ToDoItemListAdapter(this, toDoItemList));
        listView.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog.Builder alertDiBuilder = new AlertDialog.Builder(this);
            alertDiBuilder.setTitle(R.string.alert_title)
                    .setMessage(getString(R.string.alert_message) + (position + 1))
                    .setPositiveButton(R.string.alert_yes, (dialog, arg) -> {
                        toDoItemList.remove(position);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    })
                    .setNegativeButton(R.string.alert_no, (dialog, arg) -> {
                    });
            alertDiBuilder.create().show();
        });
    }
}