package com.koreait.boardapp;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//웹서버와의 http통신을 통해 CRUD를 수행해주는 객체
public class BoardDAO {
    String TAG=this.getClass().getName();
    String ip="192.168.0.3";
    int port=8888;

    //웹서버로부터 데이터 가져오기
    public void getList(){
        URL url=null;
        HttpURLConnection con=null;
        BufferedReader buffr=null;
        StringBuilder sb = new StringBuilder();

        Log.d(TAG, " getList 호출");

        try {
            url=new URL("http://"+ip+":"+port+"/rest/board");
            con=(HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            buffr = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));

            String data=null;
            while(true){
                data = buffr.readLine();
                if(data==null)break;
                sb.append(data); //한줄씩 모으기
            }
            int code=con.getResponseCode();

            Log.d(TAG, "code="+code);
            Log.d(TAG, "sb="+sb.toString());
            //여기서 서버로부터 전송된, json을 ListView에 반영하기!!!(실제적으론 BaseAdapter제어)

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(buffr!=null){
                try {
                    buffr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}