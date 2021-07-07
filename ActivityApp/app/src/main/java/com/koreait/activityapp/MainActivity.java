package com.koreait.activityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
    * 1)적절한 리스너 선택
    * 2)인터페이스 오버라이드
    * 3)리스너와 이벤트소스의 연결
    *
    *
    * */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Spring과 비교하자면, 설정파일의 내용을 읽어들여 실제 자바화(Inflation) 시키는 과정이 포함
        //따라서 이 시점 이후부터는 실제 객체가 존재하는 시점
        Button bt = (Button)this.findViewById(R.id.bt); //레퍼런스 얻기
        bt.setOnClickListener(this); //버튼과 리스너와의 연결
    }

    @Override
    public void onClick(View v) { //javaSE의 actionperformed()와 동일
        System.out.println("나 눌렀어?");
        //파란 화면이 나오도록 즉 파란화면을 담당하는 BlueActivity 클래스를 활성화 시키자!!
        Intent intent=new Intent(this, BlueActivity.class); //개발자가 new해서는 안되기 때문에 시스템에게
        //클래스 바이트명을 넘기자

        this.startActivity(intent); //지정한 내용으로 또 다른 액티비티를 호출!!
    }
}