package com.koreait.xmlapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RemoteActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = this.getClass().getName();
    Button bt;
    ListView listView;
    MyAdapter myAdapter;
    MemberHandler memberHandler; //SAX 파싱 이벤트 객체
    SAXParserFactory factory = SAXParserFactory.newInstance();

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_remote);
        bt = findViewById(R.id.bt); //버튼 얻기
        listView = findViewById(R.id.listView);//리스트뷰 얻기
        listView.setAdapter(myAdapter=new MyAdapter(this)); //리스트뷰와 어댑터 연결해 놓기

        bt.setOnClickListener(this);//버튼과 리스너연결
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                //메인쓰레드에서 하고싶은 작업을 여기서 처리
                //UI 제어는 메인쓰레드에서..(스레드에서 UI제어를 하면 불안정 하다)
                myAdapter.notifyDataSetChanged();
                listView.invalidate();
            }
        };
    }

    //리스트 뷰에 데이터 출력하기!!
    public void getList() {
        //원격지의 xml을 앱으로 불러들이기(웹상의 요청)
        URL url = null;
        HttpURLConnection con = null;//추상클래스
        InputStream is = null;


        try {
            url = new URL("http://192.168.0.3:8888/resources/data/members.xml");
            con = (HttpURLConnection) url.openConnection(); //Http 요청 객체 생성
            Log.d(TAG, "con은=" + con);
            con.setRequestMethod("GET");

            is = con.getInputStream();//스트림 얻기
            SAXParser saxParser = factory.newSAXParser(); //파서 얻기
            saxParser.parse(is, memberHandler = new MemberHandler()); //파싱시작
            Log.d(TAG, "파싱완료 후 MemberHandler가 보유한 데이터 수는 : " + memberHandler.list.size());

            //가져온 데이터를 ListView가 알아야 하므로, 리스트뷰가 사용중인 어댑터에데이터를 인식시키자!
            myAdapter.list=memberHandler.list; //가져온 리스트를 어댑터의 리스트에 대입

            //UI 제어는 메인쓰레드에서..(스레드에서 UI제어를 하면 불안정 하다)
            //myAdapter.notifyDataSetChanged();
            //listView.invalidate();
            //현재 쓰레드 영역에서 핸들로 하여금 메인스레드를 동작시킬수 있도록 부탁을 해보자
            //즉 Handler의 handleMessage메서드를 간접 호출
            handler.sendEmptyMessage(0); //핸들러에게 부탁!!

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Override
    public void onClick(View v) {
        Thread thread = new Thread() {
            public void run() {
                getList();
            }
        };
        thread.start();
    }


}