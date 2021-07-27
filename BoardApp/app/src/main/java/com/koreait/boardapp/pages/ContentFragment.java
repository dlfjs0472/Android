package com.koreait.boardapp.pages;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.koreait.boardapp.MainActivity;
import com.koreait.boardapp.R;

public class ContentFragment extends Fragment {
    String TAG=this.getClass().getName();
    MainActivity mainActivity;
    Button bt_list, bt_update, bt_delete;

    @Override
    //반환값이 View는, 현재의 프레그먼트에서 보여줄 뷰를 의미
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);//인플레이션
        mainActivity = (MainActivity) this.getActivity();

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
        });
        bt_delete.setOnClickListener((View v)->{
            Log.d(TAG, "람다로 작동성공");
        });
        return view;
    }
}