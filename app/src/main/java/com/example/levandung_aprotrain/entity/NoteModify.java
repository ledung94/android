package com.example.levandung_aprotrain.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.levandung_aprotrain.database.DBHelper;
import com.example.levandung_aprotrain.models.Note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class NoteModify {
    public static final String TABLE_NAME = "notes";
    public static final String TABLE_QUERY = "create table "+ TABLE_NAME +"  (\n" +
            "    _id INTEGER primary key autoincrement,\n" +
            "    noidung text,\n" +
            "    quantrong int,\n" +
            "    ngaytao datetime\n" +
            ")";

    public static void insert(Note note) {
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("noidung", note.getNoidung());
        values.put("quantrong", note.isQuantrong());
        String created_date = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        created_date = dateFormat.format(note.getNgaytao());
        values.put("ngaytao", created_date);

        db.insert(TABLE_NAME, null, values);
    }

    public static void update(Note note) {
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("noidung", note.getNoidung());
        values.put("quantrong", note.isQuantrong());
        String created_date = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        created_date = dateFormat.format(note.getNgaytao());
        values.put("ngaytao", created_date);

        db.update(TABLE_NAME, values, "_id="+note.getId(),null);
    }

    public static void delete(int id) {
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();
        db.delete(TABLE_NAME, "_id="+id, null);
    }

    public static Cursor findAll() {
        SQLiteDatabase db = DBHelper.getInstance(null).getWritableDatabase();

        String sql = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);

        return cursor;
    }

    public static Note find(Cursor cursor) {
        Note note = new Note();

        return note;
    }
}
