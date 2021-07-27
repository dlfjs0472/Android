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

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WriteFragment extends Fragment implements View.OnClickListener {
    String TAG=this.getClass().getName();
    Button bt_list, bt_write;
    EditText t_title, t_writer, t_content;
    MainActivity mainActivity;
    Handler handler;
    String ip="172.30.1.53";
    int port=8888;

    //반환값이 View는, 현재의 프레그먼트에서 보여줄 뷰를 의미
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write, container, false);//인플레이션
        //버튼과 리스너와의 연결
        bt_list = view.findViewById(R.id.bt_list);
        bt_write = view.findViewById(R.id.bt_write);
        t_title=view.findViewById(R.id.t_title);
        t_writer=view.findViewById(R.id.t_writer);
        t_content=view.findViewById(R.id.t_content);

        bt_list.setOnClickListener(this);
        bt_write.setOnClickListener(this);
        mainActivity = (MainActivity) this.getActivity(); //호스트 액티비티 얻어다 놓기

        handler = new Handler(){
            public void handleMessage(@NonNull Message message) {
                //메인쓰레드에 의해 제어할 영역
                AlertDialog.Builder builder=new AlertDialog.Builder(mainActivity);
                builder.setTitle("등록결과");
                Bundle bundle=message.getData();

                builder.setMessage(bundle.getString("msg"));
                builder.show();
            }
        };

        return view;
    }
    //웹서버에 등록 요청
    public void regist(){
        URL url=null;
        HttpURLConnection con=null;
        BufferedWriter buffw=null;

        try {
            url=new URL("http://"+ip+":"+port+"/rest/board");
            con=(HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");//요청 방법 지정(post, get ... )
            con.setRequestProperty("Content-Type","application/json;charset=utf-8");
            con.setDoOutput(true); //post방식은 데이터를 전송함이 목적...

            //string형으로 서버에 json문자열을 전송하자!!
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append("\"title\":\""+t_title.getText().toString()+"\",");
            sb.append("\"writer\":\""+t_writer.getText()+"\",");
            sb.append("\"content\":\""+t_content.getText()+"\"");
            sb.append("}");

            //con객체로부터 스트림을 뽑은후, 데이터 출력
            buffw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
            buffw.write(sb.toString()+"\n");
            buffw.flush();

            int code=con.getResponseCode(); //서버의 응답코드 200 등등
            Log.d("WriteFragment","등록후 결과 코드"+code);

            String msg="";
            if(code==200){
                msg="등록성공";
            }else{
                msg="등록실패";
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
    public void onClick(View v) {
        if(v.getId() ==  R.id.bt_list){
            Log.d(TAG, "목록으로 갈까요?");
            //MainActivity의 showPage(0);
            Log.d(TAG, "현재 이 프레그먼트의 주인 액티비티는 "+this.getActivity());
            mainActivity.showPage(0);
        }else if(v.getId() == R.id.bt_write){
            Log.d(TAG, "등록할까요?");

            Thread thread=new Thread(){
                public void run() {
                    regist();
                }
            };
            thread.start();
        }

    }

}