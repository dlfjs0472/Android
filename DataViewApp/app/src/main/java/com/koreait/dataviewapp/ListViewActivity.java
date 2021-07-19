package com.koreait.dataviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

public class ListViewActivity extends AppCompatActivity {
    ListView listView;
    HeroAdapter adapter;
    GridView gridView;
    Spinner spinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new HeroAdapter(this);

        setContentView(R.layout.activity_list_view);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);//리스트뷰와 어댑터와 연결

        //setContentView(R.layout.activity_grid_view);
        //gridView=(GridView)findViewById(R.id.gridView);
        //gridView.setAdapter(adapter);

        /*setContentView(R.layout.activity_spinner);
        spinner=(Spinner)findViewById(R.id.sp);
        spinner.setAdapter(adapter);*/
    }

}