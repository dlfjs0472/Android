package com.koreait.boardapp.list;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.koreait.boardapp.MainActivity;
import com.koreait.boardapp.R;
import com.koreait.boardapp.domain.Board;

import java.util.ArrayList;
import java.util.List;

//ListView 가 보여줄 게시물 목록에 대한 정보를 제공하는 어댑터
public class BoardListAdapter extends BaseAdapter {
    MainActivity mainActivity;
    LayoutInflater layoutInflater;
    public List<Board> boardList=new ArrayList<Board>();

    public BoardListAdapter(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
        layoutInflater=mainActivity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return boardList.size();
    }

    @Override
    public Object getItem(int position) {
        return boardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getCount만큼 호출하면서 각 아이템 자리에 들어갈 View를 반환해주자!! 하지만
        //이 뷰들이 xml로 구성되어 잇으므로, 인플레이션이 필요하다..
        View view=null;

        if(convertView==null) {
            view = layoutInflater.inflate(R.layout.item_board, null, false); //새로운 뷰 생성
        }else{
            view=convertView; //기존 뷰로 대체
        }
        //인플레이션 완료 후 반환된 그룹뷰는 각각 자기가 포함하고 있는 자식요소들을 접근하여 알맞는 데이터를
        //채워넣으면 된다..
        TextView t_title=view.findViewById(R.id.t_title);
        TextView t_writer=view.findViewById(R.id.t_writer);
        TextView t_regdate=view.findViewById(R.id.t_regdate);
        TextView t_hit=view.findViewById(R.id.t_hit);

        Board board=boardList.get(position); //ArrayList에 들어있는 VO꺼내기!!

        t_title.setText(board.getTitle());
        t_writer.setText(board.getWriter());
        t_regdate.setText(board.getRegdate());
        t_hit.setText(Integer.toString(board.getHit()));

        return view;
    }
}