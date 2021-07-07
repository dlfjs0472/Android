package com.koreait.activityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BlueActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_blue); //XML을 이용하여 디자인 구성
        //내부적으로 imflation 발생함(XML태그를 자바로 변환)

    }

    //★반드시 View를 매개변수로 넣어야, XML에서 명시한 메서드로 인식이 된다!!!
    public void close(View view){
        System.out.println("당신이 누른 버튼은 "+view);
        //현재 액티비티인 BlueActivity 를 stack에서 제거!! (자바에서는 객체 인스턴스 자체를 죽이는 방법은 없다)
        this.finish();
    }
}