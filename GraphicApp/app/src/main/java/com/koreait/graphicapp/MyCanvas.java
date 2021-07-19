package com.koreait.graphicapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

//LinearLayout을 상속받아, 그래픽 처리를 시스템이 아닌 내가 주도해서 그려보자!!
public class MyCanvas  extends View { //LinearLayout 도 가능
    Paint paint;
    int x=300;
    int y=300;
    Bitmap bitmap;



    //부모인 View의 그리는 메서드를 재정의 하기 위해...
    //안드로이드의 모~~든 뷰는 반드시 특정 액티비티에 소속관계를 명시해야 한다..
    //즉 액티비티의 관리를 벗어난 뷰는 존재하지 않는다
    public MyCanvas(Context context) {
        super(context);
        init(context);
    }
    //만일 이 커스텀뷰를 자바 코드가 아닌, xml에서 인플레이션 시킬 경우엔
    //생성자 중 xml속성을 처리할 수 있는 생성자 까지 명시해야 한다..
    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context){
        paint=new Paint();
        paint.setColor(Color.RED);
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.ironman);

        //얻어진 비트맵의 크기를 재조정하기
        bitmap=Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*0.5),(int)(bitmap.getHeight()*0.5),true);

    }

    //javaSE 에서의 paint() 메서드와 동일, Canvas는 javeSE에서의 Graphics객체와 목적이 동일(팔레트)
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(x,y,50,paint);
        canvas.drawBitmap(bitmap, x+200,y+200,null);
    }
}
