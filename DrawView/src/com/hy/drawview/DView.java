package com.hy.drawview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DView extends View{
	
	public float currentX = 40;
	public float currentY = 50;

	public DView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		//��������
		Paint p = new Paint();
		//���û�����ɫ
		p.setColor(Color.RED);
		//����һ��Բ
		canvas.drawCircle(currentX, currentY, 15, p);
	}
	
}
