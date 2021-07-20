package com.koreait.xmlapp.socket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.koreait.xmlapp.R;

import java.io.IOException;
import java.net.Socket;

public class ChatActivity extends AppCompatActivity {

    Socket socket;
    EditText t_ip, t_port,t_input;
    TextView area;
    Thread connectTread;
    Handler handler;
    MessageThread messageThread;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        t_ip=findViewById(R.id.t_ip);
        t_port=findViewById(R.id.t_port);
        t_input=findViewById(R.id.t_input);
        area=findViewById(R.id.area);

        handler=new Handler(){
            public void handleMessage(@NonNull Message message) {
                Bundle bundle=message.getData();
                String msg=(String)bundle.get("msg");
                area.append(msg+"\n"); //UI는 핸들러에게 부탁!
            }
        };
    }

    public void connect(View view){
        //서버에 접속하기!!
        String ip=t_ip.getText().toString();
        int port=Integer.parseInt(t_port.getText().toString());

       connectTread = new Thread(){
           public void run() {
               try {
                   socket = new Socket(ip, port); //접속

                   //메시지 전용 스레드 생성
                   messageThread=new MessageThread(ChatActivity.this);
                   messageThread.start();

                   //area.append("접속성공\n"); //view 제어는 메인 쓰레드많이 할 수 있다.. 개발자가 정의한 쓰레드가 UI를 제어하려면
                   //핸들어에게 부탁해야 한다.. 즉 간접적으로 메인쓰레드를 동작시킴
                   //Message객체는 말 그대로 메시지를 담을 수 있는 객체임
                   Message message=new Message();
                   Bundle bundle = new Bundle();
                   bundle.putString("msg","접속완료");

                   message.setData(bundle); //메시지 구성완료
                   handler.sendMessage(message); //핸들러의 handleMessage메서드로 전달!!
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       };
       connectTread.start();
    }

    public void sendMsg(View view){
        String msg=t_input.getText().toString();
        Thread thread = new Thread(){
            public void run() {
                messageThread.send(msg); //서버로 메시지 전송
            }
        };
        thread.start();
        t_input.setText(""); //입력값 초기화
    }
}