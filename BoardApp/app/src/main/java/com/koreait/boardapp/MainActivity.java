package com.koreait.boardapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.koreait.boardapp.pages.ListFragment;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager2 viewPager;
    PageAdapter pageAdapter;
    BoardDAO boardDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "MainActivity의 주소값은 "+this);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewPager);
        pageAdapter = new PageAdapter(this); //어댑터 생성
        viewPager.setAdapter(pageAdapter);

        //viewPager2의 경우 메서드 호출로 스와이핑을 scroll을 비활성화 시킬수 있을 듯..
        viewPager.setUserInputEnabled(false);

        this.setSupportActionBar(toolbar);


        //각 프레그먼트 접근하기
        ListFragment listFragment=(ListFragment) pageAdapter.pages[0];
        //listFragment.boardListAdapter

        showPage(2);//상세보기 페이지
    }

    //앱바 영역에 툴바를 이용한 메뉴구성하기

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.main_navi, menu);
        return true;
    }

    public void showPage(int index){
        viewPager.setCurrentItem(index);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_list:showPage(0);break;
            case R.id.item_write:showPage(1);break;
        }
        return super.onOptionsItemSelected(item);
    }
}