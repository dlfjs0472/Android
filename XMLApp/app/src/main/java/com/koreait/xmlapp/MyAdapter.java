package com.koreait.xmlapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.koreait.xmlapp.domain.Member;

import java.util.ArrayList;
import java.util.List;

//보여줄 데이터가 단순 String이 아닌, 보다 복합적인 경우엔, 데이터뷰에 보여줄 
//데이터를 커스텀할 수 있는 어뎁터클래스를 재정의
public class MyAdapter extends BaseAdapter {
    String TAG=this.getClass().getName();//현재클래스명 반환
    List<Member> list=new ArrayList<Member>();

    LayoutInflater layoutInflater;
    Context context;

    public MyAdapter(Context context) {
        this.context=context;
        layoutInflater=((Activity)this.context).getLayoutInflater();
    }

    public int getCount() {
        Log.d(TAG,"핸들러로부터 넘겨받은 List의 사이즈는 "+list.size());
        return list.size();
    }


    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return 0;
    }


    //어댑터는 ListView에게 각 아이템에 자리할 뷰를 반환해야 한다.
    //이때, 아이템에 채워질 뷰는 기존 뷰의 재사용일 수도 있고, 새로 생성할 경우가 있는데, 
    //스크롤에 의해 가려진 뷰를 보려고 할때는, 기존 뷰를 재사용 하는것이 훨씬 메모리 낭비가 없게된다
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        if(convertView==null){ //새로 생성해야함
            view=layoutInflater.inflate(R.layout.item_member, null, false);
        }else { //기존뷰를 재사용
            view=convertView;
        }

        //List에 들어있는 각 VO를 꺼내어 TextView에 대입시키자!!!
        Member member=list.get(position);

        //인플레이션 후, 최상위 뷰인 LinearLayout에 소속된 하위 뷰들을 접근하여 데이터 채우기
        TextView t_name=view.findViewById(R.id.t_name);
        TextView t_phone=view.findViewById(R.id.t_phone);
        TextView t_addr=view.findViewById(R.id.t_addr);

        t_name.setText(member.getName());
        t_phone.setText(member.getPhone());
        t_addr.setText(member.getAddr());

        return view;
    }
}
