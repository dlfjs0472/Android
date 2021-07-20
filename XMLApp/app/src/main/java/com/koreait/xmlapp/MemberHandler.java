package com.koreait.xmlapp;

import android.util.Log;

import com.koreait.xmlapp.domain.Member;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MemberHandler extends DefaultHandler {
    String TAG=this.getClass().getName();//현재클래스명 반환
    List<Member> list;
    Member member;
    boolean isName;
    boolean isPhone;
    boolean isAddr;



    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        if (tag.equals("members")){
            list=new ArrayList<Member>();
        }else if (tag.equals("member")){
            member=new Member();
        }else if (tag.equals("name")){
            isName=true;
        }else if (tag.equals("phone")){
            isPhone=true;
        }else if (tag.equals("addr")){
            isAddr=true;
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch,start,length);

        if (isName){
            member.setName(data);
        }else if (isPhone){
            member.setPhone(data);
        }else if (isAddr){
            member.setAddr(data);
        }
    }

    public void endElement(String uri, String localName, String tag) throws SAXException {
        if (tag.equals("name")){
            isName=false;
        }else if (tag.equals("phone")){
            isPhone=false;
        }else if (tag.equals("addr")){
            isAddr=false;
        }else if (tag.equals("member")){
            list.add(member); //한 사람이 완성되는 시점이므로
        }
    }

    public void endDocument() throws SAXException {
        Log.d(TAG,"총 레코드 수"+list.size());
    }



}
