package com.koreait.fragmentapp.pager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.koreait.fragmentapp.R;

public class HostActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    MyAdapter myAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        viewPager = findViewById(R.id.viewPager);
        myAdapter = new MyAdapter(this);
        viewPager.setAdapter(myAdapter); //뷰와 어댑터 연결
        toolbar=findViewById(R.id.toolbar);

        //현재 액티비티에서 이미 지원하는 액션바객체 얻기!!
        /*
        ActionBar bar=this.getSupportActionBar();
        bar.setTitle("나의액션바");
        */

        //액션바 대신에 툴바로 앱바 역할을 대신해보자!!
        this.setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=this.getMenuInflater();
        menuInflater.inflate(R.menu.main_navi, menu);//xml로부터 인플레이션!!

        return true;
    }

    public void showPage(int index){
        viewPager.setCurrentItem(index);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_red:showPage(0);break;
            case R.id.item_yellow:showPage(1);break;
            case R.id.item_blue:showPage(2);break;
        }

        return super.onOptionsItemSelected(item);
    }
}