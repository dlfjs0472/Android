package com.koreait.fragmentapp.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.koreait.fragmentapp.R;

//액티비티의 화면일부를 처리하는 프레그먼트를 정의한다
public class RedFragment extends Fragment {


    @Override
    //현재 이 Fragment에 보여질 뷰를 반환하는 메서드
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //매개변수로 넘겨받은 인플레이터를 이용하여 화면을 구성할 xml등을 인플레이션 시키면 됨...
        return inflater.inflate(R.layout.fragment_red, container, false);
    }
}