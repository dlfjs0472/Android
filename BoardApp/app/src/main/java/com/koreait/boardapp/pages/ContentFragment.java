package com.koreait.boardapp.pages;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.koreait.boardapp.MainActivity;
import com.koreait.boardapp.R;
import com.koreait.boardapp.domain.Board;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ContentFragment extends Fragment {
    String TAG=this.getClass().getName();
    MainActivity mainActivity;
    EditText t_title, t_writer, t_content;
    Button bt_list, bt_update, bt_delete;
    String ip="192.168.0.3";
    int port=8888;
    Handler handler;

    @Override
    //반환값이 View는, 현재의 프레그먼트에서 보여줄 뷰를 의미
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "ContentFragment가 태어나서, onCreateView호출로 초기화되었네요!!");

        View view = inflater.inflate(R.layout.fragment_content, container, false);//인플레이션
        mainActivity = (MainActivity) this.getActivity();

        t_title=view.findViewById(R.id.t_title);
        t_writer=view.findViewById(R.id.t_writer);
        t_content=view.findViewById(R.id.t_content);

        bt_list = view.findViewById(R.id.bt_list);
        bt_update = view.findViewById(R.id.bt_update);
        bt_delete = view.findViewById(R.id.bt_delete);

        //java 11 람다 Lambda: 함수형 프로그래밍..(js 스크립트 클로저에서 유래..)
        //이벤트, 내부익명클래스 대신 람다로 자주 사용..
        bt_list.setOnClickListener((View v)->{
            mainActivity.showPage(0);//목록으로..
        });
        bt_update.setOnClickListener((View v)->{
            Log.d(TAG, "람다로 작동성공");
            Thread thread = new Thread(){
                public void run() {
                    update();
                }
            };
            thread.start();
        });

        bt_delete.setOnClickListener((View v)->{
            Thread thread = new Thread(){
                public void run() {
                    del();
                }
            };
            thread.start();
        });

        handler = new Handler(){
            public void handleMessage(@NonNull Message message) {
                //메인쓰레드에 의해 제어할 영역
                AlertDialog.Builder builder=new AlertDialog.Builder(mainActivity);
                builder.setTitle("처리결과");
                Bundle bundle=message.getData();

                builder.setMessage(bundle.getString("msg"));
                builder.show();

                if(bundle.get("msg").equals("삭제성공")){
                    //
                    mainActivity.showPage(0);
                }
            }
        };

        return view;
    }


    public void del(){
        URL url=null;
        HttpURLConnection con=null;
        BufferedWriter buffw=null;

        try {
            url=new URL("http://"+ip+":"+port+"/rest/board/"+mainActivity.board.getBoard_id());
            con=(HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");//요청 방법 지정(post, get ... )
            int code=con.getResponseCode(); //서버의 응답코드 200 등등
            Log.d(TAG,"삭제 후 결과 코드"+code);

            String msg="";
            if(code==200){
                msg="삭제성공";
                ListFragment listFragment=(ListFragment)mainActivity.pages[0];
                listFragment.getList();
            }else{
                msg="삭제실패";
            }

            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("msg",msg);
            message.setData(bundle);
            handler.sendMessage(message);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(buffw!=null){
                try {
                    buffw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update(){
        URL url=null;
        HttpURLConnection con=null;
        BufferedWriter buffw=null;

        try {
            url=new URL("http://"+ip+":"+port+"/rest/board");
            con=(HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");//요청 방법 지정(post, get ... )
            con.setRequestProperty("Content-Type","application/json;charset=utf-8");
            con.setDoOutput(true); //post방식은 데이터를 전송함이 목적...

            //string형으로 서버에 json문자열을 전송하자!!
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("\"board_id\":"+mainActivity.board.getBoard_id()+",");
            sb.append("\"title\":\""+t_title.getText().toString()+"\",");
            sb.append("\"writer\":\""+t_writer.getText()+"\",");
            sb.append("\"content\":\""+t_content.getText()+"\"");
            //sb.append("\"_method\":\"put\"");
            sb.append("}");

            //con객체로부터 스트림을 뽑은후, 데이터 출력
            buffw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
            buffw.write(sb.toString()+"\n");
            buffw.flush();

            int code=con.getResponseCode(); //서버의 응답코드 200 등등
            Log.d(TAG,"수정 후 결과 코드"+code);

            String msg="";
            if(code==200){
                msg="수정성공";
            }else{
                msg="수정실패";
            }
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("msg",msg);
            message.setData(bundle);
            handler.sendMessage(message);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(buffw!=null){
                try {
                    buffw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart 호출함");
    }

    //화면에 조금이라도 등장하기 시작할때..
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume 호출함");
        if(mainActivity.board !=null) { //데이터가 존재할때만...
            //MainActivity에 보관해놓은 Board 를 접근하여 상세내용으로 출력하자!!!
            t_title.setText(mainActivity.board.getTitle());
            t_writer.setText(mainActivity.board.getWriter());
            t_content.setText(mainActivity.board.getContent());
        }
    }
}