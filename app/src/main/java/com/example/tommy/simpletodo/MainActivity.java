package com.example.tommy.simpletodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.item_listview);
        items = new ArrayList<String>();
        readItem();
        adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.item_textview,
                R.id.id_item_textview,
                items
        );
        listView.setAdapter(adapter);
        items.add("First item");
        items.add("Second item");
        setupListViewListener();


    }
    public void onAddItem(View v){
        EditText newItem = (EditText)findViewById(R.id.add_edittext);
        String newItemString = newItem.getText().toString();
        items.add(newItemString);
        newItem.setText("");
        writeItem();
    }

    private void setupListViewListener(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
                items.remove(pos);
                adapter.notifyDataSetChanged();
                writeItem();
                return true;
            }
        });
    }
    private void readItem(){
        File fileDir = getFilesDir();
        File todoFile  = new File(fileDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
    }

    private void writeItem(){
        File fileDir = getFilesDir();
        File todoFile = new File(fileDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
