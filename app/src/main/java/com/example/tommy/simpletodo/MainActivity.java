package com.example.tommy.simpletodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                R.layout.item_textview,
                R.id.id_item_textview,
                items
        );
        listView.setAdapter(adapter);
        items.add("First item");
        items.add("Second item");

    }

}
