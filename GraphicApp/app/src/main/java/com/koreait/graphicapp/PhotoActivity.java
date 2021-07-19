package com.koreait.graphicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PhotoActivity extends AppCompatActivity implements View.OnClickListener{
    Button bt_prev, bt_auto, bt_next;
    PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);//인플레이션 종료

        bt_prev=findViewById(R.id.bt_prev);
        bt_auto=findViewById(R.id.bt_auto);
        bt_next=findViewById(R.id.bt_next);
        photoView=findViewById(R.id.photoview);

        //이벤트소스와 리스너 연결
        bt_prev.setOnClickListener(this);
        bt_auto.setOnClickListener(this);
        bt_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(bt_prev)){
            photoView.index--;
        }else if(v.equals(bt_auto)){

        }else if(v.equals(bt_next)){
            photoView.index++;
        }
        showPhoto();
    }

    public void showPhoto(){
        //이미지 보여주기
        photoView.invalidate();
    }
}












