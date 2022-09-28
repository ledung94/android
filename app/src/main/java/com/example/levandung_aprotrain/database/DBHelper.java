package com.example.levandung_aprotrain.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.levandung_aprotrain.entity.NoteModify;

public class DBHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "testdb";
    private final static Integer VERSION = 1;
    private static DBHelper instance = null;

    public synchronized static DBHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DBHelper(context);
        }

        return instance;
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NoteModify.TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
