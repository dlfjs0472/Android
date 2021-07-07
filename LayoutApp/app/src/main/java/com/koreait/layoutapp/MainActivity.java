package com.koreait.layoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q10); //화면에 보여질 뷰를 XML을 이용하는 방법

        //XML이 아닌 순수 자바코드로 디자인을 구현해보자(비효율적이지만, 때에 따라서는 필요하다)
        /*
        Button bt=new Button(this); //버튼 생성
        bt.setText("나버튼");
        //버튼의  width, height 지정
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        bt.setLayoutParams(layoutParams); //레이아웃 설정 적용 (버튼의 크기)
        setContentView(bt); //버튼은 뷰니까!! 인수로 올 수 있다..
         */
    }
}