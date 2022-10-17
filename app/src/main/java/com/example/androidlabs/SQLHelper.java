package com.example.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TODO";
    private static final int DATABASE_VERSION = 1;
    public final static String TABLE_NAME = "TODO_ITEMS";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TODO = "todo";
    private static final String COLUMN_URGENT = "urgent";

    SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + TABLE_NAME + " (\n" +
                "    " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT todo_items_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + COLUMN_TODO + " varchar(200) NOT NULL,\n" +
                "    " + COLUMN_URGENT + " INTEGER NOT NULL" +
                "    );";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /*
         * We are doing nothing here
         * Just dropping and creating the table
         * */
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    /*
     * CREATE OPERATION
     * */
    public Integer add(String toDo, Integer urgent) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TODO, toDo);
        contentValues.put(COLUMN_URGENT, urgent);
        SQLiteDatabase db = getWritableDatabase();

        return (Integer) (int) db.insert(TABLE_NAME, null, contentValues);
    }

    /*
     * READ OPERATION
     * */
    public Cursor getAll() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT id, todo, urgent FROM " + TABLE_NAME, null);
    }

    /*
     * DELETE OPERATION
     * */
    public boolean delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}) == 1;
    }

    /*
     * GET DB VERSION
     * */
    public int getVersion() {
        SQLiteDatabase db = getWritableDatabase();
        return db.getVersion();
    }
}
