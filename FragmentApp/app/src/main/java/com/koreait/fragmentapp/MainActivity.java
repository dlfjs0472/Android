package com.koreait.fragmentapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.koreait.fragmentapp.fragments.BlueFragment;
import com.koreait.fragmentapp.fragments.RedFragment;
import com.koreait.fragmentapp.fragments.YellowFragment;

public class MainActivity extends AppCompatActivity {
    FragmentManager manager;
    Fragment[] fragments=new Fragment[3]; //비어있는 프레그먼트형 배열 준비

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragments[0]= new RedFragment();
        fragments[1] = new YellowFragment();
        fragments[2] = new BlueFragment();

        //기본적으로 액티비티는 프레그먼트를 사용할수 있기 때문에 메니저를 얻어오는 능력이 있다
        manager=this.getSupportFragmentManager();

        //최초에 보여질 페이지
        FragmentTransaction transaction =manager.beginTransaction();
        transaction.add(R.id.fragment_container, fragments[0]);
        transaction.commit(); //확정짓기
    }

    //어떤 프레그먼트를 보여줄지를 결정짓는 메서드
    public void showPage(int index){
        //프레그먼트 생성 및 뷰안에 넣기!!
        FragmentTransaction transaction =manager.beginTransaction();
        //화면에 프레그먼트를 등장시킬지 여부를확정지을 수 있는 객체
        transaction.replace(R.id.fragment_container, fragments[index]);
        transaction.commit(); //확정짓기
    }


    //액션 아이템 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=this.getMenuInflater();
        menuInflater.inflate(R.menu.main_navi, menu);

        return true;
    }

    //아이템 이벤트처리
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