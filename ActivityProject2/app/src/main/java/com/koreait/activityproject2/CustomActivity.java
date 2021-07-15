package com.koreait.activityproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

//템플릿을 선택하여 Activity생성시 AndroidManifest.xml 파일에 자동등록 + xml 레이아웃파일 자동 생성
public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom); //Inflation + 뷰로 사용
        LinearLayout container=(LinearLayout)this.findViewById(R.id.container);

        //복잡한 구조의 아이템뷰를 여러개 동적으로 화면에 보여주기 위해서는 반복문을 이용해야 하고,
        //반복문을 이용하려면 자바코드를 이용하여 디자인을 수행해야 함이 원칙...
        /*
        LinearLayout item=new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        item.setLayoutParams(layoutParams);
        */

        //지금부터는 디자인을 xml했기 때문에, 해당  xml에 명시한 뷰들을 실제 자바의 인스턴스화 시켜야 한다..(인플레이션 시키자)
        LayoutInflater layoutInflater=this.getLayoutInflater(); //인스턴스 얻기!
        String[] title={"바바리안","배트맨","스캇","데드풀","헐크","아이언맨","스파이더맨","울버린","원더우먼","매그니토"};

        for(int i=0;i<10;i++) {
            //board_item.xml의 가장 바깥쪽 LinearLayout을 반환 받음!!
            LinearLayout view = (LinearLayout) layoutInflater.inflate(R.layout.board_item, null, false); //그냥 인플레이션 시킨 후 결과 View를 받자

            //뷰그룹도 findViewById 메서드가 지원된다!!
            TextView t_title = (TextView)view.findViewById(R.id.t_title);
            TextView t_writer = (TextView)view.findViewById(R.id.t_writer);

            t_title.setText(title[i]);

            container.addView(view);//activity_custom.xml의 LinearLayout 에 부착하자!!!
        }
    }
}