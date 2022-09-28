package com.example.levandung_aprotrain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.levandung_aprotrain.adapter.CustomAdapter;
import com.example.levandung_aprotrain.database.DBHelper;
import com.example.levandung_aprotrain.entity.NoteModify;
import com.example.levandung_aprotrain.models.Note;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ListView noteListView;
    private CustomAdapter customAdapter;
    private Cursor currentCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper.getInstance(this);

        //Mockup data
//        Note note = new Note("Noi dung 1", true, new Date());
//        NoteModify.insert(note);
//        note = new Note("Noi dung 2", false, new Date());
//        NoteModify.insert(note);
//        note = new Note("Noi dung 3", true, new Date());
//        NoteModify.insert(note);

        // Setting adapter
        noteListView = findViewById(R.id.am_listview);
        Cursor cursor = NoteModify.findAll();
        customAdapter = new CustomAdapter(this, cursor);

        noteListView.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();

        registerForContextMenu(noteListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_view_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        currentCursor.moveToPosition(menuInfo.position);
//        Note note = NoteModify.find(currentCursor);
        switch (item.getItemId()){
            case R.id.menu_new_note:
                showNoteDialog();
                break;
            case R.id.menu_exist:
                finish();
                break;

            case R.id.menu_delete_note:
//                NoteModify.delete(note.getId());
                updateListView();
                break;
            case R.id.menu_edit_note:
                showNoteDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showNoteDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.note_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(view);

        Dialog dialog = builder.create();

        EditText contentTextView = view.findViewById(R.id.nd_content);
        CheckBox importantCheckbox = view.findViewById(R.id.nd_important);
        Button cancelBtn = view.findViewById(R.id.nd_cancel);
        Button commitBtn = view.findViewById(R.id.nd_commit);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noidung = contentTextView.getText().toString();
                boolean quantrong = importantCheckbox.isChecked();
                Date ngaytao = new Date();

                Note note = new Note(noidung, quantrong, ngaytao);

                NoteModify.insert(note);
                updateListView();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void updateListView(){
        Cursor cursor = NoteModify.findAll();
        customAdapter.changeCursor(cursor);
        customAdapter.notifyDataSetChanged();
    }
}