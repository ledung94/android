package com.example.levandung_aprotrain.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.levandung_aprotrain.R;

public class CustomAdapter extends CursorAdapter {
    public CustomAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, null);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titleTextView = view.findViewById(R.id.li_title);
        TextView createdDateTextView = view.findViewById(R.id.li_created_date);
        FrameLayout importantView = view.findViewById(R.id.li_color);

        @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("noidung"));
        @SuppressLint("Range") String created_date = cursor.getString(cursor.getColumnIndex("ngaytao"));
        @SuppressLint("Range") int important = cursor.getInt(cursor.getColumnIndex("quantrong"));

        titleTextView.setText(title);
        createdDateTextView.setText(created_date);

        if(important == 0) {
            importantView.setBackgroundColor(R.color.green);
        } else {
            importantView.setBackgroundColor(R.color.red);
        }
    }
}
