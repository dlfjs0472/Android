package com.koreait.fragmentapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.koreait.fragmentapp.R;

public class YellowFragment extends Fragment {

    @Override
    //현재 이 Fragment에 보여질 뷰를 반환하는 메서드
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //매개변수로 넘겨받은 인플레이터를 이용하여 화면을 구성할 xml등을 인플레이션 시켜면 됨...
        return inflater.inflate(R.layout.fragment_yellow, container, false);
    }
}