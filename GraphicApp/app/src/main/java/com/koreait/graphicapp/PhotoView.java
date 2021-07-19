package com.koreait.graphicapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class PhotoView extends View{

    Bitmap[] bitmaps=new Bitmap[10]; //10개를 준비하겠다
    int index=0; //몇번째 인덱스를 보고싶은지를 결정짓는 변수

    public PhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmaps[0]  = BitmapFactory.decodeResource(context.getResources(), R.drawable.ironman);
        bitmaps[1]  = BitmapFactory.decodeResource(context.getResources(), R.drawable.babarian);
        bitmaps[2]  = BitmapFactory.decodeResource(context.getResources(), R.drawable.badman);
        bitmaps[3]  = BitmapFactory.decodeResource(context.getResources(), R.drawable.captain);
        bitmaps[4]  = BitmapFactory.decodeResource(context.getResources(), R.drawable.captain2);
        bitmaps[5]  = BitmapFactory.decodeResource(context.getResources(), R.drawable.cyclops);
        bitmaps[6]  = BitmapFactory.decodeResource(context.getResources(), R.drawable.hulk);
        bitmaps[7]  = BitmapFactory.decodeResource(context.getResources(), R.drawable.logan);
        bitmaps[8]  = BitmapFactory.decodeResource(context.getResources(), R.drawable.ironman2);
        bitmaps[9]  = BitmapFactory.decodeResource(context.getResources(), R.drawable.stormbreaker);

        //이미지 크기조정
        for (int i=0; i<bitmaps.length; i++){
            bitmaps[i]=bitmaps[i].createScaledBitmap(bitmaps[i],600,500,true);
        }
    }


    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmaps[index],50,50,null);
    }
}
