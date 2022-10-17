package com.example.androidlabs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ToDoActivity";

    private final ArrayList<ToDoItem> toDoItemList = new ArrayList<>();
    private ToDoItemListAdapter adapter;
    SQLHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing the sql helper
        sqlHelper = new SQLHelper(this);
        sqlHelper.getWritableDatabase();

        EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.buttonAdd);
        Switch switchUrgent = findViewById(R.id.switchUrgent);

        button.setOnClickListener((click) -> {
            int urgent = switchUrgent.isChecked() ? 1 : 0;
            int id = sqlHelper.add(editText.getText().toString(), urgent);
            ToDoItem toDoItem = new ToDoItem(id, editText.getText().toString(), switchUrgent.isChecked());
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
                        sqlHelper.delete(toDoItemList.get(position).getId());
                        toDoItemList.remove(position);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    })
                    .setNegativeButton(R.string.alert_no, (dialog, arg) -> {
                    });
            alertDiBuilder.create().show();
        });

        loadToDoItems();
    }

    private void loadToDoItems() {
        Cursor cursor = sqlHelper.getAll();
        printCursor(cursor);

        if (cursor.moveToFirst()) {
            do {
                toDoItemList.add(
                        new ToDoItem(
                                cursor.getInt(0), cursor.getString(1), cursor.getInt(2))
                );
            } while (cursor.moveToNext());

            adapter.notifyDataSetChanged();
        }
    }

    private void printCursor(Cursor c) {
        Log.d(TAG, "The database version number: " + sqlHelper.getVersion());
        Log.d(TAG, "The number of columns in the cursor: " + c.getColumnCount());
        Log.d(TAG, "The names of the columns in the cursor: ");
        for (String columnName : c.getColumnNames()) {
            Log.d(TAG, "    column name: " + columnName);
        }
        Log.d(TAG, "The number of results in the cursor: " + c.getCount());
        Log.d(TAG, "Each row of results in the cursor: ");
        if (c.moveToFirst()) {
            do {
                Log.d(TAG, "    result: "
                        + c.getInt(0) + ", "
                        + c.getString(1) + ", "
                        + c.getInt(2));
            } while (c.moveToNext());
        }
    }
}