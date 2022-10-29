package com.example.androidlabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ItemListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<StarWarItem> starWarItems;

    public ItemListAdapter(Context context, ArrayList<StarWarItem> items) {
        this.context = context;
        this.starWarItems = items;
    }

    public ItemListAdapter() {
    }

    @Override
    public int getCount() {
        return starWarItems.size();
    }

    @Override
    public Object getItem(int index) {
        return starWarItems.get(index);
    }

    @Override
    public long getItemId(int index) {
        return 0;
    }

    @Override
    public View getView(int index, View oldView, ViewGroup parent) {
        View newView = oldView;
        LayoutInflater inflater = LayoutInflater.from(context);

//        if (newView == null) {
//            newView = inflater.inflate(R.layout.row_layout, parent, false);
//        }
//
//        ToDoItem toDoItem = (ToDoItem) getItem(index);
//
//        TextView textView = newView.findViewById(R.id.testViewRow);
//        textView.setText(toDoItem.getToDo());
//
//        if(toDoItem.getUrgent()) {
//            textView.setTextColor(Color.WHITE);
//            textView.setBackgroundColor(Color.RED);
//        } else {
//            textView.setTextColor(Color.BLACK);
//            textView.setBackgroundColor(Color.WHITE);
//        }
//
        return newView;
    }
}