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

        EditText editText = (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.buttonAdd);
        Switch switchUrgent = (Switch) findViewById(R.id.switchUrgent);

        button.setOnClickListener((click) -> {
            ToDoItem toDoItem = new ToDoItem(editText.getText().toString(), switchUrgent.isChecked());
            toDoItemList.add(toDoItem);
            editText.setText("");
            adapter.notifyDataSetChanged();
        });

        ListView listView = (ListView) findViewById(R.id.myListView);
        listView.setAdapter(adapter = new ToDoItemListAdapter());
        listView.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog.Builder alertDiBuilder = new AlertDialog.Builder(this);
            alertDiBuilder.setTitle(R.string.alert_title)
                    .setMessage(getString(R.string.alert_message) + position)
                    .setPositiveButton(R.string.alert_yes, (click, arg) -> {
                        Toast.makeText(getApplicationContext(),"Index " + position,
                        Toast.LENGTH_SHORT).show();
                        toDoItemList.remove(position);
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton(R.string.alert_no, (click, arg) -> {

                    });
            alertDiBuilder.create().show();
        });
    }

    private class ToDoItemListAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<ToDoItem> items;

        public ToDoItemListAdapter() {
        }

        public ToDoItemListAdapter(Context context, ArrayList<ToDoItem> items) {
            this.context = context;
            this.items = items;
        }

        @Override
        public int getCount() {
            return toDoItemList.size();
        }

        @Override
        public Object getItem(int index) {
            return toDoItemList.get(index);
        }

        @Override
        public long getItemId(int index) {
            return 0;
        }

        @Override
        public View getView(int index, View oldView, ViewGroup parent) {
            View newView = oldView;
            LayoutInflater inflater = getLayoutInflater();

            if (newView == null) {
                newView = inflater.inflate(R.layout.row_layout, parent, false);
            }

            ToDoItem toDoItem = (ToDoItem) getItem(index);;

            TextView textView = newView.findViewById(R.id.testViewRow);
            textView.setText(toDoItem.getToDo());

            if(toDoItem.getUrgent()) {
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundColor(Color.RED);
            }

            return newView;
        }
    }
}