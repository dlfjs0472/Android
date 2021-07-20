package com.koreait.xmlapp.socket;

import android.os.Bundle;
import android.os.Message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//서버가 보내온 메시지를 실시간으로 즉 무한 루프로 청취할 객체 스레드 객체 정의
public class MessageThread extends  Thread{
    ChatActivity chatActivity;
    Socket socket;
    BufferedReader buffr; //듣기용
    BufferedWriter buffw; //말하기용


    public MessageThread(ChatActivity chatActivity){
        this.chatActivity=chatActivity;
        this.socket= chatActivity.socket;
        try {
            buffr= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            buffw= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //듣기
    public void listen() {
        while(true) {
            String msg=null;
            try {
                msg=buffr.readLine(); //듣기

                Message message=new Message();
                Bundle bundle = new Bundle();
                bundle.putString("msg",msg);

                message.setData(bundle); //메시지 구성완료
                chatActivity.handler.sendMessage(message); //핸들러의 handleMessage메서드로 전달!!


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //말하기
    public void send(String msg) {
        try {
            buffw.write(msg+"\n");
            buffw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void run() {
        while (true){
            listen();
        }

    }
}
