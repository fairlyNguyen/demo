package com.example.fairlynguyen.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.fairlynguyen.myapplication.adapters.CustomListViewAdapter;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private ArrayList<String> mListData;
    private CustomListViewAdapter mAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getViews();
        initData();
        initAdater();
    }

    private void getViews() {
        mListView = (ListView) findViewById(R.id.listView);
    }

    private void initData() {
        mListData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mListData.add("Phong: " + i);
        }
    }

    private void initAdater() {
        mAdapter = new CustomListViewAdapter(Main2Activity.this, R.layout.listview_item, mListData);
        mListView.setAdapter(mAdapter);
    }
}
